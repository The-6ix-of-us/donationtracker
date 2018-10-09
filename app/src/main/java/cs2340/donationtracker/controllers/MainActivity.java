package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cancelSignIn = findViewById(R.id.cancel);
        cancelSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
            readLocationData();
    }

    //This method parses the CSV file and creates an array of locations

    private void readLocationData() {
        LocationModel locations = LocationModel.INSTANCE;

         Location location;
         InputStream input = getResources().openRawResource(R.raw.location_data);

         BufferedReader reader = new BufferedReader(
                 new InputStreamReader(input, Charset.forName("UTF-8"))
         );

         String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                //reading data

                location = new Location(tokens);

                locations.add(location);

                Log.d("MyActivity", "Just created;" + location);


            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading Location data");
            e.printStackTrace();
        }


    }


}
