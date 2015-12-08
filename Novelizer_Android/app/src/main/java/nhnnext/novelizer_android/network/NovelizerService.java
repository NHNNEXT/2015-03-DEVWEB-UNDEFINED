package nhnnext.novelizer_android.network;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

public interface NovelizerService {
    @GET("/project")
    void getProject(Callback<Response> cb);
    @GET("/scene?sceneId=1")
    void getScene(Callback<Response> cb);




}
