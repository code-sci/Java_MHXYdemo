package pkg1;

import java.awt.Color;
import java.awt.Robot;

public class DataBean {
	private int sumGui;
	private int sumTime;
	private int costDouble;
	private int nGui;
	private int stopH;
	private int stopM;
	private int startDouble;
	private boolean flagSend;
	
	private int state;
	private int t_0,t_1,t_2,t_3;//t_0:静止；t_1：移动；t_2：战斗；t_3：押镖；
	private Color c_pre1,c_pre2,c_pre3;//辅助点颜色
	private boolean s_0,s_1,s_2,s_3;
	private boolean flagStop;
	
	/*判断坐标点	是否变化，三点有一点是不变的则是静止
	 * 天覆阵的右红点、
	 * 战斗回合数的数字颜色点、
	 * 数字边框颜色点(目前支持到第九轮)
	 * 战斗界面右下角自动技能的小人物UI
	 * */
	public void s_update(Robot robot)
	{
		this.s_3 =(new Color(155,103,53).equals(robot.getPixelColor(715,234)));
		this.s_0 = ((this.getC_pre1().equals(robot.getPixelColor(1131,617))||this.getC_pre2().equals(robot.getPixelColor(741,328))||
				this.getC_pre3().equals(robot.getPixelColor(1085,336))))&&(!s_3);
		this.s_1 = (!(this.getC_pre1().equals(robot.getPixelColor(1131,617))&&!this.getC_pre2().equals(robot.getPixelColor(741,328))&&
				!this.getC_pre3().equals(robot.getPixelColor(1085,336))));
		this.s_2 = (robot.getPixelColor(1071,194).getRed()==255)||(new Color(255,255,238).equals(robot.getPixelColor(964, 177)))||
				(new Color(110,99,61).equals(robot.getPixelColor(987, 199)))||(new Color(240,204,153).equals(robot.getPixelColor(1295,834)));
	}
	
	public boolean isIfStop() {
		return flagStop;
	}

	public void setIfStop(boolean ifStop) {
		this.flagStop = ifStop;
	}

	public void t_clear(){
		this.t_0=0;
		this.t_1=0;
		this.t_2=0;
		this.t_3=0;
	}
	
	public void c_update(Robot robot)
	{
		c_pre1 = robot.getPixelColor(1131,617);
		c_pre2 = robot.getPixelColor(741,328);
		c_pre3 = robot.getPixelColor(1085,336);
	}
	
	public int getState() {
		return state;
	}
	public boolean isS_0() {
		return s_0;
	}

	public void setS_0(boolean s_0) {
		this.s_0 = s_0;
	}

	public boolean isS_1() {
		return s_1;
	}

	public void setS_1(boolean s_1) {
		this.s_1 = s_1;
	}

	public boolean isS_2() {
		return s_2;
	}

	public void setS_2(boolean s_2) {
		this.s_2 = s_2;
	}

	public boolean isS_3() {
		return s_3;
	}

	public void setS_3(boolean s_3) {
		this.s_3 = s_3;
	}

	public void setState(int state) {
		this.state = state;
	}
	public int getT_0() {
		return t_0;
	}
	public void setT_0(int t_0) {
		this.t_0 = t_0;
	}
	public int getT_1() {
		return t_1;
	}
	public void setT_1(int t_1) {
		this.t_1 = t_1;
	}
	public int getT_2() {
		return t_2;
	}
	public void setT_2(int t_2) {
		this.t_2 = t_2;
	}
	public int getT_3() {
		return t_3;
	}
	public void setT_3(int t_3) {
		this.t_3 = t_3;
	}
	public Color getC_pre1() {
		return c_pre1;
	}
	public void setC_pre1(Color c_pre1) {
		this.c_pre1 = c_pre1;
	}
	public Color getC_pre2() {
		return c_pre2;
	}
	public void setC_pre2(Color c_pre2) {
		this.c_pre2 = c_pre2;
	}
	public Color getC_pre3() {
		return c_pre3;
	}
	public void setC_pre3(Color c_pre3) {
		this.c_pre3 = c_pre3;
	}
	public boolean isFlagSend() {
		return flagSend;
	}
	public void setFlagSend(boolean flagSend) {
		this.flagSend = flagSend;
	}
	public int getSumGui() {
		return sumGui;
	}
	public void setSumGui(int sumGui) {
		this.sumGui = sumGui;
	}
	public int getSumTime() {
		return sumTime;
	}
	public void setSumTime(int sumTime) {
		this.sumTime = sumTime;
	}
	public int getCostDouble() {
		return costDouble;
	}
	public void setCostDouble(int costDouble) {
		this.costDouble = costDouble;
	}
	public int getnGui() {
		return nGui;
	}
	public void setnGui(int nGui) {
		this.nGui = nGui;
	}
	public int getStopH() {
		return stopH;
	}
	public void setStopH(int stopH) {
		this.stopH = stopH;
	}
	public int getStopM() {
		return stopM;
	}
	public void setStopM(int stopM) {
		this.stopM = stopM;
	}
	public int getStartDouble() {
		return startDouble;
	}
	public void setStartDouble(int startDouble) {
		this.startDouble = startDouble;
	}
	public int getStop_h() {
		return stopH;
	}
	public void setStop_h(int stop_h) {
		this.stopH = stop_h;
	}
	public int getStop_m() {
		return stopM;
	}
	public void setStop_m(int stop_m) {
		this.stopM = stop_m;
	}
	public int getN_Gui() {
		return nGui;
	}
	public void setN_Gui(int n_Gui) {
		this.nGui = n_Gui;
	}
	public int getSum_Gui() {
		return sumGui;
	}
	public void setSum_Gui(int sum_Gui) {
		this.sumGui = sum_Gui;
	}
	public int getSum_Time() {
		return sumTime;
	}
	public void setSum_Time(int sum_Time) {
		this.sumTime = sum_Time;
	}
	public int getCost_Double() {
		return costDouble;
	}
	public void setCost_Double(int cost_Double) {
		this.costDouble = cost_Double;
	}
	
}
