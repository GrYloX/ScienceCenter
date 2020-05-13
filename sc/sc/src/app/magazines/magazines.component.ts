import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../magazine.service';
import { Magazine } from '../magazine';

@Component({
  selector: 'app-magazines',
  templateUrl: './magazines.component.html',
  styleUrls: ['./magazines.component.css']
})
export class MagazinesComponent implements OnInit {

  magazines: Magazine[];

  constructor(private magazineService: MagazineService) { }

  ngOnInit() {
    this.getMagazines();
  }

  getMagazines() : void {
    this.magazineService.getMagazines()
    .subscribe(magazines => this.magazines = magazines);
  }

}
