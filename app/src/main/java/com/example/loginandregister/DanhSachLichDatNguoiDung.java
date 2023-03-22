package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLichDatNguoiDung extends AppCompatActivity {
    private RecyclerView recyclerviewLichDat;
    private LichDatNguoiDungAdapter mLichDatAdapter;
    private List<LichDat> mListLichDat;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String emailnd = user.getEmail();

    ImageView trove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lich_dat_nguoi_dung2);

        trove= findViewById(R.id.id_trove);
        recyclerviewLichDat = findViewById(R.id.recyclerViewLichDat);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerviewLichDat.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerviewLichDat.addItemDecoration(dividerItemDecoration);
        mListLichDat = new ArrayList<>();
        mLichDatAdapter = new LichDatNguoiDungAdapter(mListLichDat);
        recyclerviewLichDat.setAdapter(mLichDatAdapter);
        getListLichDatFromDatabase();

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhSachLichDatNguoiDung.this, MainActivity.class));
                finish();
            }
        });
    }

    private void getListLichDatFromDatabase(){
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference dr = fd.getReference("Thongtinlichdat");

        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LichDat lichdat = snapshot.getValue(LichDat.class);
                if(lichdat != null){
                    if(lichdat.getEmail().compareTo(emailnd)==0){
                        mListLichDat.add(lichdat);
                    }else{
                        Toast.makeText(DanhSachLichDatNguoiDung.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    mLichDatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        dr.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data : snapshot.getChildren()){
//                    LichDat lichdat = data.getValue(LichDat.class);
//                    mListLichDat.add(lichdat);
//                }
//                mLichDatAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(DanhSachLichDatNguoiDung.this, "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
