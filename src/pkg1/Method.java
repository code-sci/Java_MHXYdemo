package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Method {
	
	//接受抓鬼任务函数
	static void task_gui(Robot bush)
	{
		CheckThread ct = new CheckThread();
		//清理屏幕
		press(bush,KeyEvent.VK_ESCAPE);	
		await(bush,0.5,0.6);
		press(bush,KeyEvent.VK_ESCAPE);
		await(bush,0.5,0.6);
		
		
		System.out.println("=======开始接受抓鬼任务！=======");
		
		press2(bush,KeyEvent.VK_ALT,KeyEvent.VK_M);	//大地图
		ct.check(new PBean(1398,277,231,68,44),true);//大地图关闭按钮
		
		click(bush,926,600,true);	//大地图---长安城
		ct.check(new PBean(1398,277,231,68,44),false);//大地图关闭按钮
		
		press(bush,KeyEvent.VK_TAB);	//按键---小地图
		ct.check(new PBean(1341,301,191,21,0),true);//小地图关闭按钮
		
		click(bush,762,598,true);	//小地图---钟馗
		ct.check(new PBean(1287,521,85,57,35),true);//抓鬼任务“鬼”字

		click(bush,1219,515,true);	//点击抓鬼任务
		await(bush,1,1.5);	
		
		press(bush,KeyEvent.VK_ESCAPE);	//清理屏幕
		await(bush,0.5,0.6);
		
		press(bush,KeyEvent.VK_ESCAPE);
			//抓鬼任务有时候位置不定
		if(bush.getPixelColor(1455,338).equals(new Color(117,62,24)))
			click(bush,1455,338,true);
		else
			click(bush,1455,450,true);
		await(bush,1,1.5);
		System.out.println("=======抓鬼任务接受完毕！=======");
		
	}

	
	//点击函数（第三个参数为是否进行坐标随机偏移）
	static void  click(Robot robot,int x , int y,boolean f)
	{
		if(f){//对点击坐标进行小偏移
			x = x+(new Random().nextInt(6));
			y = y+(new Random().nextInt(6));
		
		
		robot.mouseMove(x, y);
	
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.2,0.4);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
//		System.out.println("点击("+x+","+y+")处；");
	}
	
	//双击函数
	static void click2(Robot robot,int x ,int y)
	{
		robot.mouseMove(x, y);
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.1,0.2);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		System.out.println("双击("+x+","+y+")处；");
		
	}
	
	//按键函数 
	static void  press(Robot robot,int k)
	{
		robot.keyPress(k);
		await(robot,0.2,0.4);	
		robot.keyRelease(k);
		
//		System.out.println("按下"+KeyEvent.getKeyText(k)+"键；");
	}
	
	//组合键函数
	static void  press2(Robot robot,int k1,int k2)
	{
		robot.keyPress(k1);
		await(robot,0.2,0.4);	
		robot.keyPress(k2);
		await(robot,0.2,0.4);
		robot.keyRelease(k1);
		await(robot,0.2,0.4);
		robot.keyRelease(k2);
		
//		System.out.println("按下"+KeyEvent.getKeyText(k1)+"+"+KeyEvent.getKeyText(k2)+"键；");
	}
	
	//拖动函数
	static void drag(Robot robot,int x1,int y1,int x2, int y2)
	{
		robot.mouseMove(x1, y1);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.5,0.8);
		robot.mouseMove(x2, y2);
		await(robot,0.3,0.5);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		
	}
	
	//随机延时函数
	static void await(Robot robot,double a,double b)
	{
		double v = a+(new Random()).nextDouble()*(b-a);	//随机b-a秒
		v = v*1000;
		robot.delay((int)v);
		
//		System.out.println("延时"+((int)(v*10))/10000.0+"秒；");
	}
	
	//领取或冻结双倍点数,@times 当前第几只鬼
	static void task_double(Robot robot, int times,boolean flag)
	{
		CheckThread ct = new CheckThread();
		//清理屏幕
		Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
    	Method.press(robot,KeyEvent.VK_ESCAPE);
    	Method.await(robot,0.5,0.6);
		
    	Method.press2(robot,KeyEvent.VK_ALT,KeyEvent.VK_G);
    	ct.check(new PBean(1400,264,207,0,0),true);//挂机界面关闭按钮
    	
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
	
}
