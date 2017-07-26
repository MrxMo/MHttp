package com.mrmo.mhttplib.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by moguangjian on 2017/3/11.
 */

public class MToast {
    public static void show(Context context, String msg) {
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

}
