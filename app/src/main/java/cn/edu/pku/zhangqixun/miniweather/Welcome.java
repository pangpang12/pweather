package cn.edu.pku.zhangqixun.miniweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class Welcome extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private List<View> views;

    private ImageView[] dots;
    private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3};

    private Button btn;

    SharedPreferences asdf;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asdf=getSharedPreferences("showWelcome", Context.MODE_PRIVATE);
        editor=asdf.edit();
        if (asdf.contains("shownum")){
            setContentView(R.layout.weather_info);
            int num =asdf.getInt("shownum",0);
            editor.putInt("shownum",num++);
            editor.commit();
            Intent i = new Intent(Welcome.this,MainActivity.class);
            startActivity(i);
            finish();
        }else {
            editor.putInt("shownum",1);
            editor.commit();
            setContentView(R.layout.welcome);
            initViews();
        }

        initDots();

        btn=(Button) views.get(2).findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Welcome.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    void initDots(){
        dots = new ImageView[views.size()];
        for (int i=0;i<views.size();i++){
            dots[i]=(ImageView)findViewById(ids[i]);
        }

    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.page1,null));
        views.add(inflater.inflate(R.layout.page2,null));
        views.add(inflater.inflate(R.layout.page3,null));
        vpAdapter = new ViewPagerAdapter(views,this);
        vp=(ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int a=0;a<ids.length;a++){
            if (a==position){
                dots[a].setImageResource(R.drawable.magnifying_glass);
            }else {
                dots[a].setImageResource(R.drawable.contact_search_box_edittext_keyword_background);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
