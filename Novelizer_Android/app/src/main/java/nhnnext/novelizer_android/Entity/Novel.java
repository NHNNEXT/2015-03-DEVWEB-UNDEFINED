package nhnnext.novelizer_android.Entity;


import java.util.List;

/**
 * Created by Henry on 2015. 11. 23..
 */
public class Novel {
    private String novelId;
    private List<Scene> scenes;
    /*
    * flag, variable, preset 관련 변수 및 구현은 이후에 진행
    * */

    public Novel(String novelId, List<Scene> scenes){
        this.novelId = novelId;
        this.scenes = scenes;
    }

    /* getter & setter */
    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
