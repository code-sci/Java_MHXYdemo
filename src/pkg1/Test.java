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
		//#�����ʱ������
		TimerTask t_1 =(new TimerTask(){
			@Override
	        public void run() {
				System.out.println(1);
				
			}
		});
		//#��ʱ�����������
		
		//#�����ʱ������
		TimerTask t_2 =(new TimerTask(){
			@Override
			public void run() {
				System.out.println(2);
			}
		});
		//#��ʱ�����������
		
		
	}
}
	