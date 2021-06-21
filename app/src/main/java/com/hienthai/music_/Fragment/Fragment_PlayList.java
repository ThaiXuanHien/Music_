package com.hienthai.music_.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hienthai.music_.Activity.DanhSachBaiHatActivity;
import com.hienthai.music_.Activity.DanhSachPlayListActivity;
import com.hienthai.music_.Adapter.PlayListAdapter;
import com.hienthai.music_.Model.PlayList;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_PlayList extends Fragment {

    View view;
    ListView lvPlaylist;
    TextView txtTitlePlaylist, txtXemthemPlaylist;

    ArrayList<PlayList> mangPlaylist;
    PlayListAdapter playListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lvPlaylist = view.findViewById(R.id.listviewPlaylist);
        txtTitlePlaylist = view.findViewById(R.id.textviewtitlePlaylist);
        txtXemthemPlaylist = view.findViewById(R.id.textviewviewmoreplaylist);
        getData();
        txtXemthemPlaylist.setOnClickListener(v -> startActivity(new Intent(getActivity(), DanhSachPlayListActivity.class)));
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.getPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangPlaylist = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, mangPlaylist);
                lvPlaylist.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);
                lvPlaylist.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                    intent.putExtra("itemPlayList", mangPlaylist.get(position));
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
