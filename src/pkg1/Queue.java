package pkg1;
//�ŶӺ������Զ�����������
import java.awt.AWTException;
import java.awt.Color;

import java.awt.Robot;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Queue {
	public static void main(String args[]) throws AWTException
	{
		Robot bush = new Robot();
		Timer timer = new Timer();
		Color c = new Color(225,39,19);//������ɫ
		CheckThread ct = new CheckThread();
		int account;
		Scanner reader = new Scanner(System.in);
		
		System.out.println("�������˺�λ�ã���1��2��");
		
		account = reader.nextInt();
		
		 timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		        	if(account == 1 )
		        Method.click(bush,804,444,true);	//����ͷ��һ��λ
		        	else if(account == 2)	
		        Method.click(bush,1011,436,true);	//����ͷ�����λ
		        
		        ct.check(new PBean(1068, 492,225,39,19),true,30000);//����30��
		        
		        if(c.equals(bush.getPixelColor(1068, 492))){//��������
		        	Method.click(bush, 963, 665, true);//[�˳��Ŷ�]
		        	ct.check(new PBean(1060,591,227,183,92),true,500);//���ͼ�رհ�ť
		        	Method.click(bush, 1056, 592, true);//[ȷ��]
		        }
		        else
		        {	System.exit(0);
		        	this.cancel();
		        }
		        }
		      }, 1000, 500);
		
	}
}
