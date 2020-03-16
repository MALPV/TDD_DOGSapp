package com.apps.malpv.dogs.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.malpv.dogs.R;

import java.util.List;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.ViewBreed> implements View.OnClickListener {

    private List<String> mBreedsList;
    private Context mContext;
    private View.OnClickListener listener;

    public BreedAdapter(List<String> mBreedsList, Context mContext) {
        this.mBreedsList = mBreedsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewBreed onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breeds, parent, false);
        view.setOnClickListener(this);

        return new ViewBreed(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBreed holder, int position) {
        holder.tvNameBreed.setText(mBreedsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreedsList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class ViewBreed extends RecyclerView.ViewHolder{

        //ConstraintLayout itemList;
        TextView tvNameBreed;

        public ViewBreed(@NonNull View itemView) {
            super(itemView);

            //itemList = itemView.findViewById(R.id.layoutItemList);
            tvNameBreed = itemView.findViewById(R.id.tvNameBreed);

        }
    }
}
