package model.scene;

import java.util.List;

import model.block.Block;

public class Scene {
	private int sceneId;
	private String name;
	private List<Block> blockList;

	public Scene(int sceneId, String name) {
		super();
		this.sceneId = sceneId;
		this.name = name;
	}
<<<<<<< HEAD

=======
	
>>>>>>> a9b09f4909c80e23a945c6ef8359538f89d083a4
	public int getSceneId() {
		return sceneId;
	}

	public String getName() {
		return name;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

	@Override
	public String toString() {
		return "Scene [sceneId=" + sceneId + ", name=" + name + ", blockList=" + blockList + "]";
	}
}
