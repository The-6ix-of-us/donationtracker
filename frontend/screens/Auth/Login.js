import React, { Component } from 'react';
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

import api from '../../api';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      error: false,
    };
    this.onPressRegister = this.onPressRegister.bind(this);
    this.onPressLogin = this.onPressLogin.bind(this);
  }

  onPressLogin() {
    api.post('/users/login', {
      email: this.state.email,
      password: this.state.password,
    }).then((response) => {
      console.log("response: ", response);
      if (response.json().userCredential) {
        this.props.navigation.navigate('Main');
      } else {
        this.passwordInput.shake();
        this.setState({ error: true });
        console.log(response);
      }
    });
  }

  onPressRegister() {
    this.props.navigation.navigate('Registration');
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.content}>
          <FormLabel>Username</FormLabel>
          <FormInput onChangeText={(e) => {
            this.setState({ email: e });
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
