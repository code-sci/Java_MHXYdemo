package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import pkg1.Method;
//�����κη���ʱҪȷ���Ƿ����������Ļ!

public class MyRobot {
	//��������
	static int state = 0,t_0=0,t_1=0,t_2=0,t_3;	//״̬����
	static Color c_pre1,c_pre2,c_pre3;	//״̬��鸨������
	static int n_gui = 0; //	��ǰץ�ڼ�ֻ�����,״̬��������
	static int double_gui= 8;	//ץ��˫������
	static int n_count = 0;//�ܹ�ץ������
	static int flag_send;
	public static void main(String args []) throws AWTException
	{
		Robot bush = new Robot();
		Timer timer = new Timer();
		int stop_h;
		int stop_m;
		CheckThread ct = new CheckThread();
		 c_pre1 = bush.getPixelColor(1131,617);
		 c_pre2 = bush.getPixelColor(741,328);
		 c_pre3 = bush.getPixelColor(1085,336);
		
		 /*�Զ���˫˼·
		  * ÿ�ν���ץ������ʱn_gui���㣬�����ö���˫��������
		  * ÿ�ν���ս��ʱn_gui��һ������鵱ǰn_gui�Ƿ������Ҫץ���������
		  * ��������ֿ�˫������n_gui==8ʱ������˫������
		 */
		Scanner reader = new Scanner(System.in);
		
		System.out.println("������ץ��˫�ִΣ���Ĭ��Ϊ8��");
		
		while((double_gui = reader.nextInt())<=0)
		{
			System.out.println("������һ������0������");
		}
		
		System.out.println("�����뵱ǰץ���ִΣ���Ĭ��Ϊ1��");
		
		while((n_gui = reader.nextInt())<=0)
		{
			System.out.println("������һ������0������");
		}
		n_gui--;//��Ϊ���ڽ���ս������+1��
		
		
		System.out.println("�Ƿ���ץ��������(1��0����)");
		
		while((flag_send = reader.nextInt())<0)
		{
			System.out.println("������0��1");
		}
		
		System.out.println("���������ֹͣʱ�䣺����(7:30)����ΪӢ��");
		
		String stop_time = reader.next();
		/*Java���ַ�����Ŵ�0��ʼ��indexof()�����ַ����ֵ�λ�ã�
		 * substring(A,B)����Aλ�õ�Bλ��֮ǰһλ���Ӵ���
		*/
		stop_h = Integer.parseInt(stop_time.substring(0,stop_time.indexOf(':')));
		stop_m = Integer.parseInt(stop_time.substring(stop_time.indexOf(':')+1,stop_time.length()));
		
		reader.close();
		System.out.println("���óɹ�������ÿ�ֵ�"+double_gui+"ֻ��ʱ��˫,�ű�����"+stop_h+":"+stop_m+"ֹͣ���С�");
		//ȫ��״̬���
		TimerTask state_control = (new TimerTask(){
			@Override
	        public void run() {
				//�ж�ʱ��
				if((stop_h == Calendar.getInstance().get(Calendar.HOUR))&&(stop_m == Calendar.getInstance().get(Calendar.MINUTE)))
				{
					System.out.println("���򼴽�ֹͣ��");
					Method.await(bush, 3, 5);
					System.exit(0);
				}
				
				//�ж������	�Ƿ�仯��������һ���ǲ�������Ǿ�ֹ
				boolean s_3 =(new Color(155,103,53).equals(bush.getPixelColor(715,234)));
				boolean s_0 = ((c_pre1.equals(bush.getPixelColor(1131,617))||c_pre2.equals(bush.getPixelColor(741,328))||
						c_pre3.equals(bush.getPixelColor(1085,336))))&&(!s_3);
				boolean s_1 = (!(c_pre1.equals(bush.getPixelColor(1131,617))&&!c_pre2.equals(bush.getPixelColor(741,328))&&
						!c_pre3.equals(bush.getPixelColor(1085,336))));
				/*#ս��״̬���ж���
				 * �츲����Һ�㡢
				 * ս���غ�����������ɫ�㡢
				 * ���ֱ߿���ɫ��(Ŀǰ֧�ֵ��ھ���)
				 * ս���������½��Զ����ܵ�С����UI
				 * */
				boolean s_2 = (bush.getPixelColor(1071,194).getRed()==255)||(new Color(255,255,238).equals(bush.getPixelColor(964, 177)))||
				(new Color(110,99,61).equals(bush.getPixelColor(987, 199)))||(new Color(240,204,153).equals(bush.getPixelColor(1295,834)));
				
				//0:��ֹ ��1���ƶ���2��ս����3��Ѻ��
			if(state ==2&&s_2) 
				{//ս��
				t_2++;
				System.out.println("״̬��ս������"+t_2+"�룡");
				}else if(s_2)
				{
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 2;
					System.out.println("״̬�����ս����=====");
					
					{//���Զ���˫��������
					//�ж��Ƿ��ƶ���ץ����������Ϊ��Ļ�仯���µ�ץ�����
					MyRobot.n_gui++;
					MyRobot.n_count++;
					System.out.println("��ǰ��"+n_gui+"ֻ��");
					
					if(flag_send == 1){//����ץ������
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, n_count);
					}
					//������˫������
					if(double_gui!=1&&n_gui==1) Task.getDouble(bush, n_gui,false);
					if(n_gui==double_gui) Task.getDouble(bush,n_gui,true);
					}
					
				}else if(state ==0 &&s_0)
				{//��ֹ
				t_0++;
				System.out.println("״̬����ֹ����"+t_0+"�룡");
				
					{//���Զ�ץ�������;
						//���δ����ĵ���������Ļ������������Ϸ����Ϊ����ڣ���ֹ����
						if(t_0==5)
						{
							Method.click2(bush, 972, 141);//˫������Ϸ�ϱ߿�
							Method.await(bush, 1, 2);
							Task.clearScreen(bush);
						}
						if(t_0>=8)//��ֹ��������8�룬�����½���ץ������
		        		{
							t_0 = 0;
		        			Task.zhuaGui(bush);
		        			MyRobot.n_gui = 0;//��n_guiֻ������
		        		}
					}
					
				}else if(s_0)
				{
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 0;
					System.out.println("״̬�������ֹ��=====");
				}else if(state== 3 &&s_3)
				{//Ѻ��
					t_3++;
					System.out.println("״̬��Ѻ�ڳ���"+t_3+"�룡");
				}else if(s_3){
					t_0=0; t_1=0; t_2=0; t_3 =0;
					state = 3;
					System.out.println("״̬�����Ѻ�ڣ�=====");
				}else if(state== 1 &&s_1)
				{//�ƶ�
				t_1++;
				System.out.println("״̬���ƶ�����"+t_1+"�룡");
				}else if(s_1){
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("״̬������ƶ���=====");
				}
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});
		
		//=============��ű������̡�=========================
		
		//=====ȫ��״̬��⣬����timer������Ϊȫ�ּ�ʱ������
		timer.schedule(state_control, 2000,1000);
		
		
//		System.exit(0);//�����ű�����
}
}
