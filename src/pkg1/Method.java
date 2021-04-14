package pkg1;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Method {
	
	//点击函数（第三个参数为是否进行坐标随机偏移）
	static void  click(Robot robot,int x , int y,boolean f)
	{
		if(f){//对点击坐标进行小偏移
			x = x-6+(new Random().nextInt(12));
			y = y-6+(new Random().nextInt(12));
		
		
		robot.mouseMove(x, y);
	
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		await(robot,0.2,0.4);	
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
//		System.out.println("点击("+x+","+y+")处；");
	}
	
	
	//检查点击(在检查到该点后点击
	//*f1：期望结果  f2：是否偏移
	static void checkClick(Robot robot ,PBean pb,boolean f1 ,boolean f2,int maxTime)
	{
		CheckThread ct = new CheckThread();
		ct.check(pb, f1,maxTime );
		Method.await(robot, 0.2, 0.3);
		Method.click(robot, pb.getX(), pb.getY(), f2);
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
	
	//拖动函数，涉及到拖动速度问题，暂不推荐使用；
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
	
	
	//#数据记录函数
	static  void writeToFile(String fileName,String str)
	  {
	   try {
	      //如果存在txt文件则直接往txt中追加字符串
	      FileWriter writer=new FileWriter(fileName,true);
	      writer.write(str+"\n");
	       writer.close();
	   } catch (IOException e) {e.printStackTrace();}
	 }
	
	static void seeHP(Robot robot)
	{
		while(!new Color(225,75,35).equals(robot.getPixelColor(1346, 167)))
			{
				Method.press(robot, KeyEvent.VK_ESCAPE);
				Method.await(robot, 0.5, 0.8);
			}
	}
	
	//剪切板复制函数
	//将传入的字符串内容复制到剪切板
    public static void setSysClipboardText(String writeMe) {  
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable tText = new StringSelection(writeMe);  
        clip.setContents(tText, null);  
    } 
    
    
	
}
