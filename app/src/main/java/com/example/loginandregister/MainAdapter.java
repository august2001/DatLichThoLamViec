package com.example.loginandregister;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.actions.ItemListIntents;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;


import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter <DichVu,MainAdapter.myViewHodler> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-b5e1d-default-rtdb.firebaseio.com");
    EditText email,tendichvu ,hoten,sdt,ngaydat,noidung,diachi;
    Button dat;
    DatePickerDialog.OnDateSetListener setListener;
    Date currentTime = Calendar.getInstance().getTime();

    public MainAdapter(@NonNull FirebaseRecyclerOptions<DichVu> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHodler holder, int position, @NonNull DichVu model) {
        holder.ten.setText(model.getTen());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(model);
            }
        });
        Glide.with(holder.img.getContext())
                .load(model.getAnh())
                .placeholder(com.google.firebase.database.collection.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        //tt khách hàng
        holder.btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_thongtindichvu))
                        .setExpanded(true,2000)
                        .create();
                dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText editten = view1.findViewById(R.id.id_tendv);
                TextView mota= view1.findViewById(R.id.motadv);

                mota.setText(model.getMota());
                editten.setText(model.getTen());
                dialogPlus.show();

//              Intent intent= new Intent(view.getContext(),ThongtindichvuActivity.class);
//              view.getContext().startActivity(intent);

            }
        });

        holder.btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_dat_lich))
                        .setExpanded(true, 2000)
                        .create();
                dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText editten = view1.findViewById(R.id.txttendichvu);
                editten.setText(model.getTen());
                dialogPlus.show();

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user == null){
                    Toast.makeText(holder.ten.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                String emailnd = user.getEmail();
                String sdtnd = user.getPhoneNumber();

                hoten =view1.findViewById(R.id.txttenkhachhang);
                sdt=view1.findViewById(R.id.txtsdt);
                noidung=view1.findViewById(R.id.txtnoidung);
                ngaydat=view1.findViewById(R.id.txtngaydat);
                diachi=view1.findViewById(R.id.txtdiachi);
                dat=view1.findViewById(R.id.btnDat);
                email = view1.findViewById(R.id.txtemail);
                email.setText(emailnd);
                sdt.setText(sdtnd);
//                Calendar ca = Calendar.getInstance();
//                final int ngay = ca.get(Calendar.DAY_OF_MONTH);
//                final int thang = ca.get(Calendar.MONTH);
//                final int nam = ca.get(Calendar.YEAR);
//                ngaydat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainAdapter.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, ngay, thang, nam);
//                        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        datePickerDialog.show();
//                    }
//                });
//                setListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int ngay, int thang, int nam) {
//                        thang  = thang + 1;
//                        String date = nam + "/" + thang + "/" + ngay;
//                        ngaydat.setText(date);
//                    }
//                };


                dat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dichvu1= editten.getText().toString();
                        String hoten1 = hoten.getText().toString();
                        String sdt1 = sdt.getText().toString();
                        String noidung1 = noidung.getText().toString();
                        String ngaydat1 = ngaydat.getText().toString();
                        String diachi1 = diachi.getText().toString();
                        String email1= email.getText().toString();
                        Map<String, Object> dichvu = new HashMap<>();
                        dichvu.put("Dịch vụ",dichvu1);
                        dichvu.put("Họ Tên", hoten1);
                        dichvu.put("Số điện thoại", sdt1);
                        dichvu.put("Nội dung", noidung1);
                        dichvu.put("Ngày đặt", ngaydat1);
                        dichvu.put("Email", email1);


                        databaseReference.child("Lịch Đặt").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(hoten1.isEmpty()||sdt1.isEmpty()||noidung1.isEmpty()||ngaydat1.isEmpty()||diachi1.isEmpty()){
                                    Toast.makeText(holder.ten.getContext(), "Bạn chưa nhập đầy đủ thông tin của dịch vụ trên", Toast.LENGTH_SHORT).show();
                                }else if(sdt1.length() != 10){
                                    Toast.makeText(holder.ten.getContext(), "Hãy nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
                                }else if(!ngaydat1.contains("/")){
                                    Toast.makeText(holder.ten.getContext(), "Hãy nhập đầy đủ ngày tháng năm", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    //       gửi dữ liệu thời gian thực đến firebase
                                    //       tat cả điều dùng sô điện thoai
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("dichvu").setValue(dichvu1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("email").setValue(email1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("tenkhachhang").setValue(hoten1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("sodienthoai").setValue(sdt1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("noidung").setValue(noidung1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("ngaydat").setValue(ngaydat1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("diachi").setValue(diachi1);
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("emailnv").setValue("");
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("xacnhan").setValue("Chưa xác nhận");
                                    databaseReference.child("Thongtinlichdat").child(sdt1).child("hoanthanh").setValue("Chưa hoàn thành");
                                    Toast.makeText(holder.ten.getContext(), "Bạn đã đặt dịch vụ thành công", Toast.LENGTH_SHORT).show();
                                    dialogPlus.dismiss();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }
        });
    }
    private void onClickGoToDetail(DichVu model) {
        Intent intent= new Intent();
    }
    @NonNull
    @Override
    public myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHodler(view);
    }

    class myViewHodler extends RecyclerView.ViewHolder{
        CardView cardView;
        CircleImageView img;
        TextView ten;
        Button btnDatLich;
        Button btnchitiet;

        public myViewHodler(@NonNull View itemView  ) {
            super(itemView);
            cardView=itemView.findViewById(R.id.layoutitem);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            ten=(TextView) itemView.findViewById(R.id.nametext);
            btnDatLich= (Button) itemView.findViewById(R.id.btnDatLich);
            btnchitiet=(Button) itemView.findViewById(R.id.chitiet);

        }
    }
}
