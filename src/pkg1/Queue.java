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
		        Method.click(bush,804,444,true);	//����ͷ��
		        Method.await(bush,0.3, 0.4);
		        if(c.equals(bush.getPixelColor(1068, 492))){//��������
		        	Method.click(bush, 963, 665, true);//[�˳��Ŷ�]
		        	Method.await(bush,0.3, 0.4);
		        	Method.click(bush, 1056, 592, true);//[ȷ��]
		        }
		        else
		        {	this.cancel();
		        
		        }
		        }
		      }, 1000, 2000);
	}
}
