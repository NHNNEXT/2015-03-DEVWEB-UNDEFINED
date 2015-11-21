package controller.action;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import controller.block.BlockController;
import model.dao.ActionDao;
import model.json.ActionJSONDataHandler;
import vo.action.Action;
import vo.action.BackgroundAction;
import vo.action.CharacterAction;
import vo.action.TextAction;

@WebServlet("/createAction")
public class CreateActionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ActionValidateChecker checker = new ActionValidateChecker();
		ActionDao dao = new ActionDao();

		DBObject actionJSONData = (DBObject) JSON.parse(req.getParameter("action"));

		if (!checker.isValid(actionJSONData)) {
			PrintWriter writer = resp.getWriter();
			writer.write("Error : param is not valid");
		}

		dao.saveActionData(actionJSONData);
	}

}