var express = require('express');
var router = express.Router();

const firebase = require('../../firebaseConfig');

/* GET users listing. */

router.post('/login', (req, res) => {
  console.log(req);
  firebase.firebase.auth().signInWithEmailAndPassword(req.body.email, req.body.password)
    .then((userCredential) => {
      console.log(userCredential);
      res.send({
        userCredential
      });
    })
    .catch((error) => {
      console.log(error);
      res.send({
        error
      });
    });
});

router.post('/register', (req, res) => {
  firebase.firebase.auth().createUserWithEmailAndPassword(res.userInfo.email, res.userInfo.password)
    .then((userCredential) => {
      firebase.db.collection('users').doc(userCredential.user.uid).set({
        email: userCredential.user.email,
        'user-type': res.userInfo.userType,
      });
      res.send({
        userCredential,
      });
    })
    .catch((error) => {
      res.send({
        error,
      });
    });
});

module.exports = router;
