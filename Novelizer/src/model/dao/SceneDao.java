package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import utils.Connector;
import utils.JdbcTemplate;

public class SceneDao {
	private Connector connector;
	private QueryManager mQueryManager;
	private Connection conn;
	private PreparedStatement prst;
	private ResultSet resultSet;
	private JdbcTemplate template;
	
	public SceneDao(){
		mQueryManager = new QueryManager();
		conn = connector.getConnection();
		template = new JdbcTemplate();
		prst = null;
		resultSet = null;
	}

	public boolean hasScene(long sceneId) {
		String hasSecneQuery = mQueryManager.find("scene", "ID="+sceneId);
		try {
			prst = conn.prepareStatement(hasSecneQuery);
			resultSet = prst.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException();
		}finally{
			if(prst != null)  try{prst.close();}catch(SQLException sqle){}
			if(resultSet != null)  try{resultSet.close(); return true;}catch(SQLException sqle){}
		}
		return false;
	}

	public void updateScene(JSONObject scene) {
		
	}

	public void newScene(JSONObject scene) {
		long sceneId = (long)scene.get("sceneId");
		long projectId = (long)scene.get("projectId");
		
	}
	
	public String getSceneList(){
		String getSceneListQuery = mQueryManager.findAll("scene");
		
		return null;
	}

}
