package com.quirodev.usagestatsmanagersample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.quirodev.data.dbcontract;
import com.quirodev.data.dbhelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.quirodev.usagestatsmanagersample.appitemdisplay.appname1;

public class AppleFragment extends Fragment {

    public AppleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragmentapple, container, false);
        //TextView title1= (TextView) v.findViewById(R.id.title1);
        //title1.setText("DAILY");
        TextView title2= (TextView) v.findViewById(R.id.title2);
        title2.setText("WEEKLY");
        TextView title3= (TextView) v.findViewById(R.id.title3);
        title3.setText("MONTHLY");
        TextView title4= (TextView) v.findViewById(R.id.title4);
        title4.setText("YEARLY");

        ArrayList<Entry> entries = new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        Date d1=calendar.getTime();
        calendar.add(calendar.DATE,1);


        Log.v("apppn",appname1);
        dbhelper mdbhelper= new dbhelper(getActivity());
        SQLiteDatabase db= mdbhelper.getReadableDatabase();
        Log.v("testing123","d");
        //Cursor cursor=db.rawQuery("Select * from "+appdata.TABLE_NAME,null);
        String[] projection={
                dbcontract.appdata.APP_NAME,
                dbcontract.appdata._AL,
                dbcontract.appdata.APP_DURATION,
                dbcontract.appdata._ID
        };
        //String selection=dbcontract.appdata.APP_NAME+"=?";
        String [] args={appname1,};

        Cursor cursor = db.query(
                dbcontract.appdata.TABLE_NAME,
                projection,dbcontract.appdata.APP_NAME+"=?",args
                ,null,
                null,
                null
        );
        if (cursor!=null) {
            int appcolumnindex = cursor.getColumnIndex(dbcontract.appdata.APP_NAME);
            int appduration = cursor.getColumnIndex(dbcontract.appdata.APP_DURATION);
            int date=cursor.getColumnIndex(dbcontract.appdata._AL);
            //TextView displayview=(TextView)v.findViewById(R.id.database);
            //displayview.setText("Number of rows in database table: " + cursor.getCount());
            Log.v("cursor",String.valueOf(cursor.getCount()));
            int i=0;
            while (cursor.moveToNext()) {
                String appname = cursor.getString(appcolumnindex);
                String duration =  cursor.getString(appduration);
                String datecol=cursor.getString(date);
              //  displayview.append("\n"+appname+"  "+duration+" "+datecol+"\n");
                entries.add(new Entry(Long.parseLong(duration)/60000 , i));
                i++;
                Log.v("data123",appname+"  "+duration+"  "+datecol);
            }
            cursor.close();
        }
        //entries.add(new Entry(8f, 1));
        //entries.add(new Entry(6f, 2));
        //entries.add(new Entry(2f, 3));
        //entries.add(new Entry(18f, 4));
        //entries.add(new Entry(9f, 5));
        //entries.add(new Entry(5f,7));
        LineDataSet dataset = new LineDataSet(entries,"app usage");
        ArrayList<String>labels = new ArrayList<String>();
        labels.add("today");
        labels.add("wednesday");
        labels.add("thursday");
        labels.add("friday");
        labels.add("saturday");
        labels.add("sunday");
        labels.add("monday");

        ArrayList<String> label1=new ArrayList<String>();
        label1.add("october");
        label1.add("november");
        label1.add("december");
        label1.add("january");
        label1.add("feburary");
        label1.add("march");
        label1.add("april");
        label1.add("may");
        label1.add("june");
        label1.add("july");
        label1.add("august");
        label1.add("september");
        LineData data2=new LineData(label1,dataset);
        LineData data = new LineData(labels, dataset);
        //lineChart1.setData(data);
        //lineChart1.setDescription("days");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //lineChart1.animateY(5000);
        LineChart lineChart2 = (LineChart) v.findViewById(R.id.chart2);
        lineChart2.setData(data);
        lineChart2.setDescription("weeks");
        lineChart2.animateY(5000);
        LineChart lineChart3 = (LineChart) v.findViewById(R.id.chart3);
        lineChart3.setData(data2);
        lineChart3.setDescription("monthly");
        lineChart3.animateY(5000);
        LineChart lineChart4 = (LineChart) v.findViewById(R.id.chart4);
        lineChart4.setData(data);
        lineChart4.setDescription("yearly");
        lineChart4.animateY(5000);
        return v;
    }
}