package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import pkg1.Method;
//调用任何方法时要确认是否进行清理屏幕!

public class MyRobot {
	//参数定义
	static int state = 0,t_0=0,t_1=0,t_2=0,t_3;	//状态变量
	static Color c_pre1,c_pre2,c_pre3;	//状态检查辅助变量
	static int n_gui = 0; //	当前抓第几只鬼变量,状态辅助变量
	static int double_gui= 8;	//抓鬼开双轮数；
	static int n_count = 0;//总共抓鬼数量
	public static void main(String args []) throws AWTException
	{
		Robot bush = new Robot();
		Timer timer = new Timer();
		CheckThread ct = new CheckThread();
		 c_pre1 = bush.getPixelColor(1131,617);
		 c_pre2 = bush.getPixelColor(741,328);
		 c_pre3 = bush.getPixelColor(1085,336);
		
		 /*自动领双思路
		  * 每次接受抓鬼任务时n_gui归零，并调用冻结双倍函数；
		  * 每次进入战斗时n_gui加一，并检查当前n_gui是否大于需要抓鬼的轮数，
		  * 如最后两轮开双，则在n_gui==8时调用领双函数。
		 */
		Scanner reader = new Scanner(System.in);
		
		System.out.println("请输入抓鬼开双轮次：（默认为8）");
		
		while((double_gui = reader.nextInt())<=0)
		{
			System.out.println("请输入一个大于0的数！");
		}
		
		System.out.println("请输入当前抓鬼轮次：（默认为1）");
		
		while((n_gui = reader.nextInt())<=0)
		{
			System.out.println("请输入一个大于0的数！");
		}
		n_gui--;//因为会在进入战斗界面+1；
		
		reader.close();
		System.out.println("设置成功！将在每轮第"+double_gui+"只鬼时开双；");
		//全局状态监测
		TimerTask state_control = (new TimerTask(){
			@Override
	        public void run() {//判断坐标点	是否变化，三点有一点是不变的则是静止
				boolean s_3 =(new Color(155,103,53).equals(bush.getPixelColor(715,234)));
				boolean s_0 = ((c_pre1.equals(bush.getPixelColor(1131,617))||c_pre2.equals(bush.getPixelColor(741,328))||
						c_pre3.equals(bush.getPixelColor(1085,336))))&&(!s_3);
				boolean s_1 = (!(c_pre1.equals(bush.getPixelColor(1131,617))&&!c_pre2.equals(bush.getPixelColor(741,328))&&
						!c_pre3.equals(bush.getPixelColor(1085,336))));
				/*#战斗状态的判定：
				 * 天覆阵的右红点、
				 * 战斗回合数的数字颜色点、
				 * 数字边框颜色点(目前支持到第九轮)
				 * 战斗界面右下角自动技能的小人物UI
				 * */
				boolean s_2 = (bush.getPixelColor(1071,194).getRed()==255)||(new Color(255,255,238).equals(bush.getPixelColor(964, 177)))||
				(new Color(110,99,61).equals(bush.getPixelColor(987, 199)))||(new Color(240,204,153).equals(bush.getPixelColor(1295,834)));
				
				//0:静止 、1：移动、2：战斗、3：押镖
			if(state ==2&&s_2) 
				{//战斗
				t_2++;
				System.out.println("状态：战斗持续"+t_2+"秒！");
				}else if(s_2)
				{
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 2;
					System.out.println("状态变更：战斗！=====");
					
					{//★自动领双代码区；
					//判断是否移动后抓鬼，而不是因为屏幕变化导致的抓鬼错判
					MyRobot.n_gui++;
					MyRobot.n_count++;
					System.out.println("当前第"+n_gui+"只鬼！");
					
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, n_count);//发送抓鬼数量
					//调用领双函数；
					if(double_gui!=1&&n_gui==1) Task.getDouble(bush, n_gui,false);
					if(n_gui==double_gui) Task.getDouble(bush,n_gui,true);
					}
					
				}else if(state ==0 &&s_0)
				{//静止
				t_0++;
				System.out.println("状态：静止持续"+t_0+"秒！");
				
					{//★自动抓鬼代码区;
						//针对未处理的弹窗清理屏幕，并且修正游戏窗口为活动窗口，防止误判
						if(t_0==5)
						{
							Method.click2(bush, 972, 141);//双击击游戏上边框
							Method.await(bush, 1, 2);
							Task.clearScreen(bush);
						}
						if(t_0>=8)//静止持续超过8秒，则重新接受抓鬼任务
		        		{
							t_0 = 0;
		        			Task.zhuaGui(bush);
		        			MyRobot.n_gui = 0;//第n_gui只鬼清零
		        		}
					}
					
				}else if(s_0)
				{
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 0;
					System.out.println("状态变更：静止！=====");
				}else if(state== 3 &&s_3)
				{//押镖
					t_3++;
					System.out.println("状态：押镖持续"+t_3+"秒！");
				}else if(s_3){
					t_0=0; t_1=0; t_2=0; t_3 =0;
					state = 3;
					System.out.println("状态变更：押镖！=====");
				}else if(state== 1 &&s_1)
				{//移动
				t_1++;
				System.out.println("状态：移动持续"+t_1+"秒！");
				}else if(s_1){
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("状态变更：移动！=====");
				}
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});
		
		//=============★脚本主流程★=========================
		
		//=====全局状态监测，利用timer单独作为全局计时器对象
		timer.schedule(state_control, 2000,1000);
		
		
//		System.exit(0);//结束脚本运行
}
}
