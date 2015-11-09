package controller.block;

public class BlockController {
	
	/*
	 * 씬과 블록 아이디를 받는다. 
	 * 분기 플래그가 있는지 검색하여 있으면 분기 플래그 안으로 진입한다.
	 * 분기 플래그는 자동생성된다. 
	 * 	how?
	 * 		- 사용자가 local에서 만든 분기문들을 통해 블에 플래그가 있으면 진입. 
	 * 		- 분기 플래그가 있는 블록인지 어떻게 구별?
	 * 		- 분기 플래그를 어떻게 설정? 
	 * 
	*/
	
	public BlockController() {}
	
	public String getNextBlock(int sceneId, int blockId){
		String data = "{sceneId : 3, blockId : 2}";
		findFlag(sceneId, blockId);
		
		return data;
	}
	
	private void findFlag(int sceneId, int blockId){
		
	}
}
