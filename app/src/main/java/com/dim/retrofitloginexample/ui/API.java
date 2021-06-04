package com.dim.retrofitloginexample.ui;

import retrofit.RestAdapter;

public class API {
    private static String url = "http://mobileappdatabase.in/";
    public static APIInterface getCliente() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        APIInterface apiInterface = adapter.create(APIInterface.class);
        return apiInterface;
    }
}
