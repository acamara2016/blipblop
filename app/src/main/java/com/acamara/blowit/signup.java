package com.acamara.blowit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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


public class signup extends AppCompatActivity {
    public EditText username, email, password;
    private Button sign_button, test_button;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    FirebaseHandler db = new FirebaseHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        // getting all values
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressbar = findViewById(R.id.loading);
        String email, password;

        email = this.email.getText().toString();
        password = this.password.getText().toString();
        System.out.println(email);
        System.out.println(password);

    }

    public void register(View view)
    {
        String username, first_name, last_name, email, password;
        username = this.username.getText().toString();
        first_name = "blank";
        last_name = "blank";
        email = this.email.getText().toString();
        password = this.password.getText().toString();
        progressbar.setVisibility(View.VISIBLE);
        registerNewUser(username, first_name, last_name, email, password);
    }

    public void gotoLoginScreen(View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        },1000);
    }
    public void gotoLandingScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(signup.this, landingScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        },2000);
    }
    public void registerNewUser(final String u, final String f, final String l, final String e, final String p){
            mAuth.createUserWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {

                                FirebaseUser user = mAuth.getCurrentUser();
                                //list.add(new Substance("0","default",0.00));
                                db.AddUserInfo(new User(user.getUid(),u,f,l,e,p));
                                db.AddDefaultSubstance(user.getUid());
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                gotoLandingScreen();


                            } else
                            {

                                Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                //progressbar.setVisibility(View.GONE);

                            }

                            // ...
                        }
                    });
        }




}




