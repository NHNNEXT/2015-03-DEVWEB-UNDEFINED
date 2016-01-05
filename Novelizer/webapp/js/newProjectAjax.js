
function getProjectList(callback){
    $.ajax({
        url : "http://localhost:8080/project",
        method : "GET",
        dataType : "json",
    })
    .done(callback);
}

function sendUserId(userId, callback){
	$.ajax({
		url : "http://localhost:8080/userId",
		method : "GET",
		dataType : "json"
		data: "userId : 1"
	})
	
function getSceneList()
	
	
}

	
}