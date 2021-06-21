package com.hienthai.music_.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.hienthai.music_.Activity.DanhSachBaiHatActivity;
import com.hienthai.music_.Activity.DanhSachChuDeActivity;
import com.hienthai.music_.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.hienthai.music_.Model.ChuDe;
import com.hienthai.music_.Model.TheLoai;
import com.hienthai.music_.Model.TheLoaiTrongNgay;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Chude_Theloai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemthemChudeTheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        txtXemthemChudeTheloai = view.findViewById(R.id.textviewXemthemCDTL);
        txtXemthemChudeTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });
        getData();

        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataService.getCategoryMusic();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiTrongNgay.getChuDe());
                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < chuDeArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChude() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChude()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int posI = i;
                    imageView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                        intent.putExtra("chude", chuDeArrayList.get(posI));
                        startActivity(intent);
                    });
                }
                for (int i = 0; i < theLoaiArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(i).getHinhTheLoai() != null) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int posI = i;
                    imageView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("idTheLoai", theLoaiArrayList.get(posI));
                        startActivity(intent);
                    });

                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
