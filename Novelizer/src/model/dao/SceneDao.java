package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.scene.Scene;

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
}
