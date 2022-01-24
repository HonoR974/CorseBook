import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListContactComponent } from './_components/contact/list-contact/list-contact.component';
import { CreateProductComponent } from './_components/create-product/create-product.component';
import { ListComponent } from './_components/evenement/list/list.component';
import { HomeComponent } from './_components/home/home.component';
import { LoginComponent } from './_components/login/login.component';
import { ProductDetailsComponent } from './_components/product-details/product-details.component';
import { ProductListComponent } from './_components/product-list/product-list.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { PublicationPublicComponent } from './_components/publication-public/publication-public.component';
import { RegisterComponent } from './_components/register/register.component';

import { UpdateProductComponent } from './_components/update-product/update-product.component';
import { UpdatePublicationComponent } from './_components/update-publication/update-publication.component';
import { UploadFileComponent } from './_components/upload-file/upload-file.component';


const routes: Routes = [

  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  //log 
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },

  //product 
  {path: 'products', component: ProductListComponent},
  {path: 'create-product', component: CreateProductComponent},
  {path: 'update-product/:id', component: UpdateProductComponent},
  {path: 'product-details/:id', component: ProductDetailsComponent},

  //publication
  { path: 'publication-public', component: PublicationPublicComponent },
  { path: 'update-publication/:id', component: UpdatePublicationComponent },

  //file 
  { path: 'uploadFile', component: UploadFileComponent },

  //evenement 
  { path :'evenement-list', component: ListComponent},

  //contact 
  { path :'contact-list', component : ListContactComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
