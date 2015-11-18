package controller.block;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트에서 sceneId와 blockId를 받아서 다음에 가야할 block의 정보를 넘겨줌 

@WebServlet("/postBlockInfoTest")
public class BlockControllServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int sceneId = Integer.parseInt(req.getParameter("sceneId"));
		int blockId = Integer.parseInt(req.getParameter("blockId"));

		BlockController bc = new BlockController();

		String nextBlockInfo = bc.getNextBlock(sceneId, blockId);

		ObjectOutputStream dataOutPut = new ObjectOutputStream(resp.getOutputStream());
		dataOutPut.writeUTF(nextBlockInfo);
		dataOutPut.flush();
		dataOutPut.close();

	}
}
