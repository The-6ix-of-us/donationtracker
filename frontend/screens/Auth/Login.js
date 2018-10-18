import React, { Component } from 'react';
import firebase from 'firebase';
import {
  StyleSheet,
  View,
  TouchableOpacity
} from 'react-native';

import {
  Button,
  FormLabel,
  FormInput,
  FormValidationMessage,
} from 'react-native-elements';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: false,
    };
    this.onPressRegister = this.onPressRegister.bind(this);
    this.onPressLogin = this.onPressLogin.bind(this);
  }

  onPressLogin() {
    // TODO: implement signInRequest, signInSuccess, signInFailure
    // this.props.signInRequest();
    firebase.auth().signInWithEmailAndPassword(this.state.username, this.state.password)
      .then((userCredential) => {
        // this.props.signInSuccess(userCredential.user);
        this.props.navigation.navigate('Main');
      })
      .catch((error) => {
        // TODO: add a cool alert thing here
        this.setState({ error: true })
        this.passwordInput.shake();
        // this.props.signInFailure(error);
      });
  }

  onPressRegister() {
    this.props.navigation.navigate('Register');
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.content}>
          <FormLabel>Username</FormLabel>
          <FormInput onChangeText={(e) => {
            this.setState({ username: e });
          }}/>
          <FormLabel>Password</FormLabel>
          <FormInput secureTextEntry={true} onChangeText={
            (e) => this.setState({ password: e })
          } ref={ref => this.passwordInput = ref} />
          {this.state.error && <FormValidationMessage>Incorrect email or password</FormValidationMessage>}
          <Button raised title="Login" onPress={this.onPressLogin}/>
          <TouchableOpacity onPress={this.onPressRegister}>
            <FormLabel>Register here</FormLabel>
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container2: {
    flex: 1,
    flexDirection: 'row',
    paddingLeft: 20,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  container: {
    flex: 1,
    backgroundColor: '#fff',
    justifyContent: 'center',
  },
  content: {
    backgroundColor: '#fff'
  }
});

export default Login;
