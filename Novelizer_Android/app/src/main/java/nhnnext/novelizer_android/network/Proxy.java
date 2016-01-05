package nhnnext.novelizer_android.network;


import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by JaeBong on 15. 11. 21..
 */
public class Proxy {

    RestAdapter retrofit;
    NovelizerService novelizerService;

    public Proxy() {
        retrofit = new RestAdapter.Builder().setEndpoint("http://10.0.3.2:8080").build();
        novelizerService = retrofit.create(NovelizerService.class);

    }

    public String getProjectsByJson(int userId, Callback callback) {
        Log.e("test", "Get Project Start");
        String reslut;
        novelizerService.getProjects(userId, callback);


        return null;

    }

}
