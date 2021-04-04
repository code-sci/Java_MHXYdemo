package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class Task {
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
		Method.writeToFile(fileName,"#本次脚本统计数据：(截止"+MyRobot.db.getStop_h()+":"+MyRobot.db.getStop_m()+"的最后一轮鬼)");
		Method.writeToFile(fileName,"抓鬼总数："+MyRobot.db.getSum_Gui());
		Method.writeToFile(fileName,"消耗双倍点数："+MyRobot.db.getCost_Double());
		Method.writeToFile(fileName,"抓鬼平均耗时："+avg_time+"s(仅供参考)");
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
		 
		 //弹窗--是否继续抓鬼?
		 if(new Color(154,110,66).equals(robot.getPixelColor(1108,519)))//判断弹窗字
		 {
			System.out.println("=======开始[弹窗]接受抓鬼任务=======");
				
			 Method.click(robot, 1051,595,true);//点击[确定]继续抓鬼--->跳转钟馗对话
			 
			 CheckThread ct = new CheckThread();
			 ct.check(new PBean(1287,521,85,57,35),true,8000);//等待抓鬼任务“鬼”字
				
				Method.click(robot,1219,515,true);	//点击[抓鬼任务]
				Method.await(robot,1,1.5);	
				
				Method.press(robot,KeyEvent.VK_ESCAPE);	//清理钟馗对话！
				Method.await(robot,0.5,0.6);
				
				Method.press(robot,KeyEvent.VK_ESCAPE);
				//抓鬼任务有时候位置不定
				if(robot.getPixelColor(1455,338).equals(new Color(117,62,24)))
					Method.click(robot,1455,338,true);
				else
					Method.click(robot,1455,450,true);
				Method.await(robot,1,1.5);
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
	static void zhuaGui(Robot bush)
	{
		CheckThread ct = new CheckThread();
		//清理屏幕
		Method.press(bush,KeyEvent.VK_ESCAPE);	
		Method.await(bush,0.5,0.6);
		Method.press(bush,KeyEvent.VK_ESCAPE);
		Method.await(bush,0.5,0.6);
		
		
		System.out.println("=======开始[手动]接受抓鬼任务=======");
		
		Method.press2(bush,KeyEvent.VK_ALT,KeyEvent.VK_M);	//大地图
		ct.check(new PBean(1398,277,231,68,44),true,3000);//大地图关闭按钮
		
		Method.click(bush,926,600,true);	//大地图---长安城
		ct.check(new PBean(1398,277,231,68,44),false,3000);//大地图关闭按钮
		
		Method.press(bush,KeyEvent.VK_TAB);	//按键---小地图
		ct.check(new PBean(1341,301,191,21,0),true,3000);//小地图关闭按钮
		
		Method.click(bush,762,598,true);	//小地图---钟馗
		ct.check(new PBean(1287,521,85,57,35),true,8000);//抓鬼任务“鬼”字
		
		Method.click(bush,1219,515,true);	//点击抓鬼任务
		Method.await(bush,1,1.5);	
		
		Method.press(bush,KeyEvent.VK_ESCAPE);	//清理钟馗对话！
		Method.await(bush,0.5,0.6);
		
		Method.press(bush,KeyEvent.VK_ESCAPE);
		//抓鬼任务有时候位置不定
		if(bush.getPixelColor(1455,338).equals(new Color(117,62,24)))
			Method.click(bush,1455,338,true);
		else
			Method.click(bush,1455,450,true);
		Method.await(bush,1,1.5);
		System.out.println("=======[手动]抓鬼任务接受完毕！=======");
		
	}
	
	//#抓鬼数量发送函数
	static void sendCount(Robot robot,int k)
	{
		System.out.println("===发送抓鬼总数:"+MyRobot.n_count+"===");
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
