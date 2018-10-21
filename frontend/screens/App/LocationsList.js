import React, { Component } from 'react';
import {View, StyleSheet, Text, FlatList } from 'react-native';
import { Button, ListItem } from 'react-native-elements';

class LocationsList extends Component {
  constructor(props) {
    super(props);
    this.renderListItem = this.renderListItem.bind(this);
  }

  renderListItem(item) {
    return (
      <ListItem key={item.key} title={item['Name']} onPress={() => this.props.navigation.navigate('ViewLocation', {
        location: item
      })} />
    );
  }
  render() {
    return (
      <View>
        <FlatList data={this.props.locations} renderItem={({item}) => this.renderListItem(item)} />
        <Button title="ADD LOCATION" onPress={() => this.props.navigation.navigate('AddLocation')}/>
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

export default LocationsList;
