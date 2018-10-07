package com.nemecek.stackoverflow.common.di;

import com.nemecek.stackoverflow.common.Constants;
import com.nemecek.stackoverflow.networking.StackoverflowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit mRetrofit;

    public StackoverflowApi getStackOverflowApi() {
        return getRetrofit()
                .create(StackoverflowApi.class);
    }

    private Retrofit getRetrofit() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
