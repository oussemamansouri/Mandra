import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  searchTerm:any
  formations:any
  formation:any={date_debut:"",date_fin:"",id:"",titre:"",discription:"",img:"",prix:"",heures:"",promotion:"",categorie:"",etat:"",diplome:"",certifiee:"",createdAt:"",updatedAt:"",CentreId:"",Centre:{}}
  formationid:any
  imagepath:any='http://localhost:3000/'
  participation:any=[]

  nb:number=5

  // constructor(private api:ApiService,private router:Router) { }

  ngOnInit(): void {
    // this.api.getallformations().subscribe(data=>this.formations=data)
  }

  getformationid(id:any){
    // this.formationid=id
    // this.api.getformation(id).subscribe(data=>{this.formation=data
    //   if (this.formation.certifiee=='true'){
    //     this.formation.certifiee='Oui'
    //   }else if (this.formation.certifiee=='false'){
    //     this.formation.certifiee='Non'
    //   }
    // })
    // this.api.getparticipant(id).subscribe(info=>this.participation=info)

  }

  deleteformation(){
    // this.api.deleteformation(this.formationid).subscribe(info=>this.ngOnInit())
  }
  sendid(){
// this.router.navigate(['/admin/formation/update'],{queryParams:{formationId:this.formationid}})
  }
  sendformationid(){
    // this.router.navigate(['/admin/formation/participants'],{queryParams:{formationId:this.formationid}})
      }


      filter() {
        if (!this.searchTerm) {
          return this.formations;
        }

        return this.formations.filter((item:any) =>
          item.titre.toLowerCase().includes(this.searchTerm.toLowerCase())
        );
      }


}
