package dao;

import static org.junit.Assert.*;

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
		int id = sceneDao.insertScene(new Scene("testScene",1));
		assertNotEquals(-1,id);
		System.out.println(id);
	}
	
	public void selectTest() throws Exception {
		assertNotNull(sceneDao.selectByProjectId(1));
		log.info(sceneDao.selectByProjectId(1).get(0).toString());
	}

}
