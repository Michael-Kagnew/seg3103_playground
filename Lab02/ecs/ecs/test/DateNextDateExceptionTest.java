import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;

@RunWith(Parameterized.class)
public class DateNextDateExceptionTest
{

	/**
	 * Year value for our NextDate function
	 */
	private int year;

	private int month;

	private int day;
	
	/**
	 * We should always expect an IlligalArgumentException
	 */
	//NO CLASS VAR FOR EXPECTED
	
	public DateNextDateExceptionTest(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	@Parameters
	public static List<Integer[]> data( )
	{
		List<Integer[]> params = new LinkedList<Integer[]>( );
		params.add(new Integer[] { 1975,6,-50 });
		params.add(new Integer[] { 1458,15,12 });
		params.add(new Integer[] { -1,10,20 });
		params.add(new Integer[] { 1500,2,29 });
		params.add(new Integer[] { 1500,2,31 });
		return params;
	}

	@Test
	public void testNextDateException()
	{
		 Assertions.assertThrows(IllegalArgumentException.class, () -> {
     		Date d = new Date(year,month,day);
		 });
	}

}