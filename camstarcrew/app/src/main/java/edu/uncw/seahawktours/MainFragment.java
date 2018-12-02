package edu.uncw.seahawktours;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

import static android.content.Context.LOCATION_SERVICE;

public class MainFragment extends Fragment {

    private FragmentActivity myContext;
    private Box<BuildingInfo> buildingBox;
    private Query<BuildingInfo> buildingQuery;
    CaptionedImagesAdapter adapter;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String currentLocation;


    public MainFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        CoordinatorLayout buildingCoord = (CoordinatorLayout) inflater.inflate(R.layout.fragment_building, container, false);
        final RecyclerView buildingRecycler = buildingCoord.findViewById(R.id.building_recycler);

        BoxStore boxStore = App.getBoxStore();
        buildingBox = boxStore.boxFor(BuildingInfo.class);

        buildingQuery = buildingBox.query().build();

        locationManager = (LocationManager) this.getContext().getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location: ", location.toString());
                currentLocation = location.getLatitude() + "," + location.getLongitude();
                updateBuildings(buildingRecycler, currentLocation);
                //if in geofence, return new intent of the position on the list
                //update the cardviews
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

        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
        } else {

            if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                currentLocation = location.getLatitude() + "," + location.getLongitude();
            }
        }

        updateBuildings(buildingRecycler, currentLocation);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        buildingRecycler.setLayoutManager(layoutManager);

        return buildingCoord;

    }


    private void updateBuildings(RecyclerView view, String currentLocation) {

        List<BuildingInfo> buildings = buildingQuery.find();
        String[] buildingNames = new String[buildings.size()];
        for (int i = 0; i < buildingNames.length; i++) {
            buildingNames[i] = buildings.get(i).getName();
        }

        String[] buildingImages = new String[buildings.size()];
        for (int i = 0; i < buildingImages.length; i++) {
            buildingImages[i] = buildings.get(i).getPicture();
        }

        String[] buildingLocations = new String[buildings.size()];
        for (int i = 0; i < buildingLocations.length; i++) {
            buildingLocations[i] = buildings.get(i).getCoordinates();
        }
        adapter = new CaptionedImagesAdapter(buildingNames, buildingImages, buildingLocations, currentLocation);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.setType("text/plain");
                intent.putExtra(DetailActivity.BUILDING_CHOSEN, position);
                getActivity().startActivity(intent);
            }
        });
        view.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
            }
        }
    }

}
