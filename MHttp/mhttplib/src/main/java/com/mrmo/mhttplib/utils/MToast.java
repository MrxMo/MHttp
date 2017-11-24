package com.mrmo.mhttplib.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mrmo.mhttplib.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by moguangjian on 2017/3/11.
 */

public class MToast {

    @Deprecated
    private static void showToast(Context context, String msg) {
        if (MStringUtil.isEmpty(msg)) {
            msg = "";
        }

        StyleableToast st = new StyleableToast(context, msg, Toast.LENGTH_SHORT);
        st.setBackgroundColor(Color.parseColor("#ff5a5f"));
        st.setTextColor(Color.WHITE);
//        st.setIcon(R.drawable.ic_autorenew_black_24dp);
        st.setMaxAlpha();
        st.show();
    }

    public static void show(Context context, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.http_toast_msg, null, false);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);

        TextView tvMsg = (TextView) toast.getView().findViewById(R.id.tvMsg);
        tvMsg.setText(msg);

        int roundRadius = dp2px(context, 4);
        GradientDrawable gd = new GradientDrawable();
        gd.setAlpha(200);
        gd.setColor(Color.parseColor("#000000"));
        gd.setCornerRadius(roundRadius);
        gd.setStroke(0, Color.parseColor("#000000"));
        tvMsg.setBackgroundDrawable(gd);

        toast.show();
    }

    private static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
