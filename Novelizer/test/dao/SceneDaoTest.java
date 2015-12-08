package dao;

import org.junit.Before;
import org.junit.Test;

import model.scene.Scene;

public class SceneDaoTest {
	
	SceneDao sceneDao;
	
	@Before
	public void setup() throws Exception{
		sceneDao = new SceneDao();
		sceneDao.init();
	}

	@Test
	public void insertTest() throws Exception {
		sceneDao.insert(new Scene(100,"testScene",null,1));
	}

}
