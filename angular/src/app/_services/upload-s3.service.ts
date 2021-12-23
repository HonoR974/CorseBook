import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as S3 from 'aws-sdk/clients/s3';
import { Observable } from 'rxjs';
import { FileAPI } from '../_class/file-api';

const File_API = 'http://localhost:8080/api/file/';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};



@Injectable({
  providedIn: 'root',
})
export class UploadS3Service {

  constructor(private http: HttpClient) {}


  uploadFileS3(file: any, filePath: any) {

    return new Promise((resolve, reject) => {

      const contentType = file.type;
      const bucket = new S3({
        accessKeyId: 'AKIA3JCVUBIEDKO2RXC3',// ACCESS_KEY_ID
        secretAccessKey: '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',// SECRET_ACCESS_KEY
        region: 'eu-west-3',// BUCKET_REGION
      });

      const params = {
        Bucket: 'testp12',//BUCKET_NAME
        Key: filePath,
        Body: file,
        ACL: 'public-read',
        ContentType: contentType,
      };

      bucket.upload(params,  (err: any, data: any) => {
        if (err) {
          reject(err);
        }
       this.uploadFileAPI({ name:file.name, url:data.Location}).subscribe (
         retour => {
          console.log("retour " , retour);
          resolve(data);
         }
       )
  
      });

    });
  }

  uploadFileAPI(file: FileAPI): Observable<any> 
  {
    console.log("methode api  " , file.name+ " // " , file.url + "   chemin d'acces "  + File_API);

    return this.http.post(File_API, file);
  
  
  }


  getAllFileAPI(): Observable<FileAPI[]>
  {
    return this.http.get<FileAPI[]>(File_API);
  }
}