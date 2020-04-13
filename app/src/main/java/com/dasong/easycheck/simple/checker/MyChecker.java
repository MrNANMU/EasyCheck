package com.dasong.easycheck.simple.checker;

import android.view.View;

import com.dasong.easycheck.ioc.IChecker;

public class MyChecker extends IChecker {
    @Override
    public boolean check(View v) {
        return true;
    }

    @Override
    public String getToast() {
        return "自定义检查器";
    }
}
