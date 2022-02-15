import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/_class/user';


const baseURL = "http://localhost:8080/api/contact/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private HttpClient: HttpClient) { }


  getContactList():Observable<User[]> 
  {
    return this.HttpClient.get<User[]>(baseURL + "list", httpOptions);
  }

  getSuggestContact():Observable<User[]>
  {
    return this.HttpClient.get<User[]>(baseURL + "suggest", httpOptions);
  }

  //ask invitation 
  addContact(id:number):Observable<any>
  {
    return this.HttpClient.post<User>(baseURL + id, httpOptions);
  }  


  //demande d'invitation faites par cette user 
  getUserAskList():Observable<User[]> 
  {
    return this.HttpClient.get<User[]>(baseURL + "demandes", httpOptions);
  }


  //demande d'invitation pour cette user 
  getUserInvited():Observable<User[]> 
  {
    return this.HttpClient.get<User[]>(baseURL + "invitations", httpOptions);
  }


  //accepte la demande d'invitation 
  //de l'user correspondant a l'id 
  //a l'user connecté jwt 

  acceptDemand(id:number):Observable<any>
  {
    return this.HttpClient.post<User>(baseURL+ "accept/" + id, httpOptions);
  }

  //refuse la demande d'invitation 
  refuseDemande(id:number):Observable<any>
  {
    return this.HttpClient.post<User>(baseURL+ "refuse/" + id, httpOptions);
  }



  //deleteContact
  //id => user supprimé des contacts de l'user connected 
  deleteContact(id:number):Observable<Object>
  {
    return this.HttpClient.delete(baseURL + "list/delete/" + id , httpOptions);
  }

}
