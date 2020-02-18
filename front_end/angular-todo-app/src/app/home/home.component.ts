import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '.././todo-data.service';
import { Todo } from '.././todo';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
 // styleUrls: ['./home.component.css'],
  providers:[TodoDataService]
})
export class HomeComponent implements OnInit {

  todos: Todo[] = [];
  completedCount:number;
    constructor(
      private todoDataService: TodoDataService
    ) {
    }
  
    getCompleteCount(){
      this.completedCount= this.todos.filter((t) => t.completed===true).length;
    }
  
    public ngOnInit() {
      this.todoDataService
        .getAllTodos()
        .subscribe(
          (response) => {
            console.log(response.status);
            this.todos = response.body;
            this.getCompleteCount();
          }
        );
        //this.getCompleteCount();
    }
  
    onAddTodo(todo) {
      this.todoDataService
        .addTodo(todo)
        .subscribe(
          (newTodo) => {
            this.todos = this.todos.concat(newTodo);
          }
        );
        this.getCompleteCount();
    }
  
    onToggleTodoComplete(todo) {
      this.todoDataService
        .toggleTodoComplete(todo)
        .subscribe(
          (updatedTodo) => {
            todo = updatedTodo;
          }
        );
        this.getCompleteCount();
    }
  
    onRemoveTodo(todo) {
      this.todoDataService
        .deleteTodoById(todo.id)
        .subscribe(
          (_) => {
            this.todos = this.todos.filter((t) => t.id !== todo.id);
          }
        );
        this.getCompleteCount();
    }
    onToggleAll(checkAll){
      this.todos.forEach((t: Todo) => t.completed = checkAll);
      this.todoDataService
      .toggleTodoCompleteAll(checkAll)
      .subscribe(
        (updatedTodo) => {
          checkAll = updatedTodo;
        }
      );
      this.getCompleteCount();
    }
}
