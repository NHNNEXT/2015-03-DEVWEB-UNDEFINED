/**
 * EditorDataSync : 에디터 페이지에서 변경되는 데이터 로컬스토리지에 저장 및 서버 연동 처리
 * author : cca-company
 */

/*
 * callback = {done:callbackDoneFunc, fail:callbackFailFunc}
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
 		this.blockList = data;
 		// TODO : 서버에서 받은 기존 리스트 정보를 DOM에 추가
 	},
 	getSceneData : function(callback){	// 현재 씬의 블록 리스트 데이터를 가져옴
 		$.ajax({
 			url : this.url+"/blockList",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})
 		.done(callback[done])
 		.fail(callback[fail]);
 	},
 	postSceneData : function(callback){
 		$.ajax({
 			url : this.url+"/blockList",
 			method : "POST",
 			dataType : "json",
 			data : {sceneId : this.sceneId,
 					data : JSON.stringify(this.blockList)},
 		})
		.done(callback[done])
		.fail(callback[fail]);
 	},
 	addBlock : function(callbackDone){
 		var maxIdx = this.blockList.length-1;
 		var maxBlockId = 0;
 		if(maxIdx >= 0){			
 			maxBlockId = this.blockList[maxIdx].blockId;
 			this.blockList[maxIdx].nextBlockId=maxBlockId+1;
 		}
 		var data = {
 			blockId : ++maxBlockId,
 			actionList : [],
 			nextBlockId : 0,
 		};

 		this.blockList.push(data);
 		callbackDone(data);
 		return maxBlockId;
 	},
 	removeBlock : function(blockId, callback){
 		var targetBlockIdx = this.blockList.indexOf(this.getBlock(blockId));
 		var prevBlock = this.getPrevBlock(blockId);
		var targetBlock = this.blockList.splice(targetBlockIdx,1);
 		
 		if(prevBlock){
 			prevBlock.nextBlockId = targetBlock.nextBlockId;
 		}

 	},
 	sortBlock : function(blockId, prevBlockId, callback){
 		var targetBlock = this.getBlock(blockId);
 		var currentPrevBlock = this.getBlock(prevBlockId);
 		var beforePrevBlock = this.getPrevBlock(blockId);

 		beforePrevBlock.nextBlockId = targetBlock.nextBlockId;
 		targetBlock.nextBlockId = currentPrevBlock.nextBlockId;
 		currentPrevBlock.nextBlockId = blockId;

 	},
 	sortAction : function(blockId, actionId, changeBlockId){
 		var block = this.getBlock(blockId);
 		var actionIdx = block.actionList.indexOf(this.getAction(blockId, actionId));
 		var action = block.actionList.splice(actionIdx,1);
 		var changeBlock = this.getBlock(changeBlockId);
 		var maxIdx = block.actionList.length-1;
 		var maxActionId = (block.actionList[maxIdx]) ? block.actionList[maxIdx].actionId : 0;

 		action.blockId = changeBlockId;
 		action.actionId = ++maxActionId;
 		changeBlock.actionList.push(action);

 		return action;
 	},
 	addAction : function(blockId, callbackDone){
 		var targetBlock = this.getBlock(blockId);
 		var maxIdx = targetBlock.actionList.length-1;
 		var maxActionId = (targetBlock.actionList[maxIdx]) ? targetBlock.actionList[maxIdx].actionId : 0;
 		var data = {
 			blockId : blockId,
 			actionId : ++maxActionId,
 			type : "TEXT",
 			text : "임시 데이터"
 		};

 		targetBlock.actionList.push(data);
 		callbackDone(data);
 		return maxActionId;
 	},
 	modifyAction : function(blockId, actionId, actionData){
 		var block = this.getBlock(blockId);
 		var actionIdx = block.actionList.indexOf(this.getAction(blockId,actionId));

 		actionData.actionId = actionId;
 		actionData.blockId = blockId;
 		block.actionList[actionIdx] = actionData;
 	},
 	getBlock : function(blockId){
 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].blockId == blockId){
 					return this.blockList[i];
 			}
 		}
 		console.log("blockId="+blockId+" 해당 block데이터를 찾을수 없습니다"); 	
 		return null;
 	},
 	getPrevBlock : function(blockId){
 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].nextBlockId == blockId){
 				return this.blockList[i];
 			}
 		}
 		console.log("nextBlockId="+blockId+" 해당 block데이터를 찾을수 없습니다"); 		
 		return null;
 	},
 	getAction : function(blockId, actionId){
 		var block = this.getBlock(blockId);
 		for(var i = 0; i < block.actionList.length; ++i){
 			if(block.actionList[i].actionId == actionId){
 					return block.actionList[i];
 			}
 		}
 		console.log("actionId="+actionId+" 해당 action데이터를 찾을수 없습니다"); 	
 		return null;
 	},
}