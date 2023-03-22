package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CaNhanActivity extends AppCompatActivity {
    ImageView imgtrove;
    TextView thongtincanhan,lichdat,dangxuat;
    String dt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public  static final String USER="users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);
        Intent intent=getIntent();
         dt=intent.getStringExtra("phone");

        imgtrove= (ImageView) findViewById(R.id.id_trove);
        thongtincanhan= findViewById(R.id.txtthongtincanhan);
        lichdat=findViewById(R.id.txtthongtinlichdat);
        dangxuat= findViewById(R.id.txtdãnguat);
     //   diachi= findViewById(R.id.txtdiachi);

        imgtrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaNhanActivity.this, MainActivity.class));
                finish();
            }
        });
        thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaNhanActivity.this, ThongtincanhanActivity.class));
                finish();

            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaNhanActivity.this, Login.class));
                finish();
            }
        });

//        firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference(USER);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
////                    if(ds.child("phone").getValue().equals(sdt)){
////                        hoten.setText(ds.child("hoten").getValue(String.class));
////                        sdt.setText(dt);
////                        email.setText(ds.child("email").getValue(String.class));
//
//
//                    }
//
//
//         //           diachi.setText(ds.child("name").getValue(String.class));
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

}
