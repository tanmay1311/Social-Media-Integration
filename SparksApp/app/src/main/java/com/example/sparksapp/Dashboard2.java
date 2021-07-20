package com.example.sparksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard2 extends AppCompatActivity {
    ImageView img;
    TextView name,email;
    Button signout;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        img=findViewById(R.id.img);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        signout=findViewById(R.id.signout);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!= null)
        {
            Glide.with(Dashboard2.this )
                    .load(firebaseUser.getPhotoUrl())
                    .into(img);
            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"Sign Out is Successful",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        if(firebaseAuth.getCurrentUser()==null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }



}