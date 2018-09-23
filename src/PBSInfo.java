import java.util.Objects;

/**
 * @创建人：黄强
 * @时间 ：2018/9/23 16:40
 * @描述 ：
 */
public class PBSInfo {
    private int ID;
    private String name;
    private String latitude;
    private String longitude;
    private  String date;
    private String describtion;

    public PBSInfo(int ID, String name, String latitude, String longitude, String date, String describtion) {
        this.ID=ID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.describtion = describtion;
    }
    public PBSInfo( String name, String latitude, String longitude, String date, String describtion) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.describtion = describtion;
    }
    public PBSInfo(){

    }

    public int getPBS_ID() {
        return ID;
    }

    public void setPBS_ID(int ID) {
        this.ID = ID;
    }

    public String getPBS_Name() {
        return name;
    }

    public void setPBS_Name(String name) {
        this.name = name;
    }

    public String getPBS_Latitude() {
        return latitude;
    }

    public void setPBS_Latitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPBS_Longitude() {
        return longitude;
    }

    public void setPBS_Longitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPBS_Date() {
        return date;
    }

    public void setPBS_Date(String date) {
        this.date = date;
    }

    public String getPBS_Desc() {
        return describtion;
    }

    public void setPBS_Desc(String describtion) {
        this.describtion = describtion;
    }

    @Override
    public String toString() {
        return "PBSInfo{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", date='" + date + '\'' +
                ", describtion='" + describtion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PBSInfo pbsInfo = (PBSInfo) o;
        return ID == pbsInfo.ID &&
                Objects.equals(name, pbsInfo.name) &&
                Objects.equals(latitude, pbsInfo.latitude) &&
                Objects.equals(longitude, pbsInfo.longitude) &&
                Objects.equals(date, pbsInfo.date) &&
                Objects.equals(describtion, pbsInfo.describtion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, latitude, longitude, date, describtion);
    }
}
