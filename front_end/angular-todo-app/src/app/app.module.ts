import { BrowserModule } from '@angular/platform-browser/';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
//import { HttpModule } from '@angular/http';
import {jwtInterceptor} from './authentication/jwt-interceptor';
import { AppComponent } from './app.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { TodoListFooterComponent } from './todo-list-footer/todo-list-footer.component';
import { TodoListHeaderComponent } from './todo-list-header/todo-list-header.component';
import { TodoDataService } from './todo-data.service';
import { TodoListItemComponent } from './todo-list-item/todo-list-item.component';
import { ApiService } from './api.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import {RoutingModule} from './routing/routing.module';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoListFooterComponent,
    TodoListHeaderComponent,
    TodoListItemComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
   // BrowserModule,
   BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
   // HttpModule,
   RouterModule,
   RoutingModule,
   
   HttpClientModule
  ],
  providers: [TodoDataService, ApiService,
    
    { provide: HTTP_INTERCEPTORS, useClass: jwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
