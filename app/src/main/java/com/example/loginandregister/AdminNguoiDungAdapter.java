package com.example.loginandregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminNguoiDungAdapter extends RecyclerView.Adapter<AdminNguoiDungAdapter.nguoidung>{
    ArrayList<NguoiDung> dsnguoidung;

    public AdminNguoiDungAdapter(ArrayList<NguoiDung> dsnguoidung) {
        this.dsnguoidung = dsnguoidung;
    }

    @NonNull
    @Override
    public nguoidung onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemnguoidung,parent,false);
        return new nguoidung(view);
    }

    @Override
    public void onBindViewHolder(@NonNull nguoidung holder, int position) {
        holder.tenkh.setText(dsnguoidung.get(position).getTen());
        holder.email.setText(dsnguoidung.get(position).getEmail());
        holder.sdt.setText(dsnguoidung.get(position).getSodienthoai());

    }

    @Override
    public int getItemCount() {
        return dsnguoidung.size();
    }

    class  nguoidung extends RecyclerView.ViewHolder{

        TextView tenkh,email,sdt;

        public nguoidung(@NonNull View itemView) {
            super(itemView);
            tenkh=itemView.findViewById(R.id.txttenkhachhang);
            email=itemView.findViewById(R.id.txtemail);
            sdt=itemView.findViewById(R.id.txtsdt);
        }
    }
}
