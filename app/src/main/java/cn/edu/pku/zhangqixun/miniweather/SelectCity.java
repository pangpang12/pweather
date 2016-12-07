package cn.edu.pku.zhangqixun.miniweather;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.MemoryHandler;
import java.util.logging.SocketHandler;

import cn.edu.pku.zhangqixun.app.MyApplication;
import cn.edu.pku.zhangqixun.bean.City;
import cn.edu.pku.zhangqixun.bean.TodayWeather;
import cn.edu.pku.zhangqixun.db.CityDB;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class SelectCity extends Activity implements View.OnClickListener{
    //private static final int Len = 1;
    private ImageView mBackbtn;
    private TextView mOKbtn;
    private TextView mTextView;
    private EditText mEditText;
    //private TextView cityname;
    public ListView mlistview;

    private SimpleAdapter adapter;

    MyApplication App;
    ArrayList<Map<String,String>> ndata = new ArrayList<Map<String, String>>();
    ArrayList<String> citypinyin = new ArrayList<String>();
    ArrayList<String> number = new ArrayList<String>();
    List<City> data = new ArrayList<City>();
    String SelectedNo;


    //private android.os.Handler mHandler = new android.os.Handler() {
        //public void handleMessage(android.os.Message msg) {
            //switch (msg.what) {
                //case Len:
                    //adapter.notifyDataSetChanged();
                    //break;
                //default:
                    //break;
            //}
        //}
    //};
    ////////////////////////////////////////////////

    //private void getndata(ArrayList<Map<String,String>> ndata){
        //App=(MyApplication)getApplication();
        //data=App.getCityList();
        //Map<String,String> items = new HashMap<String, String>();
        //int i=0;
        //while (i<data.size()){
            //citypinyin.add(data.get(i).getCity().toString()+data.get(i).getAllPY());
            //number.add(data.get(i).getNumber().toString());
            //items.put(citypinyin1,citypinyin.get(i));
            //items.put(numbetr1,number.get(i));
            //ndata.add(items);
            //i++;
        //}
    //}

    //private void set_mlist_adapter(){
        //mlistview = (ListView)findViewById(R.id.list_view);
        //getndata(ndata);
        //ArrayAdapter<Map<String,String>> adapter=new ArrayAdapter<Map<String, String>>(SelectCity.this,android.R.layout.simple_list_item_1,ndata);
        //mlistview.setAdapter(adapter);
        //mlistview.setOnItemClickListener();

    //}
    /////////////////////////////////////////////
    //Runnable ech = new Runnable() {
        //@Override
        //public void run() {
            //String ab = mTextView.getText().toString();
            //ndata.clear();
            //getndatasum(ndata,ab);
            //adapter.notifyDataSetChanged();
        //}
    //};

    private void getndatasum(ArrayList<Map<String,String>> mdata,String data) {
        int length = citypinyin.size();
        for (int i = 0; i < length; ++i) {
            if (citypinyin.get(i).contains(data)) {
                Map<String, String> it = new HashMap<String, String>();
                it.put("citypinyin1", citypinyin.get(i));
                it.put("numbetr1", number.get(i));
                mdata.add(it);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mBackbtn = (ImageView) findViewById(R.id.title_back);
        mBackbtn.setOnClickListener(this);
        mOKbtn = (TextView)findViewById(R.id.confirm);
        mOKbtn.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.t);
        mEditText = (EditText) findViewById(R.id.search_edit);

        mlistview = (ListView) findViewById(R.id.list_view);

        App=(MyApplication)getApplication();
        data=App.getCityList();
        for (int i=0;i<data.size();i++){
            citypinyin.add(data.get(i).getCity()+data.get(i).getAllPY());
            number.add(data.get(i).getNumber());
        }

        getndata(ndata);
        mEditText.addTextChangedListener(mTextWatcher);

        SimpleAdapter adapter = new SimpleAdapter(this,ndata,R.layout.items,new String[]{"citypinyin1","numbetr1"},new int[]{R.id.cap,R.id.n});
        mlistview.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectCity.this,"You have clicked"+ndata.get(position).get("citypinyin1"),Toast.LENGTH_LONG).show();
                SelectedNo=ndata.get(position).get("numbetr1");
            }

        });


    }

    private void getndata(ArrayList<Map<String,String>> ndata1){

        int i=0;
        while (i<data.size()){
            Map<String,String> items = new HashMap<String, String>();
            items.put("citypinyin1",citypinyin.get(i));
            items.put("numbetr1",number.get(i));
            ndata1.add(items);
            i++;
        }

    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence tmp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            tmp = charSequence;
            Log.d("myapp", "beforeTextChanged" + tmp);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            mTextView.setText(charSequence);
            Log.d("myapp", "onTextChanged" + charSequence);

        }

        @Override
        public void afterTextChanged(Editable editable) {
            editStart = mEditText.getSelectionStart();
            editEnd = mEditText.getSelectionEnd();

                if (tmp.length() > 16) {
                    Toast.makeText(SelectCity.this, "The input is overlonged!", Toast.LENGTH_SHORT).show();
                    editable.delete(editStart - 1, editEnd);
                    int tmpSelection = editStart;
                    mEditText.setText(editable);
                    mEditText.setSelection(tmpSelection);

                }else
                    if (tmp.length()!=0)
                    {
                    Message msg = new Message();
                    //msg.what=Len;
                    //msg.obj=editable;
                    //mHandler.post(ech);
                        ndata.clear();
                        int length = citypinyin.size();
                        for (int i = 0; i < length; ++i) {
                            if (citypinyin.get(i).contains(tmp)) {
                                Map<String, String> it = new HashMap<String, String>();
                                it.put("citypinyin1", citypinyin.get(i));
                                it.put("numbetr1", number.get(i));
                                ndata.add(it);
                            }

                        }
                       // getndatasum(ndata,tmp.toString());
                        adapter.notifyDataSetChanged();

                }

                Log.d("myapp", "afterTextChanged:");

        }
//
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

}
//http://mobile100.zhangqx.com/assets/docs/lects/service.pdf