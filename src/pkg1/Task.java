package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Task {
	//
	
	
	
	//#����һ������һ�
	static void guaJi(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("=======��ʼ����һ�����=======");
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//�жϵ�����
		 {
			 Method.click(robot, 855,588,true);//[ȡ��]
			 ct.check(new PBean(1108,519,154,110,66), false, 3000);//�ȴ����������ر�
		 }
		 //�򿪹һ�����
		 Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_G);
		 ct.check(new PBean(1400,264,207,0,0),true,5000);//�ȴ����һ�����رհ�ť������
		 
		 //��������ȡ��˫������
		 Method.click(robot, 1040, 793, true);
 		 System.out.println("����˫�������ɹ���");
 		
 		//����һ�������ת
		 Method.click(robot, 845,320, true);
		 
		 //���ԭ�عһ����Է���һ��
		 
		 
		 System.out.println("=======���г����һ����ű���ֹ���У�=======");
		//��ֹ�ű����У�
		 System.exit(0);
	}
	
	
	//#�����쳣����
	static void clearScreen(Robot robot)
	{
		//����-�һ�����
		if(new Color(207,0,0).equals(robot.getPixelColor(1400,264)))
			Method.click(robot, 1400, 264, true);
		
		//����-�·��
		 if(robot.getPixelColor(1404,281).getRed()==185)
	         Method.click(robot,1404,281,true);//[�ر�]
		 
		 //����--�Ƿ���˫?
		 if(new Color(142,94,45).equals(robot.getPixelColor(1100,515)))
			 Method.click(robot, 855,588,true);//[ȡ��]
		 
		 //����--�Ƿ����ץ��?
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//�жϵ�����
		 {
			System.out.println("=======��ʼ[����]����ץ������=======");
				
			 Method.click(robot, 1051,595,true);//���[ȷ��]����ץ��--->��ת��ظ�Ի�
			 
			 CheckThread ct = new CheckThread();
			 ct.check(new PBean(1287,521,85,57,35),true,8000);//�ȴ�ץ�����񡰹���
				
				Method.click(robot,1219,515,true);	//���[ץ������]
				Method.await(robot,1,1.5);	
				
				Method.press(robot,KeyEvent.VK_ESCAPE);	//������ظ�Ի���
				Method.await(robot,0.5,0.6);
				
				Method.press(robot,KeyEvent.VK_ESCAPE);
				//ץ��������ʱ��λ�ò���
				if(robot.getPixelColor(1455,338).equals(new Color(117,62,24)))
					Method.click(robot,1455,338,true);
				else
					Method.click(robot,1455,450,true);
				Method.await(robot,1,1.5);
				System.out.println("=======[����]����ץ��������ϣ�=======");
				
				MyRobot.n_gui = 0;//��n_guiֻ������
		 }
		 
		 	//������Ļ
	    	Method.await(robot,1,1.3);
	    	Method.press(robot,KeyEvent.VK_ESCAPE);
	    	Method.await(robot,0.5,0.6);
	    	Method.press(robot,KeyEvent.VK_ESCAPE);
	}
	
	//#��ȡ�򶳽�˫������,@times ��ǰ�ڼ�ֻ��
	static void getDouble(Robot robot, int times,boolean flag)
	{
		CheckThread ct = new CheckThread();
		//���������
		Method.click2(robot, 972, 141);//˫������Ϸ�ϱ߿�
		Method.await(robot, 1, 2);
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
	
	//#����ץ��������
	static void zhuaGui(Robot bush)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		Method.press(bush,KeyEvent.VK_ESCAPE);	
		Method.await(bush,0.5,0.6);
		Method.press(bush,KeyEvent.VK_ESCAPE);
		Method.await(bush,0.5,0.6);
		
		
		System.out.println("=======��ʼ[�ֶ�]����ץ������=======");
		
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
		
		Method.press(bush,KeyEvent.VK_ESCAPE);	//������ظ�Ի���
		Method.await(bush,0.5,0.6);
		
		Method.press(bush,KeyEvent.VK_ESCAPE);
		//ץ��������ʱ��λ�ò���
		if(bush.getPixelColor(1455,338).equals(new Color(117,62,24)))
			Method.click(bush,1455,338,true);
		else
			Method.click(bush,1455,450,true);
		Method.await(bush,1,1.5);
		System.out.println("=======[�ֶ�]ץ�����������ϣ�=======");
		
	}
	
	//#ץ���������ͺ���
	static void sendCount(Robot robot,int k)
	{
		System.out.println("===����ץ������:"+MyRobot.n_count+"===");
		CheckThread ct = new CheckThread();
		
		Method.click(robot, 563,707,true);//���½�[����ͷ��]
		ct.check(new PBean(609,265,192,187,180), true, 4000);
		
		Method.click(robot,609,265,true);//[�����]
		Method.await(robot, 0.5, 0.8);
		
		Task.typeK(robot, k);
		
		
		Method.press(robot, KeyEvent.VK_ENTER);//����
		
		Method.await(robot, 0.5, 0.8);
		Method.click(robot,491,208,true);//�ر�[�����]
		
		
	}
	
	//#����ץ������ �ĸ����ݹ麯��
	static void typeK(Robot robot,int k)
	{
		if(k>0)
		{	
			int b = k/10;//ÿ��ȥ��һ����λ
			Task.typeK(robot,b);//�����λ�ĵݹ�
//			System.out.println(k%10);//�����λ������
			Method.press(robot,KeyEvent.VK_0+(k%10));
			Method.await(robot, 0.5, 0.8);
		}else
			return;
	}
	
	
}
