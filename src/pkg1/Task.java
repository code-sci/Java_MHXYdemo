package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Task {
	
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
		
		Method.drag(robot, 1294, 352, 1294, 494);
		Method.await(robot, 1, 2);
		if(new Color(251,242,28).equals(robot.getPixelColor(1288, 330)))
		{
			Method.click2(robot, 1288, 330);
			System.out.println("===宝图任务接受完毕，开始宝图战斗：");	
		}
		else if(new Color(251,242,28).equals(robot.getPixelColor(1288, 416)))
		{
			Method.click2(robot, 1288, 416);
			System.out.println("===宝图任务接受完毕，开始宝图战斗：");	
		}
		else if(new Color(251,242,28).equals(robot.getPixelColor(1288, 502)))
		{
			Method.click2(robot, 1288, 502);
			System.out.println("===宝图任务接受完毕，开始宝图战斗：");	
		}
		else 
			System.out.println("未检测到宝图任务！开始检查背包是否有藏宝图：");
			
		MyRobot.db.setnBao(0);
	}
	
	//使用宝图函数
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
		}else
			{
			MyRobot.db.setnBao(-1);
			}
		Method.press(robot,KeyEvent.VK_ESCAPE);
		Method.press(robot,KeyEvent.VK_ESCAPE);
		
		//===>自动移动到宝图位置，然后检查挖宝按钮
	}
		
	
	//日常--帮派签到
	static void day_BangPai(Robot robot)
	{
		System.out.println("===开始日常---帮派签到===");
		CheckThread ct = new CheckThread();
		
		Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_B);//帮派界面
		Method.checkClick(robot,new PBean(1410,607,146,107,72), true, true,1000*5);//点击福利
		
		ct.check(new PBean(597,318,128,86,61), true, 3000);//检查签到按钮
		
		if(!new Color(170,167,160).equals(robot.getPixelColor(1321, 347)))//判断是否签到
			Method.click(robot, 1321, 347, true);
		

		//屏幕清理
		Method.seeHP(robot);
		System.out.println("#结束日常---帮派签到===");
	}
	
	//日常--宠物祈福
	static void day_PetPray(Robot robot)
	{
		System.out.println("===开始日常---宠物祈福===");
		CheckThread ct = new CheckThread();
		
		Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_D);//福利界面
		//打开刮刮乐
		Method.checkClick(robot, new PBean(1228,787,234,173,80), true,true, 1000*5);
		//祈福庭院
		Method.checkClick(robot, new PBean(591,607,252,233,126), true,true, 1000*3);
		//祈福按钮
		Method.checkClick(robot,new PBean(1311,578,243,215,179), true,true, 1000*5);
		//宝宝选择界面
		Method.checkClick(robot,new PBean(802,399,248,225,187), true,true, 1000*5);
		
		Method.await(robot, 0.8, 1.2);//祈福等待
		Method.click(robot, 955, 765, true);//确认祈福
		Method.await(robot, 5, 8);//祈福等待
		
		//屏幕清理
		Method.seeHP(robot);
		System.out.println("#结束日常---宠物祈福===");
	}
	
	
	//日常--家园打扫休息
	static void day_Home(Robot robot)
	{
		System.out.println("===开始日常---家园休息打扫===");
		CheckThread ct = new CheckThread();
		
		//家园界面
		Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_N);
		Method.checkClick(robot,new PBean(1345,670,113,121,55), true,true, 1000*5);
		
		//打理
		Method.checkClick(robot,new PBean(1286,876,242,201,81), true,true, 1000*5);
		
		//大扫除
		Method.checkClick(robot,new PBean(1197,529,237,192,96), true,true, 1000*5);
		
		//确定大扫除
		Method.checkClick(robot, new PBean(1054,626,72,48,30),true, true, 3000);
		
		//房间
		Method.checkClick(robot, new PBean(1193,338,156,102,42),true, true, 3000);
		
		//卧室休息
		Method.checkClick(robot, new PBean(609,419,159,113,76),true, true, 3000);

		//检查休息按钮后点击
		ct.check(new PBean(1050,792,236,181,85), true, 5000);
		for(int i = 0;i<4;i++)
		{
			Method.await(robot, 0.3, 0.5);
			Method.click(robot, 1050, 792, true);
		}

		//屏幕清理
		Method.seeHP(robot);
		System.out.println("#结束日常---家园休息打扫===");
	}
	
	//日常---洛阳风月录
	static void day_Chat(Robot robot)
	{
		System.out.println("===开始日常---洛阳风月录===");
		CheckThread ct = new CheckThread();
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_E);	//打开背包
		ct.check(new PBean(1069,795,168,108,53),true,3000);//检查[背包界面]
		
