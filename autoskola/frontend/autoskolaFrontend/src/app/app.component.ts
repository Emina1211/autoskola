import {Component, OnInit} from '@angular/core';
import {Termin} from "./termin";
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  termini: Termin[][];

  odabranTermin: any;
  newDate : any;
  dani: string[] = ['Ponedjeljak', 'Utorak', 'Srijeda', 'Cetvrtak', 'Petak', 'Subota', 'Nedjelja'];
  datum: Date = new Date();
  datumPocetkaSedmice: Date = new Date();
  datumKrajaSedmice: Date = new Date();
  currentDay: number;

  constructor(private http: HttpClient) { }

    ngOnInit () {
      this.currentDay = (new Date(this.datum)).getDay();
      this.namjestiPocetakIKrajSedmice(new Date());
      this.dobaviTermineZaSedmicu(this.datumPocetkaSedmice, this.datumKrajaSedmice);
    }

    dobaviTermineZaSedmicu(datumPocetkaSedmice: Date, datumKrajaSedmice: Date){
      let month  = (datumPocetkaSedmice.getMonth() + 1).toString();
      let month2 = (datumKrajaSedmice.getMonth() + 1).toString();
      let day = datumPocetkaSedmice.getDate().toString();
      let day2 = datumKrajaSedmice.getDate().toString();

      if (day.length == 1){
        day = '0' + day;
      }
      if (day2.length == 1){
        day2 = '0' + day2;
      }
      if (month.length == 1){
        month = '0' + month;
      }
      if (month2.length == 1){
        month2 = '0' + month2;
      }

      let datum = day + month + (datumPocetkaSedmice.getFullYear()).toString();
      let datum2 = day2 + month2 + datumKrajaSedmice.getFullYear().toString();
      let url = 'http://localhost:8080/api/termini/' + datum + '/' + datum2;
      return this.http.get<Termin[][]>(url).subscribe(x => this.termini = x);
    }

    promjenaDatuma(datum: Date){
      this.currentDay = (new Date(datum)).getDay();
      this.namjestiPocetakIKrajSedmice(datum);
      this.dobaviTermineZaSedmicu(this.datumPocetkaSedmice, this.datumKrajaSedmice);
    }

    namjestiPocetakIKrajSedmice(datum : Date){
      this.datumKrajaSedmice = new Date(new Date (datum).getTime()+ ((7 - this.currentDay) % 7)*24*60*60*1000);
      let toSubtract = this.currentDay - 1;
      if (this.currentDay == 0) {
        toSubtract = 6;
      }
      this.datumPocetkaSedmice = new Date(new Date(datum).getTime() - toSubtract *24 * 60 * 60 * 1000);
    }

    add(odabranTermin: any, datum: any){
      let url = 'http://localhost:8080/api/termini/';
      let body = new Termin();
      body.pocetak = +odabranTermin;
      body.kraj = +odabranTermin + 1;
      body.datum = new Date(datum);

      this.http.post(url, body).subscribe(x => console.log(x));
      this.dobaviTermineZaSedmicu(this.datumPocetkaSedmice, this.datumKrajaSedmice);

    }
}


