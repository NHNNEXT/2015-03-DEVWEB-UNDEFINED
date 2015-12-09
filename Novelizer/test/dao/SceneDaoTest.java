package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Scene;

public class SceneDaoTest {
	
	private static final Logger log = LoggerFactory.getLogger(SceneDaoTest.class);
	SceneDao sceneDao;
	
	@Before
	public void setup() throws Exception{
		sceneDao = new SceneDao();
	}

	@Test
	public void insertTest() throws Exception {
		sceneDao.insertScene(new Scene(100,"testScene",null,1));
	}
	
	@Test
	public void selectTest() throws Exception {
		assertNotNull(sceneDao.selectByProjectId(1));
		log.info(sceneDao.selectByProjectId(1).get(0).toString());
	}

}
