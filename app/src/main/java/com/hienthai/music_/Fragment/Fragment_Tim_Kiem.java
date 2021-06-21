package com.hienthai.music_.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Adapter.SearchBaiHatAdapter;
import com.hienthai.music_.Model.BaiHat;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbarSearch;
    RecyclerView rcvSearch;
    TextView txtKoCoBaiHat;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbarSearch = view.findViewById(R.id.toolbarSearchBaiHat);
        rcvSearch = view.findViewById(R.id.recycleviewSearchBaiHat);
        txtKoCoBaiHat = view.findViewById(R.id.textviewKhongcobaihat);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarSearch);
        toolbarSearch.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String tukhoa) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getSearchBaiHat(tukhoa);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayList = (ArrayList<BaiHat>) response.body();
                if (arrayList.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), arrayList);
                    rcvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rcvSearch.setAdapter(searchBaiHatAdapter);
                    txtKoCoBaiHat.setVisibility(View.GONE);
                    rcvSearch.setVisibility(View.VISIBLE);
                } else {
                    txtKoCoBaiHat.setVisibility(View.VISIBLE);
                    rcvSearch.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
