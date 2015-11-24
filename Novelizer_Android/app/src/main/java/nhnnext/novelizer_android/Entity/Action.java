package nhnnext.novelizer_android.Entity;

/**
 * Created by Henry on 2015. 11. 24..
 */
public abstract class Action {
    private int actionId;
    private String type;

    public Action(int actionId, String type){
        this.actionId = actionId;
        this.type = type;
    }
}
