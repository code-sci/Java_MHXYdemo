package pkg1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;

public class Test {
	public static void main (String args[]) throws AWTException
	{
		Robot robot = new Robot();
		Calendar c = Calendar.getInstance();
		int minute = c.get(Calendar.MILLISECOND);
		while(true)
		{
			minute = Calendar.getInstance().get(Calendar.MILLISECOND);
			Method.await(robot, 1, 1);
			System.out.println(minute);
		}
	
	}
}
	