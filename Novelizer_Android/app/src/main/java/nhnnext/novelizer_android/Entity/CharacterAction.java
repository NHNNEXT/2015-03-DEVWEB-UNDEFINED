package nhnnext.novelizer_android.Entity;

import android.graphics.Bitmap;

/**
 * Created by Henry on 2015. 11. 24..
 */
public class CharacterAction extends Action {

    private int[] position;
    private Bitmap img;
    private String option;
    private String characterId;
    /* 이후 애니메이션 관련 구현 추가 예정 */

    public CharacterAction(int actionId, String type, int[] position, Bitmap img, String characterId, String option){
        super(actionId, type);
        this.position = position;
        this.img = img;
        this.characterId = characterId;
        this.option = option;
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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }
}
