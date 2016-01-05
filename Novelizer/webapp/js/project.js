var id = 0;

getProjectData(function(response){
    var dataList = [];
    for(var i = 0; i < response.length; ++i){
        var data = response[i];
        var list = $("<li class='project' data-id='"+data['projectId']+"'>"+data['projectName']+"</li>");
        dataList.push(list);
    }
    $("#project-list").append(dataList);
})

$(".btn-add-project").click(function(){  //프로젝트 탭 추가
    var projectName = $(".project-name").val();
    sendProjectData(projectName,function(response){
        var dataList = [];
        for(var i = 0; i < response.length; ++i){
            var data = response[i];
            var list = $("<li class='project' data-id='"+data['projectId']+"'>"+data['projectName']+"</li>");
            dataList.push(list);
        }
        $("#project-list").append(dataList);
    });
})

$("#project-list").on("click",".project",function(){
    var projectId = $(this).data("id");
    getSceneData(projectId,function(response){
        $("#scene-list").empty();
        var dataList = [];
        for(var i = 0; i < response.length; ++i){
            var data = response[i];
            var list = $("<li class='scene'><a href='index.html?sceneId="+data['sceneId']+"'>"+data['sceneName']+"</a></li>");
            dataList.push(list);
        }
        $("#scene-list").append(dataList);
    });

})

function getProjectData(callback){
    $.ajax({
        url : "http://localhost:8080/project",
        method : "GET",
        dataType : "json",       
    })
    .done(callback);
};

function sendProjectData(projectName, callback){
    $.ajax({
        url : "http://localhost:8080/project",
        method : "POST",
        dataType : "json",
        data : {projectData:JSON.stringify({projectId:0, projectName : projectName})}
    })
    .done(callback);
};

function getSceneData(projectId, callback){
    $.ajax({
        url : "http://localhost:8080/scene",
        method : "GET",
        dataType : "json",
        data : {projectId:projectId}
    })
    .done(callback);
}
	