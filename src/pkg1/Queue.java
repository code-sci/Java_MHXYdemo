package pkg1;
//�ŶӺ������Զ�����������
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
		        Method.click(bush,804,444,true);	//����ͷ��һ��λ
//		        Method.click(bush,1011,436,true);	//����ͷ�����λ
		        
		        Method.await(bush,0.4, 0.5);
		        if(c.equals(bush.getPixelColor(1068, 492))){//��������
		        	Method.click(bush, 963, 665, true);//[�˳��Ŷ�]
		        	Method.await(bush,0.4, 0.5);
		        	Method.click(bush, 1056, 592, true);//[ȷ��]
		        }
		        else
		        {	System.exit(0);
		        	this.cancel();
		        }
		        }
		      }, 1000, 2000);
	}
}
