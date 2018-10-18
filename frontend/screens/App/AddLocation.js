import React, { Component } from 'react';
import { View, ScrollView } from 'react-native';
import { Button, FormInput, FormLabel } from 'react-native-elements';

import AppHeader from '../../components/AppHeader';

import { addLocation } from '../../../controllers/LocationsController';

class AddLocation extends Component {
  constructor(props) {
    super(props);

    this.state = {
      name: '',
      latitude: '',
      longitude: '',
      address: '',
      city: '',
      state: '',
      zip: '',
      type: '',
      website: '',
      phone: '',
    };

    this.onPressAddLocation = this.onPressAddLocation.bind(this);
  }

  onPressAddLocation() {
    const location = {
      'Name': this.state.name,
      'Latitude': this.state.latitude,
      'Longitude': this.state.longitude,
      'Street Address': this.state.address,
      'City': this.state.city,
      'State': this.state.state,
      'Zip': this.state.zip,
      'Website': this.state.website,
      'Type': this.state.type,
      'Phone': this.state.phone,
    };
    addLocation(location);
    this.props.navigation.navigate('Home');
  }

  render() {
    return (
      <View>
        <AppHeader {...this.props} />
        <ScrollView>
          <FormLabel>Location Name</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ name: e })}/>
          <FormLabel>Latitude</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ latitude: e })}/>
          <FormLabel>Longitude</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ longitude: e })}/>
          <FormLabel>Address</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ address: e })}/>
          <FormLabel>City</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ city: e })}/>
          <FormLabel>State</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ state: e })}/>
          <FormLabel>Zip</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ zip: e })}/>
          <FormLabel>Type</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ type: e })}/>
          <FormLabel>Website</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ website: e })}/>
          <FormLabel>Phone</FormLabel>
          <FormInput onChangeText={(e) => this.setState({ phone: e })}/>
        </ScrollView>
        <Button title="ADD LOCATION" onPress={this.onPressAddLocation}/>
      </View>
    );
  }
}

export default AddLocation;
