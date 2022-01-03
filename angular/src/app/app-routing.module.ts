import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProductComponent } from './_components/create-product/create-product.component';
import { HomeComponent } from './_components/home/home.component';
import { LoginComponent } from './_components/login/login.component';
import { ProductDetailsComponent } from './_components/product-details/product-details.component';
import { ProductListComponent } from './_components/product-list/product-list.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { PublicationPublicComponent } from './_components/publication-public/publication-public.component';
import { RegisterComponent } from './_components/register/register.component';

import { UpdateProductComponent } from './_components/update-product/update-product.component';
import { UploadFileComponent } from './_components/upload-file/upload-file.component';


const routes: Routes = [

  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  

  {path: 'products', component: ProductListComponent},
  {path: 'create-product', component: CreateProductComponent},
  {path: 'update-product/:id', component: UpdateProductComponent},
  {path: 'product-details/:id', component: ProductDetailsComponent},

  { path: 'uploadFile', component: UploadFileComponent },
  { path: 'publication-public', component: PublicationPublicComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
