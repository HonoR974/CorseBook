import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';


@Component({
  selector: 'app-list-nature',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(
    private uploadS3Service: UploadS3Service,
    private toaster: ToastrService
  ) {}
  ngOnInit(): void {
    
  }

}
