import React, { Component } from 'react';
import {
  Picker,
  View,
  Text,
  TextInput,
  StyleSheet,
  TouchableOpacity
} from 'react-native';

import {
  Button,
  FormLabel,
  FormInput
} from 'react-native-elements';

class Registration extends Component {
  static navigationOptions = {
    title: 'Register',
  };
  render() {
    return (
      <View className="container">
        {/* <FormLabel>Display Name</FormLabel>
        <FormInput /> */}
        <FormLabel>Username</FormLabel>
        <FormInput />
        <FormLabel>Password></FormLabel>
        <FormInput secureTextEntry={true} />
        {/* <Picker>
          <Picker.Item label="Admin" />
          <Picker.Item label="Location Employee" />
          <Picker.Item label="User" />
        </Picker> */}
        <Button raised title="Register" />
        <TouchableOpacity onPress={() => this.props.navigation.navigate('Login')}>
          <FormLabel>Login here</FormLabel>
        </TouchableOpacity>
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

export default Registration;
