package vo.scene;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import vo.block.Block;

public class Scene {
	public int sceneId;
	public int projectId;
	public int startBlockId;
	public String name;
	
	@JsonProperty("blockList")
	public List<Block> blockList;
}
