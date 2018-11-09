package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs2340.donationtracker.model.Location;

import java.util.ArrayList;

import cs2340.donationtracker.R;

/**
 * Created by Emily Wilson
 *
 * Creates the map activity which shows a map of the donation centers
 * indicated by red markers
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        locations = intent.getParcelableArrayListExtra("locations");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Location loc : locations) {
            LatLng coordinates = new LatLng(loc.getLatitude(), loc.getLongitude());
            MarkerOptions options = new MarkerOptions().position(coordinates).title(loc.getName()).snippet(loc.getPhone());
            googleMap.addMarker(options);
            builder.include(coordinates);
        }

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 250);
        googleMap.moveCamera(cu);
    }
}
