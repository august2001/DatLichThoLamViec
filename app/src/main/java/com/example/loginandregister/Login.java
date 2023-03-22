package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-b5e1d-default-rtdb.firebaseio.com");

    EditText email, matkhau;
    Button Login;
    TextView dangky,forgotpass;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    boolean valid= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.id_email);
        matkhau = findViewById(R.id.id_pass);
        Login = findViewById(R.id.btnLogin);
        dangky = findViewById(R.id.tvDangky);
        forgotpass=findViewById(R.id.forgotpassword);

        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản của bạn", Toast.LENGTH_SHORT).show();
                }else if(matkhau.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Vui lòng nhập mật khẩu của bạn", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(valid){
                        mAuth.signInWithEmailAndPassword(email.getText().toString(),matkhau.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        checkUserAccessLever(authResult.getUser().getUid());

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                }

            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail= new EditText(view.getContext());
                AlertDialog.Builder passwordResetDiaLog= new AlertDialog.Builder(view.getContext());
                passwordResetDiaLog.setTitle("Đặt lại mật khẩu ?");
                passwordResetDiaLog.setMessage("Nhập Mail của bạn để nhận liên kết đặt lại");
                passwordResetDiaLog.setView(resetMail);


                passwordResetDiaLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //trích xuất mail và gửi liên kết đặt lại
                        String mail= resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Login.this, "Đặt lại liên kết gửi đến mail của bạn", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Liên kết không được gửi"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDiaLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Đóng hộp thoại
                    }
                });
                passwordResetDiaLog.create().show();
            }
        });


    }
//phân quyền
    private void checkUserAccessLever(String uid) {
        DocumentReference df= fStore.collection("User").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSucces: "+documentSnapshot.getData());
                //xác định cấp độ truy cập ngừoi dùng

                if(documentSnapshot.getString("isAdmin")!=null){
                    startActivity(new Intent(getApplicationContext(),TrangchuAdminActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isUser")!=null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }
                if (documentSnapshot.getString("isEmpl")!=null){
                    startActivity(new Intent(getApplicationContext(),TrangChuNhanVienActivity.class));
                    finish();

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            DocumentReference df= FirebaseFirestore.getInstance().collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("isUser")!=null){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isAdmin")!=null){
                        startActivity(new Intent(getApplicationContext(),TrangchuAdminActivity.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isEmpl")!=null){
                        startActivity(new Intent(getApplicationContext(),TrangChuNhanVienActivity.class));
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
            });
        }
    }
}


