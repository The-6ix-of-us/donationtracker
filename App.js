import React from 'react';
import { createStackNavigator, createDrawerNavigator } from 'react-navigation';
import { StyleSheet, Text, View } from 'react-native';
import Icon from 'react-native-vector-icons';

import Home from './frontend/screens/App/Home';

import Login from './frontend/screens/Auth/Login';
import Registration from './frontend/screens/Auth/Registration';

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
