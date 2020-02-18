import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { HttpResponse} from '@angular/common/http';
@Injectable()
export class TodoDataService {

  constructor(
    private api: ApiService
  ) {
  }

  // Simulate POST /todos
  addTodo(todo: Todo): Observable<Todo> {
    return this.api.createTodo(todo);
  }

  // Simulate DELETE /todos/:id
  deleteTodoById(todoId: number): Observable<Todo> {
    return this.api.deleteTodoById(todoId);
  }

  // Simulate PUT /todos/:id
  updateTodo(todo: Todo): Observable<Todo> {
    return this.api.updateTodo(todo);
  }

  // Simulate GET /todos
  getAllTodos(): Observable<HttpResponse<Todo[]>> {
    return this.api.getAllTodos();
  }

  // Simulate GET /todos/:id
  getTodoById(todoId: number): Observable<Todo> {
    return this.api.getTodoById(todoId);
  }

  // Toggle complete
  toggleTodoComplete(todo: Todo) {
    todo.completed = !todo.completed;
    return this.api.updateTodo(todo);
  }
  toggleTodoCompleteAll(checkAll: Boolean) {
    
    
    return this.api.updateTodoAll(checkAll);
  }

}
