import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

/**
 * @创建人：黄强
 * @时间 ：2018/9/24 10:33
 * @描述 ：
 */
public class Test {
    public static void main(String[] args) {
        PBSUtils pbsUtils=new PBSUtils();
        LinkedList<PBSInfo> pbsInfoList=new LinkedList<>();
        PBSInfo pbsInfo_0=new PBSInfo("PBS_1","39.908719","116.397479","2018-1-1","北京");
        PBSInfo pbsInfo_2=new PBSInfo("PBS_3","31.114176","121.606775","2018-4-1","上海");
        PBSInfo pbsInfo_1=new PBSInfo("PBS_2","43.725784","126.796370","2018-4-1","长春");
        pbsInfoList.add(pbsInfo_0);
        pbsInfoList.add(pbsInfo_1);
        pbsInfoList.add(pbsInfo_2);
       ShowPBS.writeName(pbsInfoList);
       ShowPBS.showImage();
    }
}
