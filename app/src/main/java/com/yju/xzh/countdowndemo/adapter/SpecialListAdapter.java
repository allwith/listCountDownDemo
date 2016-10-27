package com.yju.xzh.countdowndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yju.xzh.countdowndemo.R;
import com.yju.xzh.countdowndemo.base.BasicAdapter;
import com.yju.xzh.countdowndemo.bean.SpecialSaleEntity;
import com.yju.xzh.countdowndemo.utils.TimeUtils;
import com.yju.xzh.countdowndemo.view.CountDownTask;
import com.yju.xzh.countdowndemo.view.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialListAdapter extends BasicAdapter<SpecialSaleEntity> {

    private Context context=null;
    private CountDownTask mCountDownTask=null;

    public SpecialListAdapter(Context context) {
        super();
        this.context=context;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
             view = LayoutInflater.from(context).inflate(R.layout.item_special_layout,null);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        initItemData(viewHolder, getItem(i));
        return view;
    }

    private void initItemData(ViewHolder holder, SpecialSaleEntity bean) {
        if (bean != null) {
            holder.itemImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(bean.pic).error(R.drawable.welfare_default_icon).into(holder.itemImage);
            holder.itemTitle.setText(bean.name);
            holder.itemPrice.setText("￥" + bean.id);

            //倒计时
            holder.itemCountdown.setLeftTime(TimeUtils.getCurrentLeftTime(bean.endtime));
            holder.itemCountdown.start();
            initClick(holder,bean);
        }
    }


    private void initClick(ViewHolder holder, SpecialSaleEntity bean) {
        holder.itemRushbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    class ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_mark)
        ImageView itemMark;
        @BindView(R.id.item_countdown)
        CountDownView itemCountdown;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_discount)
        TextView itemDiscount;
        @BindView(R.id.item_price)
        TextView itemPrice;
        @BindView(R.id.item_sell_count)
        TextView itemSellCount;
        @BindView(R.id.item_rushbuy)
        TextView itemRushbuy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
        }
    }
}
