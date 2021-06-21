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
import com.hienthai.music_.Model.PlayList;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> playListArrayList;

    public DanhSachPlayListAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.dong_danh_sach_playlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);
        Picasso.with(context).load(playList.getHinhPlayList()).into(holder.imgHinhDanhSachPlayList);
        holder.txtTenDanhSachPlayList.setText(playList.getTenPlayList());
    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhDanhSachPlayList;
        TextView txtTenDanhSachPlayList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhDanhSachPlayList = itemView.findViewById(R.id.imageviewDanhSachPlayList);
            txtTenDanhSachPlayList = itemView.findViewById(R.id.textviewTenDanhSachPlayList);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("itemPlayList", playListArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
