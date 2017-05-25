package com.example.sangameswaran.ftccegteam_a;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Sangameswaran on 24-05-2017.
 */

public class ViewProgressGraphActivity extends AppCompatActivity {
    GraphView progressGraph;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress_graph_layout);
        progressGraph=(GraphView)findViewById(R.id.progressGraph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 10),
                new DataPoint(1, 50),
                new DataPoint(2, 70),
                new DataPoint(3, 66),
                new DataPoint(4, 12)
        });
        series.appendData(new DataPoint(28,49),true,6);
        series.setSpacing(50);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                if(data.getY()>=70)
                {
                    return Color.parseColor("#309229");
                }
                else if(data.getY()>=30&&data.getY()<=70)
                {
                    return Color.parseColor("#ffd001");
                }

                return Color.parseColor("#ff0000");
            }
        });
        progressGraph.addSeries(series);
        progressGraph.getViewport().setScalable(true);
        progressGraph.getViewport().setScrollable(true);
        progressGraph.getViewport().setScalableY(true);
        progressGraph.getViewport().setScrollableY(true);

    }
}
