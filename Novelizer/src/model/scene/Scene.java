package model.scene;

import java.util.List;

import model.block.Block;

public class Scene {
	private int sceneId;
	private String name;
	private List<Block> blockList;

	public int getSceneId() {
		return sceneId;
	}

	public String getName() {
		return name;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public Scene(int sceneId, String name) {
		super();
		this.sceneId = sceneId;
		this.name = name;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

}
