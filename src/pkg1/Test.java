package pkg1;

import java.awt.AWTException;
import java.awt.Robot;

public class Test {
	public static void main (String args[]) throws AWTException
	{
		Robot robot = new Robot();
		Method.task_gui(robot);
	}
}
