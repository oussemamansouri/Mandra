import { Injectable } from '@angular/core';
import { User } from 'src/app/Shared/user';

const USER_KEY = 'authenticated-user'; // Key used for storing user in localStorage

@Injectable({
  providedIn: 'root'
})
export class StorageServiceService {

  constructor() { }

   // Save user object to localStorage
   saveUser(user: User) {
    window.localStorage.removeItem(USER_KEY); // Remove any existing user data
    window.localStorage.setItem(USER_KEY, JSON.stringify(user)); // Store user object as a JSON string
  }

  // Retrieve user object from localStorage
  getSavedUser(): User | null {
    const user = window.localStorage.getItem(USER_KEY); // Get user data from localStorage
    if (user) {
      return JSON.parse(user); // Parse JSON string back to User object
    }
    return null; // Return null if no user data found
  }

  // Clear all data from localStorage
  clean(): void {
    window.localStorage.clear(); // Clear all items from localStorage
  }
}
