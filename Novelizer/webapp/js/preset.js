$("#add-character").on("click",function(){
	$("#character-wrap").append("<div class ='character'><button class='select-button'>선택</button> <button class='remove-button'>삭제</button></div>");
})

$("#character-wrap").on("click",".remove-button",function(){
	$(this).parent().remove();
})

$("#character-wrap").on("click", ".select-button",function(){
	$("#character-detail").empty().append("<div>hello world</div>");
})

