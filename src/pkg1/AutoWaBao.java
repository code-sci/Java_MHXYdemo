package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class AutoWaBao {
	
	static DataBean db ;
	
	public static void main(String args[]) throws AWTException
	{
		//��Ҫ�����ʼ����
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer WaBaotimer = new Timer();
		CheckThread ct = new CheckThread();
		
		//��ʼ����״̬
		MyRobot.db.c_update(robot);
		
		TimerTask stateMonitor = (new TimerTask(){
			@Override
			public void run() {
				
				//�������ø��������״̬�ж�
				MyRobot.db.s_update(robot);
				
				//����ڱ���ť
				if(new Color(108,49,10).equals(robot.getPixelColor(1290,794)))
				{
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
						//��ֹ����5���ұ�ͼ����Ϊ0=���ڱ�����
						if(MyRobot.db.getT_0()==5&&MyRobot.db.getnBao()!=0)
						{
							System.out.println("�Զ��ڱ�����===����"+MyRobot.db.getnBao()+"�Ųر�ͼ��");
							this.cancel();
							System.out.println("�ű�����5����Զ��رգ�");
							Method.await(robot, 5, 5);
							System.exit(0);
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
		
		//======�Զ���ͼ��ͼ������==========
		//�����ڱ�����=����ʼս����
		System.out.println("3����Զ����вر�ͼ����Ľ�����ر�ͼ�ھ�\n�뱣����Ϸ����Ϊ�����...");
		Method.await(robot, 3, 3);
		Task.getWaBao(robot);
		//����״̬���=����ֹ5����=������ʹ�ñ�ͼ=����ֹ2��=����鱦ͼʹ�ð�ť=����ֹ5��=���ڱ�����
		WaBaotimer.schedule(stateMonitor, 2000,1000);
	}
}
