package nhnnext.novelizer_android.Entity;

import java.util.Map;
/**
 * Created by Henry on 2015. 11. 24..
 */
public class Block {
    private Map<String, Action> actions;
    private int blockId;
    private int nextBlock;

    public Block(int blockId, int nextBlock, Map<String, Action> actions){
        this.actions = actions;
        this.blockId = blockId;
        this.nextBlock = nextBlock;
    }

    /* getter & setter */
    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

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
