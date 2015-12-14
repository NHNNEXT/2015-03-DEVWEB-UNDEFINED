var PresetDataSync = {
	url : "http://localhost:8080",
	init : function(){

	},
	addCharacter : function(name, callback){
		var data = {
			name : name
		}

		$.ajax({
			url : this.url+"/character",
			method : "POST",
			dataType : "json",
			data : data
		})
		.done(callback[done])
		.fail(callback[fail])
	},
	removeCharacter : function(characterId, callback){
		var data = {
			characterId : characterId
		}

		$.ajax({
			url : this.url+"/character",
			method : "DELETE",
			dataType : "json",
			data : data
		})
		.done(callback[done])
		.fail(callback[fail])
	},
	addCharacterPreset : function(characterId, image, callback){
		var data {
			characterId : characterId,
			image : image
		}

		$.ajax({
			url : this.url+"/characterPreset",
			method : "POST",
			dataType : "json",
			data : data
		})
		.done(callback[done])
		.fail(callback[fail])
	},
	removeCharacterPreset : function(presetId, callback){
		var data {
			presetId : presetId
		}

		$.ajax({
			url : this.url+"/characterPreset",
			method : "DELETE",
			dataType : "json",
			data : data
		})
		.done(callback[done])
		.fail(callback[fail])
	},
}