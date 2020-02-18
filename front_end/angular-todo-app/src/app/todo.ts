export class Todo {
  id: number;
  name = '';
  completed = false;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
