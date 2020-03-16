package com.apps.malpv.dogs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.malpv.dogs.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.ViewPics> {

    private List<String> mUrlPics;
    private Context mContext;

    public PicsAdapter(List<String> mUrlPics, Context mContext) {
        this.mUrlPics = mUrlPics;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewPics onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pics, parent, false);
        return new ViewPics(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPics holder, int position) {

        Picasso.get()
                .load(mUrlPics.get(position))
                .resize(800, 600)//ajusta la imagen
                .centerCrop()//centra la imagen en la pantalla
                .into(holder.ivPics);
    }

    @Override
    public int getItemCount() {
        return mUrlPics.size();
    }

    public class ViewPics extends RecyclerView.ViewHolder{

        ImageView ivPics;

        public ViewPics(@NonNull View itemView) {
            super(itemView);

            ivPics = itemView.findViewById(R.id.ivPicsDog);
        }
    }
}
