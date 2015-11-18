package vo.action;

public class CharacterAction extends Action {

	int characterId;
	int presetId;
	String option;
	int animation;
	int[] position;
	
	public CharacterAction(int id, String type, int characterId, int presetId, String option, int animation,
			int[] position) {
		super(id, type);
		this.characterId = characterId;
		this.presetId = presetId;
		this.option = option;
		this.animation = animation;
		this.position = position;
	}
}
