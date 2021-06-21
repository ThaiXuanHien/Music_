package com.hienthai.music_.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Adapter.DanhSachChuDeAdapter;
import com.hienthai.music_.Model.ChuDe;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhSachChuDeActivity extends AppCompatActivity {

    Toolbar toolbarDanhSachChuDe;
    RecyclerView rcvDanhSachChuDe;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        anhXa();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.getDanhSachChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this, chuDeArrayList);
                rcvDanhSachChuDe.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this, 1));
                rcvDanhSachChuDe.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarDanhSachChuDe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Chủ đề");
        toolbarDanhSachChuDe.setNavigationOnClickListener(v -> finish());

    }

    private void anhXa() {
        rcvDanhSachChuDe = findViewById(R.id.recycleviewDanhSachChuDe);
        toolbarDanhSachChuDe = findViewById(R.id.toolbarDanhSachChuDe);

    }
}
