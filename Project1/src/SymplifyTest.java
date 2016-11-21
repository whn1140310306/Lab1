import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class SymplifyTest {

	/**
	 * Test method for {@link Project1#calculate(java.lang.String, java.lang.String)}.
	 */
	String str1 = "-5+x*3-6";
	String ord1 = "!simplify x=5";
	
	@Test
	public void testCalculate() {
		Project1 p= new Project1();
		String result = p.calculate(str1, ord1);
		assertEquals("4.0",result);


	}

}
