const firebase = require('../firebaseConfig');

const login = (email, password) => {
  firebase.auth().signInWithEmailAndPassword(email, password)
    .then((userCredential) => {
      return {
        userCredential
      };
    })
    .catch((error) => {
      return {
        error
      };
    });
};

const register = (userInfo) => {
  firebase.auth().createUserWithEmailAndPassword(userInfo.email, userInfo.password)
    .then((userCredential) => {
      firebase.db.collection('users').doc(userCredential.user.uid).set({
        email: userCredential.user.email,
        'user-type': userInfo.userType,
      });
      return {
        userCredential,
      };
    })
    .catch((error) => {
      console.log(error);
      return {
        error,
      };
    });
};

module.exports = {
  login,
  register,
};
