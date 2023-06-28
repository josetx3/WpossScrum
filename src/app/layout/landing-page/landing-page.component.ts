import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token==='' || token===null || token===undefined){

    }else{
      this.router.navigate(['/app'])
    }
    let body = document.body;
    let script = document.createElement('script');
    script.innerHTML = '';
    script.src = 'assets/js/landing.js';
    script.async = true;
    script.type = 'text/javascript';
    body.appendChild(script);
  }


  constructor(private router: Router){}

  auth() {
    this.router.navigateByUrl('/auth').then(r => console.log(r));
  }


}
