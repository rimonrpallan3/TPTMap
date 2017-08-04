package com.voyager.user.myapplication;

import android.content.pm.PackageManager;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap googleMap;

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    private String TAG = "";


    private PlaceAutocompleteFragment autocompleteFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }


    public void initialize() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        setUpMap();
        setUpSearchBar();
    }

    public void setUpSearchBar(){

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    public void setUpMap() {

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // Add some markers to the map, and add a data object to each marker.
        mPerth = googleMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Perth"));
        mPerth.setTag(0);
             CameraUpdate center=
                CameraUpdateFactory.newLatLng(PERTH);
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

        mSydney = googleMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Sydney"));
        mSydney.setTag(0);

        mBrisbane = googleMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Brisbane"));
        mBrisbane.setTag(0);

        // Set a listener for marker click.
        googleMap.setOnMarkerClickListener(this);
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    /*    CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044,
                        -73.98180484771729));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);*/
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

            // Retrieve the data from the marker.
            Integer clickCount = (Integer) marker.getTag();

            // Check if a click count was set, then display the click count.
            if (clickCount != null) {
                clickCount = clickCount + 1;
                marker.setTag(clickCount);
                Toast.makeText(this,
                        marker.getTitle() +
                                " has been clicked " + clickCount + " times.",
                        Toast.LENGTH_SHORT).show();
            }

            // Return false to indicate that we have not consumed the event and that we wish
            // for the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
