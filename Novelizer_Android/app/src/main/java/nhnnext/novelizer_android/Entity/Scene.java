package nhnnext.novelizer_android.Entity;

import java.util.List;
/**
 * Created by Henry on 2015. 11. 24..
 */
public class Scene {
    private List<Block> blocks;
    private int sceneId;

    public Scene(int sceneId, List<Block> blocks){
        this.sceneId = sceneId;
        this.blocks = blocks;
    }
}
