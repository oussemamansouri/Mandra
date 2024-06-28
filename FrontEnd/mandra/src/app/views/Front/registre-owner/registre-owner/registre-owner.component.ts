import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';

@Component({
  selector: 'app-registre-owner',
  templateUrl: './registre-owner.component.html',
  styleUrls: ['./registre-owner.component.css']
})
export class RegistreOwnerComponent {

  errormessage!: string
  constructor(private ownerService: OwnerService, private router: Router) { }

  ngOnInit(): void {
  }
  registre(f: NgForm) {
    let owner = f.value;
    this.ownerService.registerOwner(owner)
      .subscribe(response => this.router.navigate(['/signin']),(err)=>{
        console.log(err)
        this.errormessage='Ce email est déjà utilisé'
      });
  }

}
