package com.hienthai.music_.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.hienthai.music_.Adapter.ViewPagerPlayNhac;
import com.hienthai.music_.Fragment.Fragment_Dia_Nhac;
import com.hienthai.music_.Fragment.Fragment_Play_DanhSach_BaiHat;
import com.hienthai.music_.Model.BaiHat;
import com.hienthai.music_.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    ViewPager viewPagerPlayNhac;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar skbPlayNhac;
    ImageButton imgBtnShuffle, imgBtnPre, imgBtnPlay, imgBtnNext, imgBtnRepeat;
    public static ArrayList<BaiHat> listBaiHat = new ArrayList<>();
    public static ViewPagerPlayNhac viewPagerPlayNhacAdapter;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_DanhSach_BaiHat fragment_play_danhSach_baiHat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean random = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        getDataIntent();
        init();
        eventClick();


    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlayNhacAdapter.getItem(1) != null) {
                    if (listBaiHat.size() > 0) {
                        fragment_dia_nhac.Playnhac(listBaiHat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgBtnPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                imgBtnPlay.setImageResource(R.drawable.ic_play);
            } else {
                mediaPlayer.start();
                imgBtnPlay.setImageResource(R.drawable.ic_pause);
            }
        });
        imgBtnRepeat.setOnClickListener(v -> {
            if (!repeat) {
                if (random) {
                    random = false;
                    imgBtnRepeat.setImageResource(R.drawable.ic_repeated);
                    imgBtnShuffle.setImageResource(R.drawable.ic_shuffle);
                }
                imgBtnRepeat.setImageResource(R.drawable.ic_repeated);
                repeat = true;
            } else {
                imgBtnRepeat.setImageResource(R.drawable.ic_repeat);
                repeat = false;
            }
        });
        imgBtnShuffle.setOnClickListener(v -> {
            if (!random) {
                if (repeat) {
                    repeat = false;

                    imgBtnShuffle.setImageResource(R.drawable.ic_shuffled);
                    imgBtnRepeat.setImageResource(R.drawable.ic_repeat);
                }
                imgBtnShuffle.setImageResource(R.drawable.ic_shuffled);
                random = true;
            } else {
                imgBtnShuffle.setImageResource(R.drawable.ic_shuffle);
                random = false;
            }
        });
        skbPlayNhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgBtnNext.setOnClickListener(v -> {
            if (listBaiHat.size() > 0) {
                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                }
                if (position < listBaiHat.size()) {
                    imgBtnPlay.setImageResource(R.drawable.ic_pause);
                    position++;
                    if (repeat) {
                        if (position == 0) {
                            position = listBaiHat.size();
                        }
                        position -= 1;
                    }
                    if (random) {
                        Random random = new Random();
                        int index = random.nextInt(listBaiHat.size());
                        if (index == position) {
                            position = index - 1;
                        }
                        position = index;
                    }
                    if (position > listBaiHat.size() - 1) {
                        position = 0;
                    }
                    new PlayMp3().execute(listBaiHat.get(position).getLinkBaiHat());
                    fragment_dia_nhac.Playnhac(listBaiHat.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(listBaiHat.get(position).getTenBaiHat());
                    updateTime();
                }
            }
            imgBtnNext.setClickable(false);
            imgBtnPre.setClickable(false);
            Handler handler1 = new Handler();
            handler.postDelayed(() -> {
                imgBtnNext.setClickable(true);
                imgBtnPre.setClickable(true);
            }, 1500);
        });
        imgBtnPre.setOnClickListener(v -> {
            if (listBaiHat.size() > 0) {
                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                }
                if (position < listBaiHat.size()) {
                    imgBtnPlay.setImageResource(R.drawable.ic_pause);
                    position--;
                    if (position < 0) {
                        position = listBaiHat.size() - 1;

                    }
                    if (repeat) {
                        position += 1;
                    }
                    if (random) {
                        Random random = new Random();
                        int index = random.nextInt(listBaiHat.size());
                        if (index == position) {
                            position = index - 1;
                        }
                        position = index;
                    }

                    new PlayMp3().execute(listBaiHat.get(position).getLinkBaiHat());
                    fragment_dia_nhac.Playnhac(listBaiHat.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(listBaiHat.get(position).getTenBaiHat());

                    updateTime();
                }
            }
            imgBtnNext.setClickable(false);
            imgBtnPre.setClickable(false);
            Handler handler1 = new Handler();
            handler.postDelayed(() -> {
                imgBtnNext.setClickable(true);
                imgBtnPre.setClickable(true);
            }, 1500);
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        listBaiHat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                listBaiHat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> arrayListBaiHat = intent.getParcelableArrayListExtra("cacbaihat");
                listBaiHat = arrayListBaiHat;
            }
        }

    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong = findViewById(R.id.textviewTimeSong);
        txtTotalTimeSong = findViewById(R.id.textviewTotalTimeSong);
        skbPlayNhac = findViewById(R.id.seekbarSong);
        imgBtnShuffle = findViewById(R.id.imagebuttonShuffle);
        imgBtnPre = findViewById(R.id.imagebuttonPre);
        imgBtnPlay = findViewById(R.id.imagebuttonPlay);
        imgBtnNext = findViewById(R.id.imagebuttonNext);
        imgBtnRepeat = findViewById(R.id.imagebuttonRepeat);
        viewPagerPlayNhac = findViewById(R.id.viewPagerPlayNhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarPlayNhac.setNavigationOnClickListener(v -> {
            finish();
            mediaPlayer.stop();
            listBaiHat.clear();
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danhSach_baiHat = new Fragment_Play_DanhSach_BaiHat();
        viewPagerPlayNhacAdapter = new ViewPagerPlayNhac(getSupportFragmentManager());

        viewPagerPlayNhacAdapter.addFragment(fragment_play_danhSach_baiHat);
        viewPagerPlayNhacAdapter.addFragment(fragment_dia_nhac);
        viewPagerPlayNhac.setAdapter(viewPagerPlayNhacAdapter);

        fragment_dia_nhac = (Fragment_Dia_Nhac) viewPagerPlayNhacAdapter.getItem(1);
        if (listBaiHat.size() > 0) {
            getSupportActionBar().setTitle(listBaiHat.get(0).getTenBaiHat());
            new PlayMp3().execute(listBaiHat.get(0).getLinkBaiHat());
            imgBtnPlay.setImageResource(R.drawable.ic_pause);
        }

    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baiHat) {
            super.onPostExecute(baiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(mp -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });

                mediaPlayer.setDataSource(baiHat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            updateTime();
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skbPlayNhac.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    skbPlayNhac.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(mp -> {
                        next = true;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next) {
                    if (position < listBaiHat.size()) {
                        imgBtnPlay.setImageResource(R.drawable.ic_pause);
                        position++;
                        if (repeat) {
                            if (position == 0) {
                                position = listBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (random) {
                            Random random = new Random();
                            int index = random.nextInt(listBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > listBaiHat.size() - 1) {
                            position = 0;
                        }
                        new PlayMp3().execute(listBaiHat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(listBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(listBaiHat.get(position).getTenBaiHat());
                    }

                    imgBtnNext.setClickable(false);
                    imgBtnPre.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(() -> {
                        imgBtnNext.setClickable(true);
                        imgBtnPre.setClickable(true);
                    }, 1500);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
