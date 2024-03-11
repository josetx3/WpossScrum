import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { encrypt } from 'src/app/utils/encrypt';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public nameE:any  ;
  public charge:any   ;


  constructor(
    private router: Router,
    private encryp: encrypt
  ){

    const name: any=localStorage.getItem('name');
    const charge: any=localStorage.getItem('charge');
    this.nameE=this.encryp.decryptData(name)
    this.charge= this.encryp.decryptData(charge)
  }
  ngOnInit(): void {

    let body = document.body;
    let script = document.createElement('script');
    script.innerHTML = '';
    script.src = 'assets/js/main.js';
    script.async = true;
    script.type = 'text/javascript';
    body.appendChild(script);

  }
  logout():void{
    localStorage.clear();
  //  this.router.navigate([''])
  }


}
