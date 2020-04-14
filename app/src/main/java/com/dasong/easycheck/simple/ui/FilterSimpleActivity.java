package com.dasong.easycheck.simple.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dasong.easycheck.R;
import com.dasong.easycheck.ioc.Check;
import com.dasong.easycheck.ioc.CheckHelper;
import com.dasong.easycheck.ioc.CheckerException;
import com.dasong.easycheck.simple.checker.ButtonEnableChecker;
import com.dasong.easycheck.simple.checker.MyChecker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class FilterSimpleActivity extends AppCompatActivity implements TextWatcher {

    @Check(checker = ButtonEnableChecker.class)
    private EditText et;
    @Check(checker = ButtonEnableChecker.class)
    private EditText et2;
    @Check(checker = ButtonEnableChecker.class)
    private EditText et3;
    private TextView tv_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple2);
        et = findViewById(R.id.et);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et.addTextChangedListener(this);
        et2.addTextChangedListener(this);
        et3.addTextChangedListener(this);
        tv_filter = findViewById(R.id.tv_filter);
        CheckHelper.bind(this);
        tv_filter.setEnabled(false);
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FilterSimpleActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CheckHelper.unbind(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void afterTextChanged(Editable s) {
        try {
            CheckHelper.notShowToastThisTime();
            boolean checkResult = CheckHelper.filter(FilterSimpleActivity.this, ButtonEnableChecker.class);
            Log.e("filter",""+checkResult);
            tv_filter.setEnabled(checkResult);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tv_filter.setBackground(getDrawable(checkResult?R.drawable.shape_button:R.drawable.shape_button2));
            }
        } catch (CheckerException e) {
            e.printStackTrace();
        }
    }
}
