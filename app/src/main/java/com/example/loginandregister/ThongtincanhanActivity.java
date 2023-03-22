package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ThongtincanhanActivity extends AppCompatActivity {
    ImageView trove;
    TextView email,name, sdt, maukhau;
//    Button btnchinhsuathongtin;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);

        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        trove=findViewById(R.id.id_trove);
        email=findViewById(R.id.id_emailkhachhang);
        name= findViewById(R.id.id_name);
        sdt=findViewById(R.id.id_sdt);
        maukhau=findViewById(R.id.id_matkhau);
//        btnchinhsuathongtin=findViewById(R.id.id_chinhsuathongtincanhan);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongtincanhanActivity.this, MainActivity.class));
                finish();

            }
        });
//        btnchinhsuathongtin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ThongtincanhanActivity.this, ChinhsuathongtincanhanActivity.class));
//                finish();
//            }
//        });
        DocumentReference documentReference= fStore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("Email"));
                name.setText(value.getString("ten"));
                sdt.setText(value.getString("sodienthoai"));
                maukhau.setText(value.getString("Matkhau"));
            }
        });
//        btnchinhsuathongtin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ThongtincanhanActivity.this, ChinhsuathongtincanhanActivity.class));
//                finish();
//            }
//        });
    }





}