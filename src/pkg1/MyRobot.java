package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import pkg1.Method;
//�����κβ���ǰ��Ӧȷ���Ƿ����������Ļ!

public class MyRobot {
	//#��������
	static DataBean db = new DataBean();
	static int state = 0,t_0=0,t_1=0,t_2=0,t_3;	//״̬����
	static Color c_pre1,c_pre2,c_pre3;	//״̬��鸨������
	public static void main(String args []) throws AWTException
	{
		//��Ҫ�����ʼ����
		Robot bush = new Robot();
		Timer timer = new Timer();
		CheckThread ct = new CheckThread();
		Scanner reader = new Scanner(System.in);
		
		//ͳ�����ݳ�ʼ����
		db.setCost_Double(0);
		db.setSum_Gui(-1);//������һֻ����Ϊץ��ʱ�䲻׼ȷ
		db.setSum_Time(0);
		 c_pre1 = bush.getPixelColor(1131,617);
		 c_pre2 = bush.getPixelColor(741,328);
		 c_pre3 = bush.getPixelColor(1085,336);
		
		 /*�Զ���˫˼·
		  * ÿ�ν���ץ������ʱn_gui���㣬�����ö���˫��������
		  * ÿ�ν���ս��ʱn_gui��һ������鵱ǰn_gui�Ƿ������Ҫץ���������
		  * ��������ֿ�˫������n_gui==8ʱ������˫������
		 */
		
		System.out.println("������ץ��˫�ִΣ���Ĭ��Ϊ8��");
		
		db.setStartDouble(reader.nextInt());
		
		System.out.println("�����뵱ǰץ���ִΣ���Ĭ��Ϊ1��");
		
		db.setN_Gui(reader.nextInt()-1);
		//��һ��Ϊ���ڽ���ս������+1��
		
		System.out.println("�Ƿ���ץ��������(1��0����)");
		
		db.setFlagSend(reader.nextInt()==1?true:false);
		
		System.out.println("���������ֹͣʱ�䣺����\"7:30\"");
		
		String stop_time = reader.next();
		/*Java���ַ�����Ŵ�0��ʼ��indexof()�����ַ����ֵ�λ�ã�
		 * substring(A,B)����Aλ�õ�Bλ��֮ǰһλ���Ӵ���
		*/
		db.setStop_h(Integer.parseInt(stop_time.substring(0,stop_time.indexOf(':'))));
		db.setStop_m(Integer.parseInt(stop_time.substring(stop_time.indexOf(':')+1,stop_time.length())));
		
		reader.close();
		System.out.println("���óɹ�������ÿ�ֵ�"+db.getStartDouble()+"ֻ��ʱ��˫,�ű�����"+db.getStop_h()+":"+db.getStop_m()+"ֹͣ���С�");
		
		//#ץ�����
		TimerTask turnToGuaji = (new TimerTask(){
			@Override
	        public void run() {
				
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
					{//#����ͳ�ƴ�����
					db.setN_Gui(db.getN_Gui()+1);
					db.setSum_Gui(db.getSum_Gui()+1);
					if(db.getN_Gui()>=db.getStartDouble())//��¼˫����������
						db.setCost_Double(db.getCost_Double()+4);
					}
					
					{//���Զ���˫��������
					//�ж��Ƿ��ƶ���ץ����������Ϊ��Ļ�仯���µ�ץ�����
					System.out.println("��ǰ��"+db.getN_Gui()+"ֻ��");
					
					if(db.isFlagSend()){//����ץ������
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, db.getSum_Gui());
					}
					//������˫������
					if(db.getStartDouble()!=1&&db.getN_Gui()==1) Task.getDouble(bush, db.getN_Gui(),false);
					if(db.getN_Gui()==db.getStartDouble()) Task.getDouble(bush,db.getN_Gui(),true);
					}
					
				}else if(state ==0 &&s_0)
				{//��ֹ
				t_0++;
				System.out.println("״̬����ֹ����"+t_0+"�룡");
				
					{//��ֹ���������;
						if(t_0==5)//��ֹ����5��
						{
							Method.click2(bush, 972, 141);//˫������Ϸ�ϱ߿�
							Method.await(bush, 1, 1.5);
							Task.guaJi(bush);
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
					db.setSum_Time(db.getSum_Time()+t_2);//��¼�ϴ�ץ��ʱ����
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("״̬������ƶ���=====");
				}
			//���¸�������Ϣ��
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});
			
		
		//#ȫ��״̬���
		TimerTask state_control = (new TimerTask(){
			@Override
	        public void run() {
				//�ж�ʱ��
				if((db.getStop_h() == Calendar.getInstance().get(Calendar.HOUR))&&(db.getStop_m() == Calendar.getInstance().get(Calendar.MINUTE)))
				{
					System.out.println("ץ��ű�����ֹͣ������ץ������󽫽���һ�������");
					timer.schedule(turnToGuaji,0,1000);
					this.cancel();
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
					{//#����ͳ�ƴ�����
					db.setN_Gui(db.getN_Gui()+1);
					db.setSum_Gui(db.getSum_Gui()+1);
					if(db.getN_Gui()>=db.getStartDouble())//��¼˫����������
						db.setCost_Double(db.getCost_Double()+4);
					}
					
					
					{//���Զ���˫��������
					//�ж��Ƿ��ƶ���ץ����������Ϊ��Ļ�仯���µ�ץ�����
					System.out.println("��ǰ��"+db.getN_Gui()+"ֻ��");
					
					if(db.isFlagSend()){//����ץ������
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, db.getSum_Gui());
					}
					//������˫������
					if(db.getStartDouble()!=1&&db.getN_Gui()==1) Task.getDouble(bush, db.getN_Gui(),false);
					if(db.getN_Gui()==db.getStartDouble()) Task.getDouble(bush,db.getN_Gui(),true);
					}
					
				}else if(state ==0 &&s_0)
				{//��ֹ
				t_0++;
				System.out.println("״̬����ֹ����"+t_0+"�룡");
				
					{//��ֹ���������;
						//���δ����ĵ���������Ļ������������Ϸ����Ϊ����ڣ���ֹ����
						
						if(t_0==5)//��ֹ����5�룬������Ļ�����ץ��
						{
							Method.click2(bush, 972, 141);//˫������Ϸ�ϱ߿�
							Method.await(bush, 1, 1.5);
							Task.clearScreen(bush);
						}
						else if(t_0==8)//��ֹ����8�룬�����½���ץ������
		        		{
		        			Task.zhuaGui(bush);
		        			db.setN_Gui(0);//��n_guiֻ������
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
					if(db.getSum_Gui()!=1)
					   db.setSum_Time(db.getSum_Time()+t_2);//��¼�ϴ�ץ��ʱ��,��������һֻ��
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("״̬������ƶ���=====");
				}
			//���¸�������Ϣ��
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});

		
		
		//=============��ű������̡�=========================
		
		//=====ȫ��״̬��⣬����timer������Ϊȫ�ּ�ʱ������
		timer.schedule(state_control, 2000,1000);
		
	}
}
