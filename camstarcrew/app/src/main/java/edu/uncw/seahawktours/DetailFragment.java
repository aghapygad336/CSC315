package edu.uncw.seahawktours;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class DetailFragment extends Fragment {

    private int buildingId;
    private Box<BuildingInfo> buildingBox;
    private Query<BuildingInfo> buildingQuery;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void setBuildingId(int id) {
        this.buildingId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            BoxStore boxStore = App.getBoxStore();
            buildingBox = boxStore.boxFor(BuildingInfo.class);

            //buildingQuery = buildingBox.query().build();
            long buildId = buildingId+1; //the database starts the id's at one, but the selector starts at zero.  Go figure.

            BuildingInfo building = buildingBox.query().equal(BuildingInfo_.id, buildId).build().findFirst();

            ImageView pic = view.findViewById(R.id.picture1);
            TextView caption = view.findViewById(R.id.pic1Caption);
            TextView buildingText = view.findViewById(R.id.building_info);
            TextView buildingTitle = view.findViewById(R.id.buildingTitle);
            TextView buildingUrl = view.findViewById(R.id.buildingUrl);

            int resourceId = getResources().getIdentifier(building.getPicture(), "drawable", "edu.uncw.seahawktours");
            pic.setImageResource(resourceId);
            caption.setText(building.getCaption());
            buildingText.setText(building.getDescription());
            buildingTitle.setText(building.getName());

            String urlText = getString(R.string.find_more) + "\n" + building.getUrl();

            buildingUrl.setText(urlText);

            Pattern pattern = Pattern.compile(building.getUrl());
            Linkify.addLinks(buildingUrl, pattern, "https://");
        }
    }

}
