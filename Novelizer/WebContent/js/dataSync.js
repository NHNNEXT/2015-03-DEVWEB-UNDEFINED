/**
 * 
 */

var EditorDataSync = {
 	url : "http://server.url",
 	blockList : [],
 	sceneId : 0,
 	IDBRequest : null,
 	init : function(sceneId){
 		this.sceneId = sceneId;

 		//this.IDBRequest = window.indexedDB.open("novelizerEditor", 0);
		//this.IDBRequest.onupgradeneeded = this.upgradeDB;
 	},
 	getSceneData : function(callbackDone,callbackFail){	// 현재 씬의 블록 리스트 데이터를 가져옴
 		var request = $.ajax({
 			url : this.url+"/getBlock.do",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})

 		request.done(callbackDone);
 		request.fail(callbackFail);
 	},
 	postSceneData : function(){

 	},
 	addBlock : function(callbackDone, callbackFail){
 		var maxIdx = this.blockList.length-1;
 		var maxBlockId = (this.blockList[maxIdx]) ? this.blockList[maxIdx].blockId : 0;
 		var data = {
 			blockId : maxBlockId+1,
 			actionList : [],
 		};
 		this.blockList.push(data);

 		callbackDone(data);
 	},
 	removeBlock : function(blockId, callbackDone, callbackFail){
 		var targetBlockIdx = -1;
 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].blockId == blockId){
 				targetBlockIdx = i;
 				break;
 			}
 		}

 		if(targetBlockIdx == -1){
 			return console.log("blockId="+blockId+" 해당 block데이터를 찾을수 없습니다");
 		}

 		this.blockList.splice(targetBlockIdx,1);
 		console.log(this.blockList);

 	},
 	addAction : function(blockId, callbackDone, callbackFail){
 		// ISSUE! javascript로 더 나은 성능의 search하는 방법은? 
 		var targetBlockIdx = -1;
 		for(var i = 0; i < this.blockList.length; ++i){
 			if(this.blockList[i].blockId == blockId){
 				targetBlockIdx = i;
 				break;
 			}
 		}

 		if(targetBlockIdx == -1){
 			return console.log("blockId="+blockId+" 해당 block데이터를 찾을수 없습니다");
 		}

 		var maxIdx = this.blockList[targetBlockIdx].actionList.length-1;
 		var maxActionId = (this.blockList[targetBlockIdx].actionList[maxIdx]) ? this.blockList[targetBlockIdx].actionList[maxIdx].actionId : 0;
 		var data = {
 			actionId : maxActionId+1
 		};
 		this.blockList[targetBlockIdx].actionList.push(data);
 		callbackDone(data);
 	}
}

/*
function Dog(){
	this.name = "aaa";
}

Dog.prototype.getName = function(){

}

new Dog();
*/