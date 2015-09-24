package com.accenture.hackathon.util;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void success(T t, Response response) {
        System.out.println("Successful call to API: " + response.getUrl());
        success(t);
    }

    @Override
    public void failure(RetrofitError error) {
        System.out.println("An error occurred using the Iod API: " + error.getMessage());
        error.printStackTrace();
    }

    public abstract void success(T t);
}
