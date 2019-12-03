package com.bangni.berse_test;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LineChart chart;

    Button bt_change, bt_defult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.chart);
        bt_change = findViewById(R.id.bt_change);
        bt_defult = findViewById(R.id.bt_defult);

        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认为0.0f
                List<Float> dataFloat = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    if(i == 2){
                        dataFloat.add(24.0f);
                    }else if (i == 4){
                        dataFloat.add(36.0f);
                    }else if (i == 6){
                        dataFloat.add(17.0f);
                    }else if (i == 8){
                        dataFloat.add(50.0f);
                    }else if (i == 10){
                        dataFloat.add(82.0f);
                    }else if (i == 12){
                        dataFloat.add(12.0f);
                    }else if (i == 19){
                        dataFloat.add(23.0f);
                    }else if (i == 21){
                        dataFloat.add(20.0f);
                    }else if (i == 24){
                        dataFloat.add(40.0f);
                    }else{
                        dataFloat.add(0.0f);
                    }
//                    dataFloat.add(0.0f);
                }
                setData(24, dataFloat);
            }
        });


        bt_defult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认为0.0f
                List<Float> dataFloat = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    dataFloat.add(0.0f);
                }
                setData(24, dataFloat);
            }
        });
        //默认为0.0f
        List<Float> dataFloat = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            dataFloat.add(0.0f);
        }
        setData(24, dataFloat);

        initData();

    }

    //默认数据
    private void initData() {

        chart.setDrawGridBackground(false);

        chart.getDescription().setEnabled(false);

        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setPinchZoom(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setDrawGridLines(true);
        chart.getXAxis().setDrawAxisLine(false);

        chart.invalidate();


        //X轴
        XAxis xAxis;
        {
            xAxis = chart.getXAxis();  //获取x轴
            chart.getAxisLeft().setEnabled(true); //是否显示左边Y轴网格背景线
            xAxis.setLabelCount(13, true); //x轴长度，并是否自适配
            xAxis.setDrawGridLines(true);//绘制格网线
            xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
            xAxis.setLabelCount(13);//设置x轴显示的标签个数
            xAxis.setAxisLineWidth(1f);//设置x轴宽度, ...其他样式


            //设置X轴网格背景的颜色
            xAxis.setGridColor(Color.parseColor("#00000000"));
            //设置X轴字体的颜色
            xAxis.setTextColor(Color.parseColor("#798194"));

            //格式化显示数据
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {

                    //这里是我自己的逻辑
                    Log.d("TYY_CC", "=====" + value);
                    int time;
                    time = (int) value;
                    if(time/2  != 0){
                        time += 1;
                    }
                    if(time == 1) time = 2;
                    if(time == 23) time = 24;
                    return time+ "点";
                }
            });
        }



        //Y轴
        YAxis yAxis;
        {
            yAxis = chart.getAxisLeft();  //获取Y轴

            chart.getAxisRight().setEnabled(false);  //是否显示左边Y轴网格背景线
            yAxis.setLabelCount(8, true);  //设置Y轴的个数和自适应
            yAxis.setDrawGridLines(true);  //绘制y轴格网线
            yAxis.setDrawZeroLine(true);   //绘制0线


            yAxis.setTextColor(Color.parseColor("#ffffff"));
            yAxis.setAxisMaximum(100f);
            yAxis.setAxisMinimum(0f);
            yAxis.setDrawLabels(false);//绘制标签  指轴上的对应数值不显示

        }


        chart.animateX(1500);//x轴方向动画
    }


    private void setData(int count, List<Float> range) {
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(new Entry(i, range.get(i), null));
        }


        chart.setTouchEnabled(false);
        XAxis xAxis = chart.getXAxis();


        //设置X轴的位置（默认在上方）：
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        xAxis.setLabelCount(13, true);
        //启用X轴
        xAxis.setEnabled(true);
        xAxis.setAxisLineWidth(1f);
        xAxis.setAxisLineColor(Color.RED);
        xAxis.setDrawGridLines(false);
        //设置X轴避免图表或屏幕的边缘的第一个和最后一个轴中的标签条目被裁剪
        xAxis.setAvoidFirstLastClipping(true);


        final YAxis yAxis = chart.getAxisLeft();

//        //是否启用绘制零线:设置为true后才有后面的操作
//        yAxis.setDrawZeroLine(true);
//        //设置绘制零线宽度
//        yAxis.setZeroLineWidth(1f);
//        //绘制零线颜色
//        yAxis.setZeroLineColor(Color.parseColor("#4876FD"));

        if (Collections.max(range) == 0) {
            yAxis.setAxisMaximum(100f);
        } else {
            yAxis.setAxisMaximum(100f);
        }



        LineDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
//            chart.clearValues();

            chart.setScaleMinima(1.0f, 1.0f);

            chart.getViewPortHandler().refresh(new Matrix(), chart, true);


            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);


            set1.setValues(values);
            set1.notifyDataSetChanged();


            // redraw
            chart.invalidate();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "02");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            set1.setDrawIcons(false);

            // black lines and points
            set1.setLineWidth(2f);
            set1.setColor(Color.parseColor("#4876FD"));// 链接线颜色
            set1.setDrawFilled(true);
            set1.setDrawValues(true);
            set1.setCircleHoleRadius(0f);
            set1.setDrawCircleHole(false);//设置小圆点是否空心
            set1.setDrawCircles(false);//显示小圆心
            set1.setHighLightColor(Color.parseColor("#00000000")); // 设置点击某个点时，横竖两条线的颜色
            set1.setValueTextSize(9f);
            set1.setValueTextColor(Color.parseColor("#798194"));
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setDrawValues(false);//不显示当前数值

            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.bg);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.WHITE);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets
            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            // set data
            chart.setData(data);//设置数据
        }
    }


}
