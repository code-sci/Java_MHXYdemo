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
 * 优点：思路清晰、效率相对高
 * 缺点：单次查找
 */
public class FindTool {

    BufferedImage screenShotImage;    //屏幕截图
    BufferedImage keyImage;           //查找目标图片

    int scrShotImgWidth;              //屏幕截图宽度
    int scrShotImgHeight;             //屏幕截图高度

    int keyImgWidth;                  //查找目标图片宽度
    int keyImgHeight;                 //查找目标图片高度

    int[][] screenShotImageRGBData;   //屏幕截图RGB数据
    int[][] keyImageRGBData;          //查找目标图片RGB数据

    int find_x = -1;
	int find_y = -1;





	public FindTool() {
		// TODO Auto-generated constructor stub
	}


	/**
     * 全屏截图
     * @return 返回BufferedImage
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
     * 从本地文件读取目标图片
     * @param keyImagePath - 图片绝对路径
     * @return 本地图片的BufferedImage对象
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
     * 根据BufferedImage获取图片RGB数组
     * @param bfImage
     * @return
     */
    public int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }


 

    /**
     * 判断屏幕截图上目标图映射范围内的全部点是否全部和小图的点一一对应。
     * @param y - 与目标图左上角像素点想匹配的屏幕截图y坐标
     * @param x - 与目标图左上角像素点想匹配的屏幕截图x坐标
     * @return	比较两个像素点的RGB值是否相同，是通过异或操作比较的（据说比==效率更高）
     * 			如果异或操作后得到的值为0，说明两个像素点的RGB一样，否则不一样。
     */
    public boolean isMatchAll(int x, int y) {
    	
        int screenY = 0;
        int screenX = 0;
        int xor = 0;
        int acc_all = keyImgWidth*keyImgHeight;
        int acc = acc_all; // 匹配的像素数量
        for(int keyImageY=0; keyImageY<keyImgHeight; keyImageY++) {
            screenY = y+keyImageY;
            for(int keyImageX=0; keyImageX<keyImgWidth; keyImageX++) {
                screenX = x+keyImageX;
                if(screenY>=scrShotImgHeight || screenX>=scrShotImgWidth) {//超出屏幕
                    return false;
                }
                xor = keyImageRGBData[keyImageY][keyImageX]^screenShotImageRGBData[screenY][screenX];
                //像素点不匹配
                if(xor!=0) 	acc--;
                //达到80%匹配度即可
                if((float)acc/acc_all<=0.8) return false;
            }
            screenX = x;
        }
		return true;
    }



    //外部调用找图接口
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
        //开始查找
        //遍历屏幕截图像素点数据
        for(y=0; y<scrShotImgHeight-keyImgHeight; y++) {
            for(x=0; x<scrShotImgWidth-keyImgWidth; x++) {
                //根据目标图的尺寸，得到目标图四个角映射到屏幕截图上的四个点，
                //判断截图上对应的四个点与图B的四个角像素点的值是否相同，
                if((keyImageRGBData[0][0]^screenShotImageRGBData[y][x])==0
                        && (keyImageRGBData[0][keyImgWidth-1]^screenShotImageRGBData[y][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][keyImgWidth-1]^screenShotImageRGBData[y+keyImgHeight-1][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][0]^screenShotImageRGBData[y+keyImgHeight-1][x])==0) {
                	//如果四角相同就将屏幕截图上映射范围内的所有的点与目标图的所有的点进行比较。
                    if(isMatchAll(x, y)){
                    	System.out.println("找到坐标:("+x+","+y+")");
                		pb.setX(x);
                		pb.setY(y);
                		return pb;
                	}
                }
            }
        }
        System.out.println("未找到！");
		return null;
    }
}