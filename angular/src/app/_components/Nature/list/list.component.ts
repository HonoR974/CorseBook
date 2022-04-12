import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_services/user.service';



@Component({
  selector: 'app-list-nature',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {


   //ex
  myControl = new FormControl();

  options: string[] = ['Delhi', 'Mumbai', 'Banglore'];
  filteredOptions: Observable<string[]>;
  
  //user 
  secondControl = new FormControl();
  users:string[] = [];
  userFilter: Observable<string[]>;


  constructor(private userService:UserService)
  {}


  ngOnInit() {


    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    


    this.userService.getAllUsername().subscribe(data => 
      {
        console.log("data username", data );
        this.users = data;
      });
    

      this.userFilter = this.secondControl.valueChanges.pipe(
        startWith(''),
        map(value => this.secondFilter(value))
      );

      

  }


  private _filter(value: string): string[] {
    console.log("filter ex ", value);
    const filterValue = value.toLowerCase();

    console.log("return ex " + this.options.filter(option => option.toLowerCase()
    .indexOf(filterValue) === 0) );


    return this.options.filter(option => option.toLowerCase()
                                 .indexOf(filterValue) === 0);
  }


  private secondFilter(value:string):string[]
  {
    console.log("filter second ",value);

      const filterValue = value.toLowerCase();

      console.log("return "  + this.users.filter( user => user.toLowerCase()
      .indexOf(filterValue) ===0 ));


      return this.users.filter( user => user.toLowerCase()
      .indexOf(filterValue) ===0 );

  }



}
