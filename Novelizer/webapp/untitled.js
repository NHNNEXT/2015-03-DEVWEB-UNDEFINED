
var Editor = { //Editor 객체 형성
    selectedBlockId : 0,
    selectedActionId : 0,
    init : function(){ //init이라는 메서드 형성 
        
        // 새로운 블록,액션 추가 
        $(document).on("click","#button-add-block", this.addBlock); //버튼 추가 버튼을 누르면 addBlock 메서드 실행    
        $(document).on("click","#button-add-action", this.addAction); //액션 추가 버튼을 누르면 addAction 메서드 실행   


        // 블록 선택
        $(document).on("click","#block-list li.block", this.selectBlock); //블락리스트를 누르면 블럭 선택 
        $(document).on("click","#button-remove", this.removeBlock); //제거 버튼을 누르면 블락을 제거  


        // 액션 선택 
        $(document).on("click","ul.action-list li", this.selectAction); //액션 리스트를 클릭하면, 액션 선택이 됨
        $(document).on("click","ul.action-list li", this.unselectedBlock);
        $(document).on("click","#button-remove", this.removeBlock); // 삭제버튼 누르면 블락이 삭제됨 

        // TODO: sortable 새로 추가된 액션 리스트에 적용되게 수정하기
        // action-list li 태그를 이동할 수 있게 
        $( "#block-list,.action-list, #temp-action-list, ul.action-list li, .action-list li, li" ).sortable({
            revert: true //revert는 디폴트가 false이다. 드래그가 스탑되면 원래 자리로 돌아간다. revert가 true이면 그 요소는 언제나 되돌아 간다.
        });

        $( "#draggable" ).draggable({
            connectToSortable: "#sortable", 
            helper: "clone", //클론을 하게 되면 그 요소는 복제되어 드래그 된다.
            revert: "valid"
        });
       
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

    //TODO: removeAction

    //블록선택 
    selectBlock : function(){
        Editor.selectedBlockId = $(this).data("blockId"); //jQuery( ":data(key)" ) key: The data key.
        $("li.selected").removeClass("selected");
        $(this).addClass("selected"); // 매개변수인 selected를 클래스 특성에 추가한다. 이미 존재하는 class 특성 값에 새로운 값 추가
    },

    //액셔선택 
    selectAction : function(){
         Editor.selectedActionId = $(this).data("actionId");
        $(this).removeClass("selected");
        console.log(Editor.selectedActionId);
    },

    //액션을 선택하면 블록 선택된 것이 해제된다.(그래야 액션이 sortable 할 것 같으므)
    unselectedBlock : function(){
        Editor.selectedBlockId = $(this).data("blockId");
        $(this).removeClass("selected");
         $(this).removeClass("block");
        console.log("123");
    },

    //액션추가 //액션을 추가 했을 떄 어떻게 발생하는건가 
    addAction : function(){
        EditorDataSync.addAction(Editor.selectedBlockId,function(data){
            $("li.selected ul.action-list").append("<li data-action-id='"+data.actionId+"'>action"+data.actionId+"
                <select name ="option" class="option"><option value="text">text</option><option value="img">img</option></selct></li>");  
            //여기 li 구조에 선택옵션을 넣을 수 있는 태그를 추가해야한다. <select name="option" class="option"></select>
        });
    }, 
}

$(".tab-edit").on("click",function(event){ //클릭하면 edit으로 넘어감.
	switchTab("edit");
})
$(".tab-preset").on("click",function(event){ // 클릭하면 preset으로 넘어감. 
	switchTab("preset");
})
$(".tab-scenario").on("click",function(event){ // 클릭하면 scenario로 넘어감. 
	switchTab("scenario");
})


//탭바꾸기 
function switchTab(tabid){
	var tabList = document.querySelectorAll("main>div");
	for(var i = 0; i < tabList.length; ++i){
		tabList[i].style.display="none";
	}
	
	document.querySelector("#"+tabid).style.display="block";
}

///////


