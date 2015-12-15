package controller.character;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CharacterDao;
import model.CharacterVo;

/*
 * last update : 15.12.14
 * author : 서동유
 */

@WebServlet("/character")
public class CharacterController extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(CharacterController.class);
	private CharacterDao characterDao;

	@Override
	public void init() throws ServletException {
		characterDao = new CharacterDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String result;
		
		if(req.getParameter("characterId") != null){
			int characterId = Integer.parseInt(req.getParameter("characterId"));
			CharacterVo vo = characterDao.getCharacter(characterId);
			// TODO 해당 캐릭터 정보 리턴
		}else{
			// TODO 전체 캐릭터 리스트 리턴
		}

		//resp.getWriter().write();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String result;
		String name = req.getParameter("name");
		int projectId = Integer.parseInt(req.getParameter("projectId"));
		
		CharacterVo vo = new CharacterVo();
		vo.setProjectId(projectId);
		vo.setName(name);
		
		int characterId = characterDao.addCharacter(vo);
		result = "{\"characterId\" :" + characterId + "}";
		
		resp.getWriter().write(result);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
