
package cn.edu.pku.zhangqixun.miniweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
            temperatureTv, climateTv, windTv, city_name_Tv,timepTv,sportdegerrTv;
    private ImageView weatherImg, pmImg;
    private TextView daw;
    private TextView fw;
    private TextView tpq;
    private ImageView tpp;
    private TextView fll;
    private TextView fxx;

    private List<Map<String,Object>> views;
    private List<ImageView> vs;
    private List<String> hit;
    private List<String> lot;
    private List<String> fx;
    private List<String> fl;
    private List<String> dd;
    private List<String> tp;
    private List<String> hal;
    private ListView viewList;

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private List<View> fy;

    //private List<View> views;
    //private LinearLayout gaa;
    //private List<LinearLayout> sa;
    //private Layout saa;
    //private ViewPagerAdapter viewPagerAdapter;
    //private ViewPager viewPager;
    private LinearLayout linearLayout;
    //private List<String> v;

    //private int[] er;

    //private HorizontalScrollView ih;

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
        //createitems();
        initpages();
        //for (int j=0;j<hal.size();j++)

            //List<View> views = new ArrayList<View>();
            //creatviews(saa);
            //daw.setText(dd.get(j));
            //fw.setText(hal.get(j));
            //tpq.setText(tp.get(j));
            //tpp.setImageResource(R.drawable.biz_plugin_weather_qing);


            //if (tp.get(j).equals("暴雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_baoxue);
            //}else if (tp.get(j).equals("暴雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_baoyu);
            //}else if (tp.get(j).equals("大暴雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
            //}else if (tp.get(j).equals("大雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_daxue);
            //}else if (tp.get(j).equals("大雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_dayu);
            //}else if (tp.get(j).equals("多云")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_duoyun);
            //}else if (tp.get(j).equals("雷阵雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
            //}else if (tp.get(j).equals("雷阵雨冰雹")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
            //}else if (tp.get(j).equals("沙尘暴")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
            //}else if (tp.get(j).equals("特大暴雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
            //}else if (tp.get(j).equals("雾")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_wu);
            //}else if (tp.get(j).equals("小雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
            //}else if (tp.get(j).equals("小雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
            //}else if (tp.get(j).equals("阴")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_yin);
            //}else if (tp.get(j).equals("雨夹雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
            //}else if (tp.get(j).equals("阵雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
            //}else if (tp.get(j).equals("阵雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
            //}else if (tp.get(j).equals("中雪")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
            //}else if (tp.get(j).equals("中雨")){
                //tpp.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
            //}

            //fll.setText(fl.get(j));
            //fxx.setText(fx.get(j));
            //views.add(sa);
            //inflater=LayoutInflater.from(this);
            //sa=inflater.inflate(R.layout.days,null);
            //linearLayout.addView(sa);
            ////ih.addView(sa);
            //v.add("b"+j);
            //View b=layoutInflater.inflate(R.layout.days,null);
            //linearLayout.addView(b);

        //}


                //SimpleAdapter adapter = new SimpleAdapter(this,views,R.layout.days,new String[]{"hal","vs","fx","fl","dd","tp"},new int[]{R.id.tm,R.id.tu,R.id.fx,R.id.fl,R.id.dt,R.id.p1});
        //viewList.setAdapter(adapter);

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
            //
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
        pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getHigh() + "~" + todayWeather.getLow());
        climateTv.setText(todayWeather.getType());
        windTv.setText("风力:" + todayWeather.getFengli());
        timepTv.setText("实时温度:" + todayWeather.getWendu()+"℃");
        sportdegerrTv.setText(todayWeather.getSuggest());
        ////

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
        }else{
            pmImg.setImageResource(R.drawable.biz_plugin_weather_greater_300);
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
        }else {
            if (b.equals("多云")) {
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
        }

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
        sportdegerrTv = (TextView) findViewById(R.id.sport);
        weatherImg = (ImageView) findViewById(R.id.weather_img);



        city_name_Tv.setText("N/A");
        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");
        timepTv.setText("N/A");
        sportdegerrTv.setText("N/A");
    }

    private TextView[] d6,w6,t6,l6,x6;
    private ImageView[] sun6;

    private void createitems(){
        for (int i=0;i<hit.size();i++)  {
            hal.add(hit.get(i)+"℃~"+lot.get(i)+"℃");
        }

        for (int j=0;j<hal.size();j++){

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



    //private void creatviews(Layout view){
      //  daw=(TextView)findViewById(R.id.dt);
        //fw=(TextView)findViewById(R.id.tm);
        //tp//q=(TextView)findViewById(R.id.p1);
        //tpp=(ImageView)findViewById(R.id.tu);
        //fll=(TextView)findViewById(R.id.fx);
        //fxx=(TextView)findViewById(R.id.fl);

    //}



    private TodayWeather parseXML(String xmldata) {
        TodayWeather todayWeather = null;
        int fengxiangCount = 0;
        int fengliCount = 0;
        int dateCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;

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
                            } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengxiang(xmlPullParser.getText());
                                //Log.d("myWeather", "fengxiang: " + xmlPullParser.getText());
                                fengxiangCount++;
                            }
                            //else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount != 0){
                                //fx.add(xmlPullParser.getText());
                                //fengxiangCount++;
                                //eventType = xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli(xmlPullParser.getText());
                                //Log.d("myWeather", "fengli: " + xmlPullParser.getText());
                                fengliCount++;
                            }//else if (xmlPullParser.getName().equals("fengli") && fengliCount != 0){
                                //fl.add(xmlPullParser.getText());
                                //fengliCount++;
                                //eventType = xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("date") && dateCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setDate(xmlPullParser.getText());
                                //Log.d("myWeather", "date: " + xmlPullParser.getText());
                                dateCount++;

                            }//else if (xmlPullParser.getName().equals("date") && dateCount != 0){
                                //dd.add(xmlPullParser.getText());
                                //dateCount++;
                                //eventType=xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh(xmlPullParser.getText().substring(2).trim());
                                //Log.d("myWeather", "high: " + xmlPullParser.getText());
                                highCount++;
                            }//else if (xmlPullParser.getName().equals("high") && highCount != 0){
                                //hit.add(xmlPullParser.getText());
                                //highCount++;
                                //eventType = xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow(xmlPullParser.getText().substring(2).trim());
                                //Log.d("myWeather", "low: " + xmlPullParser.getText());
                                lowCount++;
                            }//else if (xmlPullParser.getName().equals("low") && lowCount != 0){
                                //lot.add(xmlPullParser.getText());
                                //lowCount++;
                                //eventType = xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setType(xmlPullParser.getText());
                                //Log.d("myWeather", "type: " + xmlPullParser.getText());
                                typeCount++;
                                //
                            }//else if (xmlPullParser.getName().equals("type") && typeCount != 0){
                                //tp.add(xmlPullParser.getText());
                                //typeCount++;
                                //eventType = xmlPullParser.next();
                            //}
                            else if (xmlPullParser.getName().equals("suggest")){
                                eventType = xmlPullParser.next();
                                todayWeather.setSuggest(xmlPullParser.getText());
                            }
                            //

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







