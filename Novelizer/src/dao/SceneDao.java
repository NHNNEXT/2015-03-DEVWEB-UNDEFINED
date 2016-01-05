package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Scene;

public class SceneDao extends AbstractDao<Scene> {
	private static final Logger log = LoggerFactory.getLogger(SceneDao.class);

	private final String insertQuery = "INSERT INTO scene(scene_id, scene_name, project_id) values(?,?,?);";
	private final String selectQuery = "SELECT * FROM scene WHERE scene_id = ?;";
	private final String selectAllQuery = "SELECT * FROM scene;";
	private final String selectByParentIdQuery = "SELECT * FROM scene WHERE project_id = ?;";
	private final String updateQuery = "";
	private final String deleteQuery = "DELETE FROM scene WHERE scene_id = ?;";

	public SceneDao() {
		super();
		super.insertQuery = insertQuery;
		super.selectAllQuery = selectAllQuery;
		super.selectQuery = selectQuery;
		super.selectByParentIdQuery = selectByParentIdQuery;
		super.deleteQuery = deleteQuery;
	}

	// 현재 projectId가 1로 고정 추후 project객체 생성시 수정 필요
	public int insertScene(Scene scene) {
		try {
			ArrayList<Object> insertList = new ArrayList<Object>();
			insertList.add(scene.getSceneId());
			insertList.add(scene.getSceneName());
			insertList.add(scene.getProjectId());
			return insert(insertList);
		} catch (Exception e) {
			log.error("Scene Insert Error");
			e.printStackTrace();
		}
		return -1;
	}

	public Scene selectScene(int sceneId) {

		List<Object> sceneContents = new ArrayList<>();
		Scene scene = null;

		try {
			sceneContents = select(sceneId);
			scene = convertToScene(sceneContents);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scene;
	}

	public List<Scene> selectByProjectId(int projectId) throws SQLException {

		List<List<Object>> scenes = new ArrayList<>();
		List<Scene> resultScenes = new ArrayList<>();

		try {
			scenes = selectByParentId(projectId);
			for (List<Object> sceneContents : scenes) {
				resultScenes.add(convertToScene(sceneContents));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultScenes;
	}

	public List<Scene> selectAllScenes() throws SQLException {

		List<List<Object>> scenes = new ArrayList<>();
		List<Scene> resultScenes = new ArrayList<>();

		try {
			scenes = selectAll();
			for (List<Object> sceneContents : scenes) {
				resultScenes.add(convertToScene(sceneContents));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultScenes;
	}

	private Scene convertToScene(List<Object> sceneContents) {

		int resultSceneId = (Integer) sceneContents.get(0);
		String resultSceneName = (String) sceneContents.get(2);
		int resultProjectId = (Integer) sceneContents.get(1);

		return new Scene(resultSceneId, resultSceneName, resultProjectId);
	}

	@Override
	public void update(Scene vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int key) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
