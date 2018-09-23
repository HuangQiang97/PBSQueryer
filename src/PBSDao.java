import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @创建人：黄强
 * @时间 ：2018/9/23 16:19
 * @描述 ：
 */
public class PBSDao {
    //获得连接池。
    public static BasicDataSource basicDataSource=PBSUtils.getDataSource();
    static Scanner scanner=new Scanner(System.in);
     static private QueryRunner queryRunner = new QueryRunner(basicDataSource);
     /**

     *@描述 :添加记录。
     *@参数 :[pbsInfo]
     *@返回值:void
     *@创建人 : 黄强
     *@创建时间  2018/9/24 1:01
     *@修改人和其它信息：
     *@版本：

     */
    protected  static  void addInfo(PBSInfo pbsInfo){

        String SQL="insert into Pseudo_base_station_IP.IP_tablesort(PBS_Name,PBS_Latitude,PBS_Longitude,PBS_Date,PBS_Desc)values(?,?,?,?,?);";
        // System.out.println(billRecord.getDate());
        if (pbsInfo.getPBS_Date().length()==0){
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            pbsInfo.setPBS_Date( formatter.format(currentTime));
        }
        try {
            queryRunner.update(SQL,pbsInfo.getPBS_Name(),pbsInfo.getPBS_Latitude(),pbsInfo.getPBS_Longitude(),pbsInfo.getPBS_Date(),pbsInfo.getPBS_Desc());
            System.out.println("\n添加成功！\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n添加失败！\n");
        }
    }
    /**

    *@描述 :删除记录。
    *@参数 :[deleteChoose]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:02
    *@修改人和其它信息：
    *@版本：

    */
    protected void deleteInfo(int deleteChoose){
        String SQL="delete from Pseudo_base_station_IP.IP_tablesort  where PBS_ID=?;";
        try {
            int rowChange=  queryRunner.update(SQL,deleteChoose);
            if (rowChange!=0)
                System.out.println("\n删除成功！\n");
            else
                System.out.println("\n删除失败！\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n删除失败！\n");
        }
    }
    /**

    *@描述 :编辑信息。
    *@参数 :[pbsInfo]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:03
    *@修改人和其它信息：
    *@版本：

    */
    protected void editInfo(PBSInfo pbsInfo){
        String SQL="select * from Pseudo_base_station_IP.IP_tablesort where PBS_ID=?;";
        try {
            PBSInfo queryRecord=queryRunner.query(SQL,new BeanHandler<>(PBSInfo.class),pbsInfo.getPBS_ID());
            if (queryRecord==null){
                System.out.println("\n该ＩＤ不存在！修改失败！\n");
                return;
            }else {
                if (pbsInfo.getPBS_Name().length()==0){
                    pbsInfo.setPBS_Name(queryRecord.getPBS_Name());
                }
                if (pbsInfo.getPBS_Date().length()==0){
                    pbsInfo.setPBS_Date(queryRecord.getPBS_Date());
                }
                if (pbsInfo.getPBS_Latitude().length()==0){
                    pbsInfo.setPBS_Latitude(queryRecord.getPBS_Latitude());
                }
                if (pbsInfo.getPBS_Longitude().length()==0){
                    pbsInfo.setPBS_Longitude(queryRecord.getPBS_Longitude());
                }
                if (pbsInfo.getPBS_Desc().length()==0)
                {
                    pbsInfo.setPBS_Desc(queryRecord.getPBS_Desc());
                }
                SQL="update IP_tablesort set PBS_Name=?,PBS_Latitude=?,PBS_Longitude=?,PBS_Date=?,PBS_Desc=? where PBS_ID=?";
                queryRunner.update(SQL,pbsInfo.getPBS_Name(),pbsInfo.getPBS_Latitude(),pbsInfo.getPBS_Longitude(),pbsInfo.getPBS_Date(),pbsInfo.getPBS_Desc(),pbsInfo.getPBS_ID());
                System.out.println("\n修改成功！\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n更新失败！\n");
        }


    }
    /**

    *@描述 :查询所有记录。
    *@参数 :[]
    *@返回值:java.util.List<PBSInfo>
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:06
    *@修改人和其它信息：
    *@版本：

    */
    protected List<PBSInfo> queryAllInfo(){

        String SQL = "select * from Pseudo_base_station_IP.IP_tablesort;";
        List<PBSInfo> recoeds = null;
        try {
            recoeds = queryRunner.query(SQL, new BeanListHandler<PBSInfo>(PBSInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n查询所有记录失败！\n");
        }
        return recoeds;

    }
    /**

    *@描述 :按日期查询记录。
    *@参数 :[startDate, endDate]
    *@返回值:java.util.List<PBSInfo>
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:06
    *@修改人和其它信息：
    *@版本：

    */
    protected List<PBSInfo> queryByDate(String startDate, String endDate) {
        String SQL = "select * from Pseudo_base_station_IP.IP_tablesort where PBS_Date  between ? and ?;";
        List<PBSInfo> recoeds = null;
        try {
            recoeds = queryRunner.query(SQL, new BeanListHandler<PBSInfo>(PBSInfo.class), startDate, endDate);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n查询失败！\n");
        }
        return recoeds;
    }
    /**

     *@描述 :按距离查询伪基站。
     *@参数 :[x, y, targetDostance]
     *@返回值:java.util.List<PBSInfo>
     *@创建人 : 黄强
     *@创建时间  2018/9/24 0:53
     *@修改人和其它信息：
     *@版本：

     */
    protected List<PBSInfo> queryByDistance(double x,double y,double targetDostance){
        //获取并且遍历所有伪基站。
        String SQL = "select * from Pseudo_base_station_IP.IP_tablesort;";
        List<PBSInfo> recoeds = null;
        List<PBSInfo> result=new LinkedList<>();
        try {
            recoeds = queryRunner.query(SQL, new BeanListHandler<PBSInfo>(PBSInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n查询所有记录失败！\n");
        }
        for (PBSInfo pbsInfo:recoeds
        ) {
            double latitude= Double.parseDouble(pbsInfo.getPBS_Latitude());
            double longitude= Double.parseDouble(pbsInfo.getPBS_Longitude());
            double xDistance=(latitude-x)*3.14*6400000/180;
            double yDistance=(latitude-y)*3.14*6400000/180;

            double distance=Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
            if (distance<targetDostance){
                result.add(pbsInfo);
            }
        }
        return result;
    }
    /**

    *@描述 :用户自定义功能。
    *@参数 :[SQL]
    *@返回值:java.lang.Object
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:08
    *@修改人和其它信息：
    *@版本：

    */
    protected Object defineFunction(String SQL){

        if (SQL.indexOf("select") == -1) {
            try {
                return queryRunner.update(SQL);
            } catch (SQLException e) {

                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("\n执行update失败！\n");
            }
        } else {
            if (SQL.contains("max") || SQL.contains("min") || SQL.contains("avg") || SQL.contains("sum")) {
                try {
                    return queryRunner.query(SQL, new ScalarHandler<>());
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("\n执行query失败！\n");

                }
            } else {
                try {
                    return queryRunner.query(SQL, new BeanListHandler<PBSInfo>(PBSInfo.class));
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("\n执行query失败！\n");
                }
            }
        }
        return null;

    }
}
