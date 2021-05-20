import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.util.*;
import java.lang.IllegalArgumentException;

@RunWith(Parameterized.class)
public class DateNextDateExceptionTest
{

	/**
	 * Year value for our NextDate function
	 */
	private Date inputed;
	
	/**
	 * We should always expect an IlligalArgumentException
	 */
	//NO CLASS VAR FOR EXPECTED
	
	public void DateNextDateExceptionTest(Date inputed) {
		this.inputed = inputed;
	}
	
	@Parameters
	public static List<Date[]> data( )
	{
		List<Date[]> params = new LinkedList<Date[]>( );
		params.add(new Date[] { new Date(1975,6,-50) });
		params.add(new Date[] { new Date(1458,15,12) });
		params.add(new Date[] { new Date(-1,10,20) });
		params.add(new Date[] { new Date(1500,2,29) });
		params.add(new Date[] { new Date(1500,2,31) });
		return params;
	}
	
	@DisplayName("NextDate function should return IlligalArgumentException")
	@Test
	public void testNextDateException()
	{
		Assert.assertEquals(IllegalArgumentException, inputed.nextDate());
	}
}

