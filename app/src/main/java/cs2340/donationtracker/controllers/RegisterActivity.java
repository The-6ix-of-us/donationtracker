package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cs2340.donationtracker.model.User;
import cs2340.donationtracker.R;

/**
 * Created by Peter Franzek
 *
 * Creates the Register activity and checks for valid inputs into username and password
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);

        Button register = findViewById(R.id.username_register_in_button);
        register.setOnClickListener(v -> attemptRegister());

        Button cancelSignIn = findViewById(R.id.cancel_button);
        cancelSignIn.setOnClickListener(v -> startActivity(
                new Intent(RegisterActivity.this, HomeActivity.class)));

    }

    private void attemptRegister() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the register attempt.
        Editable _username = mUsernameView.getText();
        Editable _password = mPasswordView.getText();
        String username = _username.toString();
        String password = _password.toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        // Check for a valid username
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (password.length() < 4) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            User user = new User(username, password);
            LoginActivity.credentials.addUser(user);
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
