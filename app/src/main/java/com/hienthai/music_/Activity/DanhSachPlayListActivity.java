package com.hienthai.music_.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Adapter.DanhSachPlayListAdapter;
import com.hienthai.music_.Model.PlayList;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhSachPlayListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rcvDanhSachPlayList;
    DanhSachPlayListAdapter danhSachPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        anhXa();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.getDanhSachPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhSachPlayListActivity.this, playListArrayList);
                rcvDanhSachPlayList.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this, 2));
                rcvDanhSachPlayList.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void anhXa() {
        rcvDanhSachPlayList = findViewById(R.id.recycleviewDanhSachPlayList);
        toolbar = findViewById(R.id.toolbarDanhSachPlayList);
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(v -> finish());

    }
}
