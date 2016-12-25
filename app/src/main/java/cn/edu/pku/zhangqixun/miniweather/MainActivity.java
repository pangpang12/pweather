
package cn.edu.pku.zhangqixun.miniweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.edu.pku.zhangqixun.bean.TodayWeather;
import cn.edu.pku.zhangqixun.util.NetUtil;



/**
 * Created on 2016/9/24.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final int UPDATE_TODAY_WEATHER = 1;

    private ImageView mUpdateBtn;
    //private ImageView mUpdateprogessBtn;
    private ProgressBar nUpdateprogessBtn;
    private ImageView mCitySelect;
    private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv, pmQualityTv,
            temperatureTv, climateTv, windTv, city_name_Tv,timepTv,suggestTv;
    private ImageView weatherImg, pmImg;
    private TextView[] tqq,flf,fxf;
    private ImageView[] ttu;

    private List<String> hit;
    private List<String> lot;
    private List<String> fx;
    private List<String> fl;
    private List<String> dd;
    private List<String> tp;

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private List<View> fy;
    private View start;

    //int[] id0={R.id.dt,R.id.dt1,R.id.dt2,R.id.dt3};
    //int[] id1={R.id.t,R.id.t1,R.id.t2,R.id.t3};
    int[] id2={R.id.tp,R.id.tp1,R.id.tp2,R.id.tp3};
    int[] id3={R.id.tu,R.id.tu1,R.id.tu2,R.id.tu3};
    int[] id4={R.id.fx,R.id.fx1,R.id.fx2,R.id.fx3};
    int[] id5={R.id.fl,R.id.fl1,R.id.fl2,R.id.fl3};

    int[] hitt;
    int[] lott;
    String[] da;

    private LineChart mc0;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATE_TODAY_WEATHER:
                    updateTodayWeather((TodayWeather) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);
        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);
        nUpdateprogessBtn = (ProgressBar)findViewById(R.id.title_updateprocess_btn);
        hit=new ArrayList<String>();
        lot=new ArrayList<String>();
        fx=new ArrayList<String>();
        fl=new ArrayList<String>();
        dd=new ArrayList<String>();
        tp=new ArrayList<String>();
        initpages();
        //createitems();
        if (NetUtil.getNetworkState(this) != NetUtil.NETWORK_NONE) {
            Log.d("myweather", "网络OK");
            Toast.makeText(MainActivity.this, "网络OK", Toast.LENGTH_LONG).show();
        } else {
            Log.d("myWeather", "网络挂了");
            Toast.makeText(MainActivity.this, "网络挂了!", Toast.LENGTH_LONG).show();

        }
        mCitySelect=(ImageView)findViewById(R.id.title_city_manager);
        mCitySelect.setOnClickListener(this);
        initView();

    }

    private void setimag(String n,ImageView r){
        if (n.equals("暴雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_baoxue);
        } else if (n.equals("暴雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_baoyu);
        }else if (n.equals("大暴雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
        }else if (n.equals("大雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_daxue);
        }else if (n.equals("大雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_dayu);
        }else if (n.equals("多云")){
        r.setImageResource(R.drawable.biz_plugin_weather_duoyun);
        }else if (n.equals("雷阵雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
        }else if (n.equals("雷阵雨冰雹")){
        r.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
        }else if (n.equals("沙尘暴")){
        r.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
        }else if (n.equals("特大暴雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
        }else if (n.equals("雾")){
        r.setImageResource(R.drawable.biz_plugin_weather_wu);
        }else if (n.equals("小雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
        }else if (n.equals("小雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
        }else if (n.equals("阴")){
        r.setImageResource(R.drawable.biz_plugin_weather_yin);
        }else if (n.equals("雨夹雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
        }else if (n.equals("阵雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
        }else if (n.equals("阵雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
        }else if (n.equals("中雪")){
        r.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
        }else if (n.equals("中雨")){
        r.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
        }else if (n.equals("晴")){
        r.setImageResource(R.drawable.biz_plugin_weather_qing);
        }

    }

    //private void initCharts(String[] a, int[] b,LineChart d){
        //XAxis xAxis = d.getXAxis();
        //xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //d.setDescription("");
        //ArrayList<String> xvalues = new ArrayList<>();
        //for(int i=0;i<a.length;i++){
            //xvalues.add(a[i]);
        //}



    //}


    private void initpages(){
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        fy = new ArrayList<View>();
        fy.add(layoutInflater.inflate(R.layout.days,null));
        fy.add(layoutInflater.inflate(R.layout.days1,null));
        fy.add(layoutInflater.inflate(R.layout.tread,null));
        viewPagerAdapter = new ViewPagerAdapter(fy,this);
        viewPager=(ViewPager)findViewById(R.id.viewpager1);
        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override////////after
    public void onClick(View view) {

        if (view.getId() == R.id.title_city_manager) {

            Intent i = new Intent(this, SelectCity.class);
            //startActivity(i);
            startActivityForResult(i, 1);
        }

        if (view.getId() == R.id.title_update_btn) {
            //
            mUpdateBtn.setVisibility(View.INVISIBLE);
            nUpdateprogessBtn.setVisibility(View.VISIBLE);
            //
            SharedPreferences io = getSharedPreferences("config",MODE_PRIVATE);
            String cityCode = io.getString("code","");
            //SharedPreferences stu = getSharedPreferences("config",MODE_PRIVATE);
            Toast.makeText(this,"code"+cityCode,Toast.LENGTH_LONG).show();
            //SharedPreferences Sharedpreferences = getSharedPreferences("config", MODE_PRIVATE);
            //String cityCode = Sharedpreferences.getString("main_city_code","101010100");
            Log.d("myWeather", cityCode);
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORK_NONE) {
                Log.d("myWeather", "网络ok");
                queryWeatherCode(cityCode);
            } else {
                Log.d("myWeather", "网络挂了");
                Toast.makeText(MainActivity.this, "网络挂了!", Toast.LENGTH_LONG).show();
            }

        }

        if (view.getId()==R.id.title_share){

        }

    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if (requestCode == 1&& resultCode == RESULT_OK){
            String newCityCode=data.getStringExtra("cityCode");
            Log.d("myWeather","选择城市的代码为:"+newCityCode);
            //
            SharedPreferences io = getSharedPreferences("config",MODE_PRIVATE);
            SharedPreferences.Editor editor= io.edit();
            editor.putString("code",newCityCode);
            editor.commit();
            Toast.makeText(this,"city updated",Toast.LENGTH_LONG).show();
            ////////////////////////////////////

            ////////////////////////////////////
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORK_NONE){
                Log.d("myWeather","网络OK");
                queryWeatherCode(newCityCode);
            }else {
                Log.d("myWeather","网络挂了");
                Toast.makeText(MainActivity.this,"网络挂了",Toast.LENGTH_LONG).show();
            }

        }
    }
    void updateTodayWeather(TodayWeather todayWeather) {
        city_name_Tv.setText(todayWeather.getCity() + "天气");
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime() + "发布");
        humidityTv.setText("湿度:" + todayWeather.getShidu());
        //pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getHigh() + "~" + todayWeather.getLow());
        climateTv.setText(todayWeather.getType());
        windTv.setText("风力:" + todayWeather.getFengli());
        timepTv.setText("实时温度:" + todayWeather.getWendu()+"℃");
        //suggestTv.setText(todayWeather.getSuggest());

        if (todayWeather.getPm25()==null){
            pmDataTv.setText(null);
            pmImg.setImageResource(R.drawable.biz_plugin_weather_0_50);
        }else {
            pmDataTv.setText(todayWeather.getPm25());
            int a=Integer.valueOf(todayWeather.getPm25());
            if (a>=0 &&a <=50){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_0_50);
            }else if (a>=51&&a<=100){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_51_100);
            }else if (a>=101&&a<=150){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_101_150);
            }else if (a>=151&&a<=200){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_151_200);
            }else if (a>=201&&a<=300){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_201_300);
            }else if (a>300){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_greater_300);
            }
        }

        String b=todayWeather.getType();
        if (b.equals("暴雪")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoxue);
        }else if (b.equals("暴雨")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoyu);
        }else if (b.equals("大暴雨")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
        }else if (b.equals("大雪")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_daxue);
        }else if (b.equals("大雨")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
        }else if  (b.equals("多云")){
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
        } else if (b.equals("雷阵雨")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
        } else if (b.equals("雷阵雨冰雹")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
        } else if (b.equals("沙尘暴")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
        } else if (b.equals("特大暴雨")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
        } else if (b.equals("雾")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
        } else if (b.equals("小雪")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
        } else if (b.equals("小雨")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
        } else if (b.equals("阴")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
        } else if (b.equals("雨夹雪")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
        } else if (b.equals("阵雪")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
        } else if (b.equals("阵雨")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
        } else if (b.equals("中雪")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
        } else if (b.equals("中雨")) {
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
        }

        for (int j=0;j<4;j++){
                //ddd[j].setText(todayWeather.getDd().get(j));
                //hl[j].setText(todayWeather.getHit().get(j)+"~"+todayWeather.getLot().get(j));
            tqq[j].setText(todayWeather.getTp().get(j));
            setimag(tp.get(j),ttu[j]);
            fxf[j].setText(todayWeather.getFx().get(j));
            flf[j].setText(todayWeather.getFl().get(j));
        }
        ////////////////
        SharedPreferences tw = getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor editor = tw.edit();
        editor.putString("city",todayWeather.getCity());
        editor.putString("time",todayWeather.getUpdatetime());
        editor.putString("shidu",todayWeather.getShidu());
        editor.putString("date",todayWeather.getDate());
        editor.putString("wendu",todayWeather.getHigh()+"~"+todayWeather.getLow());
        editor.putString("type",todayWeather.getType());
        editor.putString("fengli",todayWeather.getFengli());
        editor.putString("ssw",todayWeather.getWendu());
        editor.commit();

        da = new String[dd.size()];
        hitt = new int[dd.size()];
        lott = new int[dd.size()];

        for (int k=0;k<dd.size();k++){
            String dr = dd.get(k).substring(0,dd.get(k).length()-3);
            da[k] = dr;
            String ar = hit.get(k).substring(3);
            ar = ar.substring(0,ar.length()-1);
            hitt[k] = Integer.valueOf(ar);
            String br = lot.get(k).substring(3);
            br = br.substring(0,br.length()-1);
            lott[k] = Integer.valueOf(br);
        }

        XAxis xAxis=mc0.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mc0.setDescription("温度走势");
        ArrayList<String> xvalues = new ArrayList<>();
        for (int i=0;i<da.length;i++){
            xvalues.add(da[i]);
        }
        Log.e("wing",xvalues.size()+"");
        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i=0;i<hitt.length;i++){
            yValue.add(new Entry(hitt[i],i));
        }

        LineDataSet dataSet = new LineDataSet(yValue,"高温");

        ArrayList<Entry> yValue1 = new ArrayList<>();

        for (int i=0;i<hitt.length;i++){
            yValue1.add(new Entry(lott[i],i));
        }

        Log.e("wing", yValue.size() + "");
        LineDataSet dataSet1 = new LineDataSet(yValue1,"低温");
        dataSet1.setColor(Color.BLACK);
        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        dataSets.add(dataSet1);
        LineData lineData=new LineData(xvalues,dataSets);
        mc0.setData(lineData);

    Toast.makeText(MainActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
        mUpdateBtn.setVisibility(View.VISIBLE);
        nUpdateprogessBtn.setVisibility(View.GONE);

    }

    void initView() {
        city_name_Tv = (TextView) findViewById(R.id.title_city_name);
        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week_today);
        pmDataTv = (TextView) findViewById(R.id.pm_data);
        pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality);
        pmImg = (ImageView) findViewById(R.id.pm2_5_img);
        temperatureTv = (TextView) findViewById(R.id.temperature);
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        timepTv = (TextView) findViewById(R.id.timep);
        //suggestTv = (TextView) fy.get(2).findViewById(R.id.su);
        weatherImg = (ImageView) findViewById(R.id.weather_img);
        mc0=(LineChart)fy.get(2).findViewById(R.id.c1);

        SharedPreferences tw = getSharedPreferences("config",MODE_PRIVATE);
        String cnt = tw.getString("city","");
        String tt = tw.getString("time","");
        String s = tw.getString("shidu","");
        String d = tw.getString("date","");
        String w = tw.getString("wendu","");
        String ty = tw.getString("type","");
        String fl = tw.getString("fengli","");
        String ss = tw.getString("ssw","");

        city_name_Tv.setText(cnt+"天气");
        cityTv.setText(cnt);
        timeTv.setText(tt);
        humidityTv.setText("湿度:"+s);
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText(d);
        temperatureTv.setText(ss);
        climateTv.setText(ty);
        windTv.setText(fl);
        timepTv.setText(w);
        mc0.setData(null);
        //suggestTv.setText("N/A");


        //ddd=new TextView[4];
        //hl=new TextView[4];
        tqq=new TextView[4];
        fxf=new TextView[4];
        flf=new TextView[4];
        ttu=new ImageView[4];

        for (int j=0;j<4;j++) {
            double t = j/3;
            int a = (int) Math.floor(t);
            //ddd[j] = (TextView)fy.get(a).findViewById(id0[j]);
            //hl[j] = (TextView)fy.get(a). findViewById(id1[j]);
            tqq[j] = (TextView)fy.get(a). findViewById(id2[j]);
            ttu[j] = (ImageView)fy.get(a). findViewById(id3[j]);
            flf[j] = (TextView)fy.get(a).findViewById(id4[j]);
            fxf[j] = (TextView)fy.get(a). findViewById(id5[j]);

            //ddd[j].setText("N/A");
            //hl[j].setText("N/A");
            tqq[j].setText("N/A");
            //setimag(tp.get(j),ttu[j]);
            fxf[j].setText("N/A");
            flf[j].setText("N/A");

        }

    }


    /**
     * @param cityCode
     *
     */

    private void queryWeatherCode(String cityCode) {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myWeather", address);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                TodayWeather todayWeather = null;
                try {
                    URL url = new URL(address);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        response.append(str);
                        Log.d("myWeather",str);
                    }
                    String responseStr = response.toString();
                    Log.d("myWeather", responseStr);

                    todayWeather = parseXML(responseStr);
                    if (todayWeather != null) {
                        Log.d("myWeather", todayWeather.toString());
                        Message msg =new Message();
                        msg.what = UPDATE_TODAY_WEATHER;
                        msg.obj=todayWeather;
                        mHandler.sendMessage(msg);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }).start();
    }

    private TodayWeather parseXML(String xmldata) {
        TodayWeather todayWeather = null;
        int fengxiangCount = 0;
        int fengliCount = 0;
        int dateCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;

        dd.clear();
        hit.clear();
        lot.clear();
        tp.clear();
        fx.clear();
        fl.clear();

        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldata));
            int eventType = xmlPullParser.getEventType();
            Log.d("myWeather", "parseXML");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals("resp")) {
                            todayWeather = new TodayWeather();
                        }
                        if (todayWeather != null) {

                            if (xmlPullParser.getName().equals("city")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setCity(xmlPullParser.getText());
                                //Log.d("myWeather", "city: " + xmlPullParser.getText());

                            } else if (xmlPullParser.getName().equals("updatetime")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                                //Log.d("myWeather", "updatetime: " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("shidu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setShidu(xmlPullParser.getText());
                                //Log.d("myWeather", "shidu: " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("wendu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWendu(xmlPullParser.getText());
                                //Log.d("myWeather", "wendu: " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("pm25")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setPm25(xmlPullParser.getText());
                                //Log.d("myWeather", "pm25: " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("quality")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setQuality(xmlPullParser.getText());
                                //Log.d("myWeather", "quality: " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("fengxiang")) {
                                eventType = xmlPullParser.next();
                                if (fengxiangCount == 0) {
                                    todayWeather.setFengxiang(xmlPullParser.getText());
                                } else {
                                    //todayWeather.setFx(xmlPullParser.getText());
                                    fx.add(xmlPullParser.getText());
                                    todayWeather.setFx(fx);
                                }
                                fengxiangCount++;
                            }else if (xmlPullParser.getName().equals("fengli")) {
                                eventType = xmlPullParser.next();
                                if (fengliCount == 0) {
                                    todayWeather.setFengli(xmlPullParser.getText());
                                } else {
                                    fl.add(xmlPullParser.getText());
                                    todayWeather.setFl(fl);
                                }
                                fengliCount++;
                            }else if (xmlPullParser.getName().equals("date")) {
                                eventType = xmlPullParser.next();
                                if (dateCount == 0) {
                                    todayWeather.setDate(xmlPullParser.getText());
                                } else {
                                    dd.add(xmlPullParser.getText());
                                    todayWeather.setDd(dd);
                                }
                                dateCount++;
                            }else if (xmlPullParser.getName().equals("high")){
                                eventType = xmlPullParser.next();
                                if (highCount==0) {
                                    todayWeather.setHigh(xmlPullParser.getText());
                                }else {
                                    hit.add(xmlPullParser.getText());
                                    todayWeather.setHit(hit);
                                }
                                highCount++;
                            }else if (xmlPullParser.getName().equals("low")) {
                                eventType = xmlPullParser.next();
                                if (lowCount == 0) {
                                    todayWeather.setLow(xmlPullParser.getText());
                                } else {
                                    lot.add(xmlPullParser.getText());
                                    todayWeather.setLot(lot);
                                }
                                lowCount++;
                            }else if (xmlPullParser.getName().equals("type")) {// && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                if (typeCount == 0) {
                                    todayWeather.setType(xmlPullParser.getText());
                                } else if (typeCount == 2 || typeCount == 4 || typeCount == 6 || typeCount == 8) {
                                    tp.add(xmlPullParser.getText());
                                    todayWeather.setTp(tp);
                                }
                                typeCount++;
                            }else if (xmlPullParser.getName().equals("suggest")){
                                eventType=xmlPullParser.next();
                                todayWeather.setSuggest(xmlPullParser.getText());
                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayWeather;
    }

}







