package controller.block;

import org.junit.Before;
import org.junit.Test;

public class BlockControllerTest {
	
	BlockService bc;
	@Before
	public void setup(){
		bc = new BlockService();
	}
	
	@Test
	public void getBlockListTest() {
		bc.getBlockList(1);
	}

}
