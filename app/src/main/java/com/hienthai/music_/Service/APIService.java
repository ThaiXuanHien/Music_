package com.hienthai.music_.Service;

public class APIService {
    private static String base_url = "https://hienthai98.000webhostapp.com/Server/";

    public static DataService getService() {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
