package com.hfad.beeradviser;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.List;

public class FindBeerActivity extends Activity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
        ToggleButton toggle = findViewById(R.id.rainbowButton);
        Switch catSwitch = findViewById(R.id.catSwitch);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    TextView nameText = findViewById(R.id.nameText);
                    nameText.setTextColor(Color.BLUE);
                }else{
                    TextView nameText = findViewById(R.id.nameText);
                    nameText.setTextColor(Color.parseColor("#00a39d"));
                }
            }
        });

        catSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ImageView catView = findViewById(R.id.catView);
                    catView.setVisibility(View.VISIBLE);
                }else{
                    ImageView catView = findViewById(R.id.catView);
                    catView.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    public void onClickFindBeer(View view){

        //get reference to the textview
        TextView brands = findViewById(R.id.brands);

        //get reference to the spinner
        Spinner color = findViewById(R.id.color);

        //get selected item in the spinner
        String beerType = String.valueOf(color.getSelectedItem());

        //list of brands

        List<String> brandsList = expert.getBrands(beerType);
        StringBuilder brandsListBuilder = new StringBuilder();
        for(String brand : brandsList){
            brandsListBuilder.append(brand).append('\n');
        }

        //Display the beer list
        brands.setText(brandsListBuilder);

    }

    public void getRandom(View view){

        //gets the brands TextView for editing
        TextView brands = (TextView) findViewById(R.id.brands);

        //gets the spinner object to get value
        Spinner color = (Spinner) findViewById(R.id.color);

        //gets the text in the spinner
        String beerType = String.valueOf(color.getSelectedItem());

        //gets a random beer
        String randomBeer = expert.findRandom(beerType);

        //sets the random beer to the display text
        String luckyChoice = getString(R.string.randomResult, randomBeer);

        //update dat view
        brands.setText(luckyChoice);
    }
}
