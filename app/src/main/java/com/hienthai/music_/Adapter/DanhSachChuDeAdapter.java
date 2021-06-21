package com.hienthai.music_.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.hienthai.music_.Model.ChuDe;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danh_sach_chu_de, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDeArrayList.get(position);
        Picasso.with(context).load(chuDe.getHinhChude()).into(holder.imgDanhSachChuDe);
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDanhSachChuDe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDanhSachChuDe = itemView.findViewById(R.id.imageviewdongDanhSachChuDe);
            imgDanhSachChuDe.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                intent.putExtra("chude", chuDeArrayList.get(getPosition()));
                context.startActivity(intent);

            });
        }
    }
}
