package edu.uncw.seahawktours;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] captions;
    private String[] imageIds;
    private String[] locations;
    private String currentLocation;
    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    public CaptionedImagesAdapter(String[] captions, String[] imageIds, String[] locations, String currentLocation) {
        this.captions = captions;
        this.imageIds = imageIds;
        this.locations = locations;
        this.currentLocation = currentLocation;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.info_image);
        int idNumber = imageView.getContext().getResources().getIdentifier("drawable/" + imageIds[position], null, imageView.getContext().getPackageName());
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), idNumber);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

        TextView infoTextView = cardView.findViewById(R.id.info_text);
        infoTextView.setText(captions[position]);

        TextView locationTextView = cardView.findViewById(R.id.distance);
        locationTextView.setText(getDistance(locations[position], currentLocation));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);  //might have to change this depending on DB info
                }
            }
        });

    }

    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getDistance(String location, String currentLocation) {
        String[] locationSep = location.split(",");
        String[] currentSep = currentLocation.split(",");
        NumberFormat numberFormat = DecimalFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);

        double A = Math.pow(Double.parseDouble(currentSep[0]) - Double.parseDouble(locationSep[0]), 2);
        double B = Math.pow(Double.parseDouble(currentSep[1]) - Double.parseDouble(locationSep[1]), 2);
        double C = Math.sqrt(A + B) * 3280.84 * 100; //pre-converts from KM to feet 'cus MURICA.

        if (C > 2640) { //I think a half of a mile away is enough...
            return "";
        } else {
            //return String.format(Double.toString(C), "#") + " ft.";
            return numberFormat.format(C) + "ft.";
        }
    }

}
