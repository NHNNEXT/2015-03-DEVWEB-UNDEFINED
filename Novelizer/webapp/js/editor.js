// TODO 데이터객체 모델로 분리 
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

// 더미
var characterPresetContext = {
    characterPreset : [
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
    ]
}

var Editor = {
    init : function(){
        EditorDataSync.init();
        var dataObject = EditorDataSync.getDataObject();
        Template.init(dataObject);
        Timeline.init();
        Preview.init();
        Toolbox.init();
        $(".editor-save").on("click",this.save.bind(this));
    },
    save : function(){
        EditorDataSync.postTimelineData({
            done : function(){

            },
            fail : function(){

            }
        });
    },
    addBlock : function(callback){
        EditorDataSync.addBlock(callback);
    },
    removeBlock : function(blockId, callback){
        var callback = callback || function(){};
        EditorDataSync.removeBlock(blockId);
        var dataObject = EditorDataSync.getDataObject();
        Timeline.selectedBlockId = 0;
        Preview.previewBlockId = 0;
        Template.compileTimeline(dataObject);
        Toolbox.deactivate();
        Preview.updateBlock(this.getBlockPreviewData(0));
        callback();
    },
    selectBlock : function(blockId){
        Timeline.selectedBlockId = blockId;
        Preview.previewBlockId = blockId;
        Preview.updateBlock(this.getBlockPreviewData(blockId));
        Toolbox.activate();
    },
    compileTimeline : function(){
        var dataObject = EditorDataSync.getDataObject();
        Template.compileTimeline(dataObject);
    },
    changeText : function(blockId, text, characterId){
        EditorDataSync.modifyTextAction(blockId, text, characterId);
        Preview.updateText(text, characterId);
    },
    changeTextPreset : function(blockId, text, characterId){
        EditorDataSync.modifyTextAction(blockId, text, characterId);
        var dataObject = EditorDataSync.getDataObject();
        Template.compileTimeline(dataObject);
        Preview.updateText(text, characterId);
    },
    addBackground : function(blockId, presetId, callback){
        var callback = callback || function(){};
        var actionData = EditorDataSync.addBackgroundAction(blockId, presetId);
        var dataObject = EditorDataSync.getDataObject();
        var image = EditorDataSync.getPreset(actionData.presetId).image;
        Template.compileTimeline(dataObject);
        Preview.updateBackground(image);
        callback();
    },
    changeBackground : function(blockId, actionId, presetId){
        var presetData = EditorDataSync.getPreset(presetId);
        Preview.updateBackground(presetData.image);
        EditorDataSync.modifyBackgroundAction(blockId, actionId, presetId);
    },
    addCharacter : function(blockId, characterId, callback){
        var callback = callback || function(){};
        var actionData = EditorDataSync.addCharacterAction(blockId, characterId);
        var DataObject = EditorDataSync.getDataObject();
        var character = EditorDataSync.getCharacter(actionData.characterId);
        Template.compileTimeline(DataObject);
        Preview.updateCharacter(character, actionData.posX, actionData.posY);
        callback();
    },
    changeCharacter : function(blockId, actionId, characterId, optionId, posX, posY){
        var callback = callback || function(){};
        var actionData = EditorDataSync.modifyCharacterAction(blockId, actionId, characterId, optionId, posX, posY);
        var character = EditorDataSync.getCharacter(actionData.characterId);
        Preview.updateBlock(this.getBlockPreviewData(blockId));
        callback();
    },
    changeCharacterPreset : function(blockId, characterId, posX, posY){
        var action = EditorDataSync.getCharacterAction(blockId, characterId);
        if(action){
            EditorDataSync.modifyCharacterAction(blockId, action.actionId, characterId, action.optionId, posX, posY);
        }else{
            EditorDataSync.addCharacterAction(blockId, characterId, posX, posY);
        }
        var DataObject = EditorDataSync.getDataObject();
        Template.compileTimeline(DataObject);
    },
    removeAction : function(blockId, actionId, callback){
        var callback = callback || function(){};
        EditorDataSync.removeAction(blockId, actionId);
        var dataObject = EditorDataSync.getDataObject();
        Template.compileTimeline(dataObject);
        Preview.updateBlock(this.getBlockPreviewData(blockId));
        callback();
    },
    getBlockPreviewData : function(blockId){
        /*
        data{
            background:{image},
            character:[{characterData,posX,posY}],
            text:{characterId, text}
        }
        */
        var data = {
            background : null,
            character : null,
            text : null,
        }
        data.background = EditorDataSync.getBackgroundActionData(blockId);
        data.text = EditorDataSync.getTextActionData(blockId);
        data.character = EditorDataSync.getCharacterActionData(blockId);
        return data;
    },
}

