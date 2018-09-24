import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @创建人：黄强
 * @时间 ：2018/9/24 13:02
 * @描述 ：
 */
public class ShowPBS {
    /**

     *@描述 :图片加文字
     *@参数 :[x, y, Name]
     *@返回值:void
     *@创建人 : 黄强
     *@创建时间  2018/9/24 11:18
     *@修改人和其它信息：
     *@版本：

     */
    public  static void  writeName(java.util.List<PBSInfo> PBSList){
        BufferedImage bimg = null;
        LinkedList<Integer> linkedList=new LinkedList<>();
        try {
            bimg = ImageIO.read(new FileInputStream("Map.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到Graphics2D 对象
        Graphics2D g2d = (Graphics2D) bimg.getGraphics();
        //设置颜色和画笔粗细
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(20));
        g2d.setFont(new Font("Serif", Font.PLAIN, 30));
        //绘制图案或文字
        for (PBSInfo pbsInfo:PBSList
        ) {
            double x=Double.parseDouble(pbsInfo.getPBS_Latitude());
            double y=Double.parseDouble(pbsInfo.getPBS_Longitude());
            int printX=(int )((y-71.016070)*1637/66.742321);
            int printY=(int)(1051-(x-20.717464)*1051/33.475908);
            if (printX<bimg.getWidth()&&printX>=0&&printY<bimg.getHeight()&&printY>=0) {
                linkedList.add(printX);
                linkedList.add(printY);
                g2d.drawString(pbsInfo.getPBS_Name(), printX + 20, printY + 40);
            }
        }
        //保存新图片
        try {
            ImageIO.write(bimg, "JPG", new FileOutputStream("printMap.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printPoint(linkedList);
    }
    /**

     *@描述 :图片加点
     *@参数 :[linkedList]
     *@返回值:void
     *@创建人 : 黄强
     *@创建时间  2018/9/24 12:10
     *@修改人和其它信息：
     *@版本：

     */
    private  static  void printPoint(LinkedList<Integer>linkedList){
        BufferedImage bufferedImage=null;
        try {
            bufferedImage=ImageIO.read(new File("printMap.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int k=0;k<linkedList.size();) {
            int x=linkedList.get(k);
            ++k;
            int y=linkedList.get(k);
            ++k;
            for (int i = x; i < x+10; i++) {
                for (int j = y+40; j < 50+y; j++){
                    if (i<bufferedImage.getWidth()&&j<bufferedImage.getHeight()){
                        bufferedImage.setRGB(i,j,0xff0000);
                    }
                }

            }
        }
        try {
            ImageIO.write(bufferedImage,"jpg",new File("printMap.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**

     *@描述 :定义弹窗内部类。
     *@参数 :
     *@返回值:
     *@创建人 : 黄强
     *@创建时间  2018/9/24 11:18
     *@修改人和其它信息：
     *@版本：

     */
  static   class ShowImage extends JFrame {
        public ShowImage(Image image, ImageIcon icon) {
            this.setSize(1600, 1600);
            icon.setImage(image);
            JButton button = new JButton();
            button.setIcon(icon);
            this.getContentPane().add(button);
            this.setVisible(true);
            this.setDefaultCloseOperation(2);
        }
    }
    /**

     *@描述 :弹窗显示图片。
     *@参数 :[]
     *@返回值:void
     *@创建人 : 黄强
     *@创建时间  2018/9/24 11:18
     *@修改人和其它信息：
     *@版本：
     */
    public static   void showImage(){
        ImageIcon icon = new ImageIcon();
        Image image = Toolkit.getDefaultToolkit().getImage("printMap.jpg");
        image.flush();
        new ShowImage(image,icon);

    }
}
