package edu.uncw.seahawktours;

public class Building {
    private String name;
    private int imageResourceId;

    public static final Building[] buildings = {
            new Building("CIS", R.drawable.cis1),
            new Building("Warwick Center", R.drawable.warwick),
            new Building("Randall Library", R.drawable.library),
            new Building("Burney Center", R.drawable.burney),
            new Building("Bear Hall",R.drawable.bear)

    };

    private Building(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){
        return name;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

}
