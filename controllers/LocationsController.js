const firebase = require('../firebaseConfig');

const addLocation = (location) => {
  const coordinates = new firebase.firestore.GeoPoint(parseFloat(location['Latitude']), parseFloat(location['Longitude']));

  firebase.db.collection('location-data').add({
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

const fetchLocations = () => {
  let list = [];
  firebase.db.collection('location-data').get().then((snapshot) => {
    snapshot.forEach((doc, i) => {
      list.push(doc.data());
    });
  }).then(() => {
    return list;
  });
};

const editLocation = (location) => {

};

const removeLocation = (location) => {

};

module.exports = {
  addLocation,
  fetchLocations,
};
