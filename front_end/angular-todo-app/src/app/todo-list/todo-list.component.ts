import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from '../todo';

@Component(
  {
    selector: 'app-todo-list',
    templateUrl: './todo-list.component.html',
    styleUrls: ['./todo-list.component.css'],
  }
)
export class TodoListComponent {

  @Input()
  todos: Todo[];

  @Output()
  remove: EventEmitter<Todo> = new EventEmitter();

  @Output()
  crawlReq: EventEmitter<Todo> = new EventEmitter();

  @Output()
  toggleComplete: EventEmitter<Todo> = new EventEmitter();

  @Output()
  toggleAll: EventEmitter<Boolean> = new EventEmitter();

  constructor() {
  }

  onToggleTodoComplete(todo: Todo) {
    this.toggleComplete.emit(todo);
  }

  onRemoveTodo(todo: Todo) {
    this.remove.emit(todo);
  }
  onCrawlRequest(todo: Todo) {
    this.crawlReq.emit(todo);
  }
  
  setAllTo(checkAll:Boolean){
    this.toggleAll.emit(checkAll);
  }

  allCompleted() {
		return this.todos.length === this.todos.filter((todo: Todo) => todo.completed === true).length;
	}
}
