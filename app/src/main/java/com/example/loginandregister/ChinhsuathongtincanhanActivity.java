package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChinhsuathongtincanhanActivity extends AppCompatActivity {

    ImageView trove;
    EditText email,name,dienthoai,matkhau;
    Button capnhatthongtin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsuathongtincanhan);

        trove= findViewById(R.id.id_trove);
        email=findViewById(R.id.id_emailkhachhang);
        name=findViewById(R.id.id_name);
        dienthoai=findViewById(R.id.id_sdt);
        matkhau=findViewById(R.id.id_matkhau);
        capnhatthongtin=findViewById(R.id.capnhat);

        Hienthithongtin();

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChinhsuathongtincanhanActivity.this, ThongtincanhanActivity.class));
                finish();
            }
        });
    }

    private void Hienthithongtin() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        email.setText(user.getEmail());
        name.setText(user.getDisplayName());
        dienthoai.setText(user.getPhoneNumber());

    }
}