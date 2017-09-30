package ct.dc.libinfrastructure;

import com.alibaba.fastjson.JSON;
import ct.dc.libinfrastructure.common.AccessRankEnum;
import org.junit.Test;

/**
 * Created by CTWLPC on 2017/9/21.
 */
public class ReflectorTest {
    @Test
    public void invokeClassMethod() throws Exception {
        //Object result = Reflector.invokeStaticMethod("ct.dc.libinfrastructure.StudentsInfo","getMyDescription",new Class[]{Integer.class},new Object[]{43});
        //Object result = Reflector.invokeStaticMethod("ct.dc.libinfrastructure.StudentsInfo","getMyDescription",null,null);
        Object result = Reflector.invokeStaticMethod("ct.dc.libinfrastructure.StudentsInfo","getMyDescription",new Class[]{Integer.class},new Object[]{null});
        System.out.println(result.toString());
        assert result != null;
    }

    @Test
    public void invoke() throws Exception {
       // Object result = Reflector.invoke("ct.dc.libinfrastructure.StudentsInfo","getDescription",new Class[]{Integer.class,String.class},new Object[]{32,"zz"},new Class[]{Integer.class},new Object[]{20170921});
       // Object result = Reflector.invoke("ct.dc.libinfrastructure.StudentsInfo","getDescription",new Class[]{},new Object[]{},new Class[]{Integer.class},new Object[]{20170921});
        Object result = Reflector.invoke("ct.dc.libinfrastructure.StudentsInfo","getDescription",new Class[]{Integer.class,String.class},new Object[]{32,"zz"},null,null);
        System.out.println(result.toString());
        assert result != null;
    }

    @Test
    public void setAttribute() throws Exception {
        Object studentsInfo = Reflector.createInstance("ct.dc.libinfrastructure.StudentsInfo",null,null);
        Reflector.setAttribute(studentsInfo, "age",43,Integer.class);
        if (studentsInfo.getClass() == StudentsInfo.class)
            System.out.println("=======");
        System.out.println(studentsInfo.toString());
        assert studentsInfo != null;
    }


    @Test
    public void createInstance() throws Exception {
        Object obj = Reflector.createInstance("ct.dc.libinfrastructure.StudentsInfo",new Class[]{Integer.class,String.class},new Object[]{12,"shusf"});
        System.out.println(obj.toString());
        assert obj.toString().length()>10;
    }

    @Test
    public void listClassMethods() throws Exception {
        String[] methods = Reflector.listClassMethods("ct.dc.libinfrastructure.StringUtils",false,AccessRankEnum.PRIVATE);
        System.out.println(JSON.toJSONString(methods));
        assert methods.length > 0;
    }

    @Test
    public void listClassFields() throws Exception {
        String[] fields = Reflector.listClassFields("ct.dc.libinfrastructure.StringUtils",true, AccessRankEnum.ALL);
        assert fields.length>=0;
    }

    @Test
    public void getClassType() throws Exception {
        Class clazz = Reflector.getClassType("ct.dc.libinfrastructure.StringUtils");
        System.out.println(clazz.toString());
        assert clazz!=null;
    }

}