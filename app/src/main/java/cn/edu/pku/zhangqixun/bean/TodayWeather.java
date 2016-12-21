package cn.edu.pku.zhangqixun.bean;

import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String shidu;
    private String pm25;
    private String quality;
    private String fengxiang;
    private String fengli;
    private String date;
    private String high;
    private String low;
    private String type;
    private String number;
    private String suggest;
    private ImageView weatherImg, pmImg;

    private List<String> hit;
    private List<String> lot;
    private List<String> fx;
    private List<String> fl;
    private List<String> dd;
    private List<String> tp;


    public String getCity(){

        return city;
    }
    public String getUpdatetime(){

        return updatetime;
    }
    public String getWendu(){

        return wendu;
    }
    public String getShidu(){
        return shidu;
    }
    public String getPm25(){

        return pm25;
    }
    public String getQuality() {return quality;}
    public String getDate() {return date;}
    public String getHigh() {return high;}
    public String getLow() {return low;}
    public String getType() {return type;}
    public String getFengli() {return fengli;}
    public String getNumber() {return number;}
    public String getSuggest() {return suggest;}
    public List<String> getDd() {return dd;}
    public List<String> getHit() {return hit;}
    public List<String> getLot() {return lot;}
    public List<String> getTp() {return tp;}
    public List<String> getFx() {return fx;}
    public List<String> getFl() {return fl;}
    public void setDate(String date){
        this.date = date;
    }
    public void setHigh(String high){
        this.high = high;
    }
    public void setLow(String low){
        this.low = low;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setCity(String city) { this.city = city; }
    public void setUpdatetime(String updatetime) { this.updatetime = updatetime; }
    public void setShidu(String shidu) { this.shidu = shidu; }
    public void setWendu(String wendu) {this.wendu = wendu; }
    public void setPm25(String pm25) {this.pm25 = pm25; }
    public void setQuality(String quality) { this.quality = quality; }
    public void setFengxiang(String fengxiang) { this.fengxiang = fengxiang; }
    public void setFengli(String fengli) { this.fengli = fengli; }
    public void setNumber(String number) {this.number = number;}
    public void setSuggest(String suggest) {this.suggest = suggest;}
    public void setDd (List<String> dd) {this.dd=dd;}
    public void setHit(List<String> hit) {this.hit=hit;}
    public void setLot(List<String> lot) {this.lot=lot;}
    public void setTp(List<String> tp) {this.tp=tp;}
    public void setFx(List<String> fx) {this.fx=fx;}
    public void setFl(List<String> fl) {this.fl=fl;}

    @Override
    public String toString(){
        return "TodayWeather{" +
                "city='" + city + '\'' +
                ",updatetime='" + updatetime + '\'' +
                ",wendu='" + wendu + '\'' +
                ",shidu='" + shidu + '\'' +
                ",pm25='" + pm25 + '\'' +
                ",quality='" + quality + '\'' +
                ",fengxiang='" + fengxiang + '\'' +
                ",fengli='" + fengli + '\'' +
                ",date='" + date + '\'' +
                ",high='" + high + '\'' +
                ",low='" + low + '\'' +
                ",type='" + type + '\'' +
                ",suggest='"+suggest + '\''+
                ",dd'"+ dd + '\'' +
                ",hit'"+ hit + '\'' +
                ",lot'"+ lot + '\'' +
                ",tp'"+ tp + '\'' +
                ",fx'"+ fx + '\'' +
                ",fl'"+ fl + '\'' +
                '}';

    }
}
