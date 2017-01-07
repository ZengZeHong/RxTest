package com.rdc.zzh.rxtest.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.rdc.zzh.rxtest.R;
import com.rdc.zzh.rxtest.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText etAccount , etPassword , etCode;
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img_code);
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        etCode = (EditText) findViewById(R.id.et_code);

        mLoginPresenter = new LoginPresenter();
    }

    public void getCode(View view){
        mLoginPresenter.getImageCode(imageView);
    }

    public void login(View view){
        mLoginPresenter.login(etAccount.getText().toString() , etPassword.getText().toString() , etCode.getText().toString());
    }
}
