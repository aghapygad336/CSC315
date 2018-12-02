package edu.uncw.seahawktours;

import android.app.Activity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

@Entity
public class BuildingInfo extends Activity {

    @Id
    public long id;

    @Index
    private String Name;
    private String Description;
    private String url;
    private String picture;
    private String caption;
    private String coordinates;

//    public static final BuildingInfo[] buildings = {
//          //  new BuildingInfo(R.string.cis_title, R.string.cis_info, R.string.cis_pic_caption, R.string.cis_url, R.drawable.cis1),
//          //  new BuildingInfo(R.string.warwick_title, R.string.warwick_info, R.string.warwick_pic_caption, R.string.warwick_url, R.drawable.warwick),
//          //  new BuildingInfo(R.string.library_title, R.string.library_info, R.string.library_pic_caption, R.string.library_url, R.drawable.library),
//          //  new BuildingInfo(R.string.burney_title, R.string.burney_info, R.string.burney_pic_caption, R.string.burney_url, R.drawable.burney),
//          //  new BuildingInfo(R.string.bear_title, R.string.bear_info, R.string.bear_pic_caption, R.string.bear_url, R.drawable.bear)
//
//    };


    public BuildingInfo(String Name, String Description, String Caption, String url, String picture, String coordinates) {
        this.Name = Name;
        this.Description = Description;
        this.url = url;
        this.picture = picture;
        this.caption = Caption;
        this.coordinates = coordinates;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getUrl() {
        return url;
    }

    public String getPicture() {
        return picture;
    }

    public String getCaption() {
        return caption;
    }

    public String getCoordinates(){
        return coordinates;
    }

}
