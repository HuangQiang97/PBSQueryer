/**
 * @创建人：黄强
 * @时间 ：2018/9/23 16:08
 * @描述 ：启动程序。
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("===========================================================================================================");
        System.out.println(String.format("%45s","               欢迎使用伪基站查询系统            Powered By:HuangQiang"));
        PBSDao.basicDataSource=PBSUtils.getDataSource();
        new PBSMainView().run();
    }
}
