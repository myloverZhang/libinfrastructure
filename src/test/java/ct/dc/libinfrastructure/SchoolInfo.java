package ct.dc.libinfrastructure;

/**
 * Created by CTWLPC on 2017/10/12.
 */
public class SchoolInfo {
    private String name;
    private String location;

    public SchoolInfo(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
