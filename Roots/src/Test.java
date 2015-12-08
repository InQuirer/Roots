import static org.junit.Assert.*;

import java.io.IOException;


public class Test {

	@org.junit.Test
	public void test1() throws NumberFormatException, IOException {
		Squares r = new Squares("1.in");
		assertTrue(r.run());
	}
	@org.junit.Test
	public void test2() throws NumberFormatException, IOException {
		Squares r = new Squares("2.in");
		assertTrue(r.run());
	}
	@org.junit.Test
	public void test3() throws NumberFormatException, IOException {
		Squares r = new Squares("3.in");
		assertTrue(r.run());
	}
	@org.junit.Test
	public void test4() throws NumberFormatException, IOException {
		Squares r = new Squares("4.in");
		assertTrue(r.run());
	}

}
