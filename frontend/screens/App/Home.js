import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { ListItem } from 'react-native-elements';
import 'firebase/firestore';

import firebase, { db } from '../../../firebaseConfig';
import AppHeader from '../../components/AppHeader';

import LocationsList from './LocationsList';

const list = [];

class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      locations: [],
    };

    this.getLocations = this.getLocations.bind(this);
  }

  componentDidMount() {
    this.getLocations();
  }

  /* TODO: put this somewhere else */
  getLocations() {
    let list = [];
    db.collection('location-data').get().then((snapshot) => {
      snapshot.forEach((doc, i) => {
        list.push(doc.data());
      });
    }).then(() => {
      this.setState({ locations: list });
    });
  }

  render() {
    return (
      <View>
        <AppHeader {...this.props} />
        <LocationsList navigation={this.props.navigation} locations={this.state.locations}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default Home;
