package model.block;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.dao.QueryManager;
import utils.dao.SqlManager;

public class BlockDao {
	private static final Logger log = LoggerFactory.getLogger(BlockDao.class);

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public BlockDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newBlock(Block block, int sceneId) throws SQLException {
		// TODO sql 쿼리문을 block.getBlockId() + "," + block.getNextBlockId() + "," + sceneId와 같이 작성할 때의 문제점은 무엇인가?
		// TODO jdbc에서 Statement와 PreparedStatement의 차이점은? 이 둘 중 어느 것을 사용하는 것이 더 좋은가?
		String insertBlockQuery = mQueryManager.Insert("block", "blockId, nextBlockId, sceneId",
				block.getBlockId() + "," + block.getNextBlockId() + "," + sceneId);
		log.info("blockId :" + block.getBlockId() + " start");
		sqlManager.excuteUpdate(insertBlockQuery);

	}

	public List<Block> getBlocks(int sceneId) throws SQLException {
		List<Block> blocks = new ArrayList<Block>();

		String selectBlocksQuery = mQueryManager.find("block", "sceneId=" + sceneId);
		ResultSet rs = sqlManager.excuteSelect(selectBlocksQuery);
		while (rs.next()) {
			blocks.add(new Block(rs.getInt(1), rs.getInt(2)));
		}

		return blocks;
	}
}
