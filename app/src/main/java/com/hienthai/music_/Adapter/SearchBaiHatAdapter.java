package com.hienthai.music_.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Activity.PlayNhacActivity;
import com.hienthai.music_.Model.BaiHat;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_baihat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtTenCaSi.setText(baiHat.getCaSi());
        holder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinhBaiHat);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHat, txtTenCaSi;
        ImageView imgHinhBaiHat, imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textviewItemSearchTenBaiHat);
            txtTenCaSi = itemView.findViewById(R.id.textviewItemSearchTenCaSi);
            imgHinhBaiHat = itemView.findViewById(R.id.imageItemSearchHinhBaiHat);
            imgLuotThich = itemView.findViewById(R.id.imageSearchLuotThich);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                context.startActivity(intent);
            });
            imgLuotThich.setOnClickListener(v -> {
                imgLuotThich.setImageResource(R.drawable.iconloved);
                DataService dataService = APIService.getService();
                Call<String> call = dataService.updateLuotThich("1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String kq = response.body();
                        if (kq.equals("success")) {
                            Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                imgLuotThich.setEnabled(false);
            });
        }
    }
}
