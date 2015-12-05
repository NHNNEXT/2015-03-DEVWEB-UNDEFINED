package nhnnext.novelizer_android.Entity;

import java.util.List;
/**
 * Created by Henry on 2015. 11. 24..
 */
public class Block {
    private List<Action> actions;
    private int blockId;
    private int nextBlockId;

    public Block(int blockId, int nextBlock, List<Action> actions){
        this.actions = actions;
        this.blockId = blockId;
        this.nextBlockId = nextBlock;
    }

    /* getter & setter */
    public int getNextBlock() {
        return nextBlockId;
    }

    public int getNextBlockId() {
        return nextBlockId;
    }

    public void setNextBlockId(int nextBlockId) {
        this.nextBlockId = nextBlockId;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
