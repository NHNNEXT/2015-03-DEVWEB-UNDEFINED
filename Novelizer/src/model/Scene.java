package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown=true)
public class Scene {
	private int sceneId;
	private List<Block> blockList;
	private String sceneName;
	private int projectId;
	
	public Scene(){}
	
	public Scene(String sceneName, int projectId){
		this(0,sceneName,null,projectId);
	}

	public Scene(int sceneId, String name, int resultProjectId) {
		this(sceneId,name,null,resultProjectId);
	}

	public Scene(int sceneId, String sceneName, List<Block> blockList, int projectId) {
		super();
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.blockList = blockList;
		this.projectId = projectId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public String getName() {
		return sceneName;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

	@Override
	public String toString() {
		return "Scene [sceneId=" + sceneId + ", name=" + sceneName + ", blockList=" + blockList + "]";
	}

}
