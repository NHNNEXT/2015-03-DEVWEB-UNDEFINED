package model.scene;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.dao.QueryManager;
import utils.dao.SqlManager;

public class SceneDao {
	private static final Logger log = LoggerFactory.getLogger(SceneDao.class);

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public SceneDao() {

		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public boolean hasScene(long sceneId) throws SQLException {
		String hasSecneQuery = mQueryManager.find("scene", "SceneId=" + sceneId);

		ResultSet rs = sqlManager.excuteSelect(hasSecneQuery);

		if (rs.next()) {
			rs.close();
			return true;
		}
		rs.close();
		return false;
	}

	// 현재 projectId가 1로 고정 추후 project객체 생성시 수정 필요
	public void newScene(Scene scene) throws SQLException {
		log.info("newScene Start");
		String insertSceneQuery = mQueryManager.Insert("scene", "sceneId, projectId, name",
				scene.getSceneId() + "," + 1 + "," + mQueryManager.toQueryStirng(scene.getName()));
		sqlManager.excuteUpdate(insertSceneQuery);

	}

	public Scene getScene(int sceneId) throws SQLException {
		String selectSceneQuery = mQueryManager.find("scene", "sceneId=" + sceneId);
		ResultSet rs = sqlManager.excuteSelect(selectSceneQuery);
		if (rs.next()) {
			int sceneId1 = Integer.parseInt(rs.getString("sceneId"));
			String sceneName = rs.getString("name");
			return new Scene(sceneId1, sceneName);
		}
		throw new RuntimeException();

	}

	public List<Scene> getSceneList(int projectId) throws SQLException {
		List<Scene> scenes = new ArrayList<Scene>();

		String getSceneListQuery = mQueryManager.findAll("scene");
		ResultSet rs = sqlManager.excuteSelect(getSceneListQuery);
		while (rs.next()) {
			int sceneId = Integer.parseInt(rs.getString("sceneId"));
			String sceneName = rs.getString("name");
			scenes.add(new Scene(sceneId, sceneName));
		}
		return scenes;
	}

}
