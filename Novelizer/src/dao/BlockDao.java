package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Block;

public class BlockDao extends AbstractDao<Block> {
	private static final Logger log = LoggerFactory.getLogger(BlockDao.class);

	private static final String insertQuery = "INSERT INTO block(block_id, next_block_id, scene_id) VALUES(?, ?, ?);";
	private final String selectQuery = "SELECT * FROM block WHERE block_id = ?;";
	private final String selectAllQuery = "SELECT * FROM block;";
	private final String selectByParentIdQuery = "SELECT * FROM block WHERE scene_id = ?;";
	private final String updateQuery = "";
	private final String deleteQuery = "DELETE FROM block WHERE block_id = ?";
	private final String deleteByParentIdQuery = "DELETE FROM block WHERE scene_id = ?;";

	public BlockDao(){
		super.insertQuery = insertQuery;
		super.selectAllQuery = selectAllQuery;
		super.selectQuery = selectQuery;
		super.selectByParentIdQuery = selectByParentIdQuery;
		super.deleteQuery = deleteQuery;
		super.deleteByParentIdQuery = deleteByParentIdQuery;
	}
	
	public int insertBlock(Block block) throws SQLException {
		try {
			log.info(block.toString());
			ArrayList<Object> insertList = new ArrayList<Object>();
			insertList.add(block.getBlockId());
			insertList.add(block.getNextBlockId());
			insertList.add(block.getSceneId());

			return insert(insertList);
		} catch (Exception e) {
			log.error("Insert Block Error");
			e.printStackTrace();
		}
		return -1;
	}

	public Block selectBlock(int blockId) throws SQLException {
		List<Object> blockContents = new ArrayList<>();
		Block block = null;

		try {
			blockContents = select(blockId);
			block = convertToBlock(blockContents);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return block;
	}
	
	public List<Block> selectBySceneId(int sceneId){
		List<Block> resultBlocks = new ArrayList<>();
		List<List<Object>> blocks = new ArrayList<>();
		
		try{
			blocks = selectByParentId(sceneId);
			for(List<Object> block : blocks){
				resultBlocks.add(convertToBlock(block));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resultBlocks;
	}
	
	public List<Block> selectAllBlocks() {
		List<Block> resultBlocks = new ArrayList<>();
		List<List<Object>> blocks = new ArrayList<>();
		
		try{
			blocks = selectAll();
			for(List<Object> block : blocks){
				resultBlocks.add(convertToBlock(block));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resultBlocks;
	}

	private Block convertToBlock(List<Object> blockContents) {

		int resultBlockId = (Integer) blockContents.get(0);
		int resultNextBlockId = (Integer) blockContents.get(1);
		int resultSceneId = (Integer) blockContents.get(2);

		return new Block(resultBlockId, resultNextBlockId, resultSceneId);
	}

	@Override
	public void update(Block vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int key) throws Exception {
		// TODO Auto-generated method stub

	}
}
