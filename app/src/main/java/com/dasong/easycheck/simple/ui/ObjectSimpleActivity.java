package com.dasong.easycheck.simple.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dasong.easycheck.R;
import com.dasong.easycheck.ioc.Check;
import com.dasong.easycheck.ioc.CheckHelper;
import com.dasong.easycheck.ioc.CheckerException;
import com.dasong.easycheck.simple.holder.ViewHolder;

import androidx.appcompat.app.AppCompatActivity;

public class ObjectSimpleActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private TextView tv_fill;
    private TextView tv_check;
    private ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        tv_fill = findViewById(R.id.tv_fill);
        tv_check = findViewById(R.id.tv_check);
        holder = new ViewHolder(et,tv);
        tv_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("EasyCheck");
            }
        });
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check(ObjectSimpleActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        holder.unbind();
    }
}
