package analyzer.casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class HexToBinaryTest {

	@Test
	public void HexToBinaryff_ffで1111111111111111Test() {
		String actual	= HexToBinary.hexToBinary("ff ff");
		String expected	= "1111111111111111";

		assertThat(actual,is(expected));
	}
	@Test
	public void HexToBinary00_01で0000000000000001Test() {
		String actual	= HexToBinary.hexToBinary("00 01");
		String expected	= "0000000000000001";

		assertThat(actual,is(expected));
	}



}
