# Java_MHXY  
**【源码仅供Java交流学习，请勿用于商业盈利活动，如有功能疑问，请issue，欢迎交流】**

Java实现梦幻西游手游自动通宵抓鬼
 
MyRobot.jar 为已导出的jar包，可以通过JVM直接调用；

RunRobot.bat 为写好的调用jar包批处理程序；

注意：
1、jar包并非实时更新的，每次更改代码后只更新了项目文件夹内代码，新jar包需要自己重新打包出来；

2、使用此脚本时要以管理员身份运行，不然无法在梦幻西游桌面版的界面内部进行脚本操作如点击等，所以如果用cmd调用java命令运行则需要以管理员身份打开cmd窗口，或者以Eclipse编译运行则需要以管理员身份打开Eclipse！

缺点：  
通用性不强，局限于桌面版1920*1080的屏幕，所以只能当做个人Java自动化脚本的练习demo。  
使用时电脑独占，无法进行其它工作，所以用来通宵抓鬼是还行的。

优点：基于JVM调用的模拟脚本操作，游戏里一般检测不出来是脚本，较其它脚本语言相对安全，

**2021/03/21更新：**

如何利用此脚本带多个通宵鬼队？

> 因为需要占用鼠标，所以可以利用虚拟机多开一个独立系统；  
> 注意：  
> 1、要将虚拟机内操作系统分辨率调为1920*1080；  
> 2、要在虚拟机上安装VMware Tools实现虚拟机内鼠标与实体机鼠标相互独立；（同时也方便实体机与虚拟机的文件交换）  
> 3、当然虚拟机上要安装JDK开发环境和梦幻桌面版；  

**2021/03/29更新:**
如何判断执行脚本某步骤是否满足某些条件？  
> 起因：一些脚本的执行步骤经常要满足某些条件，如使用背包物品的前提是背包被打开了，在此之前，一直都是用延时来完成的，但是遇到网络或游戏卡顿时等突发状况，延时就容易出现问题，可能背包没有打开就点击了物品位置，所以需要更加精确地判断当前游戏环境是否满足某些条件；  
> 思路：利用Java中的线程(Thread)功能，在执行某些步骤前，调用另一线程判断是否满足条件，此时主线程是“暂停”的，即利用Thread类的join()方法可以实现。  


