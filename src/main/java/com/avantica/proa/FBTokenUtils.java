package com.avantica.proa;


import com.avantica.proa.Models.FBTokenModel;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class FBTokenUtils {
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public boolean checkFBToken(String token) throws Exception {
        String requestURL = "https://graph.facebook.com/me?access_token=" + token;
        Request request = new Request.Builder().url(requestURL).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gson gson = new Gson();

            FBTokenModel tokenModel = gson.fromJson(response.body().string(), FBTokenModel.class);
            return (tokenModel.getId() != 0 && tokenModel.getName() != null);

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
