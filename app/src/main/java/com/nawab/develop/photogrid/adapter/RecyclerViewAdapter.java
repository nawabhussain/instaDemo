package com.nawab.develop.photogrid.adapter;

/**
 * Created by Nawab on 06-05-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nawab.develop.photogrid.R;
import com.nawab.develop.photogrid.models.ImagesDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {
    private List<ImagesDataModel> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context) {

        this.context = context;
    }

    public void setListData(List<ImagesDataModel> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_image_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        int likes = 0;
        if (itemList.get(position).getLikes() != null)
            likes = itemList.get(position).getLikes().getCount();
        holder.imageLikes.setText(likes + "");

        Picasso.with(context)
                .load(itemList.get(position).getImages().getLow_resolution().getUrl())
                .placeholder(R.drawable.placeholder_320)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        public TextView imageLikes;
        public ImageView image;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imageLikes = (TextView) itemView.findViewById(R.id.imageLikes);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }
}