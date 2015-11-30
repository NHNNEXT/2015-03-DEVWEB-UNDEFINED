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

	// TODO 자바 convention에서 생성자의 위치는 어디에 위치하는 것이 좋은가?
	public Scene(int sceneId, String name) {
		super();
		this.sceneId = sceneId;
		this.name = name;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

}
