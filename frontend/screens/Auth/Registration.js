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

import firebase, { db } from '../../../firebaseConfig';

class Registration extends Component {
  static navigationOptions = {
    title: 'Register',
  };

  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: '',
      userType: 'User',
    }

    this.onPressRegister = this.onPressRegister.bind(this);
  }

  onPressRegister() {
    firebase.auth().createUserWithEmailAndPassword(this.state.email, this.state.password)
    .then((userCredential) => {
      db.collection('users').doc(userCredential.user.uid).set({
        email: userCredential.user.email,
        'user-type': this.state.userType,
      });
      this.props.navigation.navigate('Main');
    })
    .catch((error) => {
      // Handle Errors here.
      this.email_input.shake();
      console.log(error);
      var errorCode = error.code;
      var errorMessage = error.message;
    });
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.content}>
          {/* <FormLabel>Display Name</FormLabel>
          <FormInput /> */}
          <FormLabel>Email</FormLabel>
          <FormInput onChangeText={e => this.setState({ email: e })} ref={ref => this.email_input = ref}/>
          <FormLabel>Password</FormLabel>
          <FormInput secureTextEntry={true} onChangeText={e => this.setState({ password: e })}/>
          <Picker selectedValue={this.state.userType} onValueChange={value => this.setState({ userType: value })}>
            <Picker.Item label="Admin" value="Admin" />
            <Picker.Item label="Location Employee" value="Location Employee"/>
            <Picker.Item label="User" value="User" />
          </Picker>
          <Button raised title="Register" onPress={this.onPressRegister} />
          <TouchableOpacity onPress={() => this.props.navigation.navigate('Login')}>
            <FormLabel>Login here</FormLabel>
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    justifyContent: 'center',
  },
  content: {
    backgroundColor: '#fff'
  }
});

export default Registration;
