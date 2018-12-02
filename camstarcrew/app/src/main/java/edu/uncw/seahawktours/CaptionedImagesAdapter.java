package edu.uncw.seahawktours;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] captions;
    private String[] imageIds;
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter(String[] captions, String[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
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
        //Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        String picName = imageIds[position];

        int idNumber = imageView.getContext().getResources().getIdentifier("drawable/" + picName, null, imageView.getContext().getPackageName());


        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), idNumber);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (listener != null){
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

    public void setListener(Listener listener){
        this.listener = listener;
    }


}
