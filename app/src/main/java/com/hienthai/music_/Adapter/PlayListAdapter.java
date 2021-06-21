package com.hienthai.music_.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hienthai.music_.Model.PlayList;
import com.hienthai.music_.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView txtTitlePlaylist;
        ImageView imgPlaylist, imgBGPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTitlePlaylist = convertView.findViewById(R.id.textviewTenPlaylist);
            viewHolder.imgBGPlaylist = convertView.findViewById(R.id.imageviewbackgroudplaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageviewplaylist);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhPlayList()).into(viewHolder.imgBGPlaylist);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTitlePlaylist.setText(playList.getTenPlayList());
        return convertView;
    }
}
