package model;

public class CharacterVo {
	int projectId;
	int characterId;
	String name;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{\"characterId\" : " + characterId + ",\"projectId\" : " + projectId + ",\"name\":" + name + "}";
	}
}
