//해결해야할 문제
//1. 블록 추가시 액션 타입 지정 및 텍스트 추가/연동
// ->현재 타입지정은 select 문으로 지정했음. 여기에 텍스트 추가를 위해 <textarea>를 어디에 추가해야할까? 그리고 프리뷰와 연동하는 방법은?
//2. action list간 아이템 이동 가능하게 수정하기
//-> 암만해도 action리스트를 sortable하는게 되질 않는다 문제가 무엇일까?

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
        

        // TODO: sortable 새로 추가된 액션 리스트에  적용되게 수정하기
        // action-list li 태그를 이동할 수 있게 
        $( "#block-list" ).sortable({
            revert: true //revert는 디폴트가 false이다. 드래그가 스탑되면 원래 자리로 돌아간다. revert가 true이면 그 요소는 언제나 되돌아 간다.
        });

        $( ".action-list" ).sortable({
            revert: true //revert는 디폴트가 false이다. 드래그가 스탑되면 원래 자리로 돌아간다. revert가 true이면 그 요소는 언제나 되돌아 간다.
        });

        $( ".draggable" ).draggable({ // 아래의 내용이 있으니 drag가 안되서 일단 주석처리함.
            //connectToSortable: "#sortable", 
            //helper: "clone", //클론을 하게 되면 그 요소는 복제되어 드래그 된다.
            //revert: "valid"
        });
    },

    addBlock : function(){
        EditorDataSync.addBlock(function(data){
            $("ol#block-list").append("<li data-block-id='"+data.blockId+"' class='block'><ul class='action-list'></ul></li>");
        }); // 체이닝할 때의 타겟 
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


    selectAction : function(){
         Editor.selectedActionId = $(this).data("actionId");
        $(this).removeClass("selected");
        console.log(Editor.selectedActionId);
    },


    addAction : function(){
        EditorDataSync.addAction(Editor.selectedBlockId,function(data){
           var select = $("select.temp-action-option").clone()
           select.removeClass("temp-action-option");
           var li = $("<li></li>");
           li.data("actionId",data.actionId).append(select);
           $("li.selected ul.action-list").append(li);
          
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

//툴 박스에서의 탭 전환
 $(function () {
    $(".tab_content").hide();
    $(".tab_content:first").show();
    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    });
});


//탭바꾸기 
function switchTab(tabid){
    var tabList = document.querySelectorAll("main>div");
    for(var i = 0; i < tabList.length; ++i){
        tabList[i].style.display="none";
    }
    
    document.querySelector("#"+tabid).style.display="block";
}


