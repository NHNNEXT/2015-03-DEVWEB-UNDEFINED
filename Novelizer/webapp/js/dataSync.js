/**
 * EditorDataSync : 에디터 페이지에서 변경되는 데이터 로컬스토리지에 저장 및 서버 연동 처리
 * author : cca-company
 */

/*
 * callback = {done:callbackDoneFunc, fail:callbackFailFunc}
 */

var EditorDataSync = {
 	url : "http://127.0.0.1:8080",
 	sceneId : 1,
 	startBlockId : 1,
	blockList : [],
	presetList : [
        {
            presetId : 0,
            image : "",
            name : "없음"
        },
        {
            presetId : 1,
            image : "https://i.ytimg.com/vi/edaw69Jnjxg/maxresdefault.jpg",
            name : "하늘"
        },
        {
            presetId : 2,
            image : "https://i.ytimg.com/vi/edaw69Jnjxg/maxresdefault.jpg",
            name : "하늘"
        },
    ],
	characterList : [
        {
            characterId : 1,
            image : "http://www.mhsh.co.kr/multi/images/section1/content2.png",
            name : "영희"
        },
        {
            characterId : 2,
            image : "http://www.mhsh.co.kr/multi/images/section1/content3.png",
            name : "철수"
        },
    ],
 	init : function(sceneId){
 		this.sceneId = sceneId;

 		//this.getTimelineData();
 		//this.getCharacterData();
 		//this.getPresetData();

 	},
 	getDataObject : function(){
 		var data = {
 			blockList : this.blockList.slice(0),
 			presetList : this.presetList.slice(0),
 			characterList : this.characterList.slice(0),
 		};
 		return data;
 	},
 	getTimelineData : function(){	// 현재 씬의 블록 리스트 데이터를 가져옴
 		$.ajax({
 			url : this.url+"/scene",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})
 		.done(function(data){
 			this.blockList = data;
 		})
 		.fail();
 	},
 	postTimelineData : function(callback){
 		$.ajax({
 			url : this.url+"/scene",
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

 		for(var i = 0; i < this.blockList.length; ++i){
 			if(maxBlockId < this.blockList[i].blockId){
 				maxBlockId = this.blockList[i].blockId;
 			}
 		}
 		if(maxIdx >= 0){
 			this.blockList[maxIdx].nextBlockId=maxBlockId+1;
 		}
 		var data = {
 			blockId : ++maxBlockId,
 			actionList : [
                {actionId:1, actionType:"text", characterId:0, text:""},
            ],
 			nextBlockId : 0,
 		};

 		this.blockList.push(data);
 		callbackDone(this.getDataObject());
 		return maxBlockId;
 	},
 	removeBlock : function(blockId){
 		var targetBlockIdx = this.blockList.indexOf(this.getBlock(blockId));
 		var prevBlock = this.getPrevBlock(blockId);
		var targetBlock = this.blockList.splice(targetBlockIdx,1);
 		
 		if(prevBlock){
 			prevBlock.nextBlockId = targetBlock.nextBlockId;
 		}
 	},
 	sortBlock : function(blockId, prevBlockId){
 		var targetBlock = this.getBlock(blockId);
 		var currentPrevBlock = this.getBlock(prevBlockId);
 		var beforePrevBlock = this.getPrevBlock(blockId);

 		if(beforePrevBlock != null){
 			beforePrevBlock.nextBlockId = targetBlock.nextBlockId;
 		}

 		if(currentPrevBlock != null){
 			targetBlock.nextBlockId = currentPrevBlock.nextBlockId;
 			currentPrevBlock.nextBlockId = blockId;
 		}else{
 			targetBlock.nextBlockId = this.startBlockId;
 			this.startBlockId = blockId;
 		}

 		var targetBlockIdx = this.blockList.indexOf(targetBlock);
 		var prevBlockIdx = this.blockList.indexOf(currentPrevBlock);

 		if(targetBlockIdx > prevBlockIdx){
 			this.blockList.splice(targetBlockIdx, 1);
 			this.blockList.splice(prevBlockIdx + 1, 0, targetBlock);
 		}else{
 			this.blockList.splice(prevBlockIdx + 1, 0, targetBlock);
 			this.blockList.splice(targetBlockIdx, 1);
 		}
 	},
 	addCharacterAction : function(blockId, characterId, posX, posY){
 		var data= {
 			actionType : "character",
 			characterId : characterId,
 			optionId : 0,
 			posX : posX || 0,
 			posY : posY || 0,
 		};
 		return this.addAction(blockId, data)
 	},
 	addBackgroundAction : function(blockId, presetId){
 		var data= {
 			actionType : "background",
 			presetId : presetId,
 		};
 		return this.addAction(blockId, data)
 	},
 	addAction : function(blockId, data){
 		var targetBlock = this.getBlock(blockId);
 		var maxIdx = targetBlock.actionList.length-1;
 		var maxActionId = (targetBlock.actionList[maxIdx]) ? targetBlock.actionList[maxIdx].actionId : 0;
 		
 		data.actionId = ++maxActionId;
 		targetBlock.actionList.push(data);
 		return data;
 	},
 	removeAction : function(blockId, actionId){
 		var targetBlock = this.getBlock(blockId);
 		var targetAction = this.getAction(blockId, actionId);
 		var targetActionIdx = targetBlock.actionList.indexOf(targetAction);
 		targetBlock.actionList.splice(targetActionIdx, 1);
 	},
 	modifyTextAction : function(blockId, text, characterId){
 		var block = this.getBlock(blockId);
 		var action = null;
 		for(var i = 0; i < block.actionList.length; ++i){
 			if(block.actionList[i].actionType == "text"){
 				action = block.actionList[i];
 			}
 		}
 		action.characterId = characterId;
 		action.text = text;
 	},
 	modifyCharacterAction : function(blockId, actionId, characterId, optionId, posX, posY){
 		var action = this.getAction(blockId,actionId);
 		action.characterId = characterId;
 		action.optionId = optionId;
 		action.posX = posX;
 		action.posY = posY;

 		return action;
 	},
 	modifyBackgroundAction : function(blockId, actionId, presetId){
 		var action = this.getAction(blockId,actionId);
 		action.presetId = presetId;
 	},
 	getBlock : function(blockId){
 		return this.blockList.find(function(element, index, array){
 			return (element.blockId == blockId);
 		})
 	},
 	getPrevBlock : function(blockId){
 		if(this.startBlockId == blockId) return null

 		return this.blockList.find(function(element, index, array){
 			return (element.nextBlockId == blockId);
 		})
 	},
 	getAction : function(blockId, actionId){
 		var block = this.getBlock(blockId);
 		return block.actionList.find(function(element, index, array){
 			return (element.actionId == actionId);
 		})
 	},
 	getPreset : function(presetId){
 		return this.presetList.find(function(element, index, array){
 			return (element.presetId == presetId);
 		})
 	},
 	getCharacter : function(characterId){
 		return this.characterList.find(function(element, index, array){
 			return (element.characterId == characterId);
 		})
 	},
 	getBackgroundActionData : function(blockId){
 		var block = this.getBlock(blockId);
 		var action = null;

 		while(block != null && !action){
	 		action = block.actionList.find(function(element, index, array){
	 			return (element.actionType == "background");
	 		});
	 		blockId = block.blockId;
	 		block = this.getPrevBlock(blockId);
 		}

 		if(action){
	 		var preset = this.getPreset(action.presetId);
	 		return {image : preset.image};
 		}else{
 			return {image : ""};
 		}
 	},
 	getCharacterAction : function(blockId, characterId){
 		var block = this.getBlock(blockId);

 		return action = block.actionList.find(function(element, index, array){
 			return (element.actionType == "character" && element.characterId == characterId);
 		});
 	},
 	getCharacterActionData : function(blockId){
 		var characterList = [];
 		var closedCharacterId = [];
 		var block = this.getBlock(blockId);

 		while(block != null){
 			for(var i = 0; i < block.actionList.length; ++i){
 				var action = block.actionList[i];
 				if(action.actionType == "character" &&  closedCharacterId.indexOf(action.characterId) == -1){
	 				if(action.optionId == 0){
				 		var data = {
				 			data : this.getCharacter(action.characterId),
				 			posX : action.posX,
				 			posY : action.posY
				 		};
				 		characterList.push(data);
	 				}
			 		closedCharacterId.push(parseInt(action.characterId));
 				}
 			}
 			blockId = block.blockId;
	 		block = this.getPrevBlock(blockId);
 		}

 		return characterList;
 	},
 	getTextActionData : function(blockId){
 		var block = this.getBlock(blockId);

 		if(block){
	 		var action = block.actionList.find(function(element, index, array){
	 			return (element.actionType == "text");
	 		})

	 		return {text : action.text, characterId : action.characterId };
 		}else{
 			return {text : "", characterId : 0};
 		}
 	},
}
