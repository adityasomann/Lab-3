package lab3_email.tutorial.cs5511.com.lab3email;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pwd;

    private Button login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.emailTxt);
        pwd = (EditText)findViewById(R.id.pwdTxt);

        login = (Button)findViewById(R.id.exUser);

        mAuthListn = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, AccntActivity.class));

                }
            }
        };

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                signin();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListn);
    }

    private void signin()
    {

        String emailid = email.getText().toString();
        String password = pwd.getText().toString();

        if(TextUtils.isEmpty(emailid) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Email and/or password fields are blank", Toast.LENGTH_LONG).show();
        }

        else
        {

            mAuth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful())
                {

                    Toast.makeText(LoginActivity.this, "Username/Password is incorrect", Toast.LENGTH_LONG).show();

                }

                }

            });
        }




    }
}
