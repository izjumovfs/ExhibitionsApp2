package app.exhibitions.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.exhibitions.com.R;
import app.exhibitions.com.model.Exhibit;

public class ExhibitionsAdapter extends RecyclerView.Adapter<ExhibitionsAdapter.ItemHolder> {

    private ArrayList<Exhibit> mExihibitionsList;
    private Activity mActivity;

    public ExhibitionsAdapter (ArrayList<Exhibit> exhibits, Activity activity){
        mExihibitionsList = exhibits;
        mActivity = activity;
    }


    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.exhibition_item, parent, false);
        return new ItemHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Exhibit item = mExihibitionsList.get(position);
        holder.titleTV.setText(item.getTitle());

        ArrayList<String> paths = item.getImages();
        holder.bitmapsContainer.removeAllViews();
        for (String path: paths){
            View view = mActivity.getLayoutInflater().inflate(R.layout.bitmap_item, null, false);

            ImageView imView = view.findViewById(R.id.bitmap_view);
            Picasso.with(mActivity).load(path).into(imView);

            holder.bitmapsContainer.addView(view);

        }

    }

    @Override
    public int getItemCount() {
        return mExihibitionsList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        TextView titleTV;
        LinearLayout bitmapsContainer;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title);
            bitmapsContainer = itemView.findViewById(R.id.bitmaps_container);
        }
    }

}
