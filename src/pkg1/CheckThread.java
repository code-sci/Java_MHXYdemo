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
			Thread.sleep(1000);//ÿ��һ��ʱ����һ�������Ƿ�����
		}
	   } catch (InterruptedException | AWTException e) {e.printStackTrace();}
	}
	
	/*@����Ƿ���������Ķ���ӿں���
	 * check(PBean)���մ����һ��PBean��Ϊ�����㣬flag��Ϊ��������Ҫ������������ǲ�����������
	 * Ȼ������߳�ִ�д����ж��Ƿ����������
	 * �ڼ����̴߳��ڡ���ͣ��״̬��
	 * */
	public void check(PBean pb,Boolean flag){
		CheckThread ct = new CheckThread();
		ct.pb = pb;
		ct.flag = flag;
		ct.start();
		try {ct.join();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}


