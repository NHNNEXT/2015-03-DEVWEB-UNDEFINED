var Viewer = {
	url : "http://127.0.0.1:8080",
	sceneId : 1,
	blockList : [],
	currentBlock : null,
	presetData : ["http://placekitten.com/g/200/500","http://placekitten.com/g/205/500","http://placehold.it/1000x600"],//임시 데이터
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
 			url : this.url+"/blockList",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})

 		request.done(callbackDone);
	},
	startScene : function(data){
		this.blockList = data;
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

		if(action.type == "TEXT")
		{
			layer = $("#layer-text");
			layer.html(action.text);
		}
		else if(action.type == "CHARACTER")
		{
			layer = $("#layer-obj");
			// ISSUE! object요소에 z-index attr 추가 
			if(action.option == "APPEAR"){
				$("<div id='character"+action.characterId+"' class='character'></div>")
					.css({
						backgroundImage:"url('"+this.presetData[action.presetId-1]+"')",
						width:"200px",
						height:"500px",
						left:action.position[0],
						top:action.position[1]
					})
					.appendTo(layer);
			}else if(action.option=="DISAPPEAR"){
				$("#character"+action.characterId).remove();
			}
		}
		else if(action.type=="BACKGROUND")
		{
			layer = $("#layer-bg");
			layer.css({
				backgroundImage:"url('"+this.presetData[action.presetId-1]+"')",
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
	}
}

