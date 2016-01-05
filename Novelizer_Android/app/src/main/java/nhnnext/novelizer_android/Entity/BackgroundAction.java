package nhnnext.novelizer_android.Entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class BackgroundAction extends Action {

    private Drawable img;
    private String option;
    /* 이후 애니메이션 관련 구현 추가 예정 */

    public BackgroundAction(int actionId, String type, Drawable img, String option) {
        super(actionId, type);
        this.img = img;
        this.option = option;
    }

    /* getter & setter */
    public Drawable getImg() {
        return img;
    }

    public String getOption() {
        return option;
    }
}
