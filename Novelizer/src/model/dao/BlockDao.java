package model.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.block.Block;

public class BlockDao {
	private static final Logger log = LoggerFactory.getLogger(BlockDao.class);

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public BlockDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newBlock(Block block, int sceneId) throws SQLException {
		String insertBlockQuery = mQueryManager.Insert("block", "blockId, nextBlockId, sceneId",
				block.getBlockId() + "," + block.getNextBlockId() + "," + sceneId);
		log.info("blockId :" + block.getBlockId() + " start");
		sqlManager.excuteUpdate(insertBlockQuery);

	}
}
