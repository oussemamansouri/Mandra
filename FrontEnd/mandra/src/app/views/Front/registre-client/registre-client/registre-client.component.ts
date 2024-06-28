import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';

@Component({
  selector: 'app-registre-client',
  templateUrl: './registre-client.component.html',
  styleUrls: ['./registre-client.component.css']
})
export class RegistreClientComponent implements OnInit {

  errormessage!:string
  constructor(private clinetService: ClientService, private router: Router) { }

  ngOnInit(): void {
  }



  register(f: NgForm) {
    let client = f.value;
    console.log(client)
    this.clinetService.registerClient(client)
      .subscribe(response => this.router.navigate(['/signin']),(err)=>{
        console.log(err)
        this.errormessage='Ce email est déjà utilisé'
      });
  }

}
