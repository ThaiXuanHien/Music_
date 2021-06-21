package com.hienthai.music_.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hienthai.music_.Activity.DanhSachBaiHatActivity;
import com.hienthai.music_.Model.QuangCao;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner, null);

        ImageView imgViewBGBanner = view.findViewById(R.id.imageviewbackgroudbanner);
        ImageView imgViewBanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleSongbanner = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtNoidung = view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnhQC()).into(imgViewBGBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnhBaiHat()).into(imgViewBanner);
        txtTitleSongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtNoidung.setText(arrayListBanner.get(position).getNoiDungQC());

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
            intent.putExtra("banner", arrayListBanner.get(position));
            context.startActivity(intent);
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
