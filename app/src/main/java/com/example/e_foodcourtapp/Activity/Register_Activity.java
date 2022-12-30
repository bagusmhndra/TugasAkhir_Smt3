package com.example.e_foodcourtapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_foodcourtapp.R;

public class Register_Activity extends AppCompatActivity {
    EditText username, password;
    ConstraintLayout register;
    TextView login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.loginBtn);

        preferences = getSharedPreferences("Userinfo", 0);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if (usernameValue.length() > 1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", usernameValue);
                    editor.putString("password", passwordValue);
                    editor.apply();
                    Toast.makeText(Register_Activity.this, "User registered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register_Activity.this, "Enter Value in the fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}
