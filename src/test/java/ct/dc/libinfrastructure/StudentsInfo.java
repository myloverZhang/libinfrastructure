package ct.dc.libinfrastructure;

import java.io.Serializable;

/**
 * Created by CTWLPC on 2017/9/22.
 */
public class StudentsInfo implements Serializable{
    private Integer age;
    private String name;

    public StudentsInfo() {
    }

    public StudentsInfo(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static String getMyDescription(Integer date){
        return String.format("%d-static ===============",date);
    }
    public static String getMyDescription(){
        return String.format("no params static method call===============");
    }

    public String getDescription(Integer date){
        return String.format("%d-%s-%d",date,name,age);
    }
    public String getDescription(){
        return String.format("19970101-%s-%d",name,age);
    }
    @Override
    public String toString() {
        return "StudentsInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
