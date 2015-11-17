*
 * EditorDataSync : 에디터 페이지에서 변경되는 데이터 로컬스토리지에 저장 및 서버 연동 처리
 * author : cca-company
 */

var EditorDataSync = {
 	url : "http://127.0.0.1:8080",
 	blockList : [],
 	sceneId : 1,
 	init : function(sceneId){
 		this.sceneId = sceneId;
 		//this.getSceneData(this.loadData);

 		//ISSUE! localDB는 다음 사이클에! 
 		//this.IDBRequest = window.indexedDB.open("novelizerEditor", 0);
		//this.IDBRequest.onupgradeneeded = this.upgradeDB;
 	},
 	loadData : function(data){
 		EditorDataSync.blockList = data;
 		// TODO : 서버에서 받은 기존 리스트 정보를 DOM에 추가
 	},
 	// @review - fe
 	// getSceneData, postSceneData이 거의 비슷한데 어떻하면 좋을까?
 	getSceneData : function(callbackDone,callbackFail){	// 현재 씬의 블록 리스트 데이터를 가져옴
 		// @review - fe
 		// 여기서 this는 EditorDataSync인데, Editor#selectBlock에서는 element다.
 		// 개발하다보면 헷갈리지 않을까?
 		var request = $.ajax({
 			url : this.url+"/getBlock.do",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})
 		// @review - fe
 		// 아래처럼 할 수도 있지만, request을 사용하지 않는다면 chaining해서 써도 좋을듯.
 		request.done(callbackDone);
 		request.fail(callbackFail);
 	},
 	postSceneData : function(callbackDone,callbackFail){
 		var request = $.ajax({
 			url : this.url+"/postBlock.do",
 			method : "POST",
 			dataType : "json",
 			data : {sceneId : this.sceneId,
 					data : JSON.stringify(this.blockList)},
 		})

 		request.done(callbackDone);
 		request.fail(callbackFail);
 	},
 	addBlock : function(callbackDone, callbackFail){
 		var maxIdx = this.blockList.length-1;
 		var maxBlockId = 0;
 		if(maxIdx >= 0){			
 			maxBlockId = this.blockList[maxIdx].blockId;
 			this.blockList[maxIdx].nextBlockId=maxBlockId+1;
 		}

 		var data = {
 			blockId : maxBlockId+1,
 			actionList : [],
 			nextBlockId : 0,
 		};
 		this.blockList.push(data);

 		callbackDone(data);
 	},
 	removeBlock : function(blockId, callbackDone, callbackFail){
 		var targetBlockIdx = this.getBlock(blockId,true);
 		var prevBlock = this.getPrevBlock(blockId);
		var targetBlock = this.blockList.splice(targetBlockIdx,1);
 		
 		if(prevBlock){
 			prevBlock.nextBlockId = targetBlock.nextBlockId;
 		}

 	},
 	sortBlock : function(blockId, prevBlockId, callbackDone, callbackFail){
 		// @review - fe
 		// 파라메터의 갯수가 많이지면 좀 불편한데 객체로 만들면 뭐가 좀 좋고, 어떤 점이 불편할까?
 		var targetBlock = this.getBlock(blockId);
 		var currentPrevBlock = this.getBlock(prevBlockId);
 		var beforePrevBlock = this.getPrevBlock(blockId);

 		beforePrevBlock.nextBlockId = targetBlock.nextBlockId;
 		targetBlock.nextBlockId = currentPrevBlock.nextBlockId;
 		currentPrevBlock.nextBlockId = blockId;

 	},
 	addAction : function(blockId, callbackDone, callbackFail){
 		var targetBlock = this.getBlock(blockId);
 		var maxIdx = targetBlock.actionList.length-1;
 		var maxActionId = (targetBlock.actionList[maxIdx]) ? targetBlock.actionList[maxIdx].actionId : 0;
 		var data = {
 			actionId : maxActionId+1,
 			actionType : "text",
 			text : "임시 데이터"
 		};
 		targetBlock.actionList.push(data);
 		callbackDone(data);
 	},
 	// @review - fe
 	// getBlock와 getPrevBlock이 코드가 거의 비슷한데 어떻게 생각해?
 	getBlock : function(blockId, getIdx){
 		if(!getIdx) getIdx = false;

 		// ISSUE! javascript로 더 나은 성능의 search하는 방법은? 
 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].blockId == blockId){
 				if(getIdx == true){
 					return i;
 				}else{
 					return this.blockList[i];
 				}
 			}
 		}
 		console.log("blockId="+blockId+" 해당 block데이터를 찾을수 없습니다"); 		
 		if(getIdx == true){
 			return -1;
		}else{
 			return null;
		}
 	},
 	getPrevBlock : function(blockId, getIdx){
 		if(!getIdx) getIdx = false;

 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].nextBlockId == blockId){
 				if(getIdx == true){
 					return i;
 				}else{
 					return this.blockList[i];
 				}
 			}
 		}
 		console.log("nextBlockId="+blockId+" 해당 block데이터를 찾을수 없습니다"); 		
 		if(getIdx == true){
 			return -1;
		}else{
 			return null;
		}
 	}
}

/*
function Dog(){
	this.name = "aaa";
}

Dog.prototype.getName = function(){

}

new Dog();
