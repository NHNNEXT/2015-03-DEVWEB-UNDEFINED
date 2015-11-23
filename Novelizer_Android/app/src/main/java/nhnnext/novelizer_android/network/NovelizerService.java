package nhnnext.novelizer_android.network;


import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by JaeBong on 15. 11. 21..
 */
public interface NovelizerService {
    @GET("/getBlockList?sceneId=1")
    Call<ResponseBody> getBlockList();


}
