import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';


@Component({
  selector: 'app-list-nature',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  show:boolean;

  constructor() {
  this.show=true;
  }

  thenTemplate:TemplateRef<any>|null=null;
  
  @ViewChild('primaryTemplate')
  primaryTemplate:TemplateRef<any>|null=null;
  
  @ViewChild('secondaryTemplate')
  secondaryTemplate:TemplateRef<any>|null=null;
  
  switchThenTemplate() {
  this.thenTemplate= 
   (this.thenTemplate===this.primaryTemplate) ?
   this.secondaryTemplate:
   this.primaryTemplate;
  }
  ngOnInit() {
   this.thenTemplate=this.primaryTemplate;
  }
  }