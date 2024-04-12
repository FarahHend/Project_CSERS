import { Injectable } from '@angular/core';
import { AngularFireMessaging } from '@angular/fire/messaging';
import { BehaviorSubject } from 'rxjs';



@Injectable()
export class MessagingService {
  private notificationSubject = new BehaviorSubject<{ title: string, body: string }>(null);

  currentMessage = new BehaviorSubject(null);
  currentToken = new BehaviorSubject(null); 

  constructor(private angularFireMessaging: AngularFireMessaging) {
    this.angularFireMessaging.messaging.subscribe(
      (_messaging) => {
        _messaging.onMessage = _messaging.onMessage.bind(_messaging);
        _messaging.onTokenRefresh = _messaging.onTokenRefresh.bind(_messaging);
      }
    );
  }

  requestPermission() {
    Notification.requestPermission().then((permission) => {
      if (permission === 'granted') {
        this.getToken(); 
      } else {
        console.warn('Permission for notifications was denied');
      }
    });
  }

  private getToken() {
    this.angularFireMessaging.requestToken.subscribe(
      (token) => {
        if (token) {
          console.log("Permission granted. Token:", token);
          this.currentToken.next(token); 
        } else {
          console.log("No token received.");
        }
      },
      (err) => {
        console.error('Unable to get permission to notify.', err);
      }
    );
  }

  receiveMessage() {
    this.angularFireMessaging.messages.subscribe(
      (payload) => {
        console.log("New message received. Payload:", payload);
        this.currentMessage.next(payload);
      }
    );
  }
}
