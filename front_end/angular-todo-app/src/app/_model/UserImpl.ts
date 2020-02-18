import { User } from './user';
import {Role} from './role';
export class userimpl implements User
{  id: number;
    userName:string;
    password: string;
    firstName: string;
    lastName: string;
    email:string;
    roles :Role[];



    constructor(values: Object = {}) {
        Object.assign(this, values);
      }
}