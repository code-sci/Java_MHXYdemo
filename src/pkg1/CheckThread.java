package pkg1;

import java.awt.AWTException;
import java.awt.Robot;

class CheckThread extends Thread
{
	
	/* @PBean�������ĵ㣻
	 * @flag�������Ƿ���ϣ�
	 * �������ʽ�жϣ����������㲻ͬ��������ȴ���������������ͬ�����˳���
	 * */
	PBean pb;
	Boolean flag;
	public void run() {
	  try 
	  {
		while(flag^(this.pb.c.equals(new Robot().getPixelColor(this.pb.getX(), this.pb.getY()))))
		{
			Thread.sleep((int)(1000*Math.random()));//ÿ��һ��ʱ����һ�������Ƿ�����
		}
	   } catch (InterruptedException | AWTException e) {e.printStackTrace();}
	}
	
	/*@����Ƿ���������Ķ���ӿں���check()
	 * @PBean��Ϊ�����㣬
	 * @flag��Ϊ��������Ҫ������������ǲ�����������
	 * @MaxTime��Ϊ�̵߳ȴ��ʱ�䣬����������ʱ�仹û�������Զ��˳����Է���ס��
	 * Ȼ������߳�ִ�д����ж��Ƿ����������
	 * �ڼ����̴߳��ڡ���ͣ��״̬��
	 * */
	public void check(PBean pb,Boolean flag,int MaxTime){
		CheckThread ct = new CheckThread();
		ct.pb = pb;
		ct.flag = flag;
		ct.start();
		try {ct.join(MaxTime);} //join(long MaxTime)ָ�����߳���ȴ���ʱ�䣻
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}


