import React, { Component } from 'react';
import {
  Header,
  Text
} from 'react-native-elements';
import Icon from 'react-native-vector-icons/FontAwesome';

const AppHeader = (props) => (
  <Header
    leftComponent={<DrawerMenu {...props} />}
    centerComponent={<Title />}
    style={{ backgroundColor: "#4573bc"}}
  />
);

const DrawerMenu = (props) => (
  <Icon name="bars" style={{color: "#fff", fontSize: 20}} onPress={() => props.navigation.openDrawer()} />
);

const Title = () => (
  <Text h5 style={{color: '#fff', fontWeight: 'bold'}}>DONATION TRACKER</Text>
);

export default AppHeader;
