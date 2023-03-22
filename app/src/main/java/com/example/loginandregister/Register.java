package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class Register extends AppCompatActivity {
 //   DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-b5e1d-default-rtdb.firebaseio.com");
    EditText hotenedit,phoneedit,passedit,email, repass;
    Button dangky;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;


    boolean valid=true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
//mới

        hotenedit=findViewById(R.id.id_name);
        email= findViewById(R.id.id_email);
        phoneedit= findViewById(R.id.id_phone);
        passedit= findViewById(R.id.id_pass);
        repass = findViewById(R.id.id_pass2);

        dangky= findViewById(R.id.btnDangky);
        TextView dangnhap= findViewById(R.id.tv_dangnhap);
        mAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
//Đăng kí Email Fire Store

            dangky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(hotenedit.getText().toString().isEmpty() || passedit.getText().toString().isEmpty() || phoneedit.getText().toString().isEmpty() || email.getText().toString().isEmpty() || repass.getText().toString().isEmpty()){
                        Toast.makeText(Register.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else if(phoneedit.getText().toString().length() != 10){
                        Toast.makeText(Register.this, "Hãy nhập số điện thoại hợp lệ", Toast.LENGTH_SHORT).show();
                    }else if(!email.getText().toString().contains("@gmail.com")){
                        Toast.makeText(Register.this, "Hãy nhập email hợp lệ", Toast.LENGTH_SHORT).show();
                    }else if(!passedit.getText().toString().equals(repass.getText().toString())){
                        Toast.makeText(Register.this, "Mật khẩu nhập lại không hợp lệ", Toast.LENGTH_SHORT).show();
                    }else if(passedit.getText().toString().length()<7){
                        Toast.makeText(Register.this, "Mật khẩu phải trên 6 kí tự", Toast.LENGTH_SHORT).show();
                    }
                    else{
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
                                                        FirebaseUser user=mAuth.getCurrentUser();
                                                        Toast.makeText(Register.this, "Đã tạo tài khoản", Toast.LENGTH_SHORT).show();

                                                        DocumentReference df=fStore.collection("User").document(user.getUid());
                                                        Map<String,Object>userInfo= new HashMap<>();
                                                        userInfo.put("ten", hotenedit.getText().toString());
                                                        userInfo.put("Email", email.getText().toString());
                                                        userInfo.put("sodienthoai", phoneedit.getText().toString());
                                                        userInfo.put("Matkhau", passedit.getText().toString());
                                                        userInfo.put("isUser","1");
                                                        df.set(userInfo);
                                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(Register.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
            });




//               String hotentxt= hoten.getText().toString();
//     //           String emailtxt= email.getText().toString();
//                String phonetxt= phone.getText().toString();
//                String passtxt=pass.getText().toString();
//   //             String conpasstxt= conpass.getText().toString();
//                Map<String,Object>user= new HashMap<>();
//                user.put("Họ Tên", hotentxt);
//   //             user.put("Email", emailtxt);
//                user.put("Phone", phonetxt);
//                user.put("Password", passtxt);

//
//    Đưa dữ liệu lên ReadtimeDataBase
//               if(hotentxt.isEmpty()|| phonetxt.isEmpty()|| passtxt.isEmpty()) {
//                   Toast.makeText(Register.this, "Bạn chua điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//
//               } else {
//                    // đăng kí bằng OTP
//                  firebaseDatabase= FirebaseDatabase.getInstance();
//                  databaseReference1=firebaseDatabase.getReference("users");
//               String name= hotenedit.getText().toString();
//              String emailtxt= email.getText().toString();
//               String phone= phoneedit.getText().toString();
//               String password=passedit.getText().toString();
//
//               if(name.isEmpty()||phone.isEmpty()||password.isEmpty()){
//                   Toast.makeText(Register.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//               }
//               else{
//                   Intent intent= new Intent(getApplicationContext(),VerifyPhoneNo.class);
//                   intent.putExtra("phonetxt", phone);
//                   startActivity(intent);
//                   NguoiDung nguoiDung= new NguoiDung(phone,name, password,emailtxt);
////               databaseReference1.setValue(nguoiDung);
//
////                String conpasstxt= conpass.getText().toString();
////
//                   databaseReference1.child(phone).setValue(nguoiDung);
//                   Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                   finish();
//               }

//hết đăng kí OTP


//
//                   databaseReference.child("uesrs").addListenerForSingleValueEvent(new ValueEventListener() {
//                       @Override
//                       public void onDataChange(@NonNull DataSnapshot snapshot) {
//                           if (snapshot.hasChild(phonetxt)) {
//                               //     kiếm tra điện thoại đc đăng ký chưa
//                               Toast.makeText(Register.this, "Điện thoại đã được đăng ký", Toast.LENGTH_SHORT).show();
//                           } else {
//                               //       gửi dữ liệu thời gian thực đến firebase
//                               //       tat cả điều dùng sô điện thoai
//                               databaseReference.child("uesrs").child(phonetxt).child("phone").setValue(phonetxt);
//
//                               databaseReference.child("uesrs").child(phonetxt).child("name").setValue(hotentxt);
//                               //                               databaseReference.child("uesrs").child(phonetxt).child("email").setValue(emailtxt);
//                               databaseReference.child("uesrs").child(phonetxt).child("password").setValue(passtxt);
//                               Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//
//                               finish();
//
//                           }
//                       }
//
//                       @Override
//                       public void onCancelled(@NonNull DatabaseError error) {
//
//                       }
//                   });
//
//
//             }
  //             }


        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });


    }
}