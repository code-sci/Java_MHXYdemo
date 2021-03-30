package pkg1;

import java.awt.AWTException;
import java.awt.Robot;

class CheckThread extends Thread
{
	
	/* @PBean：期望的点；
	 * @flag：期望是否符合；
	 * 采用异或方式判断，如果期望与点不同，则继续等待。如果期望与点相同，则退出；
	 * */
	PBean pb;
	Boolean flag;
	public void run() {
	  try 
	  {
		while(flag^(this.pb.c.equals(new Robot().getPixelColor(this.pb.getX(), this.pb.getY()))))
		{
			Thread.sleep((int)(1000*Math.random()));//每隔一定时间检测一下条件是否满足
		}
	   } catch (InterruptedException | AWTException e) {e.printStackTrace();}
	}
	
	/*@检查是否符合条件的对外接口函数check()
	 * @PBean作为期望点，
	 * @flag作为期望，即要点符合条件还是不符合条件；
	 * @MaxTime作为线程等待最长时间，如果超过这个时间还没满足则自动退出，以防卡住；
	 * 然后调用线程执行代码判断是否符合条件，
	 * 期间主线程处于“暂停”状态；
	 * */
	public void check(PBean pb,Boolean flag,int MaxTime){
		CheckThread ct = new CheckThread();
		ct.pb = pb;
		ct.flag = flag;
		ct.start();
		try {ct.join(MaxTime);} //join(long MaxTime)指定了线程最长等待的时间；
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}


