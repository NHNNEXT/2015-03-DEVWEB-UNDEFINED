<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novelizer Viewer</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/viewer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
<!-- <div class="header"><h3>Novelizer Viewer</h3></div> -->
<div class="frame">
	<main>
	<div id="layer-bg" class="layer"></div>
	<div id="layer-obj" class="layer"></div>
	<div id="layer-text" class="layer">
		<div class="character-name"></div>
		<p></p>
	</div>
	</main>
	</div>
<!-- 	<div class="playbutton"></div> -->
	<script type="text/javascript" src="js/viewer.js"></script>
	<script type="text/javascript">
		Viewer.sceneId = ${param.sceneId};
        Viewer.init();
    </script>
</body>
</html>