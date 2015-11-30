package nhnnext.novelizer_android.network;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by JaeBong on 15. 11. 21..
 */
public class ServerConnector {

    RestAdapter retrofit;
    NovelizerService novelizerService;

    public ServerConnector() {
        retrofit = new RestAdapter.Builder().setEndpoint("http://10.0.3.2:8080").build();
        novelizerService = retrofit.create(NovelizerService.class);

    }

    public String getScene() {

            novelizerService.getScene(new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    try {

                        reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                        String line;

                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i("Retrofit success",sb.toString());

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("Retrofit error", error.toString());
                }
            });

       return null;
    }

}
