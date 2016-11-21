import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class CheckTest {

	/**
	 * Test method for {@link Project1#check(java.lang.String)}.
	 */
	@Test
	public void testCheck() {
		Project1 p=new Project1();
		boolean result=p.check("-5.0+1-2*3+AB+a1+ab");
		assertEquals(true,result);
	}

}
