package nhnnext.novelizer_android.Entity;

import android.graphics.Bitmap;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class BackgroundAction extends Action {

    private Bitmap img;
    /* 이후 애니메이션 관련 구현 추가 예정 */

    public BackgroundAction(int actionId, String type, Bitmap img){
        super(actionId, type);
        this.img = img;
    }
}
