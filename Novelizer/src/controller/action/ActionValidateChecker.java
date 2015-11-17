package controller.action;

import javax.servlet.http.HttpServletRequest;

public class ActionValidateChecker {

	public boolean isValid(HttpServletRequest req) {
		
		String type = req.getParameter("type");
		
		switch(type){
			case "BackgroundAction":
				return backgroundActionIsValid(req);
			case "CharacterAction" : 
				return characterActionIsValid(req);
			case "TextAction" :
				return textActionIsValid(req);
		}
		return false;
	}

	private boolean actionHas(String parameter) {
		if (parameter != null) return true;
		return false;
	}
	
	private boolean textActionIsValid(HttpServletRequest req) {
		if(!actionHas(req.getParameter("id"))) return false;
		if(!actionHas(req.getParameter("type"))) return false;
		if(!actionHas(req.getParameter("text"))) return false;
		if(!actionHas(req.getParameter("characterId"))) return false;
		return true;
	}

	private boolean characterActionIsValid(HttpServletRequest req) {
		if(!actionHas(req.getParameter("id"))) return false;
		if(!actionHas(req.getParameter("type"))) return false;
		if(!actionHas(req.getParameter("characterId"))) return false;
		if(!actionHas(req.getParameter("presetId"))) return false;
		if(!actionHas(req.getParameter("option"))) return false;
		if(!actionHas(req.getParameter("animation"))) return false;
		if(!actionHas(req.getParameter("position"))) return false;
		return true;
	}

	private boolean backgroundActionIsValid(HttpServletRequest req) {
		if(!actionHas(req.getParameter("id"))) return false;
		if(!actionHas(req.getParameter("type"))) return false;
		if(!actionHas(req.getParameter("presetId"))) return false;
		if(!actionHas(req.getParameter("option"))) return false;
		if(!actionHas(req.getParameter("animation"))) return false;
		if(!actionHas(req.getParameter("position"))) return false;
		return true;
	}

}
