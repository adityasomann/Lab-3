package lab3_email.tutorial.cs5511.com.lab3email;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private Button register;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.emailregist);
        password = (EditText)findViewById(R.id.passwordregist);

        register = (Button)findViewById(R.id.registerBtn);

        mAuthlistnr = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerFn();
            }
        });

    }

    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthlistnr);
    }

    private void registerFn()
    {
        String emailid = email.getText().toString();
        String pwd = password.getText().toString();

        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait till registration is complete...", "Processing", true);
        mAuth.createUserWithEmailAndPassword(emailid, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful())
                {
                    Toast.makeText(RegistrationActivity.this, "Registration Succesful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

                }

                else
                {
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
