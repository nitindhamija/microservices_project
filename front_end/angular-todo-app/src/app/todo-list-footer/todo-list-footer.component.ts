import { Component, Input } from '@angular/core';
import { Todo } from '../todo';

@Component({
  selector: 'app-todo-list-footer',
  templateUrl: './todo-list-footer.component.html',
  styleUrls: ['./todo-list-footer.component.css']
})
export class TodoListFooterComponent {

  @Input()
  todos: Todo[];
  
  @Input()
  completedCount:number;

  constructor() {
   //this.completedCount=this.todos.filter((todo: Todo) => todo.completed === true).length;;
  }

  
}
