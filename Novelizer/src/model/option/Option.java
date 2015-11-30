package model.option;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
// TODO Action, Block, Scene은 setter와 getter method를 가진다. 이 클래스는 field만 가지고 있다. 어느 방식으로 구현하는 것이 맞는가? convention을 정하면 좋겠다.
public class Option {
	private int characterId;
	private int presetId;
	private String option;
	private int[] position;
	private String animation;
	private String text;

	@Override
	public String toString() {
		return "Option [characterId :" + characterId + ", presetId :" + presetId + ", option :" + option
				+ ", position: " + Arrays.toString(position) + ", animation : " + animation + ", text :" + text + "]";
	}

}
