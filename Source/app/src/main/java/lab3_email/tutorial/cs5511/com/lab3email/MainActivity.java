package lab3_email.tutorial.cs5511.com.lab3email;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.transition.TransitionManager;


public class MainActivity extends AppCompatActivity {


    private Button newUsr;
    private Button exUsr;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListn;

    private Animation buttonAnim;

   // final Animation buttonAnim = AnimationUtils.loadAnimation(this, R.anim.button_anime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        newUsr = (Button) findViewById(R.id.newUser);
        exUsr = (Button) findViewById(R.id.exUser);

        buttonAnim = AnimationUtils.loadAnimation(this, R.anim.button_anime);

        mAuthListn = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        exUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                v.startAnimation(buttonAnim);
            }
        });

        newUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                v.startAnimation(buttonAnim);
            }
        });



    }


        /*mAuthListn = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                }
            }
        };*/


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListn);
    }
}
