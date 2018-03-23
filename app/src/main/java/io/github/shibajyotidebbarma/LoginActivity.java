package io.github.shibajyotidebbarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginPasswordText;
    private Button loginButton;
    private Button loginRegisterButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        //EDIT TEXT FIELDS
        loginEmailText = (EditText) findViewById(R.id.loginPageEmail);
        loginPasswordText = (EditText) findViewById(R.id.loginPagePassword);

        //BUTTONS
        loginButton = (Button) findViewById(R.id.loginPageLoginButton);
        loginRegisterButton =(Button) findViewById(R.id.loginPageRegisterButton);



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if(currentUser!=null){

            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();

        }



    }
}
