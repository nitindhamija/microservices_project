import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from '../_guards/auth.guard';
import {HomeComponent} from '../home/home.component';
import {LoginComponent} from '../login/login.component';


const routes: Routes=[
  {
    path:'home',component:HomeComponent, canActivate:[AuthGuard]
    
  },
  { path: 'login', component: LoginComponent },
  {
    path:'**', redirectTo:'home'
}
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ]
})
export class RoutingModule { }
