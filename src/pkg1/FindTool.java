package pkg1;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * �ŵ㣺˼·������Ч����Ը�
 * ȱ�㣺���β���
 */
public class FindTool {

    BufferedImage screenShotImage;    //��Ļ��ͼ
    BufferedImage keyImage;           //����Ŀ��ͼƬ

    int scrShotImgWidth;              //��Ļ��ͼ���
    int scrShotImgHeight;             //��Ļ��ͼ�߶�

    int keyImgWidth;                  //����Ŀ��ͼƬ���
    int keyImgHeight;                 //����Ŀ��ͼƬ�߶�

    int[][] screenShotImageRGBData;   //��Ļ��ͼRGB����
    int[][] keyImageRGBData;          //����Ŀ��ͼƬRGB����

    int find_x = -1;
	int find_y = -1;





	public FindTool() {
		// TODO Auto-generated constructor stub
	}


	/**
     * ȫ����ͼ
     * @return ����BufferedImage
     */
    public BufferedImage getFullScreenShot() {
        BufferedImage bfImage = null;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        try {
            Robot robot = new Robot();
            bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /**
     * �ӱ����ļ���ȡĿ��ͼƬ
     * @param keyImagePath - ͼƬ����·��
     * @return ����ͼƬ��BufferedImage����
     */
    public BufferedImage getBfImageFromPath(String keyImagePath) {
        BufferedImage bfImage = null;
        try {
            bfImage = ImageIO.read(new File(keyImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /**
     * ����BufferedImage��ȡͼƬRGB����
     * @param bfImage
     * @return
     */
    public int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //ʹ��getRGB(w, h)��ȡ�õ����ɫֵ��ARGB������ʵ��Ӧ����ʹ�õ���RGB��������Ҫ��ARGBת����RGB����bufImg.getRGB(w, h) & 0xFFFFFF��
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }


 

    /**
     * �ж���Ļ��ͼ��Ŀ��ͼӳ�䷶Χ�ڵ�ȫ�����Ƿ�ȫ����Сͼ�ĵ�һһ��Ӧ��
     * @param y - ��Ŀ��ͼ���Ͻ����ص���ƥ�����Ļ��ͼy����
     * @param x - ��Ŀ��ͼ���Ͻ����ص���ƥ�����Ļ��ͼx����
     * @return	�Ƚ��������ص��RGBֵ�Ƿ���ͬ����ͨ���������Ƚϵģ���˵��==Ч�ʸ��ߣ�
     * 			�����������õ���ֵΪ0��˵���������ص��RGBһ��������һ����
     */
    public boolean isMatchAll(int x, int y) {
    	
        int screenY = 0;
        int screenX = 0;
        int xor = 0;
        int acc_all = keyImgWidth*keyImgHeight;
        int acc = acc_all; // ƥ�����������
        for(int keyImageY=0; keyImageY<keyImgHeight; keyImageY++) {
            screenY = y+keyImageY;
            for(int keyImageX=0; keyImageX<keyImgWidth; keyImageX++) {
                screenX = x+keyImageX;
                if(screenY>=scrShotImgHeight || screenX>=scrShotImgWidth) {//������Ļ
                    return false;
                }
                xor = keyImageRGBData[keyImageY][keyImageX]^screenShotImageRGBData[screenY][screenX];
                //���ص㲻ƥ��
                if(xor!=0) 	acc--;
                //�ﵽ80%ƥ��ȼ���
                if((float)acc/acc_all<=0.8) return false;
            }
            screenX = x;
        }
		return true;
    }



    //�ⲿ������ͼ�ӿ�
    PBean FindPic(String keyImagePath){
  	  screenShotImage = this.getFullScreenShot();
        keyImage = this.getBfImageFromPath(keyImagePath);
        screenShotImageRGBData = this.getImageGRB(screenShotImage);
        keyImageRGBData = this.getImageGRB(keyImage);
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        keyImgWidth = keyImage.getWidth();
        keyImgHeight = keyImage.getHeight();
        int x = -1, y =-1;
        PBean pb = new PBean();
        //��ʼ����
        //������Ļ��ͼ���ص�����
        for(y=0; y<scrShotImgHeight-keyImgHeight; y++) {
            for(x=0; x<scrShotImgWidth-keyImgWidth; x++) {
                //����Ŀ��ͼ�ĳߴ磬�õ�Ŀ��ͼ�ĸ���ӳ�䵽��Ļ��ͼ�ϵ��ĸ��㣬
                //�жϽ�ͼ�϶�Ӧ���ĸ�����ͼB���ĸ������ص��ֵ�Ƿ���ͬ��
                if((keyImageRGBData[0][0]^screenShotImageRGBData[y][x])==0
                        && (keyImageRGBData[0][keyImgWidth-1]^screenShotImageRGBData[y][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][keyImgWidth-1]^screenShotImageRGBData[y+keyImgHeight-1][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][0]^screenShotImageRGBData[y+keyImgHeight-1][x])==0) {
                	//����Ľ���ͬ�ͽ���Ļ��ͼ��ӳ�䷶Χ�ڵ����еĵ���Ŀ��ͼ�����еĵ���бȽϡ�
                    if(isMatchAll(x, y)){
                    	System.out.println("�ҵ�����:("+x+","+y+")");
                		pb.setX(x);
                		pb.setY(y);
                		return pb;
                	}
                }
            }
        }
        System.out.println("δ�ҵ���");
		return null;
    }
}