var Template = {
    init : function(dataObject){
        Handlebars.registerHelper("equals", function (a, b, options) {
            if(a == b){
                return options.fn(this);
            }else{
                return options.inverse(this);
            }
        });

        Template.compileTimeline(dataObject);
        Template.compileBackgroundPreset(dataObject);
        Template.compileCharacterPreset(dataObject);
    },
    compileTimeline : function(context){
        var template = Handlebars.compile($("#timeline-template").html());

        this.compileTimeline = function(context){
            var html = $(template(context));
            $("#block-list").empty().append(html);
        }

        this.compileTimeline(context);
    },
    compileBackgroundPreset : function(context){
        var template = Handlebars.compile($("#background-preset-template").html());

        this.compileBackgroundPreset = function(context){
            var html = $(template(context));
            $("#toolbox-background .background-item-list").empty().append(html);
        }

        this.compileBackgroundPreset(context);
    },
    compileCharacterPreset : function(context){
        var template = Handlebars.compile($("#character-preset-template").html());

        this.compileCharacterPreset = function(context){
            var html = $(template(context));
            $("#toolbox-character .character-item-list").empty().append(html);
        }

        this.compileCharacterPreset(context);
    }
}

var Timeline = {
    selectedBlockId : 0,
    init : function(){

        $(".button-add-block").click(this.addBlock.bind(this));
        $("#block-list").on("click", ".button-add-action", this.toggleActionMenu.bind(this));  
        $("#block-list").on("click", ".button-remove-block", this.removeBlock.bind(this));
        $("#block-list").on("click", ".block", this.selectBlock.bind(this));
        $("#block-list").on("click", ".add-character-action", this.addCharacterAction.bind(this));
        $("#block-list").on("click", ".add-background-action", this.addBackgroundAction.bind(this));
        $("#block-list").on("click", ".button-remove-action", this.removeAction.bind(this));

        $("#block-list").on("change", ".action.background select", this.changeBackgroundAction.bind(this));
        $("#block-list").on("change", ".action.character select", this.changeCharacterAction.bind(this));
        $("#block-list").on("keyup", ".action.character input", this.changeCharacterAction.bind(this));
        $("#block-list").on("change", ".action.text select", this.changeTextAction.bind(this));
        $("#block-list").on("keyup", ".action.text textarea", this.changeTextAction.bind(this));

        $( "#block-list" ).sortable({
            revert: true,
            handle: ".handler",
            deactivate : this.sortBlock.bind(this)
        });

    },
    addBlock : function(e){
        Editor.addBlock(function(){
            Editor.compileTimeline();
            $("li.block[data-block-id="+this.selectedBlockId+"]").addClass("selected");
        }.bind(this));
    },
    removeBlock : function(e){
        e.stopPropagation(); // 상위 요소로의 이벤트 전파는 이 메소드로 중지할수 있어요!
        var blockId = $(e.target).closest("li.block").data("blockId");
        Editor.removeBlock(blockId);
    },
    sortBlock : function(e, ui){
        var target = $(ui.item);
        var blockId = target.data("blockId");
        var changePrevBlockId = target.prev("li.block").data("blockId");
        EditorDataSync.sortBlock(blockId, changePrevBlockId);
    },
    selectBlock : function(e){
        var blockId = $(e.target).closest("li.block").data("blockId");
        $("li.selected").removeClass("selected");
        $(e.target).closest("li.block").addClass("selected");
        Editor.selectBlock(blockId);
    },
    toggleActionMenu : function(e){
        $(".action-type-list").hide();
        $(e.target).next(".action-type-list").show();
    },
    addCharacterAction : function(e){
        var blockId = $(e.target).closest("li.block").data("blockId");
        Editor.addCharacter(blockId, 1, function(){
            $(".action-type-list").hide();
        });
    },
    addBackgroundAction : function(e){
        var blockId = $(e.target).closest("li.block").data("blockId");
        Editor.addBackground(blockId, 0, function(){
            $(".action-type-list").hide();
        });
    },
    changeCharacterAction : function(e){
        var targetAction = $(e.target).closest("li.action");
        var blockId = targetAction.closest("li.block").data("blockId");
        var actionId = targetAction.data("actionId");

        var characterId = parseInt(targetAction.children(".character-select").val());
        var optionId = parseInt(targetAction.children(".option-select").val());
        var posX = targetAction.children(".character-pos-x").val();
        var posY = targetAction.children(".character-pos-y").val();
        Editor.changeCharacter(blockId, actionId, characterId, optionId, posX, posY);
    },
    changeBackgroundAction : function(e){
        var targetAction = $(e.target).closest("li.action");
        var blockId = targetAction.closest("li.block").data("blockId");
        var actionId = targetAction.data("actionId");

        var presetId = parseInt(targetAction.children(".preset-select").val());
        var optionId = parseInt(targetAction.children(".option-select").val());

        Editor.changeBackground(blockId, actionId, presetId, optionId);
    },
    changeTextAction : function(e){
        var targetAction = $(e.target).closest("li.action");
        var blockId = targetAction.closest("li.block").data("blockId");
        var actionId = targetAction.data("actionId");

        var text = targetAction.children(".action-text").val();
        var characterId = targetAction.children(".character-select").val();

        Editor.changeText(blockId, text, characterId);
    },
    removeAction : function(e){
        var blockId = $(e.target).closest("li.block").data("blockId");
        var actionId = $(e.target).closest("li.action").data("actionId");
        Editor.removeAction(blockId, actionId);
    }
}

