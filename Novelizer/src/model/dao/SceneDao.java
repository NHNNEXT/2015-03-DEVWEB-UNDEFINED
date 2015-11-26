package model.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.scene.Scene;

public class SceneDao {
	private static final Logger log = LoggerFactory.getLogger("SceneDao.class");

	private QueryManager mQueryManager;
	private SqlManager sqlManager;
	private DataSource mDataSource;

	public SceneDao(DataSource ds) {
		mDataSource = ds;
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public boolean hasScene(long sceneId) {
		String hasSecneQuery = mQueryManager.find("scene", "ID=" + sceneId);
		if (sqlManager.excuteSelect(hasSecneQuery,mDataSource) != null) {
			return true;
		}
		return false;
	}

	// 현재 projectId가 1로 고정 추후 project객체 생성시 수정 필요
	public void newScene(Scene scene) {
		String insertSceneQuery = mQueryManager.Insert("scene", "sceneId, projectId, startBlockId, name",
				scene.getSceneId() + "," + 1 + "," + scene.getStartBlockId() + ","
						+ mQueryManager.toQueryStirng(scene.getName()));
		sqlManager.excuteUpdate(insertSceneQuery, mDataSource);

	}
}
