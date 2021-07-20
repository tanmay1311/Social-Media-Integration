package com.example.sparksapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {
    ImageView img;
    TextView name,email;
    Button signout;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        img=findViewById(R.id.img);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        signout=findViewById(R.id.signout);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!= null)
        {
            Glide.with(Dashboard.this )
                    .load(firebaseUser.getPhotoUrl())
                    .into(img);
            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
        }
        googleSignInClient= GoogleSignIn.getClient(Dashboard.this, GoogleSignInOptions.DEFAULT_SIGN_IN);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        firebaseAuth.signOut();
                            Toast.makeText(getApplicationContext(),"Sign Out is Successful",Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                });
            }
        });
    }
}