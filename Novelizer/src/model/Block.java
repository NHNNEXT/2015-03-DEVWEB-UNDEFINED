package model;

import java.util.List;

public class Block {
	private int blockId;
	private int nextBlockId;
	private int sceneId;
	private List<Action> actionList;

	public Block(int blockId, int nextBlockId, int sceneId) {
		super();
		this.blockId = blockId;
		this.nextBlockId = nextBlockId;
		this.sceneId = sceneId;
	}

	public int getBlockId() {
		return blockId;
	}

	//게터 세터만 있으면 데이터만 실어나르는걸 한다해서 그게 객체지향이 아니야 객체지향은 협업하면서 로직구현이 되야해
	//그래야 객체지향을 할 수 있어 많은 개발자다르이 클래스를 데이터를 실어나르는 도구로 사용하는 경우가 많아
	//클래스들의 필드값과 세터 게터만 있다고 하면 이거는 뭔가 개발을 잘못하고 있는거야 제대로된 객체지향 개발이 아니야
	//그래서 의심을 해봐야 해 의심을 해볼 첫번째는 객체들간에 연관관계가 있느데 다 떨어져 있는 경우
	//scene이 n개의 블락을 가지고 있는데 이걸 어떻게 표현할까?
	//List<Block> blockList; 여기에서 이 데이터를 활용해 로직을 구현할 수 있는데 세터게터만 하면 실어나르는 역할로만
	//작성하는 경우가 많은데 이 부분에 대해서 고민을 해보세요
	//로직처리에 대해서 개발을 구현해 보세요 
	/*
	 * 신에 블락을 추가해 그런데 블락을 예를 들어서 10개 이상을 추가하지 못한다는 요구사항이 있어요 그게 로직이죠
	 * 그런것들이 신 이런데서 처리가 되야한다는 거에요 그러니까 scene에다가 블락을 처리를 하는데 블락 몇개 이상이 못넘어가는 로직같은게 있다면
	 * 그런걸 scene에서 처리해야되 다른 클래스에서 따로 한다고 한다면 그건 절차지향이 된다.
	 * */
	
	public int getNextBlockId() {
		return nextBlockId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

}
