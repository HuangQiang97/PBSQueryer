import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @创建人：黄强
 * @时间 ：2018/9/23 16:19
 * @描述 ：
 */
public class PBSMainView {
    Scanner scanner=new Scanner(System.in);
    private PBSDao  pbsDao=new PBSDao();
    /**

     *@描述 :mainView主体。
     *@参数 :[]
     *@返回值:void
     *@创建人 : 黄强
     *@创建时间  2018/9/2 22:41
     *@修改人和其它信息：
     *@版本：

     */
    public void run(){
        while (true){
            System.out.println("===========================================================================================================");
            System.out.println("1:添加； 2：编辑； 3：删除； 4：查询； 5：自定义功能； 6：退出系统");
            System.out.print("请输入要操作的序列号[1~6]:");
            int choose=scanner.nextInt();
            switch(choose){
                case 1:addInfo();
                    break;
                case 2:editInfo();
                    break;
                case 3:deletInfo();
                    break;
                case 4:queryInfo();
                    break;
                case 5:defineFunction();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n输入有误！\n");
            }
        }
    }
/**

*@描述 :添加信息。
*@参数 :[]
*@返回值:void
*@创建人 : 黄强
*@创建时间  2018/9/24 0:41
*@修改人和其它信息：
*@版本：

*/
    private void addInfo(){
        System.out.print("请输入基站名称或者代号:");
        String name=scanner.next();
        System.out.print("请输入基站纬度：");
        String latitude=scanner.next();
        System.out.print("请输入基站经度：");
        String longitude=scanner.next();
        scanner.nextLine();
        System.out.print("请输入日期，形如：XXXX-XX-XX,可以不用输入，默认当前时间：");
        String date=null;
        date=scanner.nextLine();
        System.out.print("请输入描述：");
        String describtion=scanner.nextLine();
        PBSInfo PBSRecord=new PBSInfo(name,latitude,longitude,date,describtion);
        PBSDao.addInfo(PBSRecord);

    }
    /**

    *@描述 :删除记录。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:43
    *@修改人和其它信息：
    *@版本：

    */
    private void deletInfo(){
        queryAll();
        System.out.println("请输入要删除的条目ID:");
        int deleteChoose=scanner.nextInt();
        pbsDao.deleteInfo(deleteChoose);
    }
    /**

    *@描述 :编辑基站信息。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:43
    *@修改人和其它信息：
    *@版本：

    */
    private void editInfo(){
        queryAll();
        System.out.print("请输入要修改的条目的ID:");
        int editChoose=scanner.nextInt();
        System.out.println("下列参数若不填写即默认不修改该项！");
        scanner.nextLine();
        System.out.print("请输入新名称:");
        String name=scanner.nextLine();
        System.out.print("请输入纬度：");
        String latitude=scanner.nextLine();
        System.out.print("请输入经度：");
        String longitude=scanner.nextLine();
        System.out.print("请输入日期，形如：XXXX-XX-XX,可以不用输入，默认当前时间：");
        String date=scanner.nextLine();
        System.out.print("请输入描述：");
        String describtion=scanner.nextLine();
        PBSInfo billRecord=new PBSInfo(editChoose,name,latitude,longitude,date,describtion);
        pbsDao.editInfo(billRecord);
    }

   /**

   *@描述 :查询基站。
   *@参数 :[]
   *@返回值:void
   *@创建人 : 黄强
   *@创建时间  2018/9/24 0:45
   *@修改人和其它信息：
   *@版本：

   */
    private void queryInfo(){
        System.out.println("===========================================================================================================");
        System.out.println("1. 查询所有；    2. 按日期查询；  3：按距离查询；");
        System.out.print("请输入要操作的序列号：[1~3]:");
        // Scanner scanner=new Scanner(System.in);
        int queryChoose=scanner.nextInt();
        switch (queryChoose){
            case 1:
                queryAll();
                break;
            case 2:
                queryByDate();
                break;
            case 3:
                queryByDistance();
                break;
            default:
                System.out.println("\n输入有误！\n");
        }
    }
    /**

    *@描述 :查询所有记录。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:45
    *@修改人和其它信息：
    *@版本：

    */
    private void queryAll(){
        List<PBSInfo> recoeds= pbsDao.queryAllInfo();
        if (recoeds.size()!=0) {
            printRecord(recoeds);
        }else {
            System.out.println("查询结果为空！");
        }
    }
    /**

    *@描述 :依据距离查询。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:46
    *@修改人和其它信息：
    *@版本：

    */
    private void queryByDistance(){
        System.out.println("请输入当前纬度：");
        double x=scanner.nextDouble();
        System.out.println("请输入当前经度：");
        double y=scanner.nextDouble();
        System.out.println("请输入查新范围半径（单位：米）：");
        double targetDistance=scanner.nextDouble();
       List<PBSInfo>result=pbsDao.queryByDistance(x,y,targetDistance);
        if (result.size()!=0){
            printRecord(result);
        }else{
            System.out.println("\n查询结果为空！\n");
        }
    }
    /**

    *@描述 :按日期查询。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:59
    *@修改人和其它信息：
    *@版本：

    */
    private void queryByDate(){
        System.out.print("请输入开始日期，形如：XXXX-XX-XX:");
        String startDate=scanner.next();
        System.out.print("\n请输入结束日期，形如：XXXX-XX-XX:");
        String endDate=scanner.next();
        System.out.println();
        List<PBSInfo> recoeds=pbsDao.queryByDate(startDate,endDate);
        if (recoeds.size()!=0){
            printRecord(recoeds);
        }else{
            System.out.println("\n查询结果为空！\n");
        }
    }
    /**

    *@描述 :用户自定义功能。
    *@参数 :[]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 0:59
    *@修改人和其它信息：
    *@版本：

    */
    private void defineFunction()
    {
        System.out.println("===========================================================================================================");
        System.out.println("数据库信息：表名：IP_tablesort；字段名：PBS_ID,PBS_Name,PBS_Latitude,PBS_Longitude,PBS_Date,PBS_Desc.");
        System.out.print("请输入SQL语句：String SQL=");
        scanner.nextLine();
        String SQL=scanner.nextLine();
        Object result= pbsDao.defineFunction(SQL);
        try{
            printRecord( (List<PBSInfo>)result);
        }catch (Exception e){
            System.out.println("查询结果："+result.toString());
        }
    }
    /**

    *@描述 :输出查询结果。
    *@参数 :[recoeds]
    *@返回值:void
    *@创建人 : 黄强
    *@创建时间  2018/9/24 1:00
    *@修改人和其它信息：
    *@版本：

    */
    private void printRecord(List<PBSInfo> recoeds){
        if (recoeds!=null) {
            System.out.println("===========================================================================================================");

            System.out.println("ID\tName\t\tLatitude\tLongitude\tdate\t\ttdescribltion");

            for (PBSInfo PBSRecord : recoeds
            ) {
                System.out.println(String.format("% -8d", PBSRecord.getPBS_ID()) + String.format("%-15.15s",PBSRecord.getPBS_Name()) + String.format("%-15.15s", PBSRecord.getPBS_Latitude())
                        + String.format("%-15.15s", PBSRecord.getPBS_Longitude()) + String.format("%-15.15s", PBSRecord.getPBS_Date()) + String.format("%-15.15s", PBSRecord.getPBS_Desc()));
            }
        }
    }
}
