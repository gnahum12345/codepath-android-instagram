package me.gnahum12345.instagram;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;
    EditText etEmail;
    EditText etEmailConfirm;
    EditText etPasswordConfirm;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etConfirmPassword);
        etEmailConfirm = findViewById(R.id.etConfirmEmail);

        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canSignUp()) {
                    String email = etEmail.getText().toString();
                    String userName = etUserName.getText().toString();
                    String password = etPasswordConfirm.getText().toString();
                    createUser(email, userName, password);
                } else {
                    showError("There is a problem with your username, password or email...\n\n please check the fields again. ");
                }
            }
        });
    }

    private boolean canSignUp() {
        return (checkField(etUserName) && checkField(etEmailConfirm, etEmail) && checkField(etPassword, etPasswordConfirm));
    }

    private boolean checkField(EditText et1, EditText et2) {
        if (checkField(et1) && checkField(et2))
            return et1.getText().toString().equals(et2.getText().toString());
        else
            return false;
    }

    private boolean checkField(EditText et) {
        return (et != null && !et.getText().toString().equals(""));
    }

    private void createUser(String email, String userName, String password) {
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(userName);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    showError("There was an error in creating your account. Please try again");
                }
            }
        });
    }

    private void showError(String message) {
        new AlertDialog.Builder(this).setTitle("404 Error")
                .setMessage(message)
                .setPositiveButton("Ok", null)
                .show();
    }
}

