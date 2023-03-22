package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class TrangChuNhanVienActivity extends AppCompatActivity {
    ImageView thongtin,lich;
    Button dangxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_nhan_vien);
        thongtin=findViewById(R.id.imgthongtin);
        lich=findViewById(R.id.imglich);
        dangxuat=findViewById(R.id.dangxuat);

        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(TrangChuNhanVienActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuNhanVienActivity.this, ThongTinNhanVienActivity.class));
                finish();

            }
        });
    }
}