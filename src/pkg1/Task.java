package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Task {
	

	//��ȡ�򶳽�˫������,@times ��ǰ�ڼ�ֻ��
	static void getDouble(Robot robot, int times,boolean flag)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
		
    	Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_G);
    	ct.check(new PBean(1400,264,207,0,0),true,3000);//�һ�����رհ�ť
    	
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
	
	//����ץ��������
	static void zhuaGui(Robot bush)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		Method.press(bush,KeyEvent.VK_ESCAPE);	
		Method.await(bush,0.5,0.6);
		Method.press(bush,KeyEvent.VK_ESCAPE);
		Method.await(bush,0.5,0.6);
		
		
		System.out.println("=======��ʼ����ץ������=======");
		
		Method.press2(bush,KeyEvent.VK_ALT,KeyEvent.VK_M);	//���ͼ
		ct.check(new PBean(1398,277,231,68,44),true,3000);//���ͼ�رհ�ť
		
		Method.click(bush,926,600,true);	//���ͼ---������
		ct.check(new PBean(1398,277,231,68,44),false,3000);//���ͼ�رհ�ť
		
		Method.press(bush,KeyEvent.VK_TAB);	//����---С��ͼ
		ct.check(new PBean(1341,301,191,21,0),true,3000);//С��ͼ�رհ�ť
		
		Method.click(bush,762,598,true);	//С��ͼ---��ظ
		ct.check(new PBean(1287,521,85,57,35),true,8000);//ץ�����񡰹���
		
		Method.click(bush,1219,515,true);	//���ץ������
		Method.await(bush,1,1.5);	
		
		Method.press(bush,KeyEvent.VK_ESCAPE);	//������Ļ
		Method.await(bush,0.5,0.6);
		
		Method.press(bush,KeyEvent.VK_ESCAPE);
		//ץ��������ʱ��λ�ò���
		if(bush.getPixelColor(1455,338).equals(new Color(117,62,24)))
			Method.click(bush,1455,338,true);
		else
			Method.click(bush,1455,450,true);
		Method.await(bush,1,1.5);
		System.out.println("=======ץ�����������ϣ�=======");
		
	}
	
	
}
