package com.allure.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.allure.common.CustomTabLayout;
import com.allure.common.bean.TabInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   private CustomTabLayout customTabLayout;

    private int[] normalResId = {
            R.mipmap.button_album,
            R.mipmap.button_audiobook,
            R.mipmap.button_home,
            R.mipmap.button_user,
            R.mipmap.button_video
    };
    private int[] selectResId = {
            R.mipmap.button_album_click,
            R.mipmap.button_audiobook_click,
            R.mipmap.button_home_click,
            R.mipmap.button_user_click,
            R.mipmap.button_video_click
    };
    private static final String[] strs = new String[]{
            "首页", "还好", "嘿嘿", "较好", "嗯嗯"
    };
    private int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        customTabLayout = (CustomTabLayout) findViewById(R.id.custom_tablayout);
        //strs:文字描述  5：有5个Tab  0：默认选中的是第一个
        TabInfo.TabInfoBuilder tabInfoBuilder = new TabInfo.TabInfoBuilder(strs,count, 0);
        List<TabInfo> tabInfos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            TabInfo tabInfo = tabInfoBuilder
                    .setNormalResId(normalResId[i])
                    .setSelectResId(selectResId[i])
                    .setNormalTextSizeSp(12)
                    .setSelectTextSizeSp(12)
                    .setNormalTextColor(getResources().getColor(R.color.colorAccent))
                    .setSelectTextColor(getResources().getColor(R.color.colorPrimaryDark))
                    .build();
            tabInfos.add(tabInfo);
        }
//        customTabLayout.createTab(tabInfos);
//        customTabLayout.addTab(tabInfos,R.layout.tablayout_item);
        customTabLayout.addTab(tabInfos);

        customTabLayout.setOnTabOnclickListener(new CustomTabLayout.onTabOnclickListener() {
            @Override
            public void onTabClick(int position, ImageView imageView, TextView textView) {
//                imageView.setImageResource(R.mipmap.ic_launcher);
//                textView.setText("123");
//                Toast.makeText(MainActivity.this,position+"",1).show();
            }
        });
    }
}
