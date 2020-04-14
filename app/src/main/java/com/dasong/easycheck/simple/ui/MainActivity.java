package com.dasong.easycheck.simple.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dasong.easycheck.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_context_simple;
    private TextView tv_object_simple;
    private TextView tv_checker_simple;
    private TextView tv_callback_simple;
    private TextView tv_filter_simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_context_simple = findViewById(R.id.tv_context_simple);
        tv_object_simple = findViewById(R.id.tv_object_simple);
        tv_checker_simple = findViewById(R.id.tv_checker_simple);
        tv_callback_simple = findViewById(R.id.tv_callback_simple);
        tv_filter_simple = findViewById(R.id.tv_filter_simple);

        tv_context_simple.setOnClickListener(this);
        tv_object_simple.setOnClickListener(this);
        tv_checker_simple.setOnClickListener(this);
        tv_callback_simple.setOnClickListener(this);
        tv_filter_simple.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.tv_context_simple:
                intent= new Intent(this,ContextSimpleActivity.class);
                break;
            case R.id.tv_object_simple:
                intent= new Intent(this,ObjectSimpleActivity.class);
                break;
            case R.id.tv_checker_simple:
                intent= new Intent(this,CheckerActivity.class);
                break;
            case R.id.tv_callback_simple:
                intent= new Intent(this,CallbackSimpleActivity.class);
                break;
            case R.id.tv_filter_simple:
                intent= new Intent(this,FilterSimpleActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
