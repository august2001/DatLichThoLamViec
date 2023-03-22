package com.example.loginandregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminNguoiDungActivity extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    ArrayList<NguoiDung> dslist;
    FirebaseFirestore db;
    AdminNguoiDungAdapter adminNguoiDungAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_nguoi_dung);

        trove= findViewById(R.id.id_trove);
        recyclerView= findViewById(R.id.recyclerNguoidung);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dslist  = new ArrayList<>();
        adminNguoiDungAdapter= new AdminNguoiDungAdapter(dslist);
        recyclerView.setAdapter(adminNguoiDungAdapter);
        db=FirebaseFirestore.getInstance();
        db.collection("User").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list){
                                    NguoiDung obj=d.toObject(NguoiDung.class);
                                    dslist.add(obj);

                                }
                                adminNguoiDungAdapter.notifyDataSetChanged();

                            }
                        });





        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminNguoiDungActivity.this, TrangchuAdminActivity.class));
                finish();
            }
        });
    }


}