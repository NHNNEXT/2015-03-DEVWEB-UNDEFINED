package model.preset;

public class CharacterPreset{
	protected int presetId;
	protected String type;
	protected String name;
	protected String image;
	
	public void characterPreset(String jsonData){
		validate(jsonData);
	}
	
	private boolean validate(String jsonData){
		
		return true;
	}
	
}
