package pkg1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
//�����κβ���ǰ��Ӧȷ���Ƿ����������Ļ!
import java.util.TimerTask;

public class MyRobot {
	//��Ҫ���ݴ洢����DataBean��
	static DataBean db ;
	public static void main(String args []) throws AWTException
	{
		//��Ҫ�����ʼ����
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer Maintimer = new Timer();
		Scanner reader = new Scanner(System.in);
		
		//���ݳ�ʼ����
		MyRobot.db.setCost_Double(0);
		MyRobot.db.setSum_Gui(-1);//������һֻ����Ϊץ��ʱ�䲻׼ȷ
		MyRobot.db.setSum_Time(0);
		MyRobot.db.c_update(robot);
		MyRobot.db.setIfStop(false);
		
		System.out.println("������ץ��˫�ִΣ���Ĭ��Ϊ8��");
		MyRobot.db.setStartDouble(reader.nextInt());
		System.out.println("�����뵱ǰץ���ִΣ���Ĭ��Ϊ1��");
		MyRobot.db.setN_Gui(reader.nextInt()-1);
		//��һ��Ϊ���ڽ���ս������+1��
		System.out.println("�Ƿ���ץ��������(1��0����)");
		MyRobot.db.setFlagSend(reader.nextInt()==1?true:false);
		System.out.println("���������ֹͣʱ�䣺����\"7:30\"");
		String stop_time = reader.next();
	
		MyRobot.db.setStop_h(Integer.parseInt(stop_time.substring(0,stop_time.indexOf(':'))));
		MyRobot.db.setStop_m(Integer.parseInt(stop_time.substring(stop_time.indexOf(':')+1,stop_time.length())));
		
		reader.close();
		System.out.println("���óɹ�������ÿ�ֵ�"+MyRobot.db.getStartDouble()+"ֻ��ʱ��˫,�ű�����"+MyRobot.db.getStop_h()+":"+MyRobot.db.getStop_m()+"ֹͣ���С�");
		
		/*#ȫ��״̬���Ķ�����ʱ��,����Ҫ�������£�
		 * @������Ϸ״̬��
		 * @������Ϸ���ݣ�
		 * @�쳣��ֹ����(�رյ���������ץ��)
		*/
		TimerTask stateMonitor = (new TimerTask(){
			@Override
			public void run() {
				//�ж�ʱ��
				if(!MyRobot.db.isIfStop()&&(MyRobot.db.getStop_h() == Calendar.getInstance().get(Calendar.HOUR_OF_DAY))&&(MyRobot.db.getStop_m() == Calendar.getInstance().get(Calendar.MINUTE)))
				{
					System.out.println("ץ��ű�����ֹͣ������ץ������󽫽���һ�������");
					MyRobot.db.setIfStop(true);
				}
				
				//�������ø��������״̬�ж�
				MyRobot.db.s_update(robot);
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
					{//#����ͳ�ƴ�����
						MyRobot.db.setN_Gui(MyRobot.db.getN_Gui()+1);
						MyRobot.db.setSum_Gui(MyRobot.db.getSum_Gui()+1);
						if(MyRobot.db.getN_Gui()>=MyRobot.db.getStartDouble())//��¼˫����������
							MyRobot.db.setCost_Double(MyRobot.db.getCost_Double()+4);
					}
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//��ֹ
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("״̬����ֹ����"+MyRobot.db.getT_0()+"�룡");
					
					{//��ֹ���������;
						//���δ����ĵ���������Ļ������������Ϸ����Ϊ����ڣ���ֹ����
						//��ֹ����5��,�����Ѿ��ﵽֹͣʱ�䣻=>���ùһ���
						if(MyRobot.db.getT_0()==5&&MyRobot.db.isIfStop())
						{
							Method.click2(robot, 972, 141);//��������ڣ�
							Method.await(robot, 1, 1.5);
							
							Task.guaJi(robot);
							this.cancel();//�����ʱ������
						}//��ֹ����5�룬��δ�ﵽֹͣʱ�䣻=��������Ļ��
						else if(MyRobot.db.getT_0()==5)
						{
							Method.click2(robot, 972, 141);//��������ڣ�
							Method.await(robot, 1, 1.5);
							
							Task.clearScreen(robot);
						}//��ֹ����8�룬�����쳣���⣻=���ֶ�����ץ������
						else if(MyRobot.db.getT_0()==8)
						{
							Method.click2(robot, 972, 141);//��������ڣ�
							Method.await(robot, 1, 1.5);
							
							Task.zhuaGui(robot);	
							MyRobot.db.setN_Gui(0);//��n_guiֻ������
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
					if(MyRobot.db.getSum_Gui()!=1)
						MyRobot.db.setSum_Time(MyRobot.db.getSum_Time()+MyRobot.db.getT_2());//��¼�ϴ�ץ��ʱ��,��������һֻ��
					MyRobot.db.t_clear();
					MyRobot.db.t_clear();
					System.out.println("״̬������ƶ���=====");
				}
				
				//���������
				MyRobot.db.c_update(robot);
			}
		});
		
		
		//=============��ű������̡�=========================
		
		
		/*#ȫ��״̬���
		 * ��ȻͬһTimer����ͬʱ���в�ͬTimerTask��
		 * ��Ϊ�˱�������֮����߳����⣬���ǲ��ò�ͬTimer���в�ͬTimerTask�ķ�ʽ
		*/
		Maintimer.schedule(stateMonitor, 2000,1000);
		
	}
}
