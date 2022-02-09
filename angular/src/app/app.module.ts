import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './_components/product-list/product-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authInterceptorProviders } from './_helpers/auth.interceptor';

import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatBadgeModule} from '@angular/material/badge';


import { NgxDropzoneModule } from 'ngx-dropzone';
import { CommonModule } from '@angular/common';

import { ToastrModule } from 'ngx-toastr';
import { CreateProductComponent } from './_components/create-product/create-product.component';
import { CreatePublicationComponent } from './_components/create-publication/create-publication.component';
import { HomeComponent } from './_components/home/home.component';
import { LoginComponent } from './_components/login/login.component';
import { ProductDetailsComponent } from './_components/product-details/product-details.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { RegisterComponent } from './_components/register/register.component';
import { UpdateProductComponent } from './_components/update-product/update-product.component';
import { PublicationPublicComponent } from './_components/publication-public/publication-public.component';

import { UploadFileComponent } from './_components/upload-file/upload-file.component';
import { UpdatePublicationComponent } from './_components/update-publication/update-publication.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GoogleMapsModule } from '@angular/google-maps';
import { ListComponent } from './_components/evenement/list/list.component';
import {CarouselModule} from 'primeng/carousel';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import { ContactComponent } from './_components/contact/contact.component'

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    CreateProductComponent,
    UpdateProductComponent,
    ProductDetailsComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    CreatePublicationComponent,
    PublicationPublicComponent,
    UploadFileComponent,
    UpdatePublicationComponent,
    ListComponent,
    ContactComponent
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
    NgbModule,
    GoogleMapsModule,
    CarouselModule,
    ButtonModule,
    ToastModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
