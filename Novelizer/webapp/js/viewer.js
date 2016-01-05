var Viewer = {
	url : "http://127.0.0.1:8080",
	sceneId : 1,
	blockList : [],
	currentBlock : null,
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
	init : function(){
		this.getSceneData(this.startScene.bind(this));
		$(document).on("click","#layer-text",this.onCommand.bind(this));
	},
	onCommand : function(){
		var nextBlock = this.getNextBlock(this.currentBlock);
		if(nextBlock){
			this.playBlock(nextBlock);
		}else{
			alert("끝입니다");
		}
	},
	getSceneData : function(callbackDone){
 		var request = $.ajax({
 			url : this.url+"/scene",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})
 		.done(callbackDone);
	},
	startScene : function(data){
		this.blockList = data.blockList;
		var block = this.getBlock(1); // 임시 데이터
		this.playBlock(block);
	},
	playBlock : function(block){
		this.currentBlock = block;
		for(var actionIdx in block.actionList){
			var action = block.actionList[actionIdx];
			this.doAction(action);
		}
	},
	doAction : function(action){
		var layer;

		if(action.actionType == "text")
		{
			if(this.getCharacter(action.characterId)){
				$("#layer-text .character-name")
					.show()
					.text(this.getCharacter(action.characterId).name);
			}else{
				$("#layer-text .character-name").hide();
			}
			$("#layer-text p").text(action.text);
		}
		else if(action.actionType == "character")
		{
			layer = $("#layer-obj");
			// ISSUE! object요소에 z-index attr 추가 
			if(action.optionId == 0){
				if($("#character"+action.characterId).length == 0){
					var img = $("<img>")
						.attr("src",this.getCharacter(action.characterId).image);
					$("<div id='character"+action.characterId+"' class='character'></div>")
						.css({
							left:action.posX + "%",
							top:action.posY + "%"
						})
						.append(img)
						.appendTo(layer);
				}else{
					$("#character"+action.characterId)
						.css({
							left:action.posX + "%",
							top:action.posY + "%"
						})
				}
			}else if(action.optionId == 1){
				$("#character"+action.characterId).remove();
			}
		}
		else if(action.actionType=="background")
		{
			layer = $("#layer-bg");
			layer.css({
				backgroundImage:"url('"+this.getPreset(action.presetId).image+"')",
			})
		}
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
	getNextBlock : function(block){
		var nextBlockId = block.nextBlockId;
		if(nextBlockId == 0){
			// TODO : 플래그 처리 
		}
		
		return this.getBlock(nextBlockId);
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
	}
}

