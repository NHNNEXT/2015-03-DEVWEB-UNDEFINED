// TODO 데이터객체 모델로 분리 
// 더미 
var timelineContext = {
    blockList:[
        {
            blockId:1,
            actionList:[
                {actionId:1, isText:true, actionType:"text"},
                {actionId:2, isBackground:true, actionType:"background"}
            ]
        },
        {
            blockId:2,
            actionList:[
                {actionId:1, isCharacter:true, actionType:"character"},
                {actionId:2, isText:true, actionType:"text"}
            ]
        },
    ]
};

// 더미
var backgroundPresetContext = {
    backgroundPreset : [
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
    ]
}

var Template = {
    timelineSource : ,
    backgroundPresetSource : $("#background-preset-template").html(),
    TimelineTemplate : null,

    compileTimeline : function(context){
        // Closure로 개선
        if(this.TimelineTemplate == null){
            this.TimelineTemplate = Handlebars.compile($("#timeline-template").html());
        }
        var html = $(this.TimelineTemplate(context));
        $("#block-list").empty().append(html);
    },
    compileBackgroundPreset : function(context){
        var template = Handlebars.compile(this.backgroundPresetSource);
        var html = $(template(context));
        $("#toolbox-background .background-item-list").empty().append(html);

    },
    compileCharacterPreset : function(context){

    }
}

var Timeline = {
    selectedBlockId : 0,
    init : function(){
        Template.compileTimeline(timelineContext);
        Template.compileBackgroundPreset(backgroundPresetContext);

        $(".button-add-block").click(this.addBlock);

        $("#block-list").on("click",".button-add-action", this.addAction);  
        $("#block-list").on("click",".button-remove-block", this.removeBlock);
        $("#block-list").on("click",".block", this.selectBlock);

        // 텍스트박스 동기화 
        $("#block-list").on("keyup", ".action-text", this.copyText);

        // action-list li 태그를 이동할 수 있게 
        $( "#block-list" ).sortable({
            revert: true, //revert는 디폴트가 false이다. 드래그가 스탑되면 원래 자리로 돌아간다. revert가 true이면 그 요소는 언제나 되돌아 간다.
            handle: ".handler"
        });

        $( ".draggable" ).draggable({ 
            revert: true
        });

        $("#screen").droppable({
            accept: ".draggable",
            hoverClass: "ui-state-hover",
            drop: function(event, ui){
                var item = ui.draggable;

                if(item.hasClass("background-item")){
                    $("#layer-background").css({
                        backgroundImage : "url('"+item.data("image")+"')"
                    })
                }else if(item.hasClass("character-item")){

                }
            }
        });

         $(".leftChracter").droppable({
               accept: ".character1",
               activeClass: "ui-state-hover",
               hoverClass: "ui-state-active",
               drop: function( event, ui ) {

                $(".leftChracter").css({
                      backgroundImage:"url('http://www.mhsh.co.kr/multi/images/section1/content2.png')",
                      border:"0px",
                });
            }
        });

         $(".rightChracter").droppable({
               accept: ".character2",
               activeClass: "ui-state-hover",
               hoverClass: "ui-state-active",
               drop: function( event, ui ) {

                $(".rightChracter").css({
                      backgroundImage:"url('http://www.mhsh.co.kr/multi/images/section1/content3.png')",
                      border:"0px",
                });
            }
        });
    },


    addBlock : function(){
        //EditorDataSync.addBlock(function(data){
            timelineContext.blockList.push({
                blockId:1,
                actionList:[
                    {actionId:1, isText:true, actionType:"text"},
                ]
            })
            Template.compileTimeline(timelineContext);
        //}); 
    },

    removeBlock : function(){
        EditorDataSync.removeBlock(this.selectedBlockId);
        $("li.selected").remove();
    },

    //TODO: removeAction

    //블록선택 
    selectBlock : function(){
        this.selectedBlockId = $(this).data("blockId"); //jQuery( ":data(key)" ) key: The data key.
        $("li.selected").removeClass("selected");
        $(this).addClass("selected"); // 매개변수인 selected를 클래스 특성에 추가한다. 이미 존재하는 class 특성 값에 새로운 값 추가
    },

    selectAction : function(){
         this.selectedActionId = $(this).data("actionId");
        $(this).removeClass("selected");
        console.log(this.selectedActionId);
    },

    addAction : function(){
        EditorDataSync.addAction(this.selectedBlockId,function(data){
           var select = $("select.temp.action-option").clone();
           var selectText = $("option.textAction");
           select.removeClass("temp");
           var li = $("<li><div class='move'></div></li>");
          
           li.data("actionId",data.actionId).append(select);
           $("li.selected ul.action-list").append(li);
        });
    },

     changeOption : function(){
      var textArea = $("<textarea class='textsync' placeholder='이곳에 대사를 입력해주세요' maxlength='300'></textarea>");
         if($(this).val() == "text")
            $(this).closest("li").append(textArea);
         // else if($(this).val() == "character")
         //    $(this).closest("li").append(textArea);               
     },

     copyText :  function(){
           $(".textfield").val($(this).val());
        },

     backgroundFill : function(){

         $(".dragzones").draggable({
        start: handleDragStart,
        cursor: 'move',
        revert: "invalid",
        });
        $(".dropzones").droppable({
            drop: handleDropEvent,
            tolerance: "touch",              
        });
        validateDropzones();
        }
}

var Toolbar = {

}