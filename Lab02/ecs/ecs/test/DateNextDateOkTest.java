import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import java.util.List;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class DateNextDateOkTest
{

	/**
	 * The date we are inputing into the function
	 */
	private Date inputed;
	
	/**
	 * The date we are expecting to recieve
	 */
	private Date expected;
	
	public DateNextDateOkTest(Date inputed, Date expected) {
		this.inputed = inputed;
		this.expected = expected;
	}
	
	@Parameters
	public static List<Date[]> data( )
	{
		List<Date[]> params = new LinkedList<Date[]>( );
		params.add(new Date[] { new Date(1700,6,20), new Date(1700,6,21) });
		params.add(new Date[] { new Date(2005,4,15), new Date(2005,4,16) });
		params.add(new Date[] { new Date(1901,7,20), new Date(1901,7,21) });
		params.add(new Date[] { new Date(3456,3,27), new Date(3456,3,28) });
		params.add(new Date[] { new Date(1500,2,17), new Date(1500,2,18) });
		params.add(new Date[] { new Date(1700,6,29), new Date(1700,6,30) });
		params.add(new Date[] { new Date(1800,11,29), new Date(1800,11,30) });
		params.add(new Date[] { new Date(3453,1,29), new Date(3453,1,30) });
		params.add(new Date[] { new Date(444,2,29), new Date(444,3,1) });
		params.add(new Date[] { new Date(2005,4,30), new Date(2005,5,1) });
		params.add(new Date[] { new Date(3453,1,30), new Date(3453,1,31) });
		params.add(new Date[] { new Date(3456,03,30), new Date(3456,3,31) });
		params.add(new Date[] { new Date(1901,7,31), new Date(1901,8,1) });
		params.add(new Date[] { new Date(3453,01,31), new Date(3453,2,1) });
		params.add(new Date[] { new Date(3456,12,31), new Date(3457,1,1) });
		return params;
	}
	
	@Test
	public void testNextDateException()
	{
		Assert.assertEquals(expected, inputed.nextDate());
	}
	
}