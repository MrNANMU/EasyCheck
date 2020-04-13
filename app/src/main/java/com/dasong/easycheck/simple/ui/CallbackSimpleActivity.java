package com.dasong.easycheck.simple.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dasong.easycheck.R;
import com.dasong.easycheck.ioc.Check;
import com.dasong.easycheck.ioc.CheckHelper;
import com.dasong.easycheck.ioc.CheckerException;
import com.dasong.easycheck.simple.checker.MyChecker;

import androidx.appcompat.app.AppCompatActivity;

public class CallbackSimpleActivity extends AppCompatActivity {

    @Check(toast = "EditText")
    private EditText et;
    @Check(checker = MyChecker.class)
    private TextView tv;
    private TextView tv_fill;
    private TextView tv_check;
    private TextView tv_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        tv_fill = findViewById(R.id.tv_fill);
        tv_check = findViewById(R.id.tv_check);
        tv_filter = findViewById(R.id.tv_filter);
        tv_filter.setVisibility(View.VISIBLE);
        CheckHelper.bind(this);
        tv_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("EasyCheck");
            }
        });
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CheckHelper.checkForCallback(CallbackSimpleActivity.this, new CheckHelper.Callback() {
                        @Override
                        public void result(View view, boolean checkResult, String toast) {
                            if(checkResult){
                                Toast.makeText(CallbackSimpleActivity.this,"此View为空->"+toast,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (CheckerException e) {
                    e.printStackTrace();
                }
            }
        });
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CheckHelper.checkForCallback(CallbackSimpleActivity.this, MyChecker.class, new CheckHelper.Callback() {
                        @Override
                        public void result(View view, boolean checkResult, String toast) {
                            if(checkResult){
                                Toast.makeText(CallbackSimpleActivity.this,"此View为空->"+toast,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (CheckerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CheckHelper.unbind(this);
    }
}
