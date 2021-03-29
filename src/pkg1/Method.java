package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Method {
	
	//����ץ��������
	static void task_gui(Robot bush)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		press(bush,KeyEvent.VK_ESCAPE);	
		await(bush,0.5,0.6);
		press(bush,KeyEvent.VK_ESCAPE);
		await(bush,0.5,0.6);
		
		
		System.out.println("=======��ʼ����ץ������=======");
		
		press2(bush,KeyEvent.VK_ALT,KeyEvent.VK_M);	//���ͼ
		ct.check(new PBean(1398,277,231,68,44),true);//���ͼ�رհ�ť
		
		click(bush,926,600,true);	//���ͼ---������
		ct.check(new PBean(1398,277,231,68,44),false);//���ͼ�رհ�ť
		
		press(bush,KeyEvent.VK_TAB);	//����---С��ͼ
		ct.check(new PBean(1341,301,191,21,0),true);//С��ͼ�رհ�ť
		
		click(bush,762,598,true);	//С��ͼ---��ظ
		ct.check(new PBean(1287,521,85,57,35),true);//ץ�����񡰹���

		click(bush,1219,515,true);	//���ץ������
		await(bush,1,1.5);	
		
		press(bush,KeyEvent.VK_ESCAPE);	//������Ļ
		await(bush,0.5,0.6);
		
		press(bush,KeyEvent.VK_ESCAPE);
			//ץ��������ʱ��λ�ò���
		if(bush.getPixelColor(1455,338).equals(new Color(117,62,24)))
			click(bush,1455,338,true);
		else
			click(bush,1455,450,true);
		await(bush,1,1.5);
		System.out.println("=======ץ�����������ϣ�=======");
		
	}

	
	//�������������������Ϊ�Ƿ�����������ƫ�ƣ�
	static void  click(Robot robot,int x , int y,boolean f)
	{
		if(f){//�Ե���������Сƫ��
			x = x+(new Random().nextInt(6));
			y = y+(new Random().nextInt(6));
		
		
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
	
	//�϶�����
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
	
	//��ȡ�򶳽�˫������,@times ��ǰ�ڼ�ֻ��
	static void task_double(Robot robot, int times,boolean flag)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
		
    	Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_G);
    	ct.check(new PBean(1400,264,207,0,0),true);//�һ�����رհ�ť
    	
    	if(!flag)//����
    	{
    		Method.click(robot, 1040, 793, true);
    		System.out.println("����˫�������ɹ���");
    	}else //��ȡ
    	{
    		Method.click(robot, 1299, 793, true);
    		Method.await(robot,1,1.8);	
    		System.out.println("��ȡ˫�������ɹ���");
    	}
    	
    	//������Ļ
    	Method.await(robot,1,1.3);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
	}
	
}
