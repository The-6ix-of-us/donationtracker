import React from 'react';
import { createStackNavigator, createDrawerNavigator } from 'react-navigation';
import { StyleSheet, Text, View } from 'react-native';
import firebase from 'firebase';
import Icon from 'react-native-vector-icons';

import Home from './frontend/screens/App/Home';

import Login from './frontend/screens/Auth/Login';
import Registration from './frontend/screens/Auth/Registration';

// Setup firebase
var config = {
  apiKey: "AIzaSyC7qDP0nGFZaA-rnvRT_hLdz4MgZGaILVE",
  authDomain: "the-6ix-of-us.firebaseapp.com",
  databaseURL: "https://the-6ix-of-us.firebaseio.com",
  projectId: "the-6ix-of-us",
  storageBucket: "the-6ix-of-us.appspot.com",
  messagingSenderId: "662111099293"
};

firebase.initializeApp(config);

const Auth = createStackNavigator({
  Login: {
    screen: Login,
    navigationOptions: {
      title: 'Login'
    }
  },
  Registration: {
    screen: Registration,
    navigationOptions: {
      title: 'Register'
    }
  }
}, {
  initialRoute: 'Login'
});

const Main = createDrawerNavigator({
  Home: {
    screen: Home,
    navigationOptions: {
      drawerLabel: 'Home'
    }
  },
  Logout: {
    screen: Auth,
    navigationOptions: {
      drawerLabel: 'Log Out'
    }
  }
})

const App = createStackNavigator({
  Auth: { screen: Auth },
  Main: { screen: Main }
}, {
  headerMode: 'none'
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default App;
