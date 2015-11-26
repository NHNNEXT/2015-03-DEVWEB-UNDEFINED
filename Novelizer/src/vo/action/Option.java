package vo.action;

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
}
