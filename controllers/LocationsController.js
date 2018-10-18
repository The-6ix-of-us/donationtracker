import firebase, { db } from '../firebaseConfig';

export const addLocation = (location) => {
  const coordinates = new firebase.firestore.GeoPoint(parseFloat(location['Latitude']), parseFloat(location['Longitude']));

  db.collection('location-data').add({
    'Name': location['Name'],
    'Coordinates': coordinates,
    'Phone': location['Phone'],
    'State': location['State'],
    'Street Address': location['Street Address'],
    'Type': location['Type'],
    'Website': location['Website'],
    'Zip': location['Zip'],
  });
};

export const editLocation = (location) => {

};

export const removeLocation = (location) => {

};
