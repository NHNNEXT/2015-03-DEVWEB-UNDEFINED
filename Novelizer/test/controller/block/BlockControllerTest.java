package controller.block;

import org.junit.Before;
import org.junit.Test;

public class BlockControllerTest {
	
	BlockController bc;
	@Before
	public void setup(){
		bc = new BlockController();
	}
	
	@Test
	public void getBlockListTest() {
		bc.getBlockList(1);
	}

}
