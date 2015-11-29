package nhnnext.novelizer_android.Entity;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class TextAction extends Action {

    private String text;
    /* 대사와 케릭터를 연결하는 부분은 이후 구현할 예정 */

    public TextAction(int actionId, String type, String text){
        super(actionId, type);
        this.text = text;
    }

    /* getter & setter */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
