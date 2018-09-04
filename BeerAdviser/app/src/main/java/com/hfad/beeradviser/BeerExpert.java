package com.hfad.beeradviser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeerExpert {

    List<String> getBrands(String color){
        List<String> brands = new ArrayList<>();
        if (color.equals("amber")){
            brands.add("Jack Amber");
            brands.add("Amber Moose");
        }else{
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }

    String findRandom (String type){

        String randomBeer = "";
        Random randomGenerator = new Random();
        List<String> beer = new ArrayList<>();

        //this is a gross way of doing this, but it's functional for now
        switch (type){
            case "light":
                beer.add("Miller Light");
                beer.add("Bud Light");
                beer.add("Coors Light");
                beer.add("Natty Ice");
                beer.add("PBR");
                randomBeer = beer.get(randomGenerator.nextInt(beer.size()));
                break;

            case "amber":
                beer.add("Fat Tire");
                beer.add("Sam Adams");
                beer.add("Dankwood");
                beer.add("Red Skull");
                beer.add("Nosferatu");
                randomBeer = beer.get(randomGenerator.nextInt(beer.size()));
                break;

            case "brown":
                beer.add("Dogfish");
                beer.add("Kona Brown");
                beer.add("Nogne");
                beer.add("Nuts");
                beer.add("Avery");
                randomBeer = beer.get(randomGenerator.nextInt(beer.size()));
                break;

            case "dark":
                beer.add("Guinness");
                beer.add("Cutthroat");
                beer.add("Edmund Fitz");
                beer.add("Shark Attack");
                beer.add("Toohey\'s Old");
                randomBeer = beer.get(randomGenerator.nextInt(beer.size()));
                break;

        }

        return randomBeer;
    }

}
