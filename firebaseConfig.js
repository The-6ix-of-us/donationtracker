const firebase = require('firebase');
require('firebase/firestore');

const config = {
  apiKey: 'AIzaSyC7qDP0nGFZaA-rnvRT_hLdz4MgZGaILVE',
  authDomain: 'the-6ix-of-us.firebaseapp.com',
  databaseURL: 'https://the-6ix-of-us.firebaseio.com',
  projectId: 'the-6ix-of-us',
  storageBucket: 'the-6ix-of-us.appspot.com',
  messagingSenderId: '662111099293'
};

firebase.initializeApp(config);

const settings = {
  timestampsInSnapshots: true,
};

const db = firebase.firestore();
db.settings(settings);

module.exports = {
  firebase,
  db,
};
