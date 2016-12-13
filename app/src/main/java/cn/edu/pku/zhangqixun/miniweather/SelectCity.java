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
public class SelectCity extends Activity implements View.OnClickListener {
    //private static final int Len = 1;
    private ImageView mBackbtn;
    private TextView mOKbtn;
    private TextView mTextView;
    private EditText mEditText;
    //private TextView cityname;
    public ListView mlistview;

    private ArrayAdapter adapter;

    MyApplication App;
    ArrayList<String> city;
    ArrayList<String> citypinyin;
    ArrayList<String> cp;
    ArrayList<String> number;
    ArrayList<String> nn;
    List<City> data;
    String SelectedNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mBackbtn = (ImageView) findViewById(R.id.title_back);
        mBackbtn.setOnClickListener(this);
        mOKbtn = (TextView) findViewById(R.id.confirm);
        mOKbtn.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.t);
        mEditText = (EditText) findViewById(R.id.search_edit);
        mlistview = (ListView) findViewById(R.id.list_view);
        //mEditText.addTextChangedListener(mTextWatcher);
        App = (MyApplication) getApplication();
        data = App.getCityList();
        citypinyin = new ArrayList<String>();
        number = new ArrayList<String>();
        city = new ArrayList<String>();
        cp = new ArrayList<String>();
        nn = new ArrayList<String>();

        for (int i = 0; i < data.size(); i++) {
            citypinyin.add(data.get(i).getCity() + data.get(i).getAllPY());
            cp.add(data.get(i).getCity());
            city.add(data.get(i).getCity());
            number.add(data.get(i).getNumber());
            nn.add(data.get(i).getNumber());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cp);
        mlistview.setAdapter(adapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectCity.this, "You have clicked" + cp.get(position), Toast.LENGTH_LONG).show();
                SelectedNo = nn.get(position);
            }

        });
        mEditText.addTextChangedListener(mTextWatcher);
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

            } else {
                cp.clear();
                nn.clear();
                int length = data.size();
                for (int i = 0; i < length; i++) {
                    if (data.get(i).getAllPY().indexOf(editable.toString()) > -1) {
                        cp.add(data.get(i).getCity());
                        nn.add(data.get(i).getNumber());
                    }
                    if (data.get(i).getCity().indexOf(editable.toString()) > -1) {
                        cp.add(data.get(i).getCity());
                        nn.add(data.get(i).getNumber());
                    }
                    adapter.notifyDataSetChanged();
                }

            }
            Log.d("myapp", "afterTextChanged:");
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                Intent j = new Intent();
                j.putExtra("cityCode", SelectedNo);
                setResult(RESULT_OK, j);
                finish();
                break;
            default:
                break;
        }

    }

}
//http://mobile100.zhangqx.com/assets/docs/lects/service.pdf