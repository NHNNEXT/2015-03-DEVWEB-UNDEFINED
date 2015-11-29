package nhnnext.novelizer_android.Entity;

import android.graphics.Bitmap;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class CharacterAction extends Action {

    private int[] position;
    private Bitmap img;
    /* 이후 애니메이션 관련 구현 추가 예정 */

    public CharacterAction(int actionId, String type, int[] position, Bitmap img){
        super(actionId, type);
        this.position = position;
        this.img = img;
    }

    /* getter & setter*/

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
