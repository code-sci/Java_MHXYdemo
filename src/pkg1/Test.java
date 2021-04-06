package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
	public static void main (String args[]) throws AWTException
	{
		Robot robot = new Robot();
		Timer t1 = new Timer();
		t1.schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("时间：");
				System.out.println(Calendar.getInstance().get(Calendar.HOUR)+":");
				System.out.println(Calendar.getInstance().get(Calendar.MINUTE));
				if((19 == Calendar.getInstance().get(Calendar.HOUR_OF_DAY))&&21 == Calendar.getInstance().get(Calendar.MINUTE))
				{
					System.out.println("抓鬼脚本即将停止，本轮抓鬼结束后将进入挂机场景；");
					MyRobot.db.setIfStop(true);
					
				}
			}
		}, 1000,1000);
		
		
		
	}
}
	