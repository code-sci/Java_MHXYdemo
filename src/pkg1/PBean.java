package pkg1;

import java.awt.Color;

public class PBean {
	public PBean(){}
	public PBean(int x,int y,int r,int g,int b){
		this.setX(x);
		this.setY(y);
		this.setC(new Color(r,g,b));
	}
	int x = -1;
	int y = -1;
	Color c; 
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
