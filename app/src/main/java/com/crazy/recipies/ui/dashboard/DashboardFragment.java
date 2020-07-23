package com.crazy.recipies.ui.dashboard;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.crazy.recipies.OptionActivity;
import com.crazy.recipies.R;
import com.crazy.recipies.RecyclerViewClickInt;
import com.crazy.recipies.ui.home.Dish;
import com.crazy.recipies.ui.home.DishAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class DashboardFragment extends Fragment implements RecyclerViewClickInt, OnMapReadyCallback{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int NUM_COLUMNS = 1;
    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    private GoogleMap googleMap;
    private LatLng latLng;

    ArrayList<Dish> exampleList = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapView, mapFragment).commit();
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    Log.e("location", String.valueOf(latLng));
                    String myLatidude = String.valueOf(location.getAltitude());
                    String myLongitude = String.valueOf(location.getLongitude());

                    String message = "Latitude = " + myLatidude + "Longitude = " + myLongitude;
                    SmsManager smsManager1 = SmsManager.getDefault();
//                    smsManager1.sendTextMessage(phoneNumber1,null,message,null,null);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (mapFragment != null)
        {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

//                      -> marker_latlng // MAKE THIS WHATEVER YOU WANT

                        latLng = new LatLng(-34, 151);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                    }

                }
            });
        }
        exampleList.add(new Dish(R.drawable.egg, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 5", "PAPA 69"));
        exampleList.add(new Dish(R.drawable.chicken, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "HABIB"));
        exampleList.add(new Dish(R.drawable.dosa, "non on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "KHALI DHABA"));
        exampleList.add(new Dish(R.drawable.egg, "Lineguukjb 5 on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "KULLU MANALI"));
        exampleList.add(new Dish(R.drawable.chicken, "kalalallla on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "SIGNA"));
        exampleList.add(new Dish(R.drawable.dosa, "palakkka on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "LALLANTOP"));
        exampleList.add(new Dish(R.drawable.egg, "Line on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "MAKE MY DAY"));
        exampleList.add(new Dish(R.drawable.chicken, "Lidajrm, ne on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ...", "ALL IS MINE"));
        exampleList.add(new Dish(R.drawable.dosa, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 3", "WHAT THE HELL 4"));
        exampleList.add(new Dish(R.drawable.egg, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 5", "SIGNAL"));
        exampleList.add(new Dish(R.drawable.chicken, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 1", "JADUU 2"));
        exampleList.add(new Dish(R.drawable.dosa, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 3", "TERE 4"));
        exampleList.add(new Dish(R.drawable.egg, "on tuhti tietopaketti Rauman satamasta ja logistiikka-alan erityisohjeista. Tuoreimpana kirjaan on lisätty huolinta-alan yleiset määräykset eli PSYM-ehdot, jotka ... 5", "PAPA 69"));

        mRecyclerView = root.findViewById(R.id.non_dish);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new DishAdapter(exampleList,DashboardFragment.this);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onItemClick(int position) {
        Intent i=new Intent(getContext(), OptionActivity.class);
        i.putExtra("image",exampleList.get(position).getImageResource());
        i.putExtra("textmain",exampleList.get(position).getText2());
        i.putExtra("discription",exampleList.get(position).getText1());
        startActivity(i);
    }

    @Override
    public void onItemLongClick(int position) {

        Toast.makeText(getContext(), "clicked"+exampleList.get(position).getImageResource(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME,MIN_DIST,locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
}