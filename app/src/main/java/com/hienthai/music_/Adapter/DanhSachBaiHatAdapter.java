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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenBaiHatDSBH.setText(baiHat.getTenBaiHat());
        holder.txtTenCaSiDSBH.setText(baiHat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHatDSBH, txtTenCaSiDSBH;
        ImageView imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIndex = itemView.findViewById(R.id.textviewIndex);
            txtTenBaiHatDSBH = itemView.findViewById(R.id.textviewTenBaiHatDSBH);
            txtTenCaSiDSBH = itemView.findViewById(R.id.textviewTenCaSiDSBH);
            imgLuotThich = itemView.findViewById(R.id.imageviewLuotThichDSBH);
            imgLuotThich.setOnClickListener(v -> {
                imgLuotThich.setImageResource(R.drawable.iconloved);

                DataService dataService = APIService.getService();
                Call<String> callback = dataService.updateLuotThich("1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketQua = response.body();
                        if (ketQua.equals("success")) {
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
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
