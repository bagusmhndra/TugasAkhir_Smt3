package com.example.e_foodcourtapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_foodcourtapp.R;

import org.w3c.dom.Text;

public class Login_Activity extends AppCompatActivity {
    EditText username, password;
    TextView register;
    ConstraintLayout login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.btnRegister);

        preferences = getSharedPreferences("Userinfo", 0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                String registeredUsername = preferences.getString("username", "");
                String registeredPassword = preferences.getString("password", "");

                if (usernameValue.equals(registeredUsername) && passwordValue.equals(registeredPassword)){
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    Toast.makeText(Login_Activity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login_Activity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this,Register_Activity.class);
                startActivity(intent);
            }
        });


    }

}
