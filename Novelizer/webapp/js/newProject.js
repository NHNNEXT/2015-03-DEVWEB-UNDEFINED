$(function() {
	
 var tabTitle = $( "#tab_title" ),
 tabContent = $( "#tab_content" ),
 tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>x</span></li>",
 tabCounter = 0,
 sceneNumber = 1,
 sceneAreaNum = 1;
 var tabs = $( "#tabs" ).tabs();

 getProjectList(function(data){
    var projectList = data;

    for(var i=0; i<projectList.length; ++i){
        var data = projectList[i];
        var list = $("<li class='ui-state-default ui-corner-top' role='tab' aria-selected='true' aria-expanded='true'><a href='#tabs-'"+i+"' class='ui-tabs-anchor' role='presentation'>"+data['projectName']+"</a><span class='ui-icon ui-icon-close' role='presentation'>x</span></li>")
        projectList.push(list);
    }
    $("#projectList").append(projectList);

 }.bind(this))

    // modal dialog init: custom buttons and a "close" callback resetting the form inside
    var dialog = $( "#dialog" ).dialog({
    	autoOpen: false,
    	modal: true,
    	buttons: {
    		Add: function() {
    			addTab();
    			$( this ).dialog( "close" );
    		},
    		Cancel: function() {
    			$( this ).dialog( "close" );
    		}
    	},
    	close: function() {
    		form[ 0 ].reset();
    	}
    });

    // addTab form: calls addTab function on submit and closes the dialog
    var form = dialog.find( "form" ).submit(function( event ) {
    	addTab();
    	dialog.dialog( "close" );
    	event.preventDefault();

    }); 



    // actual addTab function: adds new tab using the input from the form above
    function addTab() {
        var title = tabTitle.val();
        var content = tabContent.val();
    	id = "tabs-" + tabCounter,
        sceneNum = 1,
        li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, title ) ),
        tabContentHtml = "<div class='projectContents'><div class='projectContentsText'>"+content+"</div></div>" + 
        "<div id='sortable"+tabCounter+"' class='sceneArea'><div class='newS newScene"+sceneNum+"'><div class='removeScene'></div>"+"scene"+sceneNum+++"<input type='textarea' class='sceneText'></textarea></div><div class='sceneExplain'>If you want to make story just double click! <br /> if you want to sortable scenes, just sort with click</div></div>"; 
//    	
    	tabs.find( ".ui-tabs-nav" ).append( li );
        tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p>" );
        $("#sortable"+tabCounter).append("<div class='plusButton'>"+"+"+"</div>");
        tabs.tabs( "refresh" );

        $('.plusButton').on('click', function(){ 
          $("#sortable"+tabCounter).append("<div class='newS newScene"+sceneNum+++"'>"+"scene"+(sceneNum-1)+"<input type='textarea' class='sceneText'></textarea></div>").children(':last').hide().fadeIn(1000);
          addEditor();
        });  
        
        $('.removeScene').on('click', function(){
        	alert(1);
        	$(this).remove("newS");
        });
        
        $( "#sortable"+tabCounter).sortable();
        // tabCounter++;
    	// $( "#sortable" ).disableSelection();
    }
    
    function addEditor() {

    	$(".newS").on("dblclick", function(){          
      		$('body').load('editor.jsp');
              }); 
    }
    
    // addTab button: just opens the dialog
    $( "#add_tab" )
    .button()
    .click(function() {
    	dialog.dialog( "open" );
        tabCounter++;

    });



    // close icon: removing the tab on click
    tabs.delegate( "span.ui-icon-close", "click", function() {
    	var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
    	$( "#" + panelId ).remove();
    	tabs.tabs( "refresh" );
    });

    tabs.bind( "keyup", function( event ) {
    	if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
    		var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
    		$( "#" + panelId ).remove();
    		tabs.tabs( "refresh" );
    	}
    });



    
});


function getProjectList(callback){
    $.ajax({
        url : "/newproject",
        method : "GET",
        dataType : "json",
    })
    .done(callback);
}