package com.allure.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allure.common.bean.TabInfo;
import com.allure.common.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allure on 2017/8/8.
 */

public class CustomTabLayout extends LinearLayout {

    private Context mContext;
    protected static final int LAYOUT_NONE = -1;

    private List<TabInfo> tabInfos = new ArrayList<>();
    private List<ImageView> imageViews = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();

    private int position = 0;//当前选中的position
    private int tabLayout = 0;

    private onTabOnclickListener onTabOnclickListener;


    public CustomTabLayout.onTabOnclickListener getOnTabOnclickListener() {
        return onTabOnclickListener;
    }

    public void setOnTabOnclickListener(CustomTabLayout.onTabOnclickListener onTabOnclickListener) {
        this.onTabOnclickListener = onTabOnclickListener;
    }


    public CustomTabLayout(Context context) {
        super(context);
        init(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initStyle(attrs, 0);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initStyle(attrs, defStyleAttr);
    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout, defStyleAttr, 0);
        tabLayout = typedArray.getResourceId(R.styleable.CustomTabLayout_tab_layout, LAYOUT_NONE);
        typedArray.recycle();
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.e(tabLayout + "");
    }

    //选中的Tab
    public void setSelectTab(int position) {
        //首先全部恢复状态
        for (int j = 0; j < tabInfos.size(); j++) {
            imageViews.get(j).setImageResource(tabInfos.get(j).getNormalResId());
            textViews.get(j).setTextColor(tabInfos.get(j).getNormalTextColor());
            setTextSizeofSp(textViews.get(j), tabInfos.get(j).getNormalTextSizeSp());
        }
        imageViews.get(position).setImageResource(tabInfos.get(position).getSelectResId());
        textViews.get(position).setTextColor(tabInfos.get(position).getSelectTextColor());
        setTextSizeofSp(textViews.get(position), tabInfos.get(position).getSelectTextSizeSp());
    }

    /**
     * 获取当前选中的position
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    public void addTab(List<TabInfo> tabInfos) {
        addTab(tabInfos, getItemLayout(tabLayout));
    }


    public void addTab(List<TabInfo> tabInfos, int layout) {
        this.tabInfos = tabInfos;
        tabLayout = layout;
        tabLayout = getItemLayout(tabLayout);
        if (tabLayout <= 0) {
            return;
        }
        for (int i = 0; i < tabInfos.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(tabLayout, null);
            RelativeLayout itemLayout = (RelativeLayout) view.findViewById(R.id.custom_tablayout_item);
            itemLayout.setGravity(Gravity.CENTER);
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT, 1.0f);
            itemLayout.setLayoutParams(layoutParams);

            TextView textView = (TextView) view.findViewById(R.id.custom_tablayout_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.custom_tablayout_image);

            imageViews.add(imageView);
            textViews.add(textView);
            //设置文字
            textView.setText(tabInfos.get(i).getTabText()[i]);
            if (position >= tabInfos.size() || position < 0) {
                position = 0;
            } else {
                position = tabInfos.get(i).getCurrentTab();
            }
            //选中的图片和颜色
            if (i == tabInfos.get(i).getCurrentTab()) {
                setTextSizeofSp(textView, tabInfos.get(i).getSelectTextSizeSp());
                textView.setTextColor(tabInfos.get(i).getSelectTextColor());
                imageView.setImageResource(tabInfos.get(i).getSelectResId());
            } else {
                setTextSizeofSp(textView, tabInfos.get(i).getNormalTextSizeSp());
                textView.setTextColor(tabInfos.get(i).getNormalTextColor());
                imageView.setImageResource(tabInfos.get(i).getNormalResId());
            }
            addView(view);


            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectTab(finalI);
                    onTabOnclickListener.onTabClick(finalI, imageViews.get(finalI), textViews.get(finalI));
                }
            });

        }


    }

    private int getItemLayout(int tabLayout) {
        if (tabLayout > 0) {
            return tabLayout;
        }
        return 0;
    }


    public void setTextSizeofSp(TextView textView, int sp) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public void setTextSizeofDip(TextView textView, int dip) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dip);
    }

    public void setTextSizeofPx(TextView textView, int px) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
    }

    //点击接口
    public interface onTabOnclickListener {
        void onTabClick(int position, ImageView imageView, TextView textView);
    }
}
