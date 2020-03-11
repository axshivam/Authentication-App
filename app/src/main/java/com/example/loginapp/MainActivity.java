package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailId,password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId =(EditText) findViewById(R.id.emailId);
        password = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if(email.isEmpty())
                {
                    emailId.setError("please enter the email Id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("please enter the password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"SignUp Unsuccessful, plz try again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