//		if(!new Color(245,233,215).equals(robot.getPixelColor(969, 364)))
//			{
//				Method.drag(robot, 1022,389, 1022, 603);//背包拉到最上方
//				ct.check(new PBean(969, 364,245,233,215), true,1000*3);
//			}
		
		Method.click(robot, 1258, 790, true);//整理背包
		
		//进行洛阳风月录检查
		Color c_Book = new Color(255,170,170);
		int x = 995;
		int y = 380;
		int i,j;
		int x_Book=0,y_Book=0;
		for(i=1;y<800;y+=80,i++)
		{
			for(j=1,x=995;x<1400;x+=80,j++)
			{
				if(c_Book.equals(robot.getPixelColor(x, y)))
				{
					System.out.println(i+"行"+j+"列发现洛阳风月录！");
					x_Book=x ; y_Book=y;
				}
			}
		}
		
		//===============第一个NPC===================
		//调用对话
		if(x_Book==0&&y_Book==0)
			{
				Method.seeHP(robot);
				Task.day_GetChat(robot);
			}
		else
			{
				Method.click2(robot,x_Book,y_Book);
				Method.click2(robot,x_Book,y_Book);
			}
		
		Method.checkClick(robot, new PBean(761,463,242,225,204), true, true, 1000*5);//NPC1
		
		//屏幕清理
		Method.seeHP(robot);
				
		//和他聊天
		Method.checkClick(robot, new PBean(1208,577,242,215,180), true, true, 1000*10);
		
		//内容选择
		Method.await(robot, 1, 2);
		Method.checkClick(robot, new PBean(1196,692,242,215,184), true, true, 1000*10);
		
		//聊天点击
		Method.await(robot, 1, 2);
		while(!new Color(225,75,35).equals(robot.getPixelColor(1346, 167)))
		{
			Method.click(robot,1258,636, true);
			Method.await(robot, 0.5, 0.8);
		}
		
		
		
		//===============第二个NPC===================
		//调用对话
		if(x_Book==0&&y_Book==0)
			Task.day_GetChat(robot);
		else
		{
			Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_E);	//打开背包
			ct.check(new PBean(1069,795,168,108,53),true,3000);//检查[背包界面]
			Method.click(robot, 1258, 790, true);//整理背包
				Method.click2(robot,x_Book,y_Book);
				Method.click2(robot,x_Book,y_Book);
		}
		//NPC2
		Method.checkClick(robot, new PBean(966,462,242,225,204), true, true, 1000*5);
		
		//屏幕清理
		Method.seeHP(robot);
		
		//和他聊天
		Method.checkClick(robot, new PBean(1208,577,242,215,180), true, true, 1000*10);
		
		//内容选择
		Method.await(robot, 1, 2);
		Method.checkClick(robot, new PBean(1196,692,242,215,184), true, true, 1000*10);
		
		//聊天内容
		Method.await(robot, 1, 2);
		while(!new Color(225,75,35).equals(robot.getPixelColor(1346, 167)))
		{
			Method.click(robot,1258,636, true);
			Method.await(robot, 0.5, 0.8);
		}
		
		
		
		//================第三个NPC===================
		//调用对话
		if(x_Book==0&&y_Book==0)
			Task.day_GetChat(robot);
		else
		{
			Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_E);	//打开背包
			ct.check(new PBean(1069,795,168,108,53),true,3000);//检查[背包界面]
			Method.click(robot, 1258, 790, true);//整理背包
			Method.click2(robot,x_Book,y_Book);
			Method.click2(robot,x_Book,y_Book);
		}
		//NPC3
		Method.checkClick(robot, new PBean(1169,463,242,225,204), true, true, 1000*5);
	
		//屏幕清理
		Method.seeHP(robot);
		
		//和他聊天
		Method.checkClick(robot, new PBean(1208,577,242,215,180), true, true, 1000*10);
		
		//内容选择
		Method.await(robot, 1, 2);
		Method.checkClick(robot, new PBean(1196,692,242,215,184), true, true, 1000*10);
		
		//聊天内容
		Method.await(robot, 1, 2);
		while(!new Color(225,75,35).equals(robot.getPixelColor(1346, 167)))
		{
			Method.click(robot,1258,636, true);
			Method.await(robot, 0.5, 0.8);
		}
		
		System.out.println("#结束日常---洛阳风月录===");
	}
	
	
	//日常辅助--洛阳风月录打开
	static void day_GetChat(Robot robot)
	{
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//打开地图
		Method.checkClick(robot, new PBean(1103,494,64,55,52), true, true, 5000);//洛阳城
		
		new CheckThread().check(new PBean(479, 219, 135,89,51),true,1000*5);
		
		//屏幕清理
		Method.seeHP(robot);
		
		Method.press(robot,KeyEvent.VK_TAB);	//打开小地图
		Method.checkClick(robot, new PBean(1175,382,38,81,15), true, true, 5000);//洛阳城某
		
		Method.checkClick(robot, new PBean(1342,515,100,73,49), true, true, 1000*20);//了解一下
		
	}
	
	//日常--秘境降妖

	static void day_Yao(Robot robot)
	{
		System.out.println("===开始日常---秘境降妖===");
		CheckThread ct = new CheckThread();
		//屏幕清理
		Method.seeHP(robot);
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_G);	//战斗技能修改
		Method.checkClick(robot, new PBean(1127,714,177,101,37), true, true, 5000);//挂机技能
		Method.await(robot, 0.5, 1);
		Method.click(robot, 1024,437,true);
		
		Method.await(robot, 0.5, 1);
		if(!new Color(248,222,189).equals(robot.getPixelColor(916, 719)))
			Method.click(robot, 916, 719, true);
		
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_M);	//打开地图
		Method.checkClick(robot, new PBean(1222,555,157,118,50), true, true, 5000);//东海湾
		
		ct.check(new PBean(1346,167,225,75,35), true,1000*5);//检查人物血条
		
		Method.press(robot, KeyEvent.VK_TAB);//小地图
		Method.checkClick(robot, new PBean(875,575,71,183,70), true, true, 5000);//云游乐
		
		Method.checkClick(robot, new PBean(1290,515,132,105,79), true, true, 1000*10);//秘境降妖
		
		Method.checkClick(robot, new PBean(693,527,135,133,131), true, true, 1000*5);//第一关

		Method.checkClick(robot, new PBean(999,690,237,191,96), true, true, 1000*3);//挑战
		
	
		
		//屏幕清理
		Method.seeHP(robot);
		
		//System.out.println("===结束日常---秘境降妖===");
	}

	//日常--押镖
	static void day_YaBiao(Robot robot)
	{
		System.out.println("===开始日常---押镖===");
		Method.press2(robot, KeyEvent.VK_ALT, KeyEvent.VK_M);//大地图
		Method.checkClick(robot, new PBean(923,603,230,136,26), true, true, 1000*5);
		
		//屏幕清理
		Method.seeHP(robot);
		
		Method.press(robot, KeyEvent.VK_TAB);//小地图
		Method.checkClick(robot, new PBean(678,618,64,161,57), true, true, 1000*3);//郑镖头
		MyRobot.db.setnBiao(0);
		
		Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_Z);
		new CheckThread().check(new PBean(1399,263,204,0,0), true,1000*3);//助战界面
		
		Method.click(robot, 1353, 331, true);//第一套阵容
		
		Method.press(robot,KeyEvent.VK_ESCAPE);//关闭助战
		
		
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
		
		//弹窗--队伍不足五人
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
			//组队完毕，重新调用接受抓鬼任务函数；
			Task.zhuaGui(robot);
		}
		
		Method.press(robot,KeyEvent.VK_ESCAPE);	//清理钟馗对话！
		Method.await(robot,0.5,0.6);
		
		//任务追踪界面（有时候右侧可能是组队追踪界面）
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
		
		ct.check(new PBean(1347,738,240,227,211),true, 1000*10);//等待10秒组人
		
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
