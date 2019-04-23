package com.example.administrator.hdx_conference_mvp.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.MyAppLication;
import com.example.administrator.hdx_conference_mvp.widview.MPChartMarkerView;
import com.example.administrator.hdx_conference_mvp.widview.StringAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Extra on 2018/5/1.
 */

public class MPChartHelper {

    private static int color  =  Color.rgb(181, 194, 202);
    private static Drawable drawable = ContextCompat.getDrawable(MyAppLication.getContext(), R.drawable.bg_line_red);

    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };

    public static final int[] LINE_COLORS = {
            Color.rgb(140, 210, 118), Color.rgb(159, 143, 186), Color.rgb(233, 197, 23)
    };//绿色，紫色，黄色

    public static final int[] LINE_FILL_COLORS = {
            Color.rgb(222, 239, 228), Color.rgb(246, 234, 208), Color.rgb(235, 228, 248)
    };



    /**
     * 单线单y轴图。
     *
     * @param lineChart
     * @param xAxisValue
     * @param yAxisValue
     * @param title
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     */
    public static void setLineChart(LineChart lineChart, List<String> xAxisValue, List<Integer> yAxisValue, String title, boolean showSetValues) {
        List<List<Integer>> entriesList = new ArrayList<>();
        entriesList.add(yAxisValue);

        List<String> titles = new ArrayList<>();
        titles.add(title);

        setLinesChart(lineChart, xAxisValue, entriesList, titles, showSetValues,null);
    }

    /**
     * 单线单y轴图。
     *
     * @param lineChart
     * @param xAxisValue
     * @param yAxisValue
     * @param title
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     */
    public static void setLineChart(LineChart lineChart, List<String> xAxisValue, List<Integer> yAxisValue, String title, boolean showSetValues, int color1,Drawable drawable1) {
        List<List<Integer>> entriesList = new ArrayList<>();
        entriesList.add(yAxisValue);

        color =color1;
        drawable =drawable1;
        List<String> titles = new ArrayList<>();
        titles.add(title);

        setLinesChart(lineChart, xAxisValue, entriesList, titles, showSetValues,null);
    }

    /**
     * 绘制线图，默认最多绘制三种颜色。所有线均依赖左侧y轴显示。
     *
     * @param lineChart
     * @param xAxisValue x轴的轴
     * @param yXAxisValues y轴的值
     * @param titles 每一个数据系列的标题
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     * @param lineColors 线的颜色数组。为null时取默认颜色，此时最多绘制三种颜色。
     */
    public static void setLinesChart(LineChart lineChart, List<String> xAxisValue, List<List<Integer>> yXAxisValues, List<String> titles, boolean showSetValues, int[] lineColors) {
        lineChart.getDescription().setEnabled(false);//设置描述
        lineChart.setPinchZoom(true);//设置按比例放缩柱状图

        MPChartMarkerView markerView = new MPChartMarkerView(lineChart.getContext(), R.layout.custom_marker_view);
        lineChart.setMarker(markerView);



        //x坐标轴设置
        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);

        xAxis.setTextColor(lineChart.getContext().getResources().getColor(R.color.view_99));
        /*xAxis.setAxisLineWidth(2f);*/
        xAxis.setValueFormatter(xAxisFormatter);
//        xAxis.setAxisLineWidth(1f);//设置x轴宽度, ...其他样式

        //y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();

        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
//        if (showSetValues) {
            leftAxis.setDrawLabels(true);//折线上显示值，则不显示坐标轴上的值
//        }
        leftAxis.setTextColor(lineChart.getContext().getResources().getColor(R.color.view_99));
        leftAxis.setDrawZeroLine(true);
        leftAxis.setDrawTopYLabelEntry(false);
        leftAxis.setInverted(false);
        leftAxis.setStartAtZero(true);//设置Y轴的数据不是从0开始
//        leftAxis.setDrawTopYLabelEntry(true);
        List<String> y  = new ArrayList<>();
        int max = Collections.max(yXAxisValues.get(0));

        leftAxis.setAxisMinimum(0f); // start at zero
        if (max>200)
        leftAxis.setAxisMaximum((float) (max+max*0.2)); // the axis maximum is 100
        else leftAxis.setAxisMaximum(max+10);
        if (max<6)
            leftAxis.setLabelCount(max<3?3:max, true);
        else leftAxis.setLabelCount(6, true); // force 6 labels


        lineChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(14f);
        legend.setTextColor(lineChart.getContext().getResources().getColor(R.color.view_33));

        //设置柱状图数据
        setLinesChartData(lineChart, yXAxisValues, titles, showSetValues,lineColors);

        lineChart.getDescription().setEnabled(false);
        // enable touch gestures
        lineChart.setTouchEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleXEnabled(true);
        lineChart.setPinchZoom(false);
        lineChart.setScaleYEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        //设置描述文本
        lineChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);



       /* lineChart.setExtraOffsets(10, 10, 20, 10);
        lineChart.animateX(1500);//数据显示动画，从左往右依次显示*/
    }
    private static void setLinesChartData(LineChart lineChart, List<List<Integer>> yXAxisValues, List<String> titles, boolean showSetValues, int[] lineColors) {

        List<List<Entry>> entriesList = new ArrayList<>();
        for (int i = 0; i < yXAxisValues.size(); ++i) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0, n = yXAxisValues.get(i).size(); j < n; j++) {
                entries.add(new Entry(j, yXAxisValues.get(i).get(j)));
            }
            entriesList.add(entries);
        }

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            for (int i = 0; i < lineChart.getData().getDataSetCount(); ++i) {
                LineDataSet set = (LineDataSet) lineChart.getData().getDataSetByIndex(i);

                set.setCubicIntensity(0.2f);
                // 设置为曲线显示,false为折线
                set.setDrawCircles(true);
                set.setHighlightEnabled(true);

                set.setValues(entriesList.get(i));
                set.setLabel(titles.get(i));
            }

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            //设置 标签
            for (int i = 0; i < entriesList.size(); ++i) {
                LineDataSet set = new LineDataSet(entriesList.get(i), titles.get(i));

                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set.setHighlightEnabled(true);// 设置为曲线显示,false为折线

                if(lineColors!=null){
                    set.setColor(lineColors[i % entriesList.size()]);
                    set.setCircleColor(lineColors[i % entriesList.size()]);
                    set.setCircleColorHole(Color.WHITE);
                } else {
                    set.setColor(color);
                    set.setCircleColor(color);
                    set.setCircleColorHole(Color.WHITE);
                }

                if (entriesList.size() == 1) {
                    set.setDrawFilled(true);
//                    set.setFillColor(LINE_FILL_COLORS[i % 3]);
                    set.setFillDrawable(drawable);
                }
                dataSets.add(set);
            }

            LineData data = new LineData(dataSets);
            if (showSetValues) {
                data.setValueTextSize(10f);
                data.setValueFormatter(new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                        return StringUtils.double2String(value, 1);
                    }
                });
            } else {
                data.setDrawValues(false);
            }
            lineChart.setData(data);
        }
    }

}
