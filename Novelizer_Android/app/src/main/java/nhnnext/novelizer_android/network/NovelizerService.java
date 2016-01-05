package nhnnext.novelizer_android.network;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NovelizerService {
    @GET("/newproject")
    void getProjects(
            @Query("userId") int userId,
            Callback<JsonArray> cb);

    @GET("/scene?sceneId=1")
    void getScene(Callback<String> cb);


}
