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
		Color c = new Color(225,39,19);//满字颜色
		CheckThread ct = new CheckThread();
		
		 timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		        Method.click(bush,804,444,true);	//人物头像一号位
//		        Method.click(bush,1011,436,true);	//人物头像二号位
		        
		        ct.check(new PBean(1068, 492,225,39,19),true,500);//
		        
		        if(c.equals(bush.getPixelColor(1068, 492))){//“满”字
		        	Method.click(bush, 963, 665, true);//[退出排队]
		        	ct.check(new PBean(1060,591,227,183,92),true,500);//大地图关闭按钮
		        	Method.click(bush, 1056, 592, true);//[确定]
		        }
		        else
		        {	System.exit(0);
		        	this.cancel();
		        }
		        }
		      }, 1000, 500);
		
	}
}
