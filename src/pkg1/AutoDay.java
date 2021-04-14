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
		//主要对象初始化；
		MyRobot.db = new DataBean();
		Robot robot = new Robot();
		Timer dayTimer = new Timer();
		
		//初始化点状态
		MyRobot.db.c_update(robot);
		
		
		
		//押镖计时器
		TimerTask YaBiao = (new TimerTask(){
			@Override
			public void run() {
				
				//首先利用辅助点进行状态判断
				MyRobot.db.s_update(robot);
				
				//检测挖宝按钮
				if(MyRobot.db.getnBiao()!=3&&new Color(129,101,76).equals(robot.getPixelColor(1232,636)))
				{
					MyRobot.db.setT_0(0);
					Method.click(robot, 1232,636, true);//开始押镖
					Method.checkClick(robot, new PBean(1036,589,150,116,62), true, true, 1000*3);
					MyRobot.db.setnBiao(MyRobot.db.getnBiao()+1);//押镖加一
				}else if(MyRobot.db.getnBiao()==3)
				{
					
					System.out.println("#结束日常---押镖===");
					System.out.println("#脚本终止!");
					this.cancel();
					System.exit(0);
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
		
		//秘境计时器
		TimerTask MiJing = (new TimerTask(){
			@Override
			public void run() {
				
				//首先利用辅助点进行状态判断
				MyRobot.db.s_update(robot);
				
				//判断是否需要领取礼包
				if(new Color(249,102,32).equals(robot.getPixelColor(1223,729)))
					{
						Method.click(robot, 1223,729, true);
						Method.click(robot, 1271,473,true);//点击下一关
						Method.click(robot, 1271,473,true);//点击下一关
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
					
					//新服活动窗口
					if(robot.getPixelColor(1404,281).getRed()==185)
				         Method.click(robot,1404,281,true);//[关闭]
					
					//判断是否需要点击进入战斗
					if(new Color(112,84,60).equals(robot.getPixelColor(1264,639)))
						Method.click(robot, 1264,639, true);
					
					//检测是否通关？
					if(MyRobot.db.getT_0()==5&&new Color(255,255,255).equals(robot.getPixelColor(1271,473)))
					{
						MyRobot.db.setT_0(0);
						System.out.println("#结束日常---秘境降妖===");
						this.cancel();
						Task.day_YaBiao(robot);
						new Timer().schedule(YaBiao, 3000,1000);
					}else if(MyRobot.db.getT_0()==3&&new Color(226,41,34).equals(robot.getPixelColor(510,678)))
					{//检测是否失败
						System.out.println("闯关失败：更换助战！");
						Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_Z);
						new CheckThread().check(new PBean(1399,263,204,0,0), true,1000*3);//助战界面
						
						Method.click(robot, 1354, 511, true);//第二套阵容
						
						Method.press(robot,KeyEvent.VK_ESCAPE);//关闭助战
						
						Method.click(robot, 1271,473,true);//点击下一关
						
					}else if(MyRobot.db.getT_0()==3&&new Color(0,255,0).equals(robot.getPixelColor(1372,476)))//到达最后一关
					{
						System.out.println("最后一关：更换助战！");
						Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_Z);
						new CheckThread().check(new PBean(1399,263,204,0,0), true,1000*3);//助战界面
						
						Method.click(robot, 1354, 511, true);//第二套阵容
						
						Method.press(robot,KeyEvent.VK_ESCAPE);//关闭助战
						
						Method.click(robot, 1271,473,true);//点击下一关
					}
					else if(MyRobot.db.getT_0()==3)
					{//检测是否需要点击下一关
						Method.click(robot, 1271,473,true);//点击下一关
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
		
		//挖宝计时器
		TimerTask WaBao = (new TimerTask(){
			@Override
			public void run() {
				
				//首先利用辅助点进行状态判断
				MyRobot.db.s_update(robot);
				
				//检测挖宝按钮
				if(new Color(108,49,10).equals(robot.getPixelColor(1290,794)))
				{
					MyRobot.db.setT_0(0);
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
						//静止超过10秒且宝图数不为0=》挖宝结束
						if(MyRobot.db.getT_0()==10&&MyRobot.db.getnBao()!=0)
						{
							System.out.println("自动挖宝结束===共挖"+MyRobot.db.getnBao()+"张藏宝图！");
							System.out.println("3秒后开始日常秘境");
							
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
		
	
		
		//======自动日常主流程==========================
		System.out.println("脚本运行，若干秒后自动开始日常...");
		Method.await(robot, 1, 3);
		Method.click(robot, 1022, 143, true);//活动窗口修正
		
		//日常=》宝图=》秘境=》押镖=》
		Task.day_BangPai(robot);
		Task.day_PetPray(robot);
		Task.day_Home(robot);
		Task.day_Chat(robot);
		
		
		//接受挖宝任务=》开始战斗；
		Task.getWaBao(robot);
		
		//启动状态监测=》静止5秒则=》调用使用宝图=》静止2秒=》检查宝图使用按钮=》静止5秒=》挖宝结束
		dayTimer.schedule(WaBao, 2000,1000);
	}
}
