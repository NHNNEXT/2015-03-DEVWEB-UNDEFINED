package vo.action;

public class BackgroundAction extends Action {

	int presetId;
	String option;
	int animation;
	int[] position;

	public BackgroundAction(int id, String type, int presetId,
						String option, int animation, int[] position) {
		super(id, type);
		this.presetId = presetId;
		this.option = option;
		this.animation = animation;
		this.position = position;
	}
}
