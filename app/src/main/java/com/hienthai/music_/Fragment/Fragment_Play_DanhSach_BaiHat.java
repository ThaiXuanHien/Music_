package com.hienthai.music_.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Activity.PlayNhacActivity;
import com.hienthai.music_.Adapter.PlayNhacAdapter;
import com.hienthai.music_.R;


public class Fragment_Play_DanhSach_BaiHat extends Fragment {
    View view;
    RecyclerView recyclerViewPlayDanhSachBaiHat;
    PlayNhacAdapter playNhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_baihat, container, false);
        recyclerViewPlayDanhSachBaiHat = view.findViewById(R.id.recycleviewPlayDanhSachBaiHat);
        if (PlayNhacActivity.listBaiHat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.listBaiHat);
            recyclerViewPlayDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayDanhSachBaiHat.setAdapter(playNhacAdapter);
        }

        return view;
    }
}
