package controller.action;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;

public class ActionValidateChecker {

	public boolean isValid(DBObject actionJSONData) {

		String type = (String) actionJSONData.get("type");
		
		switch(type){
		case "BackgroundAction":
			return backgroundActionIsValid(actionJSONData);
		case "CharacterAction" : 
			return characterActionIsValid(actionJSONData);
		case "TextAction" :
			return textActionIsValid(actionJSONData);
	}
		return false;
	}

	private boolean textActionIsValid(DBObject actionJSONData) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean characterActionIsValid(DBObject actionJSONData) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean backgroundActionIsValid(DBObject actionJSONData) {
		// TODO Auto-generated method stub
		return false;
	}

}
