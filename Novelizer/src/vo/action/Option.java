package vo.action;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreProperties
@JsonIgnoreType
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
