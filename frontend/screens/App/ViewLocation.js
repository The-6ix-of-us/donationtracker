import React, { Component } from 'react';

import { View } from 'react-native';
import { Text } from 'react-native-elements';

import AppHeader from '../../components/AppHeader';

class ViewLocation extends Component {
  render() {
    const location = this.props.navigation.getParam('location', {});

    return (
      <View>
        <AppHeader {...this.props} />
        <Text>Location Name: {location['Name']}</Text>
        <Text>Coordinates: {location['Coordinates']['_lat']}, {location['Coordinates']['_long']}</Text>
        <Text>Phone: {location['Phone']}</Text>
        <Text>Address: {location['State']}</Text>
        <Text>State: {location['State']}</Text>
        <Text>Zip: {location['Zip']}</Text>
        <Text>Type: {location['Type']}</Text>
        <Text>Website: {location['Website']}</Text>
      </View>
    );
  }
}

export default ViewLocation;
