import { Component, OnInit } from '@angular/core';
import { Edition } from '../edition';
import { ActivatedRoute, Router } from '@angular/router';
import { EditionService } from '../edition.service';
import { Paper } from '../paper';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { MerchantOrder, Type } from '../merchantOrder';


@Component({
  selector: 'app-edition',
  templateUrl: './edition.component.html',
  styleUrls: ['./edition.component.css']
})
export class EditionComponent implements OnInit {

  edition : Edition;
  papers : Paper[];
  isAvailable : boolean;
  currentUser : User;
  merchantOrder : MerchantOrder;

  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private editionService: EditionService,
    private authenticationService : AuthenticationService
  ) { }

  ngOnInit() {
    this.getCurrentUser();
    this.route.queryParams.subscribe(params =>{
      if(params['transactionState']=='success'){
        alert("Uspesna kupovina!");
      }
      if(params['transactionState']=='failed'){
        alert("Neuspesna kupovina");
      }
      if(params['transactionState']=='error'){
        alert("Doslo je do greske prilikom kupovine");
      }
    })
    this.isAvailable=false;
    this.getEdition();
    this.getEditionPapers();
  }
  getCurrentUser(): any {
    if(localStorage.getItem('token')!=null){
      this.authenticationService.getCurrentUser()
      .subscribe(user => this.currentUser = user);
    }
    else{
      this.currentUser=null;
    }
    
  }

  getEditionPapers(): void {
    const editionId = this.route.snapshot.paramMap.get('editionId');
    this.editionService.getEditionPapers(editionId)
    .subscribe(papers => this.papers = papers);
  }

  getEdition(): void { //dodati za user-a proveru da li je kupio
    const editionId = this.route.snapshot.paramMap.get('editionId');
    this.editionService.getEdition(editionId)
    .subscribe(edition => {
      this.edition = edition;
      if(edition.price==0){
        this.isAvailable=true; 
      }
      else{
        console.log(this.currentUser!=null);
        if(this.currentUser!=null){
          console.log(this.currentUser.editions);
          for(let id of this.currentUser.editions){
            console.log(edition.id);
            console.log(id);            
            if(edition.id==id){
              this.isAvailable=true;
            }
          }          
        }       
      }
    });
  }

  buyEdition(): void { 
    if(this.currentUser==null){
      alert("Morate se ulogovati kako biste kupili ovo izdanje!");
      this.router.navigateByUrl('/login');
    }
    else{
      this.merchantOrder = new MerchantOrder();
      this.merchantOrder.merchantOrderID = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
      this.merchantOrder.amountOrder=this.edition.price;
      this.merchantOrder.buyer_id=this.currentUser.username;
      this.merchantOrder.type=Type.MAGAZINE_EDITION;
      this.merchantOrder.purchaseTypeId=this.edition.id.toString();
      this.merchantOrder.merchant_id=this.edition.magazineIssn;
      this.merchantOrder.successUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=success';
      this.merchantOrder.failedUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=failed';
      this.merchantOrder.errorUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=error';
      this.editionService.redirectOrder(this.merchantOrder).subscribe(response => window.location.href = response.url);

    }
    
  }
  buyPaper(paperId : string) : void{

    if(this.currentUser==null){
      alert("Morate se ulogovati kako biste kupili ovaj rad!");
      this.router.navigateByUrl('/login');
    }
    else{
      this.merchantOrder = new MerchantOrder();
      this.merchantOrder.merchantOrderID = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
      for(let x of this.papers){
        if(x.doi==paperId){
          this.merchantOrder.amountOrder=x.price;
          this.merchantOrder.purchaseTypeId=x.doi;
        }
      }
      this.merchantOrder.buyer_id=this.currentUser.username;
      this.merchantOrder.type=Type.PAPER;
      this.merchantOrder.merchant_id=this.edition.magazineIssn;
      this.merchantOrder.successUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=success';
      this.merchantOrder.failedUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=failed';
      this.merchantOrder.errorUrl='http://localhost:4200/edition/'+this.edition.id+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=error';
      this.editionService.redirectOrder(this.merchantOrder).subscribe(response => window.location.href = response.url);

    }
  }

  checkPaper(paperId : string) : boolean{ //dodati za user-a proveru da li je kupio
    
    if(this.isAvailable){
      return false;
    }
    else{
      if(this.currentUser!=null){
        for(let doi of this.currentUser.papers){
          if(doi==paperId){
            return false;
          }
        }          
      }   
    }
    return true;
    
  }

  downloadEdition(editionId : string){
    this.editionService.downloadEdition(editionId)
    .subscribe(data=> this.serveData(data));
  }

  downloadPaper(paperFilename : string){
    this.serveData(this.editionService.downloadPaper(paperFilename));
  }

  serveData(data){
    //const blob = new Blob(data)
    const url= window.URL.createObjectURL(data);
    window.open(url);
  }
}
