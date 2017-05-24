import org.junit.Test;

public class MyTest {

	@Test
	public void testTryCatch() {
		try {
			int num = 5 / 0;
		} catch (Exception e) {
			System.out.println("run catch");
			return;
		}finally{
			System.out.println("run finally");
		}
		
	}
}
