package nhnnext.novelizer_android.network;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NovelizerService {
    @GET("/project")
    void getProjects(
            @Query("userId") int userId,
            Callback<Response> cb);
    @GET("/scene?sceneId=1")
    void getScene(Callback<Response> cb);




}
