/**
 * @创建人：黄强
 * @时间 ：2018/9/23 16:20
 * @描述 ：
 */

import org.apache.commons.dbcp.BasicDataSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.zip.GZIPInputStream;

/**
 * @创建人：黄强
 * @时间 ：2018/9/2 13:59
 * @描述 ：获得DataSource
 */
public class PBSUtils {
    //定义相关变量。
    private static String driverName;
    private  static String URL;
    private static String userName;
    private  static String userPassword;
    private static BasicDataSource dataSource=new BasicDataSource();
    private  static String tempPassword;
    //初始化dataSource
    static {
        try{
            initConfig();
            dataSource.setDriverClassName(driverName);
            dataSource.setUrl(URL);
            dataSource.setUsername(userName);
            dataSource.setPassword(userPassword);
            dataSource.setInitialSize(10);
            dataSource.setMaxIdle(5);
            dataSource.setMinIdle(2);
            //测试连接是否成功。
            try {
                dataSource.getConnection();
            }catch (Exception e){
                System.out.println("密码错误！结束访问！");
                Thread.sleep(1000);
                System.exit(-1);
            }
            //第一次登陆成功后记住密码。
            if (tempPassword.equals("")){
                writePassword(userPassword);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("\n数据库连接失败！\n");
        }
    }
    /**
    
    *@描述 :从配置文件载入配置信息。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:36
    *@修改人和其它信息：
    *@版本：
     
    */
    private static void initConfig() throws IOException {
        InputStream inputStream=new FileInputStream("database.properties") ;
        Properties properties=new Properties();
        properties.load(inputStream);
        driverName=properties.getProperty("driverName");
        userName=properties.getProperty("usrName");
        tempPassword= properties.getProperty("usrPassword");
        //判断是否已经存在密码，有就继续连接，没有就提醒输入。
        if (tempPassword.equals("")){
            System.out.print("请输入数据库密码：");
            userPassword=new  Scanner(System.in).next();
        }else {
            userPassword=tempPassword;
        }
        URL=properties.getProperty("URL");

        inputStream.close();
    }
    /**

    *@描述 :把密码写入配置文件
    *@参数 :[password]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:38
    *@修改人和其它信息：
    *@版本：

    */
    private static void writePassword(String password)throws IOException {
        //输入文件。
        InputStream inputStream=new FileInputStream("database.properties") ;
        Properties properties=new Properties();
        properties.load(inputStream);
        properties.setProperty("usrPassword",password);
        //输出文件。
        FileOutputStream fileOutputStream=new FileOutputStream("database.properties");
        properties.store(fileOutputStream,"数据可配置文件");
        fileOutputStream.close();
        inputStream.close();
    }
    /**

    *@描述 :获得连接。
    *@参数 :[]
    *@返回值:org.apache.commons.dbcp.BasicDataSource
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:39
    *@修改人和其它信息：
    *@版本：

    */
    public static BasicDataSource getDataSource(){
        return dataSource;
    }

}

