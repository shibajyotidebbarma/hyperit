package io.github.shibajyotidebbarma;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginPasswordText;
    private Button loginButton;
    private Button loginRegisterButton;
    private ProgressBar loginProgress;

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

        loginProgress = (ProgressBar) findViewById(R.id.loginProgress);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String loginEmail = loginEmailText.getText().toString();
                String loginPassword = loginPasswordText.getText().toString();


                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPassword)){


                    loginProgress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                sendToMain();

                            }

                            else{

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this,"Error: "+errorMessage, Toast.LENGTH_LONG).show();


                            }
                            loginProgress.setVisibility(View.INVISIBLE);

                        }
                    });

                }

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if(currentUser!=null){

            sendToMain();

        }



    }

    private void sendToMain() {

        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
