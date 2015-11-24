package nhnnext.novelizer_android.Entity;

import java.util.Map;
/**
 * Created by Henry on 2015. 11. 24..
 */
public class Block {
    private Map<String, Action> actions;
    private int blockId;

    public Block(int blockId, Map<String, Action> actions){
        this.actions = actions;
        this.blockId = blockId;
    }

    /* getter & setter */
    public Map<String, Action> getActions() {
        return actions;
    }

    public void setActions(Map<String, Action> actions) {
        this.actions = actions;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
