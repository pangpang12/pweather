package cn.edu.pku.zhangqixun.bean;

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
    public void setCity(String city) { this.type = city; }
    public void setUpdatetime(String updatetime) { this.type = updatetime; }
    public void setShidu(String shidu) { this.type = shidu; }
    public void setWendu(String wendu) {this.type = wendu; }
    public void setPm25(String pm25) {this.type = pm25; }
    public void setQuality(String quality) { this.type = quality; }
    public void setFengxiang(String fengxiang) { this.type = fengxiang; }
    public void setFengli(String fengli) { this.type = fengli; }

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
                '}';

    }
}
