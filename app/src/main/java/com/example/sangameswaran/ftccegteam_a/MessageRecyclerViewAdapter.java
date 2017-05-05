package com.example.sangameswaran.ftccegteam_a;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sangameswaran on 02-03-2017.
 */

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<ModelClass> arrayList=new ArrayList<>();
    MessageRecyclerViewAdapter(ArrayList<ModelClass> arrayList)
    {
        this.arrayList=arrayList;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_class_layout,parent,false);
        RecyclerViewHolder holder=new RecyclerViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ModelClass modelClass=arrayList.get(position);
        holder.t1.setText("ID:"+modelClass.getIdeey());
        Log.d("HE",modelClass.getMessage());
        holder.t2.setText("CONTENT:"+modelClass.getMessage());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView t1,t2;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            t1=(TextView) itemView.findViewById(R.id.tv1);
            t2=(TextView) itemView.findViewById(R.id.tv2);
        }

        @Override
        public void onClick(View v) {

           // Toast.makeText(v.getContext(),"clicked "+getAdapterPosition(),Toast.LENGTH_LONG).show();


        }

    }
    public void fun(ArrayList<ModelClass> newList)
    {
        arrayList.clear();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}

