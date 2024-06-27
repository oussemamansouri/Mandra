import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServiceService } from 'src/app/services/apiService/api-service.service';

@Component({
  selector: 'app-registre-client',
  templateUrl: './registre-client.component.html',
  styleUrls: ['./registre-client.component.css']
})
export class RegistreClientComponent {

  errormessage:any


  constructor(private api: ApiServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  maxDate() {
    const today = new Date().toISOString().split('T')[0];
    return today;
}



  register(f: NgForm) {

    let body=new FormData
    body.append('firstname',f.value.firstname)
    body.append('lastname',f.value.lastname)
    body.append('email',f.value.email)
    body.append('tel',f.value.tel)
    body.append('address',f.value.address)
    body.append('dob',f.value.dob)
    body.append('password',f.value.password)
    // this.api.registreclient(body)
    //   .subscribe(response => this.router.navigate(['/login']),(err)=>{
    //     this.errormessage='Ce email est déjà utilisé'
    //   });

  }

}
