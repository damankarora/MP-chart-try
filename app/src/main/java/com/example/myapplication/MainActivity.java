package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart myChart = (LineChart) findViewById(R.id.my_chart);

//        Setting up data
        double[] daman_cgp = new double[]{6.81, 7.91, 7.52, 7.57, 7.09, 7.86};
        double[] riya_cgp = new double[]{7.82, 6.34, 6.56, 7.03, 7.05, 7.50};
        List<Entry> ourEntries = new ArrayList<>();
        List<Entry> riyaEntries = new ArrayList<>();

//        Creating Lists
        ArrayList<DataObject> ourDataList = new ArrayList<>();
        ArrayList<DataObject> riyaDataList = new ArrayList<>();
        for(int i = 0 ; i < 6; i++){
            ourDataList.add(new DataObject(i + 1, daman_cgp[i]));
            riyaDataList.add(new DataObject(i + 1, riya_cgp[i]));
        }

//        Putting data into Entries
        for(DataObject o : ourDataList){
            ourEntries.add(new Entry(o.getX(), (float) o.getY()));
        }
        for(DataObject o : riyaDataList){
            riyaEntries.add(new Entry(o.getX(), (float) o.getY()));
        }


//      Styling datasets.
        LineDataSet ourDataSet = new LineDataSet(ourEntries, "Daman's CGPA");
        ourDataSet.setColor(Color.parseColor("#C52626"));
        ourDataSet.setCircleColor(Color.parseColor("#C52626"));
        ourDataSet.setDrawFilled(true);


        LineDataSet riyaDataSet = new LineDataSet(riyaEntries, "Riya's CGPA");
        riyaDataSet.setColor(Color.parseColor("#2080AD"));
        riyaDataSet.setCircleColor(Color.parseColor("#2080AD"));
        riyaDataSet.setDrawFilled(true);


//        Combining datasets
        List<ILineDataSet> ourDataSets = new ArrayList<>();

        ourDataSets.add(ourDataSet);
        ourDataSets.add(riyaDataSet);

//        Setting data
        LineData ourLineData = new LineData(ourDataSets);


        myChart.setData(ourLineData);




//        Chart settings.
        Description cgpCharDescription = new Description();
        cgpCharDescription.setText("CGPA Chart");
        myChart.setDescription(cgpCharDescription);

        YAxis leftAxis = myChart.getAxisLeft();
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(0);
        leftAxis.setGranularity(0.5f);

        YAxis rightAxis = myChart.getAxisRight();
        rightAxis.setEnabled(false);



//        Formatting Semesters
        final String[] formatting = new String[]{"S1", "S2", "S3", "S4", "S5", "S6"};

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return formatting[(int) value - 1];
            }
        };

        XAxis topAxis = myChart.getXAxis();
        topAxis.setAxisMinimum(1);
        topAxis.setAxisMaximum(6);
        topAxis.setGranularity(1f);
        topAxis.setValueFormatter(formatter);



        myChart.invalidate();




    }
}

class DataObject{
    private final int x;
    private final double y;
    DataObject(int x, double y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}