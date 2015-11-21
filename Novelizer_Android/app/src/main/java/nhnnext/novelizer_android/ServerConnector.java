package nhnnext.novelizer_android;


import android.util.Log;
import retrofit.Retrofit;

/**
 * Created by JaeBong on 15. 11. 21..
 */
public class ServerConnector {

    Retrofit retrofit;
    NovelizerService novelizerService;

    public ServerConnector() {
        retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:8080").build();
        novelizerService = retrofit.create(NovelizerService.class);

    }

    public String getBlockListByJSON() {
        String result;
        try {
            result = novelizerService.getBlockList().execute().body().string();
            Log.i("Body", result);
        } catch (Exception e) {
            result = "error";
            Log.e("Error", "" + e);
        }
        return result;
    }

}
