
var Editor = {
    selectedBlockId : 0,
    init : function(){
        // 새로운 블록,액션 추가 
        $(document).on("click","#button-add-block", this.addBlock);
        $(document).on("click","#button-add-action", this.addAction);

        // 블록 선택
        $(document).on("click","#block-list li.block", this.selectBlock);
        $(document).on("click","#button-remove", this.removeBlock);

        // TODO: 액션 선택 추가


        // TODO: sortable 새로 추가된 액션 리스트에 적용되게 수정하기
        // action-list li 태그를 이동할 수 있게 
        $( "#block-list,.action-list,#temp-action-list" ).sortable({
            revert: true
        });

        $( "#draggable" ).draggable({
            connectToSortable: "#sortable",
            helper: "clone",
            revert: "valid"
        });
        $( "ul, li" ).disableSelection();


    },
    addBlock : function(){
        EditorDataSync.addBlock(function(data){
            $("ol#block-list").append("<li data-block-id='"+data.blockId+"' class='block'><ul class='action-list'></ul></li>");
        });
    },
    removeBlock : function(){
        EditorDataSync.removeBlock(Editor.selectedBlockId);
        $("li.selected").remove();
    },

    //TODO: removieAction

    //
    selectBlock : function(){
        Editor.selectedBlockId = $(this).data("blockId");
        $("li.selected").removeClass("selected");
        $(this).addClass("selected");
    },
    addAction : function(){
        EditorDataSync.addAction(Editor.selectedBlockId,function(data){
            $("li.selected ul.action-list").append("<li data-action-id='"+data.actionId+"'>action"+data.actionId+"</li>");  
        });
    }, 
    selectAction : function(){
         Editor.selectedActionId = $(this).data("actionId");
        $("li.selected").removeClass("selected");
        $(this).addClass("selected");
    }


}

$(".tab-edit").on("click",function(event){
	switchTab("edit");
})
$(".tab-preset").on("click",function(event){
	switchTab("preset");
})
$(".tab-scenario").on("click",function(event){
	switchTab("scenario");
})

function switchTab(tabid){
	var tabList = document.querySelectorAll("main>div");
	for(var i = 0; i < tabList.length; ++i){
		tabList[i].style.display="none";
	}
	
	document.querySelector("#"+tabid).style.display="block";
}



