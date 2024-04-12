importScripts('https://www.gstatic.com/firebasejs/7.24.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.24.0/firebase-messaging.js');

firebase.initializeApp({
    apiKey: "AIzaSyCIjX-kSCM7J9vx3_G84m3XFsWy_I3Mtzw",
    authDomain: "csersnotification.firebaseapp.com",
    projectId: "csersnotification",
    storageBucket: "csersnotification.appspot.com",
    messagingSenderId: "419007010289",
    appId: "1:419007010289:web:5e373deb6c9a0bba306050"
});

const messaging = firebase.messaging();
