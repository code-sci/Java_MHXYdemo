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
		//主要对象初始化；
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer WaBaotimer = new Timer();
		CheckThread ct = new CheckThread();
		
		//初始化点状态
		MyRobot.db.c_update(robot);
		
		TimerTask stateMonitor = (new TimerTask(){
			@Override
			public void run() {
				
				//首先利用辅助点进行状态判断
				MyRobot.db.s_update(robot);
				
				//检测挖宝按钮
				if(new Color(108,49,10).equals(robot.getPixelColor(1290,794)))
				{
					Method.click(robot, 1290, 794, true);
					MyRobot.db.setnBao(MyRobot.db.getnBao()+1);//挖宝计数+1
				}
				
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
					
				}else if(MyRobot.db.getState() ==0 &&MyRobot.db.isS_0())
				{//静止
					MyRobot.db.setT_0(MyRobot.db.getT_0()+1);
					System.out.println("状态：静止持续"+MyRobot.db.getT_0()+"秒！");
					{//静止处理代码区;
						//静止持续5秒且宝图数为0=》战斗结束
						if(MyRobot.db.getT_0()==5&&MyRobot.db.getnBao()==0)
						{
							Task.useWaBao(robot);
						}
						//静止超过5秒且宝图数不为0=》挖宝结束
						if(MyRobot.db.getT_0()==5&&MyRobot.db.getnBao()!=0)
						{
							System.out.println("自动挖宝结束===共挖"+MyRobot.db.getnBao()+"张藏宝图！");
							this.cancel();
							System.out.println("脚本将在5秒后自动关闭！");
							Method.await(robot, 5, 5);
							System.exit(0);
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
					MyRobot.db.t_clear();
					MyRobot.db.setState(1);
					System.out.println("状态变更：移动！=====");
				}
				
				//辅助点更新
				MyRobot.db.c_update(robot);
			} 
		});
		
		//======自动打图挖图主流程==========
		//接受挖宝任务=》开始战斗；
		System.out.println("3秒后将自动进行藏宝图任务的接受与藏宝图挖掘\n请保持游戏窗口为活动窗口...");
		Method.await(robot, 3, 3);
		Task.getWaBao(robot);
		//启动状态监测=》静止5秒则=》调用使用宝图=》静止2秒=》检查宝图使用按钮=》静止5秒=》挖宝结束
		WaBaotimer.schedule(stateMonitor, 2000,1000);
	}
}
