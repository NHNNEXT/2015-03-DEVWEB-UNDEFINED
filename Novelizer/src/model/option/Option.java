package model.option;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
// TODO 현재 임시 저장소로 사용, 후에 세부적인 Option들로 나누어서 클래스 재생성 
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
