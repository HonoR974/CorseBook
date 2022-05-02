import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Comment } from 'src/app/_class/comment';

import Swal from 'sweetalert2';

import { Evenement } from 'src/app/_class/evenement';
import { User } from 'src/app/_class/user';

import { CommentService } from 'src/app/_services/comment.service';
import { EventService } from 'src/app/_services/event.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
})
export class EventListComponent implements OnInit {
  events: Evenement[] = [];

  //agm
  // google maps zoom level
  zoom: number = 8;

  // initial center position for the map
  lat: number = 41.91;
  lng: number = 8.73;

  user!: User;
  isConnected: boolean = false;

  commentForm = new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
  });

  constructor(
    private eventService: EventService,
    private tokenService: TokenStorageService,
    private commentService: CommentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.start();
  }

  start() {
    this.getAllEvent();

    if (this.tokenService.getToken()) {
      this.isConnected = true;
      this.user = this.tokenService.getUser();

      console.log('user', this.user);
    }
  }

  getAllEvent() {
    this.eventService.getallEvent().subscribe((data) => {
      console.log('getEvent ', data);
      this.events = data;

      if (this.isConnected) {
        this.checkListEvent();
      }
    });
  }

  //ajoute l'user a l'event selectionné
  addParticipant(id: number) {
    console.log('add', id);
    this.eventService.addUserOnEvent(id).subscribe((data) => {
      console.log('data ', data);

      window.location.reload();
    });
  }

  //supprime l'user a l'event selectionné
  deleteParticipant(id: number) {
    this.eventService.deleteUserOnEvent(id).subscribe((data) => {
      console.log('data delete ', data);

      window.location.reload();
    });
  }

  checkListEvent() {
    this.events.forEach(
      (element) => (element.isParticiped = this.isParticiped(element))
    );
    console.log('event ', this.events);
  }

  //verifie que l'username de l'user connecté
  //est dans la liste des participants
  isParticiped(evenement: Evenement): boolean {
    let condition = false;

    for (let participant of evenement.listParticipant) {
      if (this.user.username === participant) {
        condition = true;
      }
    }
    return condition;
  }

  //supprime un event
  deleteEvent(id: number) {
    Swal.fire({
      title: 'Voulez-vous vraiment supprimer cette évenement ? ',
      text: 'Apres la suppression de cuil-ci vous ne pourrez plus le récupérer ',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, je le supprime',
      cancelButtonText: 'Non, je le garde ',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Deleted!', 'Votre evenelent a été supprimée.', 'success');

        this.eventService.deleteEventByID(id).subscribe((data) => {
          this.start();
        });
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Votre evenemnt est toujours là :D ', 'error');
      }
    });
  }
}
