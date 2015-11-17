package vo.action;

public class TextAction extends Action {

	String text;
	int characterId;
	
	public TextAction(int id, String type, String text, int characterId) {
		super(id, type);
		this.text = text;
		this.characterId = characterId;
	}
}
