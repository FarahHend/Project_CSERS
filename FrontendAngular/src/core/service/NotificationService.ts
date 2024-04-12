import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private fcmUrl = 'https://fcm.googleapis.com/fcm/send';
  private serverKey = 'AAAAYY7DlfE:APA91bGOL6cXqF28fnrtKNe6K_l5JxV7X5GZdbMeCxufNuLPS6cbwykUvgmBCDATsLtg0LKiDXMI-2zZ47fMVEDUjPABlp0FTttIBx9vochPJzl47aUNIEAYi-00b_0yTfX9g_NhKohK';
  private DeviceToken = 'eZHceAFiyO-6mORb5hXOoe:APA91bG1u9ki7TJ6zo-IxGhXQROrzOM0C3efjr9pYaurI8Rd-LaAzB5DT1AV6r0A4RWhxwBQgXryZVBvIeTDcvtJ5mvqztFxU-GxE1-D7VGrtQyAUpOIfqc-T7jeyY571U_GzNJtf0By';
  constructor(private http: HttpClient) { }

  sendNotification(deviceToken: string, title: string, body: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `key=${this.serverKey}`
    });

    const payload = {
      to:'eZHceAFiyO-6mORb5hXOoe:APA91bG1u9ki7TJ6zo-IxGhXQROrzOM0C3efjr9pYaurI8Rd-LaAzB5DT1AV6r0A4RWhxwBQgXryZVBvIeTDcvtJ5mvqztFxU-GxE1-D7VGrtQyAUpOIfqc-T7jeyY571U_GzNJtf0By',
      notification: {
        title,
        body
      }
    };

    return this.http.post(this.fcmUrl, payload, { headers });
  }
}
