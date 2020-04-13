package com.dasong.easycheck.simple.holder;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.dasong.easycheck.ioc.Check;
import com.dasong.easycheck.ioc.CheckHelper;
import com.dasong.easycheck.ioc.CheckerException;

public class ViewHolder {
    @Check(toast = "EditText未输入内容")
    private EditText et;
    @Check(toast = "TextView为空")
    private TextView tv;

    public ViewHolder(EditText et, TextView tv) {
        this.et = et;
        this.tv = tv;
        CheckHelper.bind(this);
    }

    public boolean check(Context context){
        try {
            return CheckHelper.check(context,this);
        }catch (CheckerException e){
            e.printStackTrace();
            return false;
        }
    }

    public void unbind(){
        CheckHelper.unbind(this);
    }
}
