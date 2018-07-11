package me.gnahum12345.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import me.gnahum12345.instagram.R;

public class MainActivity extends AppCompatActivity {

    EditText etEmailLogin;
    EditText etPasswordLogin;
    Button btnLogin;
    Button btnSignUp;

    private static String TAG = "LoginActivityTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailInput = etEmailLogin.getText().toString();
                final String passwordInput = etPasswordLogin.getText().toString();

                login(emailInput, passwordInput);
            }
        });
        if (ParseUser.getCurrentUser() != null) {
            switchActivity();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void login(String username, String password) {
        //TODO Later
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Login unsuccessful");
                    e.printStackTrace();
                } else {
                    Log.d(TAG, "Login successful");
                    switchActivity();
                }
            }
        });
    }

    private void switchActivity() {
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
