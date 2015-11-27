package vo.scene;

import java.util.List;

import vo.block.Block;

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

}
