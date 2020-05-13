import { Injectable } from '@angular/core';
import { Magazine } from './magazine';
import { Edition } from './edition';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable} from 'rxjs';
import { MerchantOrder } from './merchantOrder';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  private magazinesUrl = 'https://localhost:8090/magazine';
  private magazineEditionsUrl = 'https://localhost:8090/edition/getMagazineEditions';

  constructor(
    private http: HttpClient
    ) {}

  getMagazines(): Observable<Magazine[]> {
      return this.http.get<Magazine[]>(this.magazinesUrl+'/getMagazines');
  }

  getMagazine(magazineId : String): Observable<Magazine> {
      return this.http.get<Magazine>(this.magazinesUrl+'/'+magazineId);
  }

  getMagazineEditions(magazineId): Observable<Edition[]>{
      return this.http.get<Edition[]>(this.magazineEditionsUrl+'/'+magazineId);
  }
  
  magazineSubscribe(merchantOrder : MerchantOrder){
    return this.http.post('https://localhost:8090/merchantOrderi/subscribe',merchantOrder, httpOptions) as Observable<any>;
  }
}
