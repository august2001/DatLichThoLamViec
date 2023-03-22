package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKiNhanVien extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText hotenedit,phoneedit,passedit, repass, email;
    Spinner congviec;
    Button dangky, quaylai;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;


    boolean valid=true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_nhan_vien);
        hotenedit=findViewById(R.id.id_name);
        congviec= findViewById(R.id.id_cv);
        email= findViewById(R.id.id_email);
        phoneedit= findViewById(R.id.id_phone);
        passedit = findViewById(R.id.id_pass);
        repass = findViewById(R.id.id_pass2);

        dangky = findViewById(R.id.btnDangky);
        quaylai = findViewById(R.id.btnquaylai);
        mAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.cong_viec,R.layout.mauspinnerlayout);
        adapter.setDropDownViewResource(R.layout.mauthaxuongspinner);
        congviec.setAdapter(adapter);
        congviec.setOnItemSelectedListener(this);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hotenedit.getText().toString().isEmpty() || passedit.getText().toString().isEmpty() || phoneedit.getText().toString().isEmpty() || email.getText().toString().isEmpty() || repass.getText().toString().isEmpty()){
                    Toast.makeText(DangKiNhanVien.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else if(phoneedit.getText().toString().length() != 10){
                    Toast.makeText(DangKiNhanVien.this, "Hãy nhập số điện thoại hợp lệ", Toast.LENGTH_SHORT).show();
                }else if(!email.getText().toString().contains("@gmail.com")){
                    Toast.makeText(DangKiNhanVien.this, "Hãy nhập email hợp lệ", Toast.LENGTH_SHORT).show();
                }else if(!passedit.getText().toString().equals(repass.getText().toString())){
                    Toast.makeText(DangKiNhanVien.this, "Mật khẩu nhập lại không hợp lệ", Toast.LENGTH_SHORT).show();
                }else if(passedit.getText().toString().length()<7){
                    Toast.makeText(DangKiNhanVien.this, "Mật khẩu phải trên 6 kí tự", Toast.LENGTH_SHORT).show();
                }
                if(valid){
                    mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if(!check){
                                mAuth.createUserWithEmailAndPassword(email.getText().toString(),passedit.getText().toString())
                                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                            @Override
                                            public void onSuccess(AuthResult authResult) {
                                                FirebaseUser user = mAuth.getCurrentUser();

                                                Toast.makeText(DangKiNhanVien.this, "Đã tạo tài khoản", Toast.LENGTH_SHORT).show();
                                                DocumentReference df = fStore.collection("User").document(user.getUid());
                                                Map<String,Object> userInfo = new HashMap<>();
                                                userInfo.put("ten", hotenedit.getText().toString());
                                                userInfo.put("Email", email.getText().toString());
                                                userInfo.put("Congviec", congviec.getSelectedItem().toString());
                                                userInfo.put("sodienthoai", phoneedit.getText().toString());
                                                userInfo.put("Matkhau", passedit.getText().toString());
                                                // ngừoi dùng là Admin;
                                                userInfo.put("isEmpl","1");
                                                df.set(userInfo);
                                                startActivity(new Intent(getApplicationContext(),TrangchuAdminActivity.class));
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(DangKiNhanVien.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }else{
                                Toast.makeText(DangKiNhanVien.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKiNhanVien.this,TrangchuAdminActivity.class));
            }
        });
    }
    //chọn item spinner cong việc

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}