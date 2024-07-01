import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  errmessage!: string
  constructor(private clientService: ClientService, private router: Router) { }

  ngOnInit(): void {
  }



  addClient(f: NgForm) {
    let client= f.value;
    this.clientService.registerClient(client).subscribe(
      info => {
        this.router.navigate(['/admin/clients/active'])
        console.log("client added")
      },
      (err: HttpErrorResponse) => {
        this.errmessage = "Ce email est déjà utilisé!";
        console.log("client add error")
      }
    );
  }

}
