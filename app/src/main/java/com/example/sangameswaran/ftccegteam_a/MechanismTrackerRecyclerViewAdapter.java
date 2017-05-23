package com.example.sangameswaran.ftccegteam_a;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sangameswaran on 23-05-2017.
 */

public class MechanismTrackerRecyclerViewAdapter extends RecyclerView.Adapter<MechanismTrackerRecyclerViewAdapter.MechanismTrackerRecyclerViewHolder> {
    ArrayList<MechanismTrackerEntity> entities=new ArrayList<>();
    Context context;
    MechanismTrackerRecyclerViewAdapter(ArrayList<MechanismTrackerEntity> entities,Context context)
    {
        this.entities=entities;
        this.context=context;
    }

    @Override
    public MechanismTrackerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_mechanism_tracker_card_layout,parent,false);
        MechanismTrackerRecyclerViewHolder holder=new MechanismTrackerRecyclerViewHolder(v);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(MechanismTrackerRecyclerViewHolder holder, int position) {

        MechanismTrackerEntity entity=new MechanismTrackerEntity();
        entity=entities.get(position);
        holder.t1.setText("MECHANISM : "+entity.getName_of_mechanism());
        holder.t2.setText("PURPOSE : "+entity.getPurpose_of_mechanism());
        holder.t3.setText("SUCCESS PERCENT: "+entity.getPercentage_of_success());
        holder.t4.setText("FAILURE REASON : "+entity.getFailure_reason());
        holder.t5.setText("COMMENTS : "+entity.getComment());
        holder.t6.setText("RECORDED TIMESTAMP : "+entity.getReporting_time());
        if(entity.getPriority().equals("red"))
        {
            //holder.linearLayout.setBackgroundColor(context.getColor(R.color.red));
        }
        else if(entity.getPriority().equals("yellow"))
        {
           // holder.linearLayout.setBackgroundColor(context.getColor(R.color.yellow));
        }
        else if(entity.getPriority().equals("green"))
        {
            //holder.linearLayout.setBackgroundColor(context.getColor(R.color.colorAccent));
        }
        else {
            //holder.linearLayout.setBackgroundColor(context.getColor(R.color.blue));
        }
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }
    public class MechanismTrackerRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6;
        LinearLayout linearLayout;
        public MechanismTrackerRecyclerViewHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout2);
            t1=(TextView)itemView.findViewById(R.id.tview1);
            t2=(TextView)itemView.findViewById(R.id.tview2);
            t3=(TextView)itemView.findViewById(R.id.tview3);
            t4=(TextView)itemView.findViewById(R.id.tview4);
            t5=(TextView)itemView.findViewById(R.id.tview5);
            t6=(TextView)itemView.findViewById(R.id.tview6);

        }
    }
}
