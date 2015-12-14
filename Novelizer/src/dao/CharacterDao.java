package dao;

import java.util.ArrayList;
import java.util.List;

import model.CharacterVo;

/*
 * last update : 15.12.14
 * author : 서동유
 */

public class CharacterDao extends AbstractDao<CharacterVo> {

	public int addCharacter(CharacterVo vo) {
		insertQuery = "INSERT INTO `character` (`projectId`, `name`) VALUES (?, ?)";
		List<Object> dataList = new ArrayList<Object>();
		dataList.add(vo.getProjectId());
		dataList.add(vo.getName());
		
		return super.insert(dataList);
	}

	public CharacterVo getCharacter(int characterId) {
		selectQuery = "SELECT * FROM `character` WHERE `id` = "+characterId;
		List<Object> dataList = super.select(characterId);
		CharacterVo vo = new CharacterVo();
		vo.setCharacterId((int)dataList.get(0));
		vo.setProjectId((int)dataList.get(1));
		vo.setName((String)dataList.get(2));
		return vo;
	}

	@Override
	public void update(CharacterVo vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int key) throws Exception {
		// TODO Auto-generated method stub

	}

}
