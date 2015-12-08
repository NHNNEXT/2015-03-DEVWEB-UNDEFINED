package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.block.Block;
import utils.dao.QueryManager;
import utils.dao.SqlManager;

public class BlockDao extends AbstractDao<Block, Integer> {
	private static final Logger log = LoggerFactory.getLogger(BlockDao.class);

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public BlockDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newBlock(Block block) throws SQLException {
		try {
			insert(block);
		} catch (Exception e) {
			log.error("Insert Block Error");
			e.printStackTrace();
		}
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

	@Override
	public void select(Integer key) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Block vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer key) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void query() {
		// TODO Auto-generated method stub

	}
}
