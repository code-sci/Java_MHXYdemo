package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
	public static void main (String args[]) throws AWTException
	{
		Timer tt = new Timer();
		//#定义计时器任务
		TimerTask t_1 =(new TimerTask(){
			@Override
	        public void run() {
				System.out.println(1);
				
			}
		});
		//#计时器任务定义结束
		
		//#定义计时器任务
		TimerTask t_2 =(new TimerTask(){
			@Override
			public void run() {
				System.out.println(2);
			}
		});
		//#计时器任务定义结束
		
		
	}
}
	