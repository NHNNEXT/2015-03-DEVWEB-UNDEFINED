package controller.action;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.block.BlockController;
import model.dao.ActionDAO;
import vo.action.Action;
import vo.action.BackgroundAction;
import vo.action.CharacterAction;
import vo.action.TextAction;

@WebServlet("/createAction")
public class CreateActionServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ActionValidateChecker checker = new ActionValidateChecker();
		ActionDAO dao = new ActionDAO();
		
		if(!checker.isValid(req)){
			PrintWriter writer = resp.getWriter();
			writer.write("Error : param is not valid");
			};

		dao.saveActionData(getActionObj(req));
		
	}

	private Action getActionObj(HttpServletRequest req) {
		String type = req.getParameter("type");
	
		switch(type){
		case "BackgroundAction":
			return getBackgroundActionObj(req);
		case "CharacterAction" : 
			return getCharacterActionObj(req);
		case "TextAction" :
			return getTextActionObj(req);
	}
		/* ActionValidateChecker에서 이미 체크하여 null로 오는 경우 없음 */
		return null;
	}

	private Action getTextActionObj(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		String text = req.getParameter("text");
		int characterId = Integer.parseInt(req.getParameter("characterId"));
		return new TextAction(id, type, text, characterId);
	}

	private Action getCharacterActionObj(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		int characterId = Integer.parseInt(req.getParameter("characterId"));
		int presetId = Integer.parseInt(req.getParameter("presetId"));
		String option = req.getParameter("option");
		int animation = Integer.parseInt(req.getParameter("animation"));
		int[] position = parsePosition(req.getParameter("position"));
		return new CharacterAction(id, type, characterId, presetId, option, animation, position);
	}

	private Action getBackgroundActionObj(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		int presetId = Integer.parseInt(req.getParameter("presetId"));
		String option = req.getParameter("option");
		int animation = Integer.parseInt(req.getParameter("animation"));
		int[] position = parsePosition(req.getParameter("position"));
		return new BackgroundAction(id, type, presetId, option, animation, position);
	}
	
	private int[] parsePosition(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}