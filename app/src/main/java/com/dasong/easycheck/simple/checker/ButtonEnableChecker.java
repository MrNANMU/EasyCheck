package com.dasong.easycheck.simple.checker;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dasong.easycheck.ioc.IChecker;

public class ButtonEnableChecker extends IChecker {
    @Override
    public boolean check(View v) {
        EditText et = (EditText)v;
        return TextUtils.isEmpty(et.getText());
    }
}
