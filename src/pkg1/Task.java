package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Task {
	static Robot robot ;
	static CheckThread ct;
	
	//接受挖宝任务函数
	static void getWaBao(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("===开始接受宝图任务：");
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//大地图
		ct.check(new PBean(1398,277,231,68,44),true,3000);//大地图关闭按钮
		
		Method.click(robot,926,600,true);	//大地图---长安城
		ct.check(new PBean(1398,277,231,68,44),false,3000);//大地图关闭按钮
		
		Method.press(robot,KeyEvent.VK_TAB);	//按键---小地图
		ct.check(new PBean(1341,301,191,21,0),true,3000);//小地图关闭按钮
		
		Method.click(robot,1155,566,true);	//小地图---店小二
		
		ct.check(new PBean(1263,695,136,109,83), true, 1000*15);//检查[宝图任务]按钮
		
		Method.click(robot, 1263, 695, true);
		Method.press(robot, KeyEvent.VK_ESCAPE);//清理屏幕
		
		Method.await(robot, 0.5, 1);
		//任务追踪
		if(new Color(251,242,28).equals(robot.getPixelColor(1288, 330)))
			Method.click2(robot, 1288, 330);
		else if(new Color(251,242,28).equals(robot.getPixelColor(1288, 416)))
			Method.click2(robot, 1288, 416);
		else
			Method.click2(robot, 1288, 502);
		
		System.out.println("===宝图任务接受完毕，开始宝图战斗：");
		MyRobot.db.setnBao(0);
	}
	
	static void useWaBao(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("===开始使用藏宝图进行挖宝：");
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_E);	//打开背包
		ct.check(new PBean(1069,795,168,108,53),true,3000);//检查[背包界面]
		Method.click(robot, 1258, 790, true);//整理背包
		
		//进行宝图检查
		Color c_Bao = new Color(102,255,204);
		int x = 1000;
		int y = 400;
		int i,j;
		int x_Bao=0,y_Bao=0;
		for(i=1;y<800;y+=80,i++)
		{
			for(j=1,x=1000;x<1400;x+=80,j++)
			{
				if(c_Bao.equals(robot.getPixelColor(x, y)))
				{
					System.out.println(i+"行"+j+"列发现藏宝图一张！");
					x_Bao=x ; y_Bao=y;
				}
			}
		}
		
		if(x_Bao!=0&&y_Bao!=0)
		{
			System.out.println("====开始挖宝！");
			Method.click(robot, x_Bao, y_Bao,true);//单击藏宝图位置
			ct.check(new PBean(847,622,148,93,37), true, 3000);
			Method.click(robot, 847, 622, true);
		}
		
		//===>自动移动到宝图位置，然后检查挖宝按钮
	}
		
	/*#记录统计本次脚本数据：
	 * @抓鬼总数；
	 * @开双抓鬼数量；
	 * @消耗双倍点数；
	 * @抓鬼平均耗时；
	*/
	static void noteStatis()
	{
		float avg_time = (float)MyRobot.db.getSum_Time()/MyRobot.db.getSum_Gui();//保留小数处理；
		avg_time = Float.parseFloat(new DecimalFormat("0.00").format(avg_time));
		String fileName="F:\\桌面\\position.txt";
		Calendar c = Calendar.getInstance();
		int m = c.get(Calendar.MONTH)+1;//从0开始
		int d = c.get(Calendar.DAY_OF_MONTH);
		int h = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		Method.writeToFile(fileName,"\n#本次脚本统计数据：(截止"+m+"月"+d+"日"+h+":"+min+")");
		Method.writeToFile(fileName,"设定停止时间："+MyRobot.db.getStop_h()+":"+MyRobot.db.getStop_m());
		Method.writeToFile(fileName,"抓鬼总数："+MyRobot.db.getSum_Gui());
		Method.writeToFile(fileName,"消耗双倍点数："+MyRobot.db.getCost_Double());
		Method.writeToFile(fileName,"抓鬼平均耗时："+avg_time+"s(仅供参考)");
	}
	
	//接受抓鬼任务并处理队伍异常
	static void getZhuagui(Robot robot)
	{
		CheckThread ct = new CheckThread();
		ct.check(new PBean(1287,521,85,57,35),true,8000);//抓鬼任务“鬼”字
		
		Method.click(robot,1219,515,true);	//接受[抓鬼任务]
		Method.await(robot,2,3);	
		
		if(new Color(227,75,55).equals(robot.getPixelColor(900, 502)))
		{
			System.out.println("队伍存在异常，进行处理！");
			Method.click(robot,1035,608, true);//确定调整队伍=》打开队伍界面
			ct.check(new PBean(1313,320,235,190,95), true, 5000);//检查[队伍界面]
			Color c_Have = new Color(240,227,211);
			
			//队伍人数处理
		
			while(!c_Have.equals(robot.getPixelColor(833,738)))//p2异常
			{
				System.out.println("P2异常!");
				Method.click(robot, 833, 738, true);//点击[队员位置]
				ct.check(new PBean(1050,539,85,57,35), true, 3000);//检查[请离按钮]
				Method.click(robot, 1050, 540, true);//点击[请离]；
				
				Task.getZhuagui_team(robot, ct);
				
				
			}
			while(!c_Have.equals(robot.getPixelColor(1003,738)))//p3异常
			{
				System.out.println("P3异常!");
				Method.click(robot, 1003,720, true);//点击[队员位置]
				ct.check(new PBean(1221,539,85,57,35), true, 3000);//检查[请离按钮]
				Method.click(robot,1221,539, true);//点击[请离]；
				
				Task.getZhuagui_team(robot, ct);
			}
			while(!c_Have.equals(robot.getPixelColor(1172,738)))//p4异常
			{
				System.out.println("P4异常!");
				Method.click(robot, 1172,720, true);//点击[队员位置]
				ct.check(new PBean(941,539,85,57,35), true, 3000);//检查[请离按钮]
				Method.click(robot, 941,539, true);//点击[请离]；
				
				Task.getZhuagui_team(robot, ct);
			}
			while(!c_Have.equals(robot.getPixelColor(1347,738)))//p5异常
			{
				System.out.println("P5异常!");
				Method.click(robot, 1347,720, true);//点击[队员位置]
				ct.check(new PBean(1113,539,85,57,35), true, 3000);//检查[请离按钮]
				Method.click(robot, 1113,539, true);//点击[请离]；
				
				Task.getZhuagui_team(robot, ct);
			}
			
			Method.press(robot, KeyEvent.VK_ESCAPE);//关闭队伍界面；
			
			System.out.println("队伍处理完毕！继续抓鬼");
		}
		
		Method.press(robot,KeyEvent.VK_ESCAPE);	//清理钟馗对话！
		Method.await(robot,0.5,0.6);
		
		//任务追踪界面
		if(!(new Color(197,227,167).equals(robot.getPixelColor(1267,282))))
			Method.click(robot, 1267,282, true);
		ct.check(new PBean(1267,282,197,227,167), true, 3000);
		
		
		Method.press(robot,KeyEvent.VK_ESCAPE);
		//抓鬼任务有时候位置不定
		if(robot.getPixelColor(1455,338).equals(new Color(117,62,24)))
			Method.click(robot,1455,338,true);
		else
			Method.click(robot,1455,450,true);
		Method.await(robot,1,1.5);
	}
	
	//抓鬼组队处理
	static void getZhuagui_team(Robot robot,CheckThread ct)
	{
		Method.await(robot, 0.5, 1);//请离后延迟
		Method.click(robot, 1149,319, true);//点击[组队目标]
		ct.check(new PBean(1069,806,108,49,10),true,3000);//检查[组队目标]界面
		if(new Color(185,180,173).equals(robot.getPixelColor(1079,620)))//如果目标为空
		{
			Method.setSysClipboardText("通宵侠义鬼来稳定暴力");
			Method.click(robot, 1079,620, true);
			ct.check(new PBean(1079,620,185,180,173),false,2000);//检查[目标输入框]
			Method.press2(robot, KeyEvent.VK_CONTROL, KeyEvent.VK_V);//粘贴目标内容
		}
		Method.click(robot, 1069,806, true);//点击[确定]=》开始匹配
		ct.check(new PBean(1252,795,237,186,87), true,3000);//[一键喊话]
		Method.click(robot, 1252,795, true);
		ct.check(new PBean(1261,578,108,49,10), true,3000);//[当前频道]
		Method.click(robot,1261,578, true);
		
		Method.click(robot, 1252,795, true);//[一键喊话]
		ct.check(new PBean(1256,501,192,163,138), true,3000);//[帮派频道]
		Method.click(robot,1256,501, true);
		
		ct.check(new PBean(1347,738,240,227,211),true, 1000*30);//等待30秒组人
		
	}
	
	//#进入挂机场景挂机
	static void guaJi(Robot robot)
	{
		CheckThread ct = new CheckThread();
		System.out.println("=======开始进入挂机场景=======");
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//判断继续抓鬼弹窗字
		 {
			 Method.click(robot, 855,588,true);//[取消]
			 ct.check(new PBean(1108,519,154,110,66), false, 3000);//等待【弹窗】关闭
		 }
		 //打开挂机界面
		 Method.await(robot, 0.5, 1);
		 Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_G);
		 ct.check(new PBean(1400,264,207,0,0),true,5000);//等待【挂机界面关闭按钮】出现
		 
		 Method.await(robot, 0.5, 1);
		 //冻结已领取的双倍点数
		 Method.click(robot, 1040, 793, true);
 		 System.out.println("冻结双倍点数成功！");
 		
 		Method.await(robot, 0.5, 1);
 		 //检测是否打开自动战斗
 		 if(new Color(206,166,112).equals(robot.getPixelColor(901,717)))
 		 {
 			 Method.await(robot, 0.5, 0.8);
 			 Method.click(robot, 901, 717, true);
 			 System.out.println("打开自动战斗成功！");
 		 }
 		 
 		Method.await(robot, 0.5, 1);
 		//点击挂机场景跳转
		 Method.click(robot, 845,320, true);
		 ct.check(new PBean(1400,264,207,0,0),false,5000);//等待【挂机界面关闭按钮】出现
		 
		 
		 Method.await(robot, 0.5, 1);
		 //点击原地挂机（以防万一）
		 Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_G);
		 ct.check(new PBean(1400,264,207,0,0),true,5000);//等待【挂机界面关闭按钮】出现
		 Method.click(robot, 660, 717, true);
		 
		 Method.await(robot, 0.5, 1);
		 System.out.println("脚本数据统计中...");
		 Task.noteStatis();
		 
		 //清理
		 
		 Method.await(robot, 0.5, 1);
		 System.out.println("=======进行场景挂机，脚本终止运行！=======");
		//终止脚本运行；
		 System.exit(0);
	}
	
	
	//#弹窗异常处理
	static void clearScreen(Robot robot)
	{
		//界面-挂机界面
		if(new Color(207,0,0).equals(robot.getPixelColor(1400,264)))
			Method.click(robot, 1400, 264, true);
		
		//弹窗-新服活动
		 if(robot.getPixelColor(1404,281).getRed()==185)
	         Method.click(robot,1404,281,true);//[关闭]
		 
		 //弹窗--是否领双?
		 if(new Color(142,94,45).equals(robot.getPixelColor(1100,515)))
			 Method.click(robot, 855,588,true);//[取消]
		 
		 //弹窗--三界奇缘
		 if(new Color(151,107,61).equals(robot.getPixelColor(855,511)))
			 Method.click(robot, 855,588,true);//[取消]
		 
		 //弹窗--是否继续抓鬼?
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//判断弹窗字
		 {
			System.out.println("=======开始[弹窗]接受抓鬼任务=======");
				
			 Method.click(robot, 1051,595,true);//点击[确定]继续抓鬼--->跳转钟馗对话
			 
			 
			 Task.getZhuagui(robot);//调用接受抓鬼任务
			 
				System.out.println("=======[弹窗]接收抓鬼任务完毕！=======");
				
				 MyRobot.db.setN_Gui(0);//第n_gui只鬼清零
		 }
		 
		 	//清理屏幕
	    	Method.await(robot,1,1.3);
	    	Method.press(robot,KeyEvent.VK_ESCAPE);
	    	Method.await(robot,0.5,0.6);
	    	Method.press(robot,KeyEvent.VK_ESCAPE);
	}
	
	//#领取或冻结双倍点数,@times 当前第几只鬼
	static void getDouble(Robot robot, int times,boolean flag)
	{
		CheckThread ct = new CheckThread();
		//活动窗口修正
		Method.click2(robot, 972, 141);//双击击游戏上边框
		Method.await(robot, 1, 2);
		//清理屏幕
		Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
		
    	Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_G);
    	ct.check(new PBean(1400,264,207,0,0),true,3000);//挂机界面关闭按钮
    	
    	if(!flag)//冻结
    	{
    		Method.click(robot, 1040, 793, true);
    		System.out.println("冻结双倍点数成功！");
    	}else //领取
    	{
    		Method.click(robot, 1299, 793, true);
    		Method.await(robot,1,1.8);	
    		System.out.println("领取双倍点数成功！");
    	}
   
    	
    	//清理屏幕
    	Method.await(robot,1,1.3);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
	}
	
	//#接受抓鬼任务函数
	static void zhuaGui(Robot robot)
	{
		CheckThread ct = new CheckThread();
		//清理屏幕
		Method.press(robot,KeyEvent.VK_ESCAPE);	
		Method.await(robot,0.5,0.6);
		Method.press(robot,KeyEvent.VK_ESCAPE);
		Method.await(robot,0.5,0.6);
		
		
		System.out.println("=======开始[手动]接受抓鬼任务=======");
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//大地图
		ct.check(new PBean(1398,277,231,68,44),true,3000);//大地图关闭按钮
		
		Method.click(robot,926,600,true);	//大地图---长安城
		ct.check(new PBean(1398,277,231,68,44),false,3000);//大地图关闭按钮
		
		Method.press(robot,KeyEvent.VK_TAB);	//按键---小地图
		ct.check(new PBean(1341,301,191,21,0),true,3000);//小地图关闭按钮
		
		Method.click(robot,762,598,true);	//小地图---钟馗
		
		Task.getZhuagui(robot);
		
		
		
		System.out.println("=======[手动]抓鬼任务接受完毕！=======");
		
	}
	
	//#抓鬼数量发送函数
	static void sendCount(Robot robot,int k)
	{
		System.out.println("===发送抓鬼总数:"+MyRobot.db.getSum_Gui()+"===");
		CheckThread ct = new CheckThread();
		
		Method.click(robot, 563,707,true);//左下角[人物头像]
		ct.check(new PBean(609,265,192,187,180), true, 4000);
		
		Method.click(robot,609,265,true);//[输入框]
		Method.await(robot, 0.5, 0.8);
		
		Task.typeK(robot, k);
		
		
		Method.press(robot, KeyEvent.VK_ENTER);//发送
		
		Method.await(robot, 0.5, 0.8);
		Method.click(robot,491,208,true);//关闭[输入框]
		
		
	}
	
	//#发送抓鬼数量 的辅助递归函数
	static void typeK(Robot robot,int k)
	{
		if(k>0)
		{	
			int b = k/10;//每次去掉一个低位
			Task.typeK(robot,b);//进入低位的递归
//			System.out.println(k%10);//输出高位的余数
			Method.press(robot,KeyEvent.VK_0+(k%10));
			Method.await(robot, 0.5, 0.8);
		}else
			return;
	}
	
	
}
