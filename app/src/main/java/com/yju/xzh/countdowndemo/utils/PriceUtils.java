package com.yju.xzh.countdowndemo.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by user on 2016/8/29.
 */
public class PriceUtils {

    public static String priceDoubleNoDot(double data) {
        BigDecimal bdRet = new BigDecimal(data);
        String price = bdRet.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        int dotIndex=price.indexOf(".");
        price=price.substring(0,dotIndex);
        return price;
    }

    public static void convertPriceSize(Context context, TextView tv, String money, int fontSize) {
        Spannable spannable = new SpannableString(money);
        if (money.contains(".")){
            int indexDot = money.indexOf(".");
            spannable.setSpan(new AbsoluteSizeSpan(UIUtils.dp2px(context, fontSize)), indexDot, money.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spannable.setSpan(new AbsoluteSizeSpan(UIUtils.dp2px(context, fontSize)), 1, money.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(spannable);
    }

    public static void formatPriceSpannable(int size, String price, TextView label) {
        if (TextUtils.isEmpty(price)) return;
        Spannable priceSpan = new SpannableString(price);
        int start = price.indexOf("￥");
        int end = price.indexOf(".");
        if (start != -1 && end != -1){
            priceSpan.setSpan(new AbsoluteSizeSpan(size), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        label.setText(priceSpan);
    }


    /**
     * @param moneyFontSize 形如：￥1000.00（羊角符号和小数点后面的字体大小一样）
     */
    public static void formatPriceStyle(Context context, TextView tv, String money, int moneyFontSize) {
        int indexDot = money.indexOf(".");
        Spannable spannable = new SpannableString(money);
        spannable.setSpan(new AbsoluteSizeSpan(UIUtils.dp2px(context, moneyFontSize)), indexDot, money.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(UIUtils.dp2px(context, moneyFontSize)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }


    /**
     * 将价格转换成带有两位小数的String
     */
    public static String formatDoubleString(String s) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String doublString = "";
        if (null != s && !s.equals("")) {
            double mPrice = Double.valueOf(s);
            doublString = String.valueOf(df.format(mPrice));

        }
        return doublString;
    }
}
