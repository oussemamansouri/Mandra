import { BaseURL } from 'src/app/Shared/base-url';
import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-owner-details',
  templateUrl: './owner-details.component.html',
  styleUrls: ['./owner-details.component.css']
})
export class OwnerDetailsComponent implements OnInit{

  ownerId: any
  owner: any
  imagepath!: string
  participation: any
  participants: any = []
  achat: any
  formationid: any
  formation: any = { date_debut: "", date_fin: "", id: "", titre: "", discription: "", img: "", prix: "", heures: "", promotion: "", categorie: "", etat: "", diplome: "", certifiee: "", createdAt: "", updatedAt: "", CentreId: "", Centre: {} }
  buys: any = []
  ebookid: any
  ebook: any = { id: '', titre: '', discription: '', auteur: '', format: '', nb_pages: '', img: '', prix: '', promotion: '', book: '', createdAt: '', updatedAt: '', CentreId: '' }

  constructor(private route: ActivatedRoute, private ownerService: OwnerService, private router: Router,
    @Inject('BaseURL') private BaseURL: string
  ) { this.imagepath = this.BaseURL }

  ngOnInit(): void {
    this.loadOwner()
  }

  loadOwner() {
    this.ownerId = this.route.snapshot.queryParams['ownerId']
    this.ownerService.getOwnerById(this.ownerId).subscribe({
      next: (data) =>
        this.owner = data,
      error: (err: HttpErrorResponse) =>
        console.log('error when traying to load owner' + err.message)
    })
  }

  updateimage(event: any) {
    if (event.target.files.length > 0) {
      const path = event.target.files[0];
      const formData = new FormData();
      formData.append('image', path)
      this.ownerService.updateOwnerImage(this.ownerId, formData).subscribe(info =>
        this.router.navigate(['/admin']),
        (err: HttpErrorResponse) =>
          console.log("error when trying to upload the owner image!" + err.message))
    }
  }

  deleteOwner() {
    this.ownerService.deleteOwner(this.ownerId).subscribe(info =>
      this.router.navigate(['/admin']),
      (err: HttpErrorResponse) =>
        // console.log("error when trying to delete the owner!" + err.message))
      this.router.navigate(['/admin'])) // i will fix that later

  }

  sendOwnerToUpdate() {
    this.router.navigate(['/admin/owners/update'],{queryParams:{ownerId:this.ownerId}})
  }




  sendid2() {
    // this.router.navigate(['/admin/ebook/buys'],{queryParams:{ebookId:this.ebookid}})
  }

  getformationid(id: any) {
    this.formationid = id
    // this.api.getformation(id).subscribe(data=>{this.formation=data
    //   if (this.formation.certifiee=='true'){
    //     this.formation.certifiee='Oui'
    //   }else if (this.formation.certifiee=='false'){
    //     this.formation.certifiee='Non'
    //   }
    // })
    // this.api.getparticipant(id).subscribe(info=>this.participants=info)
  }

  deleteformation() {
    // this.api.deleteparticipation(this.clientId,this.formationid).subscribe(info=>this.ngOnInit())
  }

  getbookId(id: any) {
    //     this.ebookid=id
    // this.api.getebookbyid(id).subscribe(info=>{this.ebook=info})
    // this.api.getbuyersebook(id).subscribe(data=>this.buys=data)
  }

  deleteebook() {
    // this.api.deleteachatclient(this.clientId,this.ebookid).subscribe(info=>this.ngOnInit())
  }

  downloadebook() {
    // this.api.downloadebook(this.ebookid).subscribe((blob) => {
    //   const fileName = `Ebook.${this.ebook.format}`;
    //   const url = window.URL.createObjectURL(blob);
    //   const a = document.createElement('a');
    //   document.body.appendChild(a);
    //   a.setAttribute('style', 'display: none');
    //   a.href = url;
    //   a.download = fileName;
    //   a.click();
    //   window.URL.revokeObjectURL(url);
    //   a.remove(); // remove the element
    // });
  }

  sendformationid() {
    // this.router.navigate(['/admin/formation/update'],{queryParams:{formationId:this.formationid}})
  }
  sendebookid() {
    // this.router.navigate(['/admin/ebook/update'],{queryParams:{ebookId:this.ebookid}})
  }
  sendid() {
    // this.router.navigate(['/admin/formation/participants'],{queryParams:{formationId:this.formationid}})
  }




}
