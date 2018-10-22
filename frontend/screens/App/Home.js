import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { ListItem } from 'react-native-elements';

import api from '../../api';

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
    this.setState({ locations: api.get('/fetch_locations') });
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
