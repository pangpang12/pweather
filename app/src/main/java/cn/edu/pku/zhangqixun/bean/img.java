package cn.edu.pku.zhangqixun.bean;

import android.widget.ImageView;

import cn.edu.pku.zhangqixun.miniweather.R;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class Img {
    private ImageView weatherImg, pmImg;
    private String pm25;
    private String type;
    public String getPm25(){

        return pm25;
    }
    public String getType() {
        return type;
    }
    public void setPm25(String pm25) {this.pm25 = pm25; }
    public void setType(String type){
        this.type = type;
    }
    int a=Integer.getInteger(getPm25());
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
    String b=getType();
    if (b=="暴雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoxue);
    }else if (b=="暴雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoyu);
    }else if (b=="大暴雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
    }else if (b=="大雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_daxue);
    }else if (b=="大雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
    }else if (b=="多云"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
    }else if (b=="雷阵雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
    }else if (b=="雷阵雨冰雹"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
    }else if (b=="沙尘暴"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
    }else if (b=="特大暴雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
    }else if (b=="雾"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
    }else if (b=="小雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
    }else if (b=="小雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
    }else if (b=="阴"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
    }else if (b=="雨夹雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
    }else if (b=="阵雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
    }else if (b=="阵雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
    }else if (b=="中雪"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
    }else if (b=="中雨"){
        weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
    }

}
