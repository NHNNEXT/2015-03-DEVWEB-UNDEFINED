package dao;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.CharacterVo;

public class CharacterDaoTest {
	private static final Logger log = LoggerFactory.getLogger(CharacterDaoTest.class);
	CharacterDao characterDao;

	@Before
	public void setup() {
		characterDao = new CharacterDao();
	}

	@Test
	public void addCharacterTest() {
		CharacterVo vo = new CharacterVo();
		vo.setProjectId(1);
		vo.setName("testName");

		int characterId = characterDao.addCharacter(vo);
		System.out.println(characterId);
		assertNotEquals(-1, characterId);
	}

}
