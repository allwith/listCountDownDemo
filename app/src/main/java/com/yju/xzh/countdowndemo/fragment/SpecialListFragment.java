package com.yju.xzh.countdowndemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yju.xzh.countdowndemo.R;
import com.yju.xzh.countdowndemo.adapter.SpecialListAdapter;
import com.yju.xzh.countdowndemo.base.BaseFragment;
import com.yju.xzh.countdowndemo.bean.SpecialSaleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpecialListFragment extends BaseFragment implements PullToRefreshListView.OnRefreshListener2, AdapterView.OnItemClickListener {

    @BindView(R.id.refreshListView)
    PullToRefreshListView refreshListView;
    @BindView(R.id.goto_top)
    ImageView gotoTop;

    private int lastVisiblePosition = 0;
    private int type = 1;
    private SpecialListAdapter specialAdapter = null;
    private int page = 0;

    public static SpecialListFragment newInstance(int type) {
        SpecialListFragment fragment = new SpecialListFragment();
        fragment.setType(type);
        return fragment;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_list_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initView();
        initScroller();
        getData();
    }

    /*firstVisibleItem :表示在屏幕中第一条显示的数据在adapter中的位置,visibleItemCount 则是屏幕中最后一条数据在adapter中的数据,
    totalItemCount则是adapter中的总条数!*/
    private void initScroller() {
        refreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // 判断滚动到底部
//                if (refreshListView.getRefreshableView().getLastVisiblePosition() == (refreshListView.getRefreshableView().getCount() - 1)) {
//
//                }
//                // 判断滚动到顶部
//                if (refreshListView.getRefreshableView().getFirstVisiblePosition() <= 0) {
//                    gotoTop.setVisibility(View.VISIBLE);
//                } else {
//                    gotoTop.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > lastVisiblePosition) {
                    gotoTop.setVisibility(View.VISIBLE);
                } else if (firstVisibleItem < lastVisiblePosition) {
                    gotoTop.setVisibility(View.GONE);
                }
                lastVisiblePosition = visibleItemCount;
            }
        });
    }

    private void initView() {
        specialAdapter = new SpecialListAdapter(getActivity());
        refreshListView.setAdapter(specialAdapter);
        refreshListView.setOnRefreshListener(this);
        refreshListView.setOnItemClickListener(this);
        refreshListView.setScrollingWhileRefreshingEnabled(true);
    }

    private void getData() {
        List<SpecialSaleEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SpecialSaleEntity entity = new SpecialSaleEntity();
            entity.endtime = getRandom();
            entity.name = "澳洲进口牛奶德运牛奶1L";
            entity.discount = (int) (Math.random() * 10);
            entity.originPrice = 50;
            list.add(entity);
        }
        if (refreshListView.isRefreshing())
            refreshListView.onRefreshComplete();
        specialAdapter.addList(list);

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //上拉刷新
        page = 0;
        if (specialAdapter != null)
            specialAdapter.getList().clear();
        getData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //下拉
        page++;
        getData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @OnClick(R.id.goto_top)
    public void topClick() {
        refreshListView.getRefreshableView().setSelection(0);
    }


    private long getRandom() {
        int max = 10000;
        int min = 1000;
        Random random = new Random();
        long s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
