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


    $(document).ready(function(){

        $('#makeMeDraggable').draggable();

        $("#block").click(function(){
            $("ol").append("<li>blcok+</li>");
        });
        $("#action").click(function(){
            $("ol").append("<li>action+</li>");  
        });
        $("#remover").click(function(){
            $("li").remove();
        });

        $( "#sortable" ).sortable({
            revert: true
        });
        $( "#draggable" ).draggable({
            connectToSortable: "#sortable",
            helper: "clone",
            revert: "invalid"
        });
        $( "ul, li" ).disableSelection();
    });  