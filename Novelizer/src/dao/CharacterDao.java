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
		super.insertQuery = "INSERT INTO `character` (`projectId`, `name`) VALUES (?, ?)";
		List<Object> dataList = new ArrayList<Object>();
		dataList.add(vo.getProjectId());
		dataList.add(vo.getName());

		return super.insert(dataList);
	}

	public CharacterVo getCharacter(int characterId) {
		super.selectQuery = "SELECT * FROM `character` WHERE `id` = ?";
		List<Object> data = super.select(characterId);
		CharacterVo vo = new CharacterVo();
		vo.setCharacterId((int) data.get(0));
		vo.setProjectId((int) data.get(1));
		vo.setName((String) data.get(2));
		return vo;
	}

	public List<CharacterVo> getCharacters() {
		super.selectQuery = "SELECT * FROM `character`";
		List<List<Object>> dataList = super.selectAll();
		List<CharacterVo> voList = new ArrayList<CharacterVo>();

		for (List<Object> data : dataList) {
			CharacterVo vo = new CharacterVo();
			vo.setCharacterId((int) data.get(0));
			vo.setProjectId((int) data.get(1));
			vo.setName((String) data.get(2));
			voList.add(vo);
		}
		return voList;
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
