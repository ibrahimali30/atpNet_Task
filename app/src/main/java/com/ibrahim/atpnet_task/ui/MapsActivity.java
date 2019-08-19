package com.ibrahim.atpnet_task.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibrahim.atpnet_task.R;
import com.ibrahim.atpnet_task.data.model.MetroStation;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private MapsViewModel mViewModel ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        mViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);

        mViewModel.getMetroStationsLiveData().observe(this, new Observer<List<MetroStation>>() {
            @Override
            public void onChanged(List<MetroStation> metroStations) {
                Log.d(TAG, "onChanged: "+metroStations);
                placeMetroMerks(metroStations);
            }
        });

    }

    private void placeMetroMerks(List<MetroStation> rows) {
        if (mMap == null) return;

        mMap.clear();

        //resize marker
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker );
        Bitmap markerBitmap = Bitmap.createScaledBitmap(bitmap, 100 , 100 , false);

        for (MetroStation metroStation : rows){

            LatLng latLng = parseLocation(metroStation.getDestination_Long_Lat());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
                    .title(metroStation.getTitle())
            );
        }
    }


    private LatLng parseLocation(String[] destination_long_lat) {
        //provided api provide location in an array format like ["31.216,32.515"]
        String[] lat_long_arrey = destination_long_lat[0].split(",");
        double latitude = Double.parseDouble(lat_long_arrey[0]);
        double longitude = Double.parseDouble(lat_long_arrey[1]);
        return new LatLng(latitude , longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng middle = new LatLng(30.07, 31.28);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(middle , 12));
    }
}
