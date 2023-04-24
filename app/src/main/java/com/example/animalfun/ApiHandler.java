package com.example.animalfun;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHandler {

    public interface ApiCallback {
        void onSuccess(String response);

        void onError(String message);
    }
    public void makeApiCall(String name, ApiCallback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuiler = HttpUrl.parse("https://api.api-ninjas.com/v1/animals").newBuilder();
        urlBuiler.addQueryParameter("name", name);
        String url =urlBuiler.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Api-Key", "4q+3g5IbG0vMFtN2Nxu5oA==rf6pd8PLI74yYT4E")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("ApiHandler", responseBody);
                callback.onSuccess(responseBody);
            }
        });

    }
}

