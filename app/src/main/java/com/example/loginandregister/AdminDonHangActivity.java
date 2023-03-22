package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminDonHangActivity extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
   // AdminDonHangAdapter adminDonHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_don_hang);
        trove= findViewById(R.id.id_trove);
        recyclerView= findViewById(R.id.recyclerViewdonhang);

        new FirebaseDatabaseHelper().readDanhsach(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(recyclerView,AdminDonHangActivity.this,lichdat,keys);
            }

            @Override
            public void DataIsInserter() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDelete() {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonHangActivity.this, TrangchuAdminActivity.class));
                finish();
            }
        });
//        FirebaseRecyclerOptions<LichDat> options =
//                new FirebaseRecyclerOptions.Builder<LichDat>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat"), LichDat.class)
//                        .build();
//
//        adminDonHangAdapter= new AdminDonHangAdapter(options);
//        recyclerView.setAdapter(adminDonHangAdapter);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adminDonHangAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adminDonHangAdapter.stopListening();
//    }
}