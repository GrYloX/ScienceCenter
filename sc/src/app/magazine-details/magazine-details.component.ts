import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Magazine } from '../magazine';
import { MagazineService } from '../magazine.service';
import { Edition } from '../edition';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { MerchantOrder, Type } from '../merchantOrder';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'app-magazine-details',
  templateUrl: './magazine-details.component.html',
  styleUrls: ['./magazine-details.component.css']
})
export class MagazineDetailsComponent implements OnInit {

  magazine: Magazine;
  free : Boolean;
  editions : Edition[];
  currentUser : User;
  merchantOrder : MerchantOrder;

  constructor(
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private magazineService: MagazineService,
    private router : Router,        
    ){}

  ngOnInit() : void {
    this.getCurrentUser();
    this.getMagazine();
    this.getMagazineEditions();
  }
  getMagazineEditions(): void {
    const magazineId = this.route.snapshot.paramMap.get('magazineIssn');
    this.magazineService.getMagazineEditions(magazineId)
    .subscribe(editions => {
      this.editions = editions;
      this.editions.sort((a,b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
    });    
  }

  getMagazine(): void {
    const magazineId = this.route.snapshot.paramMap.get('magazineIssn');
    this.magazineService.getMagazine(magazineId)
      .subscribe(magazine => {this.magazine = magazine;
      if(magazine.paymentType.toString()==='open_access'){
          this.free=true;
      }
      else{
        this.free=false;
      }      
      });    
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

  subscribe() : void {
    if(this.currentUser==null){
      alert("Morate se ulogovati kako biste se pretplatili za ovaj casopis!");
      this.router.navigateByUrl('/login');
    }
    else{
      this.merchantOrder = new MerchantOrder();
      this.merchantOrder.merchantOrderID = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
      this.merchantOrder.amountOrder=this.magazine.membershipPrice;
      this.merchantOrder.buyer_id=this.currentUser.username;
      this.merchantOrder.type=Type.SUBSCRIPTION;
      this.merchantOrder.merchant_id=this.magazine.issn;
      this.merchantOrder.successUrl='http://localhost:4200/magazineDetails/'+this.magazine.issn+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=success';
      this.merchantOrder.failedUrl='http://localhost:4200/magazineDetails/'+this.magazine.issn+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=failed';
      this.merchantOrder.errorUrl='http://localhost:4200/magazineDetails/'+this.magazine.issn+'?merchantOrderId='+this.merchantOrder.merchantOrderID+'&transactionState=error';
      this.magazineService.magazineSubscribe(this.merchantOrder).subscribe(response => window.location.href = response.url);
   }
  }

}
