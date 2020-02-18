
import {throwError as observableThrowError,  Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Http ,Headers,RequestOptions,URLSearchParams }       from '@angular/http';
import { HttpClient,HttpHeaders,HttpParams,HttpResponse} from '@angular/common/http';
import { Todo } from './todo';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';



const API_URL = environment.apiUrl+"todo";

const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
@Injectable()
export class ApiService {

  constructor(
    private http: HttpClient,private router: Router
  ) {
  }

  public getAllTodos(): Observable<HttpResponse<Todo[]>> {
    return this.http
      .get<Todo[]>(API_URL +  '/todos',{observe: 'response'}).pipe(catchError(this.handleError.bind(this)));  }




  public createTodo(todo: any): Observable<Todo> {
   

    return this.http
      .post<Todo>(API_URL + '/todos', todo);
      //.catch(this.handleError);
  }

  public getTodoById(todoId: number): Observable<Todo> {
    return this.http
      .get<Todo>(API_URL +  '/todos/' + todoId);
  }

  public updateTodo(todo: any): Observable<Todo> {
   
    return this.http
      .put<Todo>(API_URL + '/todos' , todo);
  }
  public updateTodoAll(checkAll: any): Observable<any> {
  //  let options = new RequestOptions({ headers: headers });
    let params: HttpParams = new HttpParams();
    params=params.set('checkAll', checkAll);
    //options.params = params;
    return this.http
      .put<Todo>(API_URL +  '/todosall' ,null, {
        headers:headers,
        params: params
      });
      //.catch(this.handleError);
  }

  public deleteTodoById(todoId:any): Observable<any> {
  
  //      let options = new RequestOptions({ headers: headers });
        let params =  new HttpParams();
        params= params.append('id', todoId);
       // options.params = params;
       
    return this.http
      .delete(API_URL + '/todos' , {
        headers:headers,
        params: params
       
      }
    );
    
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
console.log(error.status );    // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
        if(error.status==401)
        this.sendRedirect();
        //this.router.navigate(['/login']);
    }
    return observableThrowError(error);
  }
 /**
  * name
  */
 public sendRedirect() {
  this.router.navigate(['/login']);
 } 
}
