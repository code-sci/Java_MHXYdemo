package pkg1;
//排队函数，自动点击挤入队列
import java.awt.AWTException;
import java.awt.Color;

import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class Queue {
	public static void main(String args[]) throws AWTException
	{
		Robot bush = new Robot();
		Timer timer = new Timer();
		Color c = new Color(225,39,19);
		 timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		        Method.click(bush,804,444,true);	//人物头像
		        Method.await(bush,0.3, 0.4);
		        if(c.equals(bush.getPixelColor(1068, 492))){//“满”字
		        	Method.click(bush, 963, 665, true);//[退出排队]
		        	Method.await(bush,0.3, 0.4);
		        	Method.click(bush, 1056, 592, true);//[确定]
		        }
		        else
		        {	this.cancel();
		        
		        }
		        }
		      }, 1000, 2000);
	}
}
