package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Task {
	static Robot robot ;
	static CheckThread ct;
	
	//�����ڱ�������
	static void getWaBao(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("===��ʼ���ܱ�ͼ����");
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//���ͼ
		ct.check(new PBean(1398,277,231,68,44),true,3000);//���ͼ�رհ�ť
		
		Method.click(robot,926,600,true);	//���ͼ---������
		ct.check(new PBean(1398,277,231,68,44),false,3000);//���ͼ�رհ�ť
		
		Method.press(robot,KeyEvent.VK_TAB);	//����---С��ͼ
		ct.check(new PBean(1341,301,191,21,0),true,3000);//С��ͼ�رհ�ť
		
		Method.click(robot,1155,566,true);	//С��ͼ---��С��
		
		ct.check(new PBean(1263,695,136,109,83), true, 1000*15);//���[��ͼ����]��ť
		
		Method.click(robot, 1263, 695, true);
		Method.press(robot, KeyEvent.VK_ESCAPE);//������Ļ
		
		Method.await(robot, 0.5, 1);
		//����׷��
		if(new Color(251,242,28).equals(robot.getPixelColor(1288, 330)))
			Method.click2(robot, 1288, 330);
		else if(new Color(251,242,28).equals(robot.getPixelColor(1288, 416)))
			Method.click2(robot, 1288, 416);
		else
			Method.click2(robot, 1288, 502);
		
		System.out.println("===��ͼ���������ϣ���ʼ��ͼս����");
		MyRobot.db.setnBao(0);
	}
	
	static void useWaBao(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("===��ʼʹ�òر�ͼ�����ڱ���");
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_E);	//�򿪱���
		ct.check(new PBean(1069,795,168,108,53),true,3000);//���[��������]
		Method.click(robot, 1258, 790, true);//������
		
		//���б�ͼ���
		Color c_Bao = new Color(102,255,204);
		int x = 1000;
		int y = 400;
		int i,j;
		int x_Bao=0,y_Bao=0;
		for(i=1;y<800;y+=80,i++)
		{
			for(j=1,x=1000;x<1400;x+=80,j++)
			{
				if(c_Bao.equals(robot.getPixelColor(x, y)))
				{
					System.out.println(i+"��"+j+"�з��ֲر�ͼһ�ţ�");
					x_Bao=x ; y_Bao=y;
				}
			}
		}
		
		if(x_Bao!=0&&y_Bao!=0)
		{
			System.out.println("====��ʼ�ڱ���");
			Method.click(robot, x_Bao, y_Bao,true);//�����ر�ͼλ��
			ct.check(new PBean(847,622,148,93,37), true, 3000);
			Method.click(robot, 847, 622, true);
		}
		
		//===>�Զ��ƶ�����ͼλ�ã�Ȼ�����ڱ���ť
	}
		
	/*#��¼ͳ�Ʊ��νű����ݣ�
	 * @ץ��������
	 * @��˫ץ��������
	 * @����˫��������
	 * @ץ��ƽ����ʱ��
	*/
	static void noteStatis()
	{
		float avg_time = (float)MyRobot.db.getSum_Time()/MyRobot.db.getSum_Gui();//����С������
		avg_time = Float.parseFloat(new DecimalFormat("0.00").format(avg_time));
		String fileName="F:\\����\\position.txt";
		Calendar c = Calendar.getInstance();
		int m = c.get(Calendar.MONTH)+1;//��0��ʼ
		int d = c.get(Calendar.DAY_OF_MONTH);
		int h = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		Method.writeToFile(fileName,"\n#���νű�ͳ�����ݣ�(��ֹ"+m+"��"+d+"��"+h+":"+min+")");
		Method.writeToFile(fileName,"�趨ֹͣʱ�䣺"+MyRobot.db.getStop_h()+":"+MyRobot.db.getStop_m());
		Method.writeToFile(fileName,"ץ��������"+MyRobot.db.getSum_Gui());
		Method.writeToFile(fileName,"����˫��������"+MyRobot.db.getCost_Double());
		Method.writeToFile(fileName,"ץ��ƽ����ʱ��"+avg_time+"s(�����ο�)");
	}
	
	//����ץ�����񲢴�������쳣
	static void getZhuagui(Robot robot)
	{
		CheckThread ct = new CheckThread();
		ct.check(new PBean(1287,521,85,57,35),true,8000);//ץ�����񡰹���
		
		Method.click(robot,1219,515,true);	//����[ץ������]
		Method.await(robot,2,3);	
		
		if(new Color(227,75,55).equals(robot.getPixelColor(900, 502)))
		{
			System.out.println("��������쳣�����д���");
			Method.click(robot,1035,608, true);//ȷ����������=���򿪶������
			ct.check(new PBean(1313,320,235,190,95), true, 5000);//���[�������]
			Color c_Have = new Color(240,227,211);
			
			//������������
		
			while(!c_Have.equals(robot.getPixelColor(833,738)))//p2�쳣
			{
				System.out.println("P2�쳣!");
				Method.click(robot, 833, 738, true);//���[��Աλ��]
				ct.check(new PBean(1050,539,85,57,35), true, 3000);//���[���밴ť]
				Method.click(robot, 1050, 540, true);//���[����]��
				
				Task.getZhuagui_team(robot, ct);
				
				
			}
			while(!c_Have.equals(robot.getPixelColor(1003,738)))//p3�쳣
			{
				System.out.println("P3�쳣!");
				Method.click(robot, 1003,720, true);//���[��Աλ��]
				ct.check(new PBean(1221,539,85,57,35), true, 3000);//���[���밴ť]
				Method.click(robot,1221,539, true);//���[����]��
				
				Task.getZhuagui_team(robot, ct);
			}
			while(!c_Have.equals(robot.getPixelColor(1172,738)))//p4�쳣
			{
				System.out.println("P4�쳣!");
				Method.click(robot, 1172,720, true);//���[��Աλ��]
				ct.check(new PBean(941,539,85,57,35), true, 3000);//���[���밴ť]
				Method.click(robot, 941,539, true);//���[����]��
				
				Task.getZhuagui_team(robot, ct);
			}
			while(!c_Have.equals(robot.getPixelColor(1347,738)))//p5�쳣
			{
				System.out.println("P5�쳣!");
				Method.click(robot, 1347,720, true);//���[��Աλ��]
				ct.check(new PBean(1113,539,85,57,35), true, 3000);//���[���밴ť]
				Method.click(robot, 1113,539, true);//���[����]��
				
				Task.getZhuagui_team(robot, ct);
			}
			
			Method.press(robot, KeyEvent.VK_ESCAPE);//�رն�����棻
			
			System.out.println("���鴦����ϣ�����ץ��");
		}
		
		Method.press(robot,KeyEvent.VK_ESCAPE);	//������ظ�Ի���
		Method.await(robot,0.5,0.6);
		
		//����׷�ٽ���
		if(!(new Color(197,227,167).equals(robot.getPixelColor(1267,282))))
			Method.click(robot, 1267,282, true);
		ct.check(new PBean(1267,282,197,227,167), true, 3000);
		
		
		Method.press(robot,KeyEvent.VK_ESCAPE);
		//ץ��������ʱ��λ�ò���
		if(robot.getPixelColor(1455,338).equals(new Color(117,62,24)))
			Method.click(robot,1455,338,true);
		else
			Method.click(robot,1455,450,true);
		Method.await(robot,1,1.5);
	}
	
	//ץ����Ӵ���
	static void getZhuagui_team(Robot robot,CheckThread ct)
	{
		Method.await(robot, 0.5, 1);//������ӳ�
		Method.click(robot, 1149,319, true);//���[���Ŀ��]
		ct.check(new PBean(1069,806,108,49,10),true,3000);//���[���Ŀ��]����
		if(new Color(185,180,173).equals(robot.getPixelColor(1079,620)))//���Ŀ��Ϊ��
		{
			Method.setSysClipboardText("ͨ����������ȶ�����");
			Method.click(robot, 1079,620, true);
			ct.check(new PBean(1079,620,185,180,173),false,2000);//���[Ŀ�������]
			Method.press2(robot, KeyEvent.VK_CONTROL, KeyEvent.VK_V);//ճ��Ŀ������
		}
		Method.click(robot, 1069,806, true);//���[ȷ��]=����ʼƥ��
		ct.check(new PBean(1252,795,237,186,87), true,3000);//[һ������]
		Method.click(robot, 1252,795, true);
		ct.check(new PBean(1261,578,108,49,10), true,3000);//[��ǰƵ��]
		Method.click(robot,1261,578, true);
		
		Method.click(robot, 1252,795, true);//[һ������]
		ct.check(new PBean(1256,501,192,163,138), true,3000);//[����Ƶ��]
		Method.click(robot,1256,501, true);
		
		ct.check(new PBean(1347,738,240,227,211),true, 1000*30);//�ȴ�30������
		
	}
	
	//#����һ������һ�
	static void guaJi(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("=======��ʼ����һ�����=======");
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//�жϼ���ץ������
		 {
			 Method.click(robot, 855,588,true);//[ȡ��]
			 ct.check(new PBean(1108,519,154,110,66), false, 3000);//�ȴ����������ر�
		 }
		 //�򿪹һ�����
		 Method.await(robot, 0.5, 1);
		 Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_G);
		 ct.check(new PBean(1400,264,207,0,0),true,5000);//�ȴ����һ�����رհ�ť������
		 
		 Method.await(robot, 0.5, 1);
		 //��������ȡ��˫������
		 Method.click(robot, 1040, 793, true);
 		 System.out.println("����˫�������ɹ���");
 		
 		Method.await(robot, 0.5, 1);
 		 //����Ƿ���Զ�ս��
 		 if(new Color(206,166,112).equals(robot.getPixelColor(901,717)))
 		 {
 			 Method.await(robot, 0.5, 0.8);
 			 Method.click(robot, 901, 717, true);
 			 System.out.println("���Զ�ս���ɹ���");
 		 }
 		 
 		Method.await(robot, 0.5, 1);
 		//����һ�������ת
		 Method.click(robot, 845,320, true);
		 ct.check(new PBean(1400,264,207,0,0),false,5000);//�ȴ����һ�����رհ�ť������
		 
		 
		 Method.await(robot, 0.5, 1);
		 //���ԭ�عһ����Է���һ��
		 Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_G);
		 ct.check(new PBean(1400,264,207,0,0),true,5000);//�ȴ����һ�����رհ�ť������
		 Method.click(robot, 660, 717, true);
		 
		 Method.await(robot, 0.5, 1);
		 System.out.println("�ű�����ͳ����...");
		 Task.noteStatis();
		 
		 //����
		 
		 Method.await(robot, 0.5, 1);
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
		 
		 //����--������Ե
		 if(new Color(151,107,61).equals(robot.getPixelColor(855,511)))
			 Method.click(robot, 855,588,true);//[ȡ��]
		 
		 //����--�Ƿ����ץ��?
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//�жϵ�����
		 {
			System.out.println("=======��ʼ[����]����ץ������=======");
				
			 Method.click(robot, 1051,595,true);//���[ȷ��]����ץ��--->��ת��ظ�Ի�
			 
			 
			 Task.getZhuagui(robot);//���ý���ץ������
			 
				System.out.println("=======[����]����ץ��������ϣ�=======");
				
				 MyRobot.db.setN_Gui(0);//��n_guiֻ������
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
	static void zhuaGui(Robot robot)
	{
		CheckThread ct = new CheckThread();
		//������Ļ
		Method.press(robot,KeyEvent.VK_ESCAPE);	
		Method.await(robot,0.5,0.6);
		Method.press(robot,KeyEvent.VK_ESCAPE);
		Method.await(robot,0.5,0.6);
		
		
		System.out.println("=======��ʼ[�ֶ�]����ץ������=======");
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//���ͼ
		ct.check(new PBean(1398,277,231,68,44),true,3000);//���ͼ�رհ�ť
		
		Method.click(robot,926,600,true);	//���ͼ---������
		ct.check(new PBean(1398,277,231,68,44),false,3000);//���ͼ�رհ�ť
		
		Method.press(robot,KeyEvent.VK_TAB);	//����---С��ͼ
		ct.check(new PBean(1341,301,191,21,0),true,3000);//С��ͼ�رհ�ť
		
		Method.click(robot,762,598,true);	//С��ͼ---��ظ
		
		Task.getZhuagui(robot);
		
		
		
		System.out.println("=======[�ֶ�]ץ�����������ϣ�=======");
		
	}
	
	//#ץ���������ͺ���
	static void sendCount(Robot robot,int k)
	{
		System.out.println("===����ץ������:"+MyRobot.db.getSum_Gui()+"===");
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
