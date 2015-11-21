var Viewer = {
	sceneId : 1,
	blockList : [],
	currentBlock : null,
	presetData : ["http://placekitten.com/g/200/500","http://placekitten.com/g/205/500","http://placehold.it/1000x600"],//임시 데이터
	init : function(){
		this.getSceneData(this.startScene);
		$(document).on("click","#layer-text",this.onCommand);
	},
	onCommand : function(){
		var nextBlock = Viewer.getNextBlock(Viewer.currentBlock);
		if(nextBlock){
			Viewer.playBlock(nextBlock);
		}else{
			alert("끝입니다");
		}
	},
	getSceneData : function(callbackDone){
		/*
 		var request = $.ajax({
 			url : this.url+"/getBlock.do",
 			method : "GET",
 			dataType : "json",
 			data : {sceneId : this.sceneId},
 		})

 		request.done(callbackDone);
		*/
		// 임시 데이터 하드코딩...ㅋㅋ 
		callbackDone([
{
	"blockId" : 1,
	"nextBlockId" : 2,
	"actionList":[
		{
			"type":"CHARACTER",
			"characterId":1,
			"presetId":1,
			"option":"APPEAR",
			"position":[100,0],
			"animation":null
		},
		{
			"type":"BACKGROUND",
			"presetId":3,
			"option":"APPEAR",
			"position":[0,0],
			"animation":null
		}
	]
},
{
	"blockId" : 2,
	"nextBlockId" : 3,
	"actionList":[
		{
			"type":"TEXT",
			"text":"왜 이렇게 철수가 늦지?",
			"characterId":1
		}
	]
},
{
	"blockId" : 3,
	"nextBlockId" : 4,
	"actionList":[
		{
			"type":"CHARACTER",
			"characterId":2,
			"presetId":2,
			"option":"APPEAR",
			"position":[700,0],
			"animation":null
		},
		{
			"type":"TEXT",
			"text":"헉헉, 미안해 내가 너무 늦었지?",
			"characterId":2
		}
	]
},
{
	"blockId" : 4,
	"nextBlockId" : 5,
	"actionList":[
		{
			"type":"TEXT",
			"text":"그걸 지금 말이라고 해? 우리 헤어져! ",
			"characterId":1
		}
	]
},
{
	"blockId" : 5,
	"nextBlockId" : 0,
	"actionList":[
		{
			"type":"CHARACTER",
			"characterId":1,
			"presetId":1,
			"option":"DISAPPEAR",
			"position":[0,0],
			"animation":null
		}
	]
}
]);
	},
	startScene : function(data){
		Viewer.blockList = data;
		var block = Viewer.getBlock(1); // 임시 데이터
		Viewer.playBlock(block);
	},
	playBlock : function(block){
		Viewer.currentBlock = block;
		for(var actionIdx in block.actionList){
			var action = block.actionList[actionIdx];
			Viewer.doAction(action);
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
						backgroundImage:"url('"+Viewer.presetData[action.presetId-1]+"')",
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
				backgroundImage:"url('"+Viewer.presetData[action.presetId-1]+"')",
			})
		}
	},
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
	getNextBlock : function(block){
		var nextBlockId = block.nextBlockId;
		if(nextBlockId == 0){
			// TODO : 플래그 처리 
		}
		
		return Viewer.getBlock(nextBlockId);
	}
}