var Preview = {
    previewBlockId : 0,
    init : function(){
        $("#screen").droppable({
            accept: ".toolbox-item",
            hoverClass: "ui-state-hover",
            drop: this.screenDropEvent.bind(this)
        });
        $(".preview-select-character").on("change", this.changeText.bind(this));
        $(".preview-text-box").on("keyup", this.changeText.bind(this));
    },
    screenDropEvent : function(e, ui){
        if(this.previewBlockId == 0){
            alert("먼저 장면을 선택하세요");
            return;
        }
        if(ui.draggable.hasClass("background-item")){
            this.addBackground(e,ui);
        }else if(ui.draggable.hasClass("character-item")){
            this.addCharacter(e,ui);
        }
    },
    addBackground : function(e, ui){
        var item = ui.draggable;
        Editor.addBackground(this.previewBlockId, item.data("presetId"));
    },
    addCharacter : function(e, ui){
        var item = ui.draggable;
        Editor.addCharacter(this.previewBlockId, item.data("characterId"));
    },
    moveCharacter : function(e, ui){
        var characterId = $(e.target).data("characterId");
        var posX = ui.position.left / $("#screen").width() * 100;
        var posY = ui.position.top / $("#screen").height() * 100;
        Editor.changeCharacterPreset(this.previewBlockId, characterId, posX, posY);
    },
    changeText : function(e){
        var text = $(".preview-text-box").val();
        var characterId = $(".preview-select-character").val();
        Editor.changeTextPreset(this.previewBlockId, text, characterId);
    },
    updateBlock : function(data){
        this.updateBackground(data.background.image);
        this.updateText(data.text.text, data.text.characterId);
        $("#layer-object").empty();
        for(var i = 0; i < data.character.length; ++i){
            var character = data.character[i];
            this.updateCharacter(character.data, character.posX, character.posY);
        }
    },
    updateText : function(text, characterId){
        $("#layer-text .preview-text-box").val(text);
        $("#layer-text .preview-select-character").val(characterId);
    },
    updateBackground : function(image){
        $("#layer-background").css({
            backgroundImage : "url('"+image+"')"
        })
    },
    updateCharacter : function(data, posX, posY){
        var container = $("#layer-object .character[data-character-id="+data.characterId+"]");
        if(container.length == 0){
            var newImage = $("<img>")
                .attr("src",data.image)
            var container = $("<div>")
                .attr("data-character-id",data.characterId)
                .addClass("character")
                .append(newImage)
                .appendTo("#layer-object")
                .css({
                    "top" : posY+"%",
                    "left" : posX+"%",
                })
                .draggable({stop:this.moveCharacter.bind(this)});
        }else{
            container.css({
                "top" : posY+"%",
                "left" : posX+"%"
            })
        }
    },
}

var Toolbox = {
    init : function(){
        $(".toolbox-item").draggable({ 
            helper: "clone"
        });

        $(".tab-background").on("click",this.clickTabBtn.bind(this));
        $(".tab-character").on("click",this.clickTabBtn.bind(this));
        this.switchTab("background");
    },
    activate : function(){
        $("#toolbox-deactivate").hide();
    },
    deactivate : function(){
        $("#toolbox-deactivate").show();
    },
    clickTabBtn : function(e){

        var id = e.target.className.substring(4);
        this.switchTab(id);
    },
    switchTab : function(id){
        $("#toolbox .toolbox-tab").hide();
        $("#toolbox-"+id).show();
    }
}

Editor.init();