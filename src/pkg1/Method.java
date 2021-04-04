package pkg1;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Method {
	
	//�������������������Ϊ�Ƿ�����������ƫ�ƣ�
	static void  click(Robot robot,int x , int y,boolean f)
	{
		if(f){//�Ե���������Сƫ��
			x = x-6+(new Random().nextInt(12));
			y = y-6+(new Random().nextInt(12));
		
		
		robot.mouseMove(x, y);
	
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.2,0.4);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
//		System.out.println("���("+x+","+y+")����");
	}
	
	//˫������
	static void click2(Robot robot,int x ,int y)
	{
		robot.mouseMove(x, y);
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		System.out.println("˫��("+x+","+y+")����");
		
	}
	
	//�������� 
	static void  press(Robot robot,int k)
	{
		robot.keyPress(k);
		await(robot,0.2,0.4);	
		robot.keyRelease(k);
		
//		System.out.println("����"+KeyEvent.getKeyText(k)+"����");
	}
	
	//��ϼ�����
	static void  press2(Robot robot,int k1,int k2)
	{
		robot.keyPress(k1);
		await(robot,0.2,0.4);	
		robot.keyPress(k2);
		await(robot,0.2,0.4);
		robot.keyRelease(k1);
		await(robot,0.2,0.4);
		robot.keyRelease(k2);
		
//		System.out.println("����"+KeyEvent.getKeyText(k1)+"+"+KeyEvent.getKeyText(k2)+"����");
	}
	
	//�϶��������漰���϶��ٶ����⣬�ݲ��Ƽ�ʹ�ã�
	static void drag(Robot robot,int x1,int y1,int x2, int y2)
	{
		robot.mouseMove(x1, y1);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.5,0.8);
		robot.mouseMove(x2, y2);
		await(robot,0.3,0.5);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		
	}
	
	//�����ʱ����
	static void await(Robot robot,double a,double b)
	{
		double v = a+(new Random()).nextDouble()*(b-a);	//���b-a��
		v = v*1000;
		robot.delay((int)v);
		
//		System.out.println("��ʱ"+((int)(v*10))/10000.0+"�룻");
	}
	
	
	//#���ݼ�¼����
	static  void writeToFile(String fileName,String str)
	  {
	   try {
	      //�������txt�ļ���ֱ����txt��׷���ַ���
	      FileWriter writer=new FileWriter(fileName,true);
	      writer.write(str+"\n");
	       writer.close();
	   } catch (IOException e) {e.printStackTrace();}
	 }
}
