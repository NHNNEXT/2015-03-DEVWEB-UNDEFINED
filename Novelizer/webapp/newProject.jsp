<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Novelizer</title>
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/newProject.css">
	<link rel='stylesheet' href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css'>
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script type="text/javascript"></script>

</head>
<body>
	<div id="editor">
		<div class="background">		
		<h1 id="text3d">Project List</h1>

		<div id="projectArea">
			<div id="dialog">
				<form>
					<fieldset class="ui-helper-reset">
						<label for="tab_title">project name</label> <input type="text"
							name="tab_title" id="tab_title"
							class="ui-widget-content ui-corner-all" autocomplete="off"><br />
						<label for="tab_content">Content</label>
						<textarea name="tab_content" id="tab_content"
							class="ui-widget-content ui-corner-all"></textarea>
					</fieldset>
				</form>
			</div>

			<button id="add_tab">new project</button>

			<div id="tabs">
				<ul id="projectList">
				</ul>
				<div class="plusBotton">
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="js/newProject.js"></script>
</body>
</html>