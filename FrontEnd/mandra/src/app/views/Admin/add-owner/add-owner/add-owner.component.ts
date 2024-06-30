import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';

@Component({
  selector: 'app-add-owner',
  templateUrl: './add-owner.component.html',
  styleUrls: ['./add-owner.component.css']
})
export class AddOwnerComponent {

  newOwnerId!:number
  file: any = null
  poof: any
  errmessage!: string
  showModal: boolean = false;
  showPoofModal: boolean = false;
  uploadCinImageErrur!:string
  uploadProofErrur!:string
  constructor(private ownerService: OwnerService, private router: Router) { }

  ngOnInit(): void {
  }



  addOwner(f: NgForm) {
    let newOwner = f.value;
    this.ownerService.registerOwner(newOwner).subscribe(
      info => {
        this.newOwnerId = info.id;
        this.showModal = true;
        this.errmessage = '';
      },
      (err: HttpErrorResponse) => {
        this.errmessage = "Ce email est déjà utilisé!";
        this.showModal = true;
      }
    );
  }

  hideModal() {
    this.showModal = false;
  }



  selectImage(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];

      // Check if the file is an image
      if (file && file.type.startsWith('image/')) {
        this.file = file;
        this.uploadCinImageErrur = "";
      } else {
        this.file = null;  // Clear the file if it's not an image
        this.uploadCinImageErrur = "Type d’image non valide";
      }
    } else {
      this.file = null;  // Clear the file if no file is selected
      this.uploadCinImageErrur = "Aucun fichier sélectionné";
    }
  }

  updateCinImage() {
    if (!this.file) {
      console.error("No file selected!");
      this.uploadCinImageErrur = "No file selected";
      return;
    }

    console.log("The file:", this.file);
    const formData = new FormData();
    formData.append('cinImage', this.file);

    this.ownerService.updateOwnerCin(this.newOwnerId, formData).subscribe(
      res => {
        this.showModal = false;
        this.uploadCinImageErrur = "";
        this.file = null;
        this.showPoofModal = true;
      },
      (err: HttpErrorResponse) => {
        this.uploadCinImageErrur = "Échec du chargement!";
      }
    );
  }


  selectPDF(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];

      // Check if the file is a PDF
      if (file && file.type === 'application/pdf') {
        this.file = file;
        this.uploadProofErrur = "";
      } else {
        this.file = null;  // Clear the file if it's not a PDF
        this.uploadProofErrur = "Type de fichier non valide. Veuillez sélectionner un fichier PDF.";
      }
    } else {
      this.file = null;  // Clear the file if no file is selected
      this.uploadProofErrur = "Aucun fichier sélectionné";
    }
  }


  updateProof() {
    if (!this.file) {
      console.error("No file selected!");
      this.uploadProofErrur = "No file selected";
      return;
    }

    console.log("The file:", this.file);
    const formData = new FormData();
    formData.append('proof', this.file);

    this.ownerService.updateOwnerProof(this.newOwnerId, formData).subscribe(
      res => {
        this.router.navigate(['/admin/owners/disabled'])
      },
      (err: HttpErrorResponse) => {
        this.uploadProofErrur = "Échec du chargement!";
      }
    );
  }

}
