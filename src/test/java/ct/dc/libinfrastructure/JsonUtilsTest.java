package ct.dc.libinfrastructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTWLPC on 2017/9/25.
 */
public class JsonUtilsTest {
    @Test
    public void jsonSerialize() throws Exception {
        StudentsInfo studentsInfo = new StudentsInfo(24,"ssf");
        StudentsInfo studentsInfo1 = new StudentsInfo(14,"one");
        ArrayList<StudentsInfo> studentsIfs = new ArrayList<>();
        studentsIfs.add(studentsInfo);
        studentsIfs.add(studentsInfo1);
        String jsonStr = JsonUtils.jsonSerialize(studentsIfs);
        System.out.println(jsonStr);
        assert jsonStr.length()> 0;
    }

    @Test
    public void jsonDeserialize() throws Exception {
        String jsonStr = "{\"age\":24,\"description\":\"19970101-ssf-24\",\"name\":\"ssf\"}";
        StudentsInfo studentsInfo = JsonUtils.jsonObjDeserialize(jsonStr,StudentsInfo.class);
        System.out.println(studentsInfo.toString());
        assert studentsInfo != null;
    }

    @Test
    public void jsonArrayDeserialize() throws Exception {
        String arrayStr = "[{\"age\":24,\"description\":\"19970101-ssf-24\",\"name\":\"ssf\"},{\"age\":14,\"description\":\"19970101-one-14\",\"name\":\"one\"}]";
        List<StudentsInfo> studentsIfs = JsonUtils.jsonListDeserialize(arrayStr,StudentsInfo.class);
        System.out.println(studentsIfs.toString());
        assert studentsIfs.size() > 0;
    }

}