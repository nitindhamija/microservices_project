import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { Observable,of } from 'rxjs';


@Injectable()
export class ApiMockService {

  constructor(
  ) {
  }

  public getAllTodos(): Observable<Todo[]> {
    return of([
      new Todo({id: 1, name: 'Read article', completed: false})
    ]);
  }

  public createTodo(todo: Todo): Observable<Todo> {
    return of(
      new Todo({id: 1, name: 'Read article', completed: false})
    );
  }

  public getTodoById(todoId: number): Observable<Todo> {
    return of(
      new Todo({id: 1, name: 'Read article', completed: false})
    ); 
  }

  public updateTodo(todo: Todo): Observable<Todo> {
    return of(
      new Todo({id: 1, name: 'Read article', completed: false})
    );
  }

  public deleteTodoById(todoId: number): Observable<null> {
    return null;
  }
}
