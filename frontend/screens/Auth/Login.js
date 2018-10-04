import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  TouchableOpacity
} from 'react-native';

import {
  Button,
  FormLabel,
  FormInput
} from 'react-native-elements';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: ""
    }
    this.onPressLogin = this.onPressLogin.bind(this);
  }

  onPressLogin() {
    if (this.state.username === "User" && this.state.password === "pass") {
      this.props.navigation.navigate('Main');
    }
  }

  render() {
    return (
      <View style={styles.container}>
      <View style={styles.content}>
        <FormLabel>Username</FormLabel>
        <FormInput onChangeText={(e) => {
          this.setState({username: e})
        }}/>
        <FormLabel>Password</FormLabel>
        <FormInput secureTextEntry={true} onChangeText={(e) => this.setState({password: e})} />
        <Button raised title="Login" onPress={this.onPressLogin}/>
        <TouchableOpacity onPress={() => this.props.navigation.navigate('Register')}>
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
