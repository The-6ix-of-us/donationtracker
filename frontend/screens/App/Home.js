import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';

import AppHeader from '../../components/AppHeader';

class Home extends Component {
  static navigationOptions = {
    title: 'Donation Tracker',
  };
  render() {
    return (
      <View>
        <AppHeader {...this.props}/>
        <View style={styles.container}>
          <Text>Home</Text>
        </View>
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
