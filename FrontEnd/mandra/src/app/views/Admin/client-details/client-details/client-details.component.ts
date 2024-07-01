import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  clientId: any
  client: any
  imagepath!: string
  participation: any
  participants: any = []
  achat: any
  formationid: any
  formation: any
  buys: any = []
  ebookid: any
  ebook: any

  constructor(private route: ActivatedRoute, private clientService: ClientService, private router: Router,
    @Inject('BaseURL') private BaseURL: string
  ) { this.imagepath = this.BaseURL }

  ngOnInit(): void {
    this.loadOwner()
  }

  loadOwner() {
    this.clientId = this.route.snapshot.queryParams['clientId']
    this.clientService.getClientById(this.clientId).subscribe({
      next: (data) =>
        this.client = data,
      error: (err: HttpErrorResponse) =>
        console.log('error when traying to load client' + err.message)
    })
  }

  updateimage(event: any) {
    if (event.target.files.length > 0) {
      const path = event.target.files[0];
      const formData = new FormData();
      formData.append('image', path)
      this.clientService.updateClientImage(this.clientId, formData).subscribe(info =>
        this.router.navigate(['/admin']),
        (err: HttpErrorResponse) =>
          console.log("error when trying to upload the client image!" + err.message))
    }
  }

  deleteClient() {
    this.clientService.deleteClient(this.clientId).subscribe(info =>
      this.router.navigate(['/admin']),
      (err: HttpErrorResponse) =>
        // console.log("error when trying to delete the owner!" + err.message))
      this.router.navigate(['/admin'])) // i will fix that later
  }

  sendClientToUpdate() {
    this.router.navigate(['/admin/clients/update'],{queryParams:{clientId:this.clientId}})
  }

  changeAccountStauts() {
    this.clientService.changeClientStatus(this.clientId).subscribe({
      next:() =>{
        this.loadOwner();
      },
      error:(err:HttpErrorResponse) =>
        console.log("Error while changing client account state")
  })
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
