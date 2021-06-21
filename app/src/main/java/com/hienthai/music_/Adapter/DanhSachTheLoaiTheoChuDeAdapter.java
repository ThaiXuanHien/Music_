package com.hienthai.music_.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Activity.DanhSachBaiHatActivity;
import com.hienthai.music_.Model.TheLoai;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.dong_the_loai_theo_chu_de, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        holder.txtTenChuDe.setText(theLoai.getTenTheloai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgHinhNenChuDe);
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenChuDe;
        ImageView imgHinhNenChuDe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenChuDe = itemView.findViewById(R.id.textviewTenTheloaitheochude);
            imgHinhNenChuDe = itemView.findViewById(R.id.imageviewtheloaitheochude);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("idTheLoai", theLoaiArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
