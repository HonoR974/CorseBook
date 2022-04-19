import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './_components/chat/chat.component';

import { EventComponent } from './_components/Evenement/event/event.component';
import { HomeComponent } from './_components/Accueil/home/home.component';

import { RegisterComponent } from './_components/Logs/register/register.component';

import { UpdatePublicationComponent } from './_components/Accueil/update-publication/update-publication.component';
import { LoginComponent } from './_components/Logs/login/login.component';
import { PublicationPublicComponent } from './_components/Accueil/publication-public/publication-public.component';
import { ProfileComponent } from './_components/Espace/profile/profile.component';
import { CreateEventComponent } from './_components/Evenement/create-event/create-event.component';
import { UploadFileComponent } from './_components/upload-file/upload-file.component';
import { ListComponent } from './_components/Nature/list/list.component';
import { EventListComponent } from './_components/Evenement/event-list/event-list.component';
import { TableComponent } from './_components/Evenement/table/table.component';



const routes: Routes = [

  //accueil 
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  //log 
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  //profile 
  { path: 'profile/:id', component: ProfileComponent },
  { path :'update-profile/:id', component:UpdatePublicationComponent},

  //publication
  { path: 'publication-public', component: PublicationPublicComponent },
  { path: 'update-publication/:id', component: UpdatePublicationComponent },

  //file 
  { path: 'uploadFile', component: UploadFileComponent },

  //evenement 
  { path :'event', component: EventComponent},
  { path :'create-event', component: CreateEventComponent},
  { path :'list-event', component:EventListComponent},
  { path :'table-event', component:TableComponent},

  //chat 
  { path: 'chat/:id', component:ChatComponent},

  //nature 
  { path :'nature', component:ListComponent}

  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
