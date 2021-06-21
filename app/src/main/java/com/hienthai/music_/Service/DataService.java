package com.hienthai.music_.Service;


import com.hienthai.music_.Model.Album;
import com.hienthai.music_.Model.BaiHat;
import com.hienthai.music_.Model.ChuDe;
import com.hienthai.music_.Model.PlayList;
import com.hienthai.music_.Model.QuangCao;
import com.hienthai.music_.Model.TheLoai;
import com.hienthai.music_.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay> getCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatYeuThich();

    @FormUrlEncoded
    @POST("danhSachBaiHat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoQuangCao(@Field("IdQuangCao") String idQuangCao);


    @FormUrlEncoded
    @POST("danhSachBaiHat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idPlayList") String idPlayList);

    @GET("danhsachPlayList.php")
    Call<List<PlayList>> getDanhSachPlayList();


    @FormUrlEncoded
    @POST("danhSachBaiHat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("IdTheLoai") String IdTheLoai);

    @GET("tatcachudevatheloai.php")
    Call<List<ChuDe>> getDanhSachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idChuDe") String idChuDe);


    @GET("tatcaAlbum.php")
    Call<List<Album>> getAllAlbum();

    @FormUrlEncoded
    @POST("danhSachBaiHat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoAlbum(@Field("IdAlbum") String idPlayList);

    @FormUrlEncoded
    @POST("updateLuotThich.php")
    Call<String> updateLuotThich(@Field("luotThich") String luotThich, @Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("searchBaiHat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tukhoa);
}
