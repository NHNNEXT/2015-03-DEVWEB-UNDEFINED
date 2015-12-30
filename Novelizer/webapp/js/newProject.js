$(function() {
 var tabTitle = $( "#tab_title" ),
 tabContent = $( "#tab_content" ),
 tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>x</span></li>",
 tabCounter = 1;

 var tabs = $( "#tabs" ).tabs();

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
    	var label = tabTitle.val() || "Tab " + tabCounter,
    	id = "tabs-" + tabCounter,
        sceneNum = 1,
    	li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
    	tabContentHtml = "<div class='projectContents'><div class='projectContentsText'>"+tabContent.val()+"</div></div>" + 
                         "<div id='sortable"+tabCounter+"' class='sceneArea'><div class='newS newScene"+sceneNum+"'>"+"scene"+sceneNum+"<input type='textarea' class='sceneText'></textarea></div></div>"; 
    	tabs.find( ".ui-tabs-nav" ).append( li );
    	tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p>" );
        $("#"+id).append("<div class='plusButton'>"+"+"+"</div>");
    	tabs.tabs( "refresh" );
    	
    	$('.plusButton').on('click', function(){           
            sceneNum++;
    		$(".newScene"+sceneNum-1).after("<div class='newS newScene"+sceneNum+"'>"+"scene"+sceneNum+"<input type='textarea' class='sceneText'></textarea></div>");
        });


    	$( "#sortable"+tabCounter).sortable();

        tabCounter++;
    	// $( "#sortable" ).disableSelection();
    }

    // addTab button: just opens the dialog
    $( "#add_tab" )
    .button()
    .click(function() {
    	dialog.dialog( "open" );
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