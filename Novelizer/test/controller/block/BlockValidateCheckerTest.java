package controller.block;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BlockValidateCheckerTest {

	BlockValidateChecker validateChecker;
	@Before
	public void setup(){
		validateChecker = new BlockValidateChecker();
	}
	
	@Test
	public void isValidatetest() {
		String jsonData ="[{\"blockId\":1,\"nextBlockId\":2,\"actionList\":[{\"type\":\"CHARACTER\",\"characterId\":1,\"presetId\":1,\"option\":\"appear\",\"position\":[0,0],\"animation\":null},{\"type\":\"BACKGROUND\",\"presetId\":3,\"option\":\"appear\",\"position\":[0,0],\"animation\":null},{\"type\":\"CHARACTER\",\"characterId\":2,\"presetId\":2,\"option\":\"appear\",\"position\":[0,0],\"animation\":null}]},{\"blockId\":2,\"nextBlockId\":3,\"actionList\":[{\"type\":\"TEXT\",\"TEXT\":\"왜 이렇게 철수가 늦지?\",\"characterId\":1}]},{\"blockId\":3,\"nextBlockId\":4,\"actionList\":[{\"type\":\"TEXT\",\"TEXT\":\"헉헉, 미안해 내가 너무 늦었지?\",\"characterId\":2}]},{\"blockId\":4,\"nextBlockId\":5,\"actionList\":[{\"type\":\"TEXT\",\"TEXT\":\"그걸 말이라고 해? 우리 헤어져! \",\"characterId\":1}]},{\"blockId\":5,\"nextBlockId\":6,\"actionList\":[{\"type\":\"CHARACTER\",\"characterId\":1,\"presetId\":1,\"option\":\"disappear\",\"position\":[0,0],\"animation\":null}]}]\n";
		assertEquals(true,validateChecker.isValidate(jsonData));
	}	
}
