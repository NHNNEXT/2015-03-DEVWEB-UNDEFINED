package nhnnext.novelizer_android.Entity;

import android.graphics.Bitmap;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class BackgroundAction extends Action {

    private Bitmap img;
    private String option;
    /* 이후 애니메이션 관련 구현 추가 예정 */

    public BackgroundAction(int actionId, String type, Bitmap img, String option){
        super(actionId, type);
        this.img = img;
        this.option = option;
    }

    /* getter & setter */
    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
