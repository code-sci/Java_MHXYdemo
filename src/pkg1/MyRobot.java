package pkg1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
//进行任何操作前都应确认是否进行清理屏幕!
import java.util.TimerTask;

public class MyRobot {
	//主要数据存储对象DataBean；
	static DataBean db ;
	public static void main(String args []) throws AWTException
	{
		//主要对象初始化；
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer Maintimer = new Timer();
		Scanner reader = new Scanner(System.in);
		
		//数据初始化；
		MyRobot.db.setCost_Double(0);
		MyRobot.db.setSum_Gui(-1);//跳过第一只鬼，因为抓鬼时间不准确
		MyRobot.db.setSum_Time(0);
		MyRobot.db.c_update(robot);
		MyRobot.db.setIfStop(false);
		
		System.out.println("请输入抓鬼开双轮次：（默认为8）");
		MyRobot.db.setStartDouble(reader.nextInt());
		System.out.println("请输入当前抓鬼轮次：（默认为1）");
		MyRobot.db.setN_Gui(reader.nextInt()-1);
		//减一因为会在进入战斗界面+1；
		System.out.println("是否发送抓鬼总数：(1发0不发)");
		MyRobot.db.setFlagSend(reader.nextInt()==1?true:false);
		System.out.println("请输入程序停止时间：例如\"7:30\"");
		String stop_time = reader.next();
	
		MyRobot.db.setStop_h(Integer.parseInt(stop_time.substring(0,stop_time.indexOf(':'))));
		MyRobot.db.setStop_m(Integer.parseInt(stop_time.substring(stop_time.indexOf(':')+1,stop_time.length())));
		
		reader.close();
		System.out.println("设置成功！将在每轮第"+MyRobot.db.getStartDouble()+"只鬼时开双,脚本将于"+MyRobot.db.getStop_h()+":"+MyRobot.db.getStop_m()+"停止运行。");
		
		/*#全局状态检测的独立计时器,其主要工作如下：
		 * @更新游戏状态；
		 * @更新游戏数据；
		 * @异常静止处理；(关闭弹窗、调用抓鬼)
		*/
		TimerTask stateMonitor = (new TimerTask(){
			@Override
			public void run() {
				//判断时间
				if(!MyRobot.db.isIfStop()&&(MyRobot.db.getStop_h() == Calendar.getInstance().get(Calendar.HOUR_OF_DAY))&&(MyRobot.db.getStop_m() == Calendar.getInstance().get(Calendar.MINUTE)))
				{
					System.out.println("抓鬼脚本即将停止，本轮抓鬼结束后将进入挂机场景；");
					MyRobot.db.setIfStop(true);
				}
				
				//首先利用辅助点进行状态判断
				MyRobot.db.s_update(robot);
				//状态更新
				if(MyRobot.db.getState() ==2&&MyRobot.db.isS_2()) 
				{//战斗
					MyRobot.db.setT_2(MyRobot.db.getT_2()+1);
					System.out.println("状态：战斗持续"+MyRobot.db.getT_2()+"秒！");
				}else if(MyRobot.db.isS_2())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(2);
					System.out.println("状态变更：战斗！=====");
					{//#数据统计代码区
						MyRobot.db.setN_Gui(MyRobot.db.getN_Gui()+1);
						MyRobot.db.setSum_Gui(MyRobot.db.getSum_Gui()+1);
						if(MyRobot.db.getN_Gui()>=MyRobot.db.getStartDouble())//记录双倍点数消耗
							MyRobot.db.setCost_Double(MyRobot.db.getCost_Double()+4);
					}
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//静止
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("状态：静止持续"+MyRobot.db.getT_0()+"秒！");
					
					{//静止处理代码区;
						//针对未处理的弹窗清理屏幕，并且修正游戏窗口为活动窗口，防止误判
						//静止持续5秒,并且已经达到停止时间；=>调用挂机；
						if(MyRobot.db.getT_0()==5&&MyRobot.db.isIfStop())
						{
							Method.click2(robot, 972, 141);//修正活动窗口；
							Method.await(robot, 1, 1.5);
							
							Task.guaJi(robot);
							this.cancel();//清理计时器任务；
						}//静止持续5秒，但未达到停止时间；=》清理屏幕；
						else if(MyRobot.db.getT_0()==5)
						{
							Method.click2(robot, 972, 141);//修正活动窗口；
							Method.await(robot, 1, 1.5);
							
							Task.clearScreen(robot);
						}//静止持续8秒，存在异常问题；=》手动接收抓鬼任务；
						else if(MyRobot.db.getT_0()==8)
						{
							Method.click2(robot, 972, 141);//修正活动窗口；
							Method.await(robot, 1, 1.5);
							
							Task.zhuaGui(robot);	
							MyRobot.db.setN_Gui(0);//第n_gui只鬼清零
						}
					}
					
				}else if(MyRobot.db.isS_0())
				{
					MyRobot.db.t_clear();
					MyRobot.db.setState(0);
					System.out.println("状态变更：静止！=====");
				}else if(MyRobot.db.getState()== 3 &&MyRobot.db.isS_3())
				{//押镖
					MyRobot.db.setT_3(MyRobot.db.getT_3()+1);
					System.out.println("状态：押镖持续"+MyRobot.db.getT_3()+"秒！");
				}else if(MyRobot.db.isS_3()){
					MyRobot.db.t_clear();
					MyRobot.db.setState(3);
					System.out.println("状态变更：押镖！=====");
				}else if(MyRobot.db.getState()== 1 &&MyRobot.db.isS_1())
				{//移动			
					MyRobot.db.setT_1(MyRobot.db.getT_1()+1);
					System.out.println("状态：移动持续"+MyRobot.db.getT_1()+"秒！");
				}else if(MyRobot.db.isS_1()){
					if(MyRobot.db.getSum_Gui()!=1)
						MyRobot.db.setSum_Time(MyRobot.db.getSum_Time()+MyRobot.db.getT_2());//记录上次抓鬼时长,并跳过第一只鬼；
					MyRobot.db.t_clear();
					MyRobot.db.t_clear();
					System.out.println("状态变更：移动！=====");
				}
				
				//辅助点更新
				MyRobot.db.c_update(robot);
			}
		});
		
		
		//=============★脚本主流程★=========================
		
		
		/*#全局状态监测
		 * 虽然同一Timer可以同时运行不同TimerTask；
		 * 但为了避免意料之外的线程问题，还是采用不同Timer运行不同TimerTask的方式
		*/
		Maintimer.schedule(stateMonitor, 2000,1000);
		
	}
}
