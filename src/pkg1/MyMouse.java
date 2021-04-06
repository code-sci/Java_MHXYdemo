package pkg1;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
 
public class MyMouse extends JFrame {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private final JPanel contentPanel = new JPanel();
  JLabel value_x = null;
  JLabel value_y = null;
  JLabel value_rgb =null;
  static JTextField value_text = null;
  
  
  static String fileName="F:\\桌面\\position.txt";
  static String str ="";

  
  
  public static void main(String[] args) {
    try {
      MyMouse info_frame = new MyMouse();
      info_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      info_frame.setVisible(true);
      info_frame.setAlwaysOnTop(true);
      Robot robot = new Robot();
      
      //键盘监听,以文本框为监听目标
      value_text.addKeyListener(new KeyAdapter()		
    	        {
    	            public void keyPressed(KeyEvent e)
    	            {//监听到按下空格键
//    	                
        	            if(e.getKeyCode()==KeyEvent.VK_ENTER)
    	                {
    	                	   Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
    	                       Color c = robot.getPixelColor(point.x,point.y );
    	                       str = "("+(int)point.getX()+","+(int)point.getY()+") ";
    	                       str = str+"["+c.getRed()+","+c.getGreen()+","+c.getBlue()+"] ";
    	                       str = str+(value_text.getText());
    	                       
    	                        Method.writeToFile(fileName,str);
    	                 
    	                    System.out.println(" 记录成功："+str);
    	                    value_text.setText("");
    	                }   
    	                //组合键
//    	                else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER)
//    	                   System.exit(0);
    	                
    	            }
    	        });
      
      //鼠标捕获
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
          Color c = robot.getPixelColor(point.x,point.y );
//           System.out.println("Location:x=" + point.x + ", y=" +  point.y);
          
          info_frame.value_x.setText("" + point.x);
          info_frame.value_y.setText("" + point.y);
          info_frame.value_rgb.setText(c.getRed()+","+c.getGreen()+","+c.getBlue());
        }
      }, 100, 100);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  
	
  public MyMouse() {
    setTitle("鼠标状态");
    setBounds(1690, 10, 235, 200);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
 
    JLabel lblx = new JLabel("坐标x:");
    lblx.setFont(new Font("楷体", Font.PLAIN, 15));
    lblx.setBounds(22, 15, 66, 31);
    contentPanel.add(lblx);
 
    JLabel lbly = new JLabel("坐标y:");
    lbly.setFont(new Font("楷体", Font.PLAIN, 15));
    lbly.setBounds(22, 40, 66, 31);
    contentPanel.add(lbly);
    
    JLabel lrgb = new JLabel("颜色:");
    lrgb.setFont(new Font("楷体", Font.PLAIN, 15));
    lrgb.setBounds(22, 65, 66, 31);
    contentPanel.add(lrgb);
 
    value_x = new JLabel("0");
    value_x.setForeground(Color.BLUE);
    value_x.setFont(new Font("黑体", Font.PLAIN, 15));
    value_x.setBounds(82, 15, 66, 31);
    contentPanel.add(value_x);
 
    value_y = new JLabel("0");
    value_y.setForeground(Color.BLUE);
    value_y.setFont(new Font("黑体", Font.PLAIN, 15));
    value_y.setBounds(82, 40, 66, 31);
    contentPanel.add(value_y);
    
    value_rgb = new JLabel("0");
    value_rgb.setForeground(Color.BLUE);
    value_rgb.setFont(new Font("黑体", Font.PLAIN, 15));
    value_rgb.setBounds(82, 65, 100, 31);
    contentPanel.add(value_rgb);
    
    value_text = new JTextField();
    value_text.setFont(new Font("楷体", Font.PLAIN, 15));
    value_text.setBounds(22,100,150,25);
    contentPanel.add(value_text);
    
  }
}