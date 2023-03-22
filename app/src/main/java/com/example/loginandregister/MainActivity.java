package com.example.loginandregister;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerOptions;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //user
    private FirebaseDatabase user;
    private DatabaseReference reference;
    private String sdt ;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbarmenu;
    TextView txtname, txtemail;
    ImageView qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        toolbarmenu=(Toolbar)findViewById(R.id.toobaltrangchu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout) ;
        qrcode=findViewById(R.id.imgqrcode);

        actionToolbal();
        initUi();

        //menu
      //  navigationView.bringToFront();//chọn nổi navication

        navigationView.setNavigationItemSelectedListener(this);
        showUserInformation();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DichVu> options =
                new FirebaseRecyclerOptions.Builder<DichVu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dichvu"), DichVu.class)
                        .build();

        mainAdapter=new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,QuetQR.class);
                startActivity(intent);
            }
        });




//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawerlayout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.openmenunavigation, R.string.closemenunavigation);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationview = findViewById(R.id.navigation);
//        navigationview.setNavigationItemSelectedListener(this);
//        replaceFragment(new TrangchuNguoidung());
//        navigationview.getMenu().findItem(R.id.trangchu).setChecked(true);
//
//        //canhan=findViewById(R.id.ttcanhan);
        //canhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ThongtincanhanActivity.class));
//               finish();
//            }
//        });

    //    canhan=(ImageView) findViewById(R.id.img_canhan);
//        canhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ThongtincanhanActivity.class));
//                finish();
//
//            }
//        });
    }
     private  void initUi(){
         navigationView=(NavigationView)findViewById(R.id.navigationView);
         txtname=navigationView.getHeaderView(0).findViewById(R.id.tvname);
         txtemail=navigationView.getHeaderView(0).findViewById(R.id.tvemail);
     }

    private void actionToolbal() {
        setSupportActionBar(toolbarmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmenu.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbarmenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
    //chọn menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.lich:
                Intent intent1= new Intent(MainActivity.this,DanhSachLichDatNguoiDung.class);
                startActivity(intent1);
                break;
            case R.id.thongtin:
                Intent intent= new Intent(MainActivity.this,ThongtincanhanActivity.class);
                startActivity(intent);
                break;
            case R.id.thongtinlienhe:
                Intent intent3= new Intent(MainActivity.this,ThongtinlienheActivity.class);
                startActivity(intent3);
                break;
            case R.id.dangxuat:
                FirebaseAuth.getInstance().signOut();
                Intent intent2= new Intent(MainActivity.this,Login.class);
                startActivity(intent2);

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //lấy tên người dùng cho navication View
    private void showUserInformation(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        String name=user.getDisplayName();
        String email=user.getEmail();
        if(name==null){
            txtname.setVisibility(View.GONE);
        }else{
            txtname.setVisibility(View.VISIBLE);
            txtname.setText(name);
        }
        txtemail.setText(email);
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.trangchu){
//            if(currentfragment != fragment_trangchu){
//                replaceFragment(new TrangchuNguoidung());
//                currentfragment = fragment_trangchu;
//            }
//        }else if(id == R.id.lich){
//            if(currentfragment != fragment_dslichdat){
//                replaceFragment(new DanhsachLichdatNguoidung());
//                currentfragment = fragment_dslichdat;
//            }
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else{
//            super.onBackPressed();
//        }
//    }
//    private void replaceFragment(Fragment fragment){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame, fragment);
//        transaction.commit();
//    }
    //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search, menu);
//        MenuItem item= menu.findItem(R.id.search);
//        MenuItem item1= menu.findItem(R.id.canhan);
//        MenuItem item2= menu.findItem(R.id.khachhang);
//        SearchView searchView=(SearchView)item.getActionView();
//        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                startActivity(new Intent(MainActivity.this, CaNhanActivity.class));
//                finish();
//
//                return false;
//            }
//        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                txtSearch(query);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//    private void txtSearch(String str){
//        FirebaseRecyclerOptions<DichVu> options =
//                new FirebaseRecyclerOptions.Builder<DichVu>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dichvu").orderByChild("ten").startAt(str).endAt(str+"~"), DichVu.class)
//                        .build();
//
//        mainAdapter= new MainAdapter(options);
//        mainAdapter.startListening();
//        recyclerView.setAdapter(mainAdapter);
//
//    }

}