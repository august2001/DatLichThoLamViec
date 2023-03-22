package com.example.loginandregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LichDatNguoiDungAdapter extends RecyclerView.Adapter<LichDatNguoiDungAdapter.LichDatViewHolder>{
    private List<LichDat> mListLichDat;

    public LichDatNguoiDungAdapter(List<LichDat> mListLichDat) {
        this.mListLichDat = mListLichDat;
    }
    @NonNull
    @Override
    public LichDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlichdatnguoidung, parent, false);
        return new LichDatViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LichDatViewHolder holder, int position) {
        LichDat lichdat = mListLichDat.get(position);
        if(lichdat == null){
            return;
        }
        holder.tendichvu.setText(lichdat.getDichvu());
        holder.hoanthanh.setText(lichdat.getHoanthanh());
        holder.xacnhan.setText(lichdat.getXacnhan());
        holder.ngaydat.setText(lichdat.getNgaydat());
        holder.sodienthoai.setText(lichdat.getSodienthoai());
        holder.noidung.setText(lichdat.getNoidung());
        holder.diachi.setText(lichdat.getDiachi());
        holder.emailnv.setText(lichdat.getEmailnv());
        holder.emailnd.setText(lichdat.getEmail());
    }
    @Override
    public int getItemCount() {
        if(mListLichDat != null){
            return mListLichDat.size();
        }
        return 0;
    }
    public class LichDatViewHolder extends RecyclerView.ViewHolder {
        private TextView tendichvu;
        private TextView hoanthanh;
        private TextView xacnhan;
        private TextView ngaydat;
        private  TextView sodienthoai;
        private TextView noidung;
        private TextView diachi;
        private TextView emailnv;
        private TextView emailnd;
        public LichDatViewHolder(@NonNull View itemView) {
            super(itemView);
            tendichvu = itemView.findViewById(R.id.txttendonhang);
            hoanthanh = itemView.findViewById(R.id.txthoanthanh);
            xacnhan = itemView.findViewById(R.id.txtxacnhan);
            ngaydat=itemView.findViewById(R.id.txtngaydat);
            sodienthoai=itemView.findViewById(R.id.txtsdtdat);
            noidung=itemView.findViewById(R.id.txtnoidung);
            diachi=itemView.findViewById(R.id.txtdiachi);
            emailnv=itemView.findViewById(R.id.txtemialnv);
            emailnd=itemView.findViewById(R.id.txtemail);
        }
    }
}