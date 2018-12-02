package edu.uncw.seahawktours;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

import java.util.List;

public class MainFragment extends Fragment {

//    private boolean isFirstLoad = true;
    private FragmentActivity myContext;
    private Box<BuildingInfo> buildingBox;
    private Query<BuildingInfo> buildingQuery;
    CaptionedImagesAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        CoordinatorLayout buildingCoord = (CoordinatorLayout) inflater.inflate(R.layout.fragment_building, container, false);
        RecyclerView buildingRecycler = buildingCoord.findViewById(R.id.building_recycler);

        //insert some DB stuff later for this!
//        String[] buildingNames = new String[Building.buildings.length];
//        for (int i = 0; i < buildingNames.length; i++) {
//            buildingNames[i] = Building.buildings[i].getName();
//
//        }
//
//        int[] buildingImages = new int[Building.buildings.length];
//        for (int i = 0; i < buildingImages.length; i++) {
//            buildingImages[i] = Building.buildings[i].getImageResourceId();
//        }

        BoxStore boxStore = App.getBoxStore();
        buildingBox = boxStore.boxFor(BuildingInfo.class);

        buildingQuery = buildingBox.query().build();
        updateBuildings();


      //  CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(buildingNames, buildingImages);
        buildingRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        buildingRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.setType("text/plain");
                intent.putExtra(DetailActivity.BUILDING_CHOSEN, position); //have to do this to account for the first position
                getActivity().startActivity(intent);
            }
        });

        return buildingCoord;

//        String[] items = getActivity().getResources().getStringArray(R.array.campus_buildings);
//        final View v = inflater.inflate(R.layout.fragment_main, container, false);
//        Spinner campusSpins = v.findViewById(R.id.building_spinner);
//
//        ArrayAdapter<String> buildingAdapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item, items);
//        campusSpins.setAdapter(buildingAdapter);
//
//        campusSpins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    if (!isFirstLoad) {
//                        View fragmentContainer = myContext.findViewById(R.id.fragment_container);
//                        if (fragmentContainer != null) {
//                            DetailFragment details = new DetailFragment();
//                            FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
//                            details.setBuildingId(position - 1);
//                            ft.replace(R.id.fragment_container, details);
//                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                            ft.addToBackStack(null);
//                            ft.commit();
//                        } else {
//                            Intent intent = new Intent(getActivity(), DetailActivity.class);
//
//                            intent.setType("text/plain");
//                            intent.putExtra(DetailActivity.BUILDING_CHOSEN, position - 1); //have to do this to account for the first position
//                            startActivity(intent);
//                        }
//                    }
//                }
//                isFirstLoad = false;
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        return v;
    }

    private void updateBuildings(){

        List<BuildingInfo> buildings = buildingQuery.find();
        String[] buildingNames = new String[buildings.size()];
        for (int i = 0; i < buildingNames.length; i++) {
            buildingNames[i] = buildings.get(i).getName();

        }

        String[] buildingImages = new String[buildings.size()];
        for (int i = 0; i < buildingImages.length; i++) {
            buildingImages[i] = buildings.get(i).getPicture();
        }
        adapter = new CaptionedImagesAdapter(buildingNames, buildingImages);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
