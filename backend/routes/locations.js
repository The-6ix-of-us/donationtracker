var express = require('express');
var router = express.Router();

const firebase = require('../../firebaseConfig');

/* GET users listing. */
router.get('/', function(req, res, next) {
  let list = [];
  firebase.db.collection('location-data').get().then((snapshot) => {
    snapshot.forEach((doc, i) => {
      list.push(doc.data());
    });
  }).then(() => {
    res.send(list);
  });
});

router.post('/', (req, res) => {
  const coordinates = new firebase.firestore.GeoPoint(parseFloat(location['Latitude']), parseFloat(location['Longitude']));

  firebase.db.collection('location-data').add({
    'Name': res.location['Name'],
    'Coordinates': res.coordinates,
    'Phone': res.location['Phone'],
    'State': res.location['State'],
    'Street Address': res.location['Street Address'],
    'Type': res.location['Type'],
    'Website': res.location['Website'],
    'Zip': res.location['Zip'],
  });
});

module.exports = router;
