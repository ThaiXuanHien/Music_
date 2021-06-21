package com.hienthai.music_.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("tenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("tenCasiAlbum")
    @Expose
    private String tenCasiAlbum;
    @SerializedName("hinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTenCasiAlbum() {
        return tenCasiAlbum;
    }

    public void setTenCasiAlbum(String tenCasiAlbum) {
        this.tenCasiAlbum = tenCasiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

}