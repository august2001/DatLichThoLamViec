package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChitietdonhangActivity extends AppCompatActivity {
    TextView txtdichvu,txtdiachi,txtnoidung,txtngaydat,txtsdt, txtemailnv, txtemail;
    Button capnhat,trove,xoadon;
    CheckBox chuaxn, daxn, chuaht, daht;

    private String key;
    private String diachi;
    private String dichvu;
    private String ngaydat;
    private String noidung;
    private String sodienthoai;
    private String emailnv;
    private String hoanthanh;
    private String xacnhan;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietdonhang);

        key=getIntent().getStringExtra("key");
        diachi=getIntent().getStringExtra("diachi");
        dichvu=getIntent().getStringExtra("dichvu");
        ngaydat=getIntent().getStringExtra("ngaydat");
        noidung=getIntent().getStringExtra("noidung");
        sodienthoai=getIntent().getStringExtra("sodienthoai");
        email = getIntent().getStringExtra("email");
        emailnv = getIntent().getStringExtra("emailnv");
        hoanthanh = getIntent().getStringExtra("hoanthanh");
        xacnhan = getIntent().getStringExtra("xacnhan");

        txtdichvu=findViewById(R.id.dichvu);
        txtdichvu.setText(dichvu);
        txtdiachi=findViewById(R.id.diachi);
        txtdiachi.setText(diachi);
        txtnoidung=findViewById(R.id.noidung);
        txtnoidung.setText(noidung);
        txtngaydat=findViewById(R.id.ngaydat);
        txtngaydat.setText(ngaydat);
        txtsdt=findViewById(R.id.sdt);
        txtsdt.setText(sodienthoai);
        txtemailnv = findViewById(R.id.emailnv);
        txtemailnv.setText(emailnv);
        txtemail = findViewById(R.id.email);
        txtemail.setText(email);

        chuaxn = findViewById(R.id.chuaxn);
        daxn = findViewById(R.id.daxn);
        chuaht = findViewById(R.id.chuaht);
        daht = findViewById(R.id.daht);

        capnhat=findViewById(R.id.btncapnhatthongtinkhachhang);
        trove=findViewById(R.id.btntrove);
        xoadon=findViewById(R.id.btnxoadonhang);

        if(hoanthanh.compareTo("Chưa hoàn thành") == 0){
            chuaht.setChecked(true);
            daht.setChecked(false);
        }else if(hoanthanh.compareTo("Đã hoàn thành") == 0){
            daht.setChecked(true);
            chuaht.setChecked(false);
        }
        if(xacnhan.compareTo("Chưa xác nhận") == 0){
            chuaxn.setChecked(true);
            daxn.setChecked(false);
        }else if(xacnhan.compareTo("Đã xác nhận") == 0){
            daxn.setChecked(true);
            chuaxn.setChecked(false);
        }

        chuaxn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                daxn.setChecked(false);
                chuaxn.setChecked(b);
            }
        });
        daxn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chuaxn.setChecked(false);
                daxn.setChecked(b);
            }
        });
        chuaht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                daht.setChecked(false);
                chuaht.setChecked(b);
            }
        });
        daht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chuaht.setChecked(false);
                daht.setChecked(b);
            }
        });

        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtemailnv.getText().toString().contains("@gmail.com")){
                    Toast.makeText(ChitietdonhangActivity.this, "Hãy nhập đúng email", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.fetchSignInMethodsForEmail(txtemailnv.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if (check) {
                                LichDat lichDat = new LichDat();
                                if (chuaxn.isChecked()) {
                                    lichDat.setXacnhan("Chưa xác nhận");

                                } else if (daxn.isChecked()) {
                                    lichDat.setXacnhan("Đã xác nhận");

                                }
                                if (chuaht.isChecked()) {
                                    lichDat.setHoanthanh("Chưa hoàn thành");

                                } else if (daht.isChecked()) {
                                    lichDat.setHoanthanh("Đã hoàn thành");

                                }
                                lichDat.setDiachi(txtdiachi.getText().toString());
                                lichDat.setDichvu(txtdichvu.getText().toString());
                                lichDat.setNoidung(txtnoidung.getText().toString());
                                lichDat.setNgaydat(txtngaydat.getText().toString());
                                lichDat.setSodienthoai(txtsdt.getText().toString());
                                lichDat.setEmailnv(txtemailnv.getText().toString());
                                lichDat.setEmail(txtemail.getText().toString());
                                new FirebaseDatabaseHelper().updateLichDat(key, lichDat, new FirebaseDatabaseHelper.DataStatus() {
                                    @Override
                                    public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {

                                    }

                                    @Override
                                    public void DataIsInserter() {


                                    }

                                    @Override
                                    public void DataIsUpdated() {
                                        Toast.makeText(ChitietdonhangActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void DataIsDelete() {

                                    }
                                });
                            } else {
                                Toast.makeText(ChitietdonhangActivity.this, "Không tồn tại nhân viên với email này", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();return;
            }
        });
        xoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteLichDat(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserter() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDelete() {
                        Toast.makeText(ChitietdonhangActivity.this, "Xóa đơn hàng thành công", Toast.LENGTH_SHORT).show();
                        finish();return;

                    }
                });
            }
        });


    }
//
}