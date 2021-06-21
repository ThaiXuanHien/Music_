package com.hienthai.music_.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Adapter.TatCaAlbumAdapter;
import com.hienthai.music_.Model.Album;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhSachAlbumActivity extends AppCompatActivity {

    Toolbar toolbarAllAlbum;
    RecyclerView rcvAllAlbum;

    TatCaAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);

        init();

        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allAlbumAdapter = new TatCaAlbumAdapter(DanhSachAlbumActivity.this, albumArrayList);
                rcvAllAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this, 2));
                rcvAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarAllAlbum = findViewById(R.id.toolbarAllAlbum);
        rcvAllAlbum = findViewById(R.id.recycleviewAllAlbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Album");
        toolbarAllAlbum.setNavigationOnClickListener(v -> finish());
    }
}
