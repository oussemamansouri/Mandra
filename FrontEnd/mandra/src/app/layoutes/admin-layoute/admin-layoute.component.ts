import { Component, OnInit,Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common'

@Component({
  selector: 'app-admin-layoute',
  templateUrl: './admin-layoute.component.html',
  styleUrls: ['./admin-layoute.component.css']
})
export class AdminLayouteComponent {

  constructor(@Inject(DOCUMENT) private document: Document) {}

  ngOnInit(): void {

  }

  sidebarToggle()
  {
    //toggle sidebar function
    this.document.body.classList.toggle('toggle-sidebar');
  }


}
