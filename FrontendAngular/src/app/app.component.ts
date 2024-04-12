import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NotificationService } from '../core/service/NotificationService';
import { MessagingService } from '../core/service/messaging.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit{
  title = 'angular-dashboard-page';
  message;
  //notificationSubscription: Subscription;


  constructor(private messagingService: MessagingService, private notificationService: NotificationService) { }

  ngOnInit() {
    this.messagingService.receiveMessage();
    this.message = this.messagingService.currentMessage;
   
  }

  enableNotifications() {
    this.messagingService.requestPermission();
  }

  sendTestNotification() {
    
    const deviceToken = 'YOUR_DEVICE_TOKEN';
    const title = 'Incident';
    const body = 'This is a test notification ';
    
    this.notificationService.sendNotification(deviceToken, title, body)
      .subscribe(response => {
        console.log('Notification sent successfully:', response);
      }, error => {
        console.error('Error sending notification:', error);
      });
  }

  
}
