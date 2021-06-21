package com.hienthai.music_.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

    @SerializedName("idPlayList")
    @Expose
    private String idPlayList;
    @SerializedName("tenPlayList")
    @Expose
    private String tenPlayList;
    @SerializedName("hinhPlayList")
    @Expose
    private String hinhPlayList;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getTenPlayList() {
        return tenPlayList;
    }

    public void setTenPlayList(String tenPlayList) {
        this.tenPlayList = tenPlayList;
    }

    public String getHinhPlayList() {
        return hinhPlayList;
    }

    public void setHinhPlayList(String hinhPlayList) {
        this.hinhPlayList = hinhPlayList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}