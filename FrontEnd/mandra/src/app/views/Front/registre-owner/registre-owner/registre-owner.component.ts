import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServiceService } from 'src/app/services/apiService/api-service.service';

@Component({
  selector: 'app-registre-owner',
  templateUrl: './registre-owner.component.html',
  styleUrls: ['./registre-owner.component.css']
})
export class RegistreOwnerComponent {

  errmessage: any
  constructor(private api: ApiServiceService, private router: Router) { }

  ngOnInit(): void {
  }
  registre(f: NgForm) {
    // let body = f.value
    // this.api.registrecentre(body).subscribe(info => this.router.navigate(['/login']),
    //  (err: HttpErrorResponse) => this.errmessage = 'Ce email est déjà utilisé')
  }

}
