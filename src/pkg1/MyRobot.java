package pkg1;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import pkg1.Method;
//进行任何操作前都应确认是否进行清理屏幕!

public class MyRobot {
	//#参数定义
	static DataBean db = new DataBean();
	static int state = 0,t_0=0,t_1=0,t_2=0,t_3;	//状态变量
	static Color c_pre1,c_pre2,c_pre3;	//状态检查辅助变量
	public static void main(String args []) throws AWTException
	{
		//主要对象初始化；
		Robot bush = new Robot();
		Timer timer = new Timer();
		CheckThread ct = new CheckThread();
		Scanner reader = new Scanner(System.in);
		
		//统计数据初始化；
		db.setCost_Double(0);
		db.setSum_Gui(-1);//跳过第一只鬼，因为抓鬼时间不准确
		db.setSum_Time(0);
		 c_pre1 = bush.getPixelColor(1131,617);
		 c_pre2 = bush.getPixelColor(741,328);
		 c_pre3 = bush.getPixelColor(1085,336);
		
		 /*自动领双思路
		  * 每次接受抓鬼任务时n_gui归零，并调用冻结双倍函数；
		  * 每次进入战斗时n_gui加一，并检查当前n_gui是否大于需要抓鬼的轮数，
		  * 如最后两轮开双，则在n_gui==8时调用领双函数。
		 */
		
		System.out.println("请输入抓鬼开双轮次：（默认为8）");
		
		db.setStartDouble(reader.nextInt());
		
		System.out.println("请输入当前抓鬼轮次：（默认为1）");
		
		db.setN_Gui(reader.nextInt()-1);
		//减一因为会在进入战斗界面+1；
		
		System.out.println("是否发送抓鬼总数：(1发0不发)");
		
		db.setFlagSend(reader.nextInt()==1?true:false);
		
		System.out.println("请输入程序停止时间：例如\"7:30\"");
		
		String stop_time = reader.next();
		/*Java中字符串序号从0开始，indexof()返回字符出现的位置；
		 * substring(A,B)返回A位置到B位置之前一位的子串；
		*/
		db.setStop_h(Integer.parseInt(stop_time.substring(0,stop_time.indexOf(':'))));
		db.setStop_m(Integer.parseInt(stop_time.substring(stop_time.indexOf(':')+1,stop_time.length())));
		
		reader.close();
		System.out.println("设置成功！将在每轮第"+db.getStartDouble()+"只鬼时开双,脚本将于"+db.getStop_h()+":"+db.getStop_m()+"停止运行。");
		
		//#抓鬼结束
		TimerTask turnToGuaji = (new TimerTask(){
			@Override
	        public void run() {
				
				//判断坐标点	是否变化，三点有一点是不变的则是静止
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
					{//#数据统计代码区
					db.setN_Gui(db.getN_Gui()+1);
					db.setSum_Gui(db.getSum_Gui()+1);
					if(db.getN_Gui()>=db.getStartDouble())//记录双倍点数消耗
						db.setCost_Double(db.getCost_Double()+4);
					}
					
					{//★自动领双代码区；
					//判断是否移动后抓鬼，而不是因为屏幕变化导致的抓鬼错判
					System.out.println("当前第"+db.getN_Gui()+"只鬼！");
					
					if(db.isFlagSend()){//发送抓鬼数量
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, db.getSum_Gui());
					}
					//调用领双函数；
					if(db.getStartDouble()!=1&&db.getN_Gui()==1) Task.getDouble(bush, db.getN_Gui(),false);
					if(db.getN_Gui()==db.getStartDouble()) Task.getDouble(bush,db.getN_Gui(),true);
					}
					
				}else if(state ==0 &&s_0)
				{//静止
				t_0++;
				System.out.println("状态：静止持续"+t_0+"秒！");
				
					{//静止处理代码区;
						if(t_0==5)//静止持续5秒
						{
							Method.click2(bush, 972, 141);//双击击游戏上边框
							Method.await(bush, 1, 1.5);
							Task.guaJi(bush);
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
					db.setSum_Time(db.getSum_Time()+t_2);//记录上次抓鬼时长；
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("状态变更：移动！=====");
				}
			//更新辅助点信息；
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});
			
		
		//#全局状态监测
		TimerTask state_control = (new TimerTask(){
			@Override
	        public void run() {
				//判断时间
				if((db.getStop_h() == Calendar.getInstance().get(Calendar.HOUR))&&(db.getStop_m() == Calendar.getInstance().get(Calendar.MINUTE)))
				{
					System.out.println("抓鬼脚本即将停止，本轮抓鬼结束后将进入挂机场景；");
					timer.schedule(turnToGuaji,0,1000);
					this.cancel();
				}
				
				//判断坐标点	是否变化，三点有一点是不变的则是静止
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
					{//#数据统计代码区
					db.setN_Gui(db.getN_Gui()+1);
					db.setSum_Gui(db.getSum_Gui()+1);
					if(db.getN_Gui()>=db.getStartDouble())//记录双倍点数消耗
						db.setCost_Double(db.getCost_Double()+4);
					}
					
					
					{//★自动领双代码区；
					//判断是否移动后抓鬼，而不是因为屏幕变化导致的抓鬼错判
					System.out.println("当前第"+db.getN_Gui()+"只鬼！");
					
					if(db.isFlagSend()){//发送抓鬼数量
					Method.await(bush, 1, 2);
					ct.check(new PBean(543,680,240,230,217),true,3000);
					Task.sendCount(bush, db.getSum_Gui());
					}
					//调用领双函数；
					if(db.getStartDouble()!=1&&db.getN_Gui()==1) Task.getDouble(bush, db.getN_Gui(),false);
					if(db.getN_Gui()==db.getStartDouble()) Task.getDouble(bush,db.getN_Gui(),true);
					}
					
				}else if(state ==0 &&s_0)
				{//静止
				t_0++;
				System.out.println("状态：静止持续"+t_0+"秒！");
				
					{//静止处理代码区;
						//针对未处理的弹窗清理屏幕，并且修正游戏窗口为活动窗口，防止误判
						
						if(t_0==5)//静止持续5秒，清理屏幕或调用抓鬼；
						{
							Method.click2(bush, 972, 141);//双击击游戏上边框
							Method.await(bush, 1, 1.5);
							Task.clearScreen(bush);
						}
						else if(t_0==8)//静止持续8秒，则重新接受抓鬼任务
		        		{
		        			Task.zhuaGui(bush);
		        			db.setN_Gui(0);//第n_gui只鬼清零
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
					if(db.getSum_Gui()!=1)
					   db.setSum_Time(db.getSum_Time()+t_2);//记录上次抓鬼时长,并跳过第一只鬼；
					t_0=0; t_1=0; t_2=0;t_3 =0;
					state = 1;
					System.out.println("状态变更：移动！=====");
				}
			//更新辅助点信息；
			c_pre1 = bush.getPixelColor(1131,617);
			c_pre2 = bush.getPixelColor(741,328);
			c_pre3 = bush.getPixelColor(1085,336);
			
			}
		});

		
		
		//=============★脚本主流程★=========================
		
		//=====全局状态监测，利用timer单独作为全局计时器对象
		timer.schedule(state_control, 2000,1000);
		
	}
}
