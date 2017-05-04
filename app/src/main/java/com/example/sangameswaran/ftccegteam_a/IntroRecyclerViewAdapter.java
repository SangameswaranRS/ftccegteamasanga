package com.example.sangameswaran.ftccegteam_a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sangameswaran on 26-04-2017.
 */

public class IntroRecyclerViewAdapter extends RecyclerView.Adapter<IntroRecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<IntroModelClass> setters=new ArrayList<>();
    IntroRecyclerViewAdapter(ArrayList<IntroModelClass> setters)
    {
        this.setters=setters;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.intro_layout,parent,false);
        IntroRecyclerViewAdapter.RecyclerViewHolder holder=new IntroRecyclerViewAdapter.RecyclerViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        IntroModelClass modelClass=new IntroModelClass();
        modelClass=setters.get(position);
        holder.t1.setText(modelClass.getAbout());
        holder.t2.setImageResource(modelClass.getUrl());

    }

    @Override
    public int getItemCount() {
        return setters.size();
    }
    class  RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        TextView t1;
        ImageView t2;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.about);
            t2=(ImageView)itemView.findViewById(R.id.intropic);
        }
    }
}
