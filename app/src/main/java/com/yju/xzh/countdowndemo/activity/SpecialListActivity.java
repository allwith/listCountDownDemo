package com.yju.xzh.countdowndemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.yju.xzh.countdowndemo.R;
import com.yju.xzh.countdowndemo.base.BaseActivity;
import com.yju.xzh.countdowndemo.fragment.SpecialListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/8/29.
 * 超值特卖列表
 */
public class SpecialListActivity extends BaseActivity {

    @BindView(R.id.title_more)
    ImageView titleMore;
    @BindView(R.id.radio_special)
    RadioButton radioSpecial;
    @BindView(R.id.radio_clear)
    RadioButton radioClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_list_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTab();
    }

    private void initTab() {
        initTab(1);
    }

    private void initTab(int type) {
        SpecialListFragment fragment = SpecialListFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .commit();
    }

    @OnClick({R.id.radio_special, R.id.radio_clear})
    public void radioClick(View view) {
        switch (view.getId()) {
            case R.id.radio_special:
                initTab(1);
                break;
            case R.id.radio_clear:
                initTab(2);
                break;
        }
    }


    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.title_more)
    public void moreClick() {

    }


    public static void open(Context context) {
        Intent in = new Intent(context, SpecialListActivity.class);
        context.startActivity(in);

    }
}
