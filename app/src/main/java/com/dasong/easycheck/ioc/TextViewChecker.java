package com.dasong.easycheck.ioc;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class TextViewChecker extends IChecker{
    @Override
    public boolean check(View v) {
        if(v instanceof TextView){
            TextView tv = (TextView)v;
            return TextUtils.isEmpty(tv.getText());
        }
        return false;
    }
}
