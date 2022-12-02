package com.example.appexam;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button sign;
    private Button guest;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private TextInputLayout email;
    private TextInputLayout password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign = (Button) findViewById(R.id.continueBttn);
        guest = (Button) findViewById(R.id.guestBttn);
        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        sign.setOnClickListener(onClick());
        guest.setOnClickListener(onClick());
        prefs = getApplicationContext().getSharedPreferences("preferences", 0);
        editor = prefs.edit();
    }

    private boolean checkEmail() {
        if (email.getEditText().getText().toString().equals(prefs.getString("email",null))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPwd() {
        if (password.getEditText().getText().toString().equals(prefs.getString("password",null))) {
            return true;
        } else {
            return false;
        }
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.continueBttn:
                        if (email.getEditText().getText().toString().isEmpty()) {
                            return;
                        } else if(password.getVisibility() == View.GONE) {
                            if (checkEmail()) {
                                password.setVisibility(View.VISIBLE);
                            }
                        } else if (checkEmail() && checkPwd()){
                            startActivity(new Intent(MainActivity.this, QuestionsActivity.class));
                        }
                        break;
                    case R.id.guestBttn:
                        if(email.getEditText().getText().toString().isEmpty()) {
                            return;
                        } else if(password.getVisibility() == View.GONE) {
                            editor.putString("email", email.getEditText().getText().toString());
                            password.setVisibility(View.VISIBLE);
                        } else {
                            editor.putString("password", password.getEditText().getText().toString());
                            editor.commit();
                            startActivity(new Intent(MainActivity.this, QuestionsActivity.class));
                        }
                        break;
                }
            }
        };
    }
}