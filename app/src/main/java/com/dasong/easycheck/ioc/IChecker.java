package com.dasong.easycheck.ioc;

import android.view.View;

public abstract class IChecker {
    String toast;
    /**
     * return true if you wang to get a toast
     * @param v
     * @return
     */
    abstract public boolean check(View v);
    public void setToast(String toast){
        this.toast = toast;
    }
    public String getToast(){
        return toast;
    }
}
