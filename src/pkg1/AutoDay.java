package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class AutoDay {
	
	
	public static void main(String args[]) throws AWTException
	{
		//��Ҫ�����ʼ����
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer dayTimer = new Timer();
		
		//��ʼ����״̬
		MyRobot.db.c_update(robot);
		
		
		
		//Ѻ�ڼ�ʱ��
		TimerTask YaBiao = (new TimerTask(){
			@Override
			public void run() {
				
				//�������ø��������״̬�ж�
				MyRobot.db.s_update(robot);
				
				//����ڱ���ť
				if(MyRobot.db.getnBiao()!=3&&new Color(129,101,76).equals(robot.getPixelColor(1232,636)))
				{
					MyRobot.db.setT_0(0);
					Method.click(robot, 1232,636, true);//��ʼѺ��
					Method.checkClick(robot, new PBean(1036,589,150,116,62), true, true, 1000*3);
					MyRobot.db.setnBiao(MyRobot.db.getnBiao()+1);//Ѻ�ڼ�һ
				}else if(MyRobot.db.getnBiao()==3)
				{
					
					System.out.println("#�����ճ�---Ѻ��===");
					System.out.println("#�ű���ֹ!");
					this.cancel();
					System.exit(0);
				}
				
				//״̬����
				if(MyRobot.db.getState() ==2&&MyRobot.db.isS_2()) 
				{//ս��
					MyRobot.db.setT_2(MyRobot.db.getT_2()+1);
					System.out.println("״̬��ս������"+MyRobot.db.getT_2()+"�룡");
				}else if(MyRobot.db.isS_2())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(2);
					System.out.println("״̬�����ս����=====");
					
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//��ֹ
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("״̬����ֹ����"+MyRobot.db.getT_0()+"�룡");
					
				}else if(MyRobot.db.isS_0())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(0);
					System.out.println("״̬�������ֹ��=====");
				}else if(MyRobot.db.getState()== 3 &&MyRobot.db.isS_3())
				{//Ѻ��
					MyRobot.db.setT_3(MyRobot.db.getT_3()+1);
					System.out.println("״̬��Ѻ�ڳ���"+MyRobot.db.getT_3()+"�룡");
				}else if(MyRobot.db.isS_3()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(3);
					System.out.println("״̬�����Ѻ�ڣ�=====");
				}else if(MyRobot.db.getState()== 1 &&MyRobot.db.isS_1())
				{//�ƶ�			
					MyRobot.db.setT_1(MyRobot.db.getT_1()+1);
					System.out.println("״̬���ƶ�����"+MyRobot.db.getT_1()+"�룡");
				}else if(MyRobot.db.isS_1()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(1);
					System.out.println("״̬������ƶ���=====");
				}
				
				//���������
				MyRobot.db.c_update(robot);
			} 
		});
		
		//�ؾ���ʱ��
		TimerTask MiJing = (new TimerTask(){
			@Override
			public void run() {
				
				//�������ø��������״̬�ж�
				MyRobot.db.s_update(robot);
				
				//�ж��Ƿ���Ҫ��ȡ���
				if(new Color(249,102,32).equals(robot.getPixelColor(1223,729)))
					{
						Method.click(robot, 1223,729, true);
						Method.click(robot, 1271,473,true);//�����һ��
						Method.click(robot, 1271,473,true);//�����һ��
					}
				
				
				
				
				//״̬����
				if(MyRobot.db.getState() ==2&&MyRobot.db.isS_2()) 
				{//ս��
					MyRobot.db.setT_2(MyRobot.db.getT_2()+1);
					System.out.println("״̬��ս������"+MyRobot.db.getT_2()+"�룡");
				}else if(MyRobot.db.isS_2())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(2);
					System.out.println("״̬�����ս����=====");
					
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//��ֹ
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("״̬����ֹ����"+MyRobot.db.getT_0()+"�룡");
					
					//�·������
					if(robot.getPixelColor(1404,281).getRed()==185)
				         Method.click(robot,1404,281,true);//[�ر�]
					
					//�ж��Ƿ���Ҫ�������ս��
					if(new Color(112,84,60).equals(robot.getPixelColor(1264,639)))
						Method.click(robot, 1264,639, true);
					
					//����Ƿ�ͨ�أ�
					if(MyRobot.db.getT_0()==5&&new Color(255,255,255).equals(robot.getPixelColor(1271,473)))
					{
						MyRobot.db.setT_0(0);
						System.out.println("#�����ճ�---�ؾ�����===");
						this.cancel();
						Task.day_YaBiao(robot);
						new Timer().schedule(YaBiao, 3000,1000);
					}else if(MyRobot.db.getT_0()==3&&new Color(226,41,34).equals(robot.getPixelColor(510,678)))
					{//����Ƿ�ʧ��
						System.out.println("����ʧ�ܣ�������ս��");
						Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_Z);
						new CheckThread().check(new PBean(1399,263,204,0,0), true,1000*3);//��ս����
						
						Method.click(robot, 1354, 511, true);//�ڶ�������
						
						Method.press(robot,KeyEvent.VK_ESCAPE);//�ر���ս
						
						Method.click(robot, 1271,473,true);//�����һ��
						
					}else if(MyRobot.db.getT_0()==3&&new Color(0,255,0).equals(robot.getPixelColor(1372,476)))//�������һ��
					{
						System.out.println("���һ�أ�������ս��");
						Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_Z);
						new CheckThread().check(new PBean(1399,263,204,0,0), true,1000*3);//��ս����
						
						Method.click(robot, 1354, 511, true);//�ڶ�������
						
						Method.press(robot,KeyEvent.VK_ESCAPE);//�ر���ս
						
						Method.click(robot, 1271,473,true);//�����һ��
					}
					else if(MyRobot.db.getT_0()==3)
					{//����Ƿ���Ҫ�����һ��
						Method.click(robot, 1271,473,true);//�����һ��
					}
				}else if(MyRobot.db.isS_0())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(0);
					System.out.println("״̬�������ֹ��=====");
				}else if(MyRobot.db.getState()== 3 &&MyRobot.db.isS_3())
				{//Ѻ��
					MyRobot.db.setT_3(MyRobot.db.getT_3()+1);
					System.out.println("״̬��Ѻ�ڳ���"+MyRobot.db.getT_3()+"�룡");
				}else if(MyRobot.db.isS_3()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(3);
					System.out.println("״̬�����Ѻ�ڣ�=====");
				}else if(MyRobot.db.getState()== 1 &&MyRobot.db.isS_1())
				{//�ƶ�			
					MyRobot.db.setT_1(MyRobot.db.getT_1()+1);
					System.out.println("״̬���ƶ�����"+MyRobot.db.getT_1()+"�룡");
				}else if(MyRobot.db.isS_1()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(1);
					System.out.println("״̬������ƶ���=====");
				}
				
				//���������
				MyRobot.db.c_update(robot);
			} 
		});
		
		//�ڱ���ʱ��
		TimerTask WaBao = (new TimerTask(){
			@Override
			public void run() {
				
				//�������ø��������״̬�ж�
				MyRobot.db.s_update(robot);
				
				//����ڱ���ť
				if(new Color(108,49,10).equals(robot.getPixelColor(1290,794)))
				{
					MyRobot.db.setT_0(0);
					Method.click(robot, 1290, 794, true);
					MyRobot.db.setnBao(MyRobot.db.getnBao()+1);//�ڱ�����+1
				}
				
				//״̬����
				if(MyRobot.db.getState() ==2&&MyRobot.db.isS_2()) 
				{//ս��
					MyRobot.db.setT_2(MyRobot.db.getT_2()+1);
					System.out.println("״̬��ս������"+MyRobot.db.getT_2()+"�룡");
				}else if(MyRobot.db.isS_2())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(2);
					System.out.println("״̬�����ս����=====");
					
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//��ֹ
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("״̬����ֹ����"+MyRobot.db.getT_0()+"�룡");
					{//��ֹ���������;
						//��ֹ����5���ұ�ͼ��Ϊ0=��ս������
						if(MyRobot.db.getT_0()==5&&MyRobot.db.getnBao()==0)
						{
							Task.useWaBao(robot);
						}
						//��ֹ����10���ұ�ͼ����Ϊ0=���ڱ�����
						if(MyRobot.db.getT_0()==10&&MyRobot.db.getnBao()!=0)
						{
							System.out.println("�Զ��ڱ�����===����"+MyRobot.db.getnBao()+"�Ųر�ͼ��");
							System.out.println("3���ʼ�ճ��ؾ�");
							
							Method.await(robot, 2, 2);
							Task.day_Yao(robot);
							MyRobot.db.setT_0(0);
							dayTimer.schedule(MiJing,1000,1000);
							this.cancel();
						}
					}
					
				}else if(MyRobot.db.isS_0())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(0);
					System.out.println("״̬�������ֹ��=====");
				}else if(MyRobot.db.getState()== 3 &&MyRobot.db.isS_3())
				{//Ѻ��
					MyRobot.db.setT_3(MyRobot.db.getT_3()+1);
					System.out.println("״̬��Ѻ�ڳ���"+MyRobot.db.getT_3()+"�룡");
				}else if(MyRobot.db.isS_3()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(3);
					System.out.println("״̬�����Ѻ�ڣ�=====");
				}else if(MyRobot.db.getState()== 1 &&MyRobot.db.isS_1())
				{//�ƶ�			
					MyRobot.db.setT_1(MyRobot.db.getT_1()+1);
					System.out.println("״̬���ƶ�����"+MyRobot.db.getT_1()+"�룡");
				}else if(MyRobot.db.isS_1()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(1);
					System.out.println("״̬������ƶ���=====");
				}
				
				//���������
				MyRobot.db.c_update(robot);
			} 
		});
		
	
		
		//======�Զ��ճ�������==========================
		System.out.println("�ű����У���������Զ���ʼ�ճ�...");
		Method.await(robot, 1, 3);
		Method.click(robot, 1022, 143, true);//���������
		
		//�ճ�=����ͼ=���ؾ�=��Ѻ��=��
		Task.day_BangPai(robot);
		Task.day_PetPray(robot);
		Task.day_Home(robot);
		Task.day_Chat(robot);
		
		
		//�����ڱ�����=����ʼս����
		Task.getWaBao(robot);
		
		//����״̬���=����ֹ5����=������ʹ�ñ�ͼ=����ֹ2��=����鱦ͼʹ�ð�ť=����ֹ5��=���ڱ�����
		dayTimer.schedule(WaBao, 2000,1000);
	}
}
