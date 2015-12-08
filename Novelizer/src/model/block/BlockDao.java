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
		/*
		 * JDBC공부하면 statement가 3가지 있어요. 
		 * state
		 * ptrparedS
		 * collableStatement ->stare precedure
		 * 
		 * 여러분이 JDBC쓸 때 사용할때는 preparedStatment쓰라고 했죠
		 * 
		 * statement같은 경우에 나중에 공부하세요 statement는 쿼리를 생성해서 던져요 insert나 update를 실행하면서 쿼리를 날리는건데 
		 * prepareS는 connection.pre()만들 떄 sql쿼리를 던져요 여기에다가 쿼리에서 필요로 하는 값을 셋팅한다음
		 * 처리하는데 이 차이가 무슨차이냐
		 * 여기있는 이 쿼리를 재사용하겠다는 거에요 쿼리를 데이타베이스에 던지면 쿼리가 파싱을 해서 
		 * 쿼리구문이 바뀌면 안되죠 prepaS는 insert INTO table, values 
		 * 값이 맵핑이 안된 상태로 실행해야지 쿼리자체를 재사용할 수 있습니다. 그런데 커ㅜ리를 만들 때 우리 코딩은
		 * 이미 value가 다 들어가 있는 상태입니다 Id가 바뀔때마다 다른 쿼리가 만들어지는 거에요
		 * 백날 preparandS를 사용해도 쿼리를 재사용할 수 있는 비율이 엄청 떨어지는거죠 
		 * 사용한 보람이 없는 거지
		 * 
		 * 그래서 여러분들이 preS 할때는 ??? 에 의해서 이런 쿼리를 미리 던지는 경우가 파싱을 해서 정상적인 쿼리인지
		 * 만들고, 준비를 하라는 거죠 거기에다가 값들만 바꿔주면 쿼리를 실행할 수 있는 메커니즘이 동작하는거에요
		 * 쿼리를 지금 한 식으로 사용하면 성능상 별로고 preS를 이용하는 효과가 없다 그래서 동적으로 사용하는거에 대해서는
		 * ??를 사용해라 pre.S 
		 * 
		 * SQL 인젝셔닝라는 공격방법이 있어요 이 사이에 delete table이런걸 쿼리문으로 인식할 수 있어요
		 * 여러분들이 데이타베이스를 입력값을 조작하면 테이블 데이타를 날릴 수 있는 공격이에요
		 * 특히 우리가 php나 이런 프로그램을 보면 쿼리문을 이런식으로 짜는 경우 많이 보이거든요 
		 * 자바진영도 있었고 최근에는 prepareS 사용하는데 쿼리와 value를 분리하기 때문에
		 * value에 들어간 값이 쿼리문으로 인식되지 않는다.
		 * 
		 * 하나의 문자열로 합쳐져서 디비에 들어가면 하나의 쿼리로 파싱이 되서 Inner Query처럼 동작할 수가 있다.
		 * 
		 * */
		
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
