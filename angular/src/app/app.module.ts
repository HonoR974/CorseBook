import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authInterceptorProviders } from './_helpers/auth.interceptor';

import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatBadgeModule} from '@angular/material/badge';
import { NgxDropzoneModule } from 'ngx-dropzone';
import { CommonModule } from '@angular/common';

import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './_components/Accueil/home/home.component';
import { RegisterComponent } from './_components/Logs/register/register.component';

import { UpdatePublicationComponent } from './_components/Accueil/update-publication/update-publication.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import {CarouselModule} from 'primeng/carousel';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import { ContactComponent } from './_components/contact/contact.component';
import { ChatComponent } from './_components/chat/chat.component';
import { EventComponent } from './_components/Evenement/event/event.component';

import { AgmCoreModule } from '@agm/core';
import { LoginComponent } from './_components/Logs/login/login.component';
import { ProfileComponent } from './_components/Espace/profile/profile.component';
import { CreatePublicationComponent } from './_components/Accueil/create-publication/create-publication.component';
import { PublicationPublicComponent } from './_components/Accueil/publication-public/publication-public.component';
import { UploadFileComponent } from './_components/upload-file/upload-file.component';
import { PublicationProfileComponent } from './_components/Espace/publication-profile/publication-profile.component';
import { CreateEventComponent } from './_components/Evenement/create-event/create-event.component';
import { EventListComponent } from './_components/Evenement/event-list/event-list.component';


import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { ListComponent } from './_components/Nature/list/list.component';
import { CreateComponent } from './_components/Comment/create/create.component';
import { CommentPubComponent } from './_components/Comment/comment-pub/comment-pub.component';
import { CommentEventComponent } from './_components/Comment/comment-event/comment-event.component';
import { UpdateProfileComponent } from './_components/Espace/update-profile/update-profile.component';
import { MenuComponent } from './_components/menu/menu.component';

import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    CreatePublicationComponent,
    PublicationPublicComponent,
    UploadFileComponent,
    UpdatePublicationComponent,
    ContactComponent,
    ChatComponent,
    PublicationProfileComponent,
    EventComponent,
    CreateEventComponent,
    EventListComponent,
    ListComponent,
    CreateComponent,
    CommentPubComponent,
    CommentEventComponent,
    UpdateProfileComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    NgxDropzoneModule,
    CommonModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule,
    MatIconModule,
    MatTooltipModule,
    MatBadgeModule,
    MatSlideToggleModule,
    NgbModule,
    MatProgressBarModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule,
    MatInputModule,
    CarouselModule,
    ButtonModule,
    ToastModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyD-5cW7KLHBPf4-lQwRkYw0_ndyzY0Ydd0',
      libraries: ['places']
    })
 
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
