package cn.edu.pku.zhangqixun.miniweather;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    private TextView mTextView;
    private EditText mEditText;
    //private TextView cityname;

    private ListView mlistview;
    private ListView mListview1;

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
        mTextView=(TextView)findViewById(R.id.t);
        mEditText=(EditText)findViewById(R.id.search_edit);
        mEditText.addTextChangedListener(mTextWatcher);
        //cityname=(TextView)findViewById(R.id.title_name);

        mlistview=(ListView)findViewById(R.id.list_view);
        mListview1=(ListView)findViewById(R.id.search_list);

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
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence tmp;
        private int editStart;
        private int editEnd;
        @Override
        public void beforeTextChanged(CharSequence charSequence,int i,int i2,int i3){
            tmp=charSequence;
            Log.d("myapp","beforeTextChanged"+tmp);
        }
        @Override
        public void onTextChanged(CharSequence charSequence,int i,int i2,int i3){
            mlistview.setVisibility(View.GONE);
            mListview1.setVisibility(View.VISIBLE);
            mTextView.setText(charSequence);
            Log.d("myapp","onTextChanged"+charSequence);

            i=0;
            i2=0;
            while (i<data.size()){
                if (data.get(i).getAllPY().contentEquals(tmp)){
                    i2++;

                }
                else{
                    data.remove(i);
                }
                i++;
            }

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,city);
            mListview1.setAdapter(adapter);

            mListview1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?>adapterView,View view,int i,long l){
                    Toast.makeText(SelectCity.this,"You have clicked:"+city.get(i),
                            Toast.LENGTH_SHORT).show();
                    SelectedNo=number.get(i);
                }
            });





        }
        @Override
        public void afterTextChanged(Editable editable){
            editStart=mEditText.getSelectionStart();
            editEnd=mEditText.getSelectionEnd();
            if (tmp.length()>16){
                Toast.makeText(SelectCity.this,"The input is overlonged!",Toast.LENGTH_SHORT).show();
                editable.delete(editStart-1,editEnd);
                int tmpSelection=editStart;
                mEditText.setText(editable);
                mEditText.setSelection(tmpSelection);

            }

            Log.d("myapp","afterTextChanged:");
        }



        //
        //@Override
        //public void String toFisrstPY(String qpt){
    };





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

    //
    //void updatetodaywerther(TodayWeather todayWeather){
        //cityname.setText("当前城市："+todayWeather.getCity());
    //}
    //
}
//http://mobile100.zhangqx.com/assets/docs/lects/service.pdf