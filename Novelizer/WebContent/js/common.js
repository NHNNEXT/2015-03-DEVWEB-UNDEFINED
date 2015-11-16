document.querySelector(".tab-edit").addEventListener("click",function(e){
	switchTab("edit");
})
document.querySelector(".tab-preset").addEventListener("click",function(){
	switchTab("preset");
})
document.querySelector(".tab-scenario").addEventListener("click",function(){
	switchTab("scenario");
})

function switchTab(tabid){
	var tabList = document.querySelectorAll("main>div");
	for(var i = 0; i < tabList.length; ++i){
		tabList[i].style.display="none";
	}
	
	document.querySelector("#"+tabid).style.display="block";
}

var selectedBlockId = 0;


$(document).ready(function(){

    $('#makeMeDraggable').draggable();

    // 새로운 블록 추가
    $("#button-add-block").click(function(){
        EditorDataSync.addBlock(function(data){
            $("ol#block-list").append("<li data-block-id='"+data.blockId+"' class='block'><ul class='action-list'></ul></li>");
        });
    });

    $(document).on("click","#block-list li.block",function(e){
        selectedBlockId = $(this).data("blockId");
        $("li.selected").removeClass("selected");
        $(this).addClass("selected");
    })

    $("#button-add-action").click(function(){
        EditorDataSync.addAction(selectedBlockId,function(data){
            $("li.selected ul.action-list").append("<li data-action-id='"+data.actionId+"'>action"+data.actionId+"</li>");  
        });
    });
    
    $("#button-remove").click(function(){
        $("li").remove();
    });

    $( "#block-list,.action-list,#temp-action-list" ).sortable({
        revert: true
    });
    $( "#draggable" ).draggable({
        connectToSortable: "#sortable",
        helper: "clone",
        revert: "invalid"
    });
    $( "ul, li" ).disableSelection();
});  