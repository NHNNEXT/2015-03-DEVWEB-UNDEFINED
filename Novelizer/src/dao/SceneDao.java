package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.utils.DaoUtil;
import model.scene.Scene;

public class SceneDao extends AbstractDao<Scene, Integer>{
	private static final Logger log = LoggerFactory.getLogger(SceneDao.class);

	public SceneDao() {
	}

	public boolean hasScene(long sceneId) {
		String hasSecneQuery = "SELECT * FROM scene WHERE SceneId=?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = utils.dao.DataSource.getInstance().getConnection();
			psmt = conn.prepareStatement(hasSecneQuery);
			psmt.setLong(1, sceneId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			log.error("excuteSelect " + hasSecneQuery + "Error\n" + e);
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("" + e);
		} finally {
			DaoUtil.close(psmt, conn);
		}

		return false;
	}

	// 현재 projectId가 1로 고정 추후 project객체 생성시 수정 필요
	public void newScene(Scene scene) {
		try {
			insert(scene);
		} catch (Exception e) {
			log.error("Scene Insert Error");
			e.printStackTrace();
		}
	}

	public Scene getScene(int sceneId) throws SQLException {
		String selectSceneQuery = "SELECT * FROM scene WHERE sceneId=?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = utils.dao.DataSource.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(selectSceneQuery);
			preparedStatement.setInt(1, sceneId);
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("excuteSelect " + selectSceneQuery + "Error\n" + e);
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("" + e);
		}

		if (rs.next()) {
			int sceneId1 = Integer.parseInt(rs.getString("sceneId"));
			String sceneName = rs.getString("name");
			return new Scene(sceneId1, sceneName);
		}
		throw new RuntimeException();

	}

	public List<Scene> getSceneList(int projectId) throws SQLException {
		List<Scene> scenes = new ArrayList<Scene>();

		String getSceneListQuery = "SELECT * FROM scene";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = utils.dao.DataSource.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(getSceneListQuery);
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("excuteSelect " + getSceneListQuery + "Error\n" + e);
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("" + e);
		}

		while (rs.next()) {
			int sceneId = Integer.parseInt(rs.getString("sceneId"));
			String sceneName = rs.getString("name");
			scenes.add(new Scene(sceneId, sceneName));
		}
		return scenes;
	}

	@Override
	public void select(Integer key) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Scene vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer key) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void query() {
		// TODO Auto-generated method stub
		
	}

}
