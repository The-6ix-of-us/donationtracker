package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import cs2340.donationtracker.R;

/**
 * Created by Peter Franzek
 *
 * Creates the Home activity which shows the register and login buttons
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register_button);

        login.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,
                LoginActivity.class)));

        register.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,
                RegisterActivity.class)));
    }

}
