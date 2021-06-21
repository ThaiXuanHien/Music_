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
import com.hienthai.music_.Model.Album;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TatCaAlbumAdapter extends RecyclerView.Adapter<TatCaAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public TatCaAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.dong_tat_ca_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txtTenAllAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgAllAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAllAlbum;
        TextView txtTenAllAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllAlbum = itemView.findViewById(R.id.imageviewAllAlbum);
            txtTenAllAlbum = itemView.findViewById(R.id.textviewTenAllAlbum);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("album", albumArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
