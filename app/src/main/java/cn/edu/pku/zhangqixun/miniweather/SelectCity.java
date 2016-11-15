package cn.edu.pku.zhangqixun.miniweather;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.zhangqixun.app.MyApplication;
import cn.edu.pku.zhangqixun.bean.City;
import cn.edu.pku.zhangqixun.bean.TodayWeather;
import cn.edu.pku.zhangqixun.db.CityDB;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class SelectCity extends Activity implements View.OnClickListener{
    private ImageView mBackbtn;

    private ListView mlistview;

    //private String[] data = {"北京","上海","青岛","济南","南京","沈阳","哈尔滨"};
    MyApplication App;
    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> number = new ArrayList<String>();
    List<City> data = new ArrayList<City>();
    String SelectedNo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackbtn = (ImageView)findViewById(R.id.title_back);
        mBackbtn.setOnClickListener(this);


        mlistview=(ListView)findViewById(R.id.list_view);

        App = (MyApplication)getApplication();
        data=App.getCityList();
        int i=0;
        while (i<data.size()){
            city.add(data.get(i).getCity().toString());
            number.add(data.get(i).getNumber().toString());
            i++;


        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,city);
        mlistview.setAdapter(adapter);

        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapterView,View view,int i,long l){
                Toast.makeText(SelectCity.this,"You have clicked:"+city.get(i),
                        Toast.LENGTH_SHORT).show();
                SelectedNo=number.get(i);
            }
        });

        //mSearchbtn.setAdapter(adapter);
        //mSearchbtn.setOnItemClickListener(new AdapterView.OnItemClickListener(){
         //   @Override
            //public void onItemClick(AdapterView<?>adapterView,View view,int i,int l){

            //}
        //});

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                Intent j = new Intent();
                j.putExtra("cityCode", SelectedNo);
                setResult(RESULT_OK,j);
                finish();
                break;
            default:
                break;


        }
    }
}
//http://mobile100.zhangqx.com/assets/docs/lects/service.pdf