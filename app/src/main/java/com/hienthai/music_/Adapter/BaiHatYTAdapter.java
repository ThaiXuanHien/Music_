package com.hienthai.music_.Adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.music_.Activity.PlayNhacActivity;
import com.hienthai.music_.Model.BaiHat;
import com.hienthai.music_.R;
import com.hienthai.music_.Service.APIService;
import com.hienthai.music_.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hienthai.music_.util.MyApplication.CHANNEL_ID;

public class BaiHatYTAdapter extends RecyclerView.Adapter<BaiHatYTAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatYeuThichArrayList;

    public BaiHatYTAdapter(Context context, ArrayList<BaiHat> baiHatYeuThichArrayList) {
        this.context = context;
        this.baiHatYeuThichArrayList = baiHatYeuThichArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihat_yeuthich, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHatYeuThich = baiHatYeuThichArrayList.get(position);
        holder.txtTenBaiHatYT.setText(baiHatYeuThich.getTenBaiHat());
        holder.txttenCaSiYT.setText(baiHatYeuThich.getCaSi());
        Picasso.with(context).load(baiHatYeuThich.getHinhBaiHat()).into(holder.imgBaiHatYT);
    }

    @Override
    public int getItemCount() {
        return baiHatYeuThichArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHatYT, txttenCaSiYT;
        ImageView imgBaiHatYT, imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHatYT = itemView.findViewById(R.id.textviewTenBaiHatYT);
            txttenCaSiYT = itemView.findViewById(R.id.textviewTenCaSiBaiHatYT);
            imgBaiHatYT = itemView.findViewById(R.id.imageviewBaiHatYT);
            imgLuotThich = itemView.findViewById(R.id.imageviewLuotThich);
            imgLuotThich.setOnClickListener(v -> {
                imgLuotThich.setImageResource(R.drawable.iconloved);

                DataService dataService = APIService.getService();
                Call<String> callback = dataService.updateLuotThich("1", baiHatYeuThichArrayList.get(getPosition()).getIdBaiHat());
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
                intent.putExtra("cakhuc", baiHatYeuThichArrayList.get(getPosition()));
                sendNotificationMedia(context);
                context.startActivity(intent);
            });
        }


    }
    public void sendNotificationMedia(Context context) {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.custom_backgroud_banner);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_audio)
                .setContentTitle("imageTitle")
                .setContentText("imageDescription")
                .setLargeIcon(bitmap)

                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_previous, "Previous", null) // #0
                .addAction(R.drawable.ic_pause, "Pause", null)  // #1
                .addAction(R.drawable.ic_next, "Next", null)     // #2
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button */)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))

                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, notification);
    }

}
