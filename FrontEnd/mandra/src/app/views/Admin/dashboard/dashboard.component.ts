import { HttpErrorResponse } from '@angular/common/http';
import { StatisticService } from './../../../services/apiServices/statisticService/statistic.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  entitiesCounts:any = {}

  constructor( private statisticService:StatisticService) { }

  ngOnInit(): void {

   this.loadEntitienCounts()
  }


  loadEntitienCounts(){
    this.statisticService.getEntitiesCounts().subscribe(
      counts =>
        this.entitiesCounts = counts
      ,(err:HttpErrorResponse) =>
        console.log("Error while getting the content of all entities")
    )

  }

}
