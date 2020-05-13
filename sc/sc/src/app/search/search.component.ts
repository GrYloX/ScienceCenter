import { Component, OnInit } from '@angular/core';
import { AdvancedQuery } from '../advanced.query';
import { SearchService } from '../search.service';
import { SimpleQuery } from '../simple.query';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  results: any[];
  advancedQuery: AdvancedQuery;
  fields: any[];

  constructor(
    private searchService: SearchService
  ) {
  }

  ngOnInit() {
    this.results = [];
    this.advancedQuery = {
      queries: [],
      operation: "",
    };

    this.fields = [
      {
        name: "Casopis",
        value: "magazineName"
      },
      {
        name: "Naslov rada",
        value: "title"
      },
      {
        name: "Ime i prezime autora",
        value: "authorName"
      },
      {
        name: "Kljucni pojmovi",
        value: "keywords"
      },
      {
        name: "Naucna oblast",
        value: "scienceField"
      },
      {
        name: "Sadrzaj",
        value: "text"
      }
    ]
  }

  addFilter() {
    this.advancedQuery.queries.push(
      {
        field: "",
        value: ""
      }
    );
  }

  removeFilter(filter: SimpleQuery) {
    this.advancedQuery.queries.splice(this.advancedQuery.queries.indexOf(filter), 1);
  }

  checkIfFieldAlreadyTaken(fieldName: string): boolean{
    for(let field of this.advancedQuery.queries) {
      if(fieldName === field.field) {
        return true;
      }
    }

    return false;
  }

  go() {
    if(this.advancedQuery.queries.length>1){
      console.log(this.advancedQuery.operation);
      this.searchService.searchBoolean(this.advancedQuery).subscribe(
        (data) => {
          this.results = data;
        }
      )
    }
    else{
      this.searchService.searchSimple(this.advancedQuery.queries[0]).subscribe(
        (data) => {
          this.results = data;
        })
    }
  }


}
