import {Role} from './role';
export interface User {
    id: number;
    userName?: string;
    password: string;
    firstName: string;
    lastName: string;
    email:string;
    roles :Role[];
}
