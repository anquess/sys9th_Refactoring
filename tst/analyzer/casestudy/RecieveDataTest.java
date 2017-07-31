package analyzer.casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RecieveDataTest {

	@Test
	public void RecieveDataにff_ffで1111111111111111Test() {
		RecieveData sut = new RecieveData("ff ff");
		String actual	= sut.hexToBinary();
		String expected	= "1111111111111111";

		assertThat(actual,is(expected));
	}
	@Test
	public void RecieveDataに00_01で0000000000000001Test() {
		RecieveData sut = new RecieveData("00 01");
		String actual	= sut.hexToBinary();
		String expected	= "0000000000000001";

		assertThat(actual,is(expected));
		}

}
