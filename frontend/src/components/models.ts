import { EventBus } from 'quasar';
export const bus = new EventBus();
export interface Todo {
  id: number;
  content: string;
}

export interface Meta {
  totalCount: number;
}

export interface Yacht {
  id: number;
  name: string;
  typeId: number;
  price: number;
  crew: string;
  description: string;
  sleepingCapacity: number;
  cruiseCapacity: number;
  imagePath: string;
}

export interface User {
  fullName: string;
  username: string;
  roles: Array<string>;
}
