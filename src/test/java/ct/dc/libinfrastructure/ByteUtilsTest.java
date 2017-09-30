package ct.dc.libinfrastructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CTWLPC on 2017/9/25.
 */
public class ByteUtilsTest {
    @Test
    public void bytes2hexStr() throws Exception {
        byte[] bytes = new byte[]{1,35,69,103,-119,-85,-51,-17};
        String hexStr = ByteUtils.bytes2hexStr(bytes);
        System.out.println(hexStr);
        assert hexStr.equalsIgnoreCase("0123456789abcdef");
    }

    @Test
    public void hexStr2bytes() throws Exception {
        String hexStr = "0123456789abcdef";
        byte[] bytes = ByteUtils.hexStr2bytes(hexStr);
        System.out.println(bytes.toString());
        assert bytes.length > 0;
    }

    @Test
    public void bytes2str() throws Exception {
        byte[] bytes = new byte[]{119,101,108,99,111,109,101,32,116,111,32,84,104,101,32,78,101,119,32,87,111,114,108,100};
        String str = ByteUtils.bytes2str(bytes);
        System.out.println(str);
        assert str.equalsIgnoreCase("welcome to The New World");
    }

    @Test
    public void bytes2str1() throws Exception {
        byte[] bytes = new byte[]{119,101,108,99,111,109,101,32,116,111,32,84,104,101,32,78,101,119,32,87,111,114,108,100};
        String str = ByteUtils.bytes2str(bytes,"gbk");
        System.out.println(str);
        assert str.equalsIgnoreCase("welcome to The New World");
    }

    @Test
    public void str2bytes() throws Exception {
        String str = "welcome to The New World";
        byte[] bytes = ByteUtils.str2bytes(str);
        for (byte b: bytes){
            System.out.println(b);
        }
        assert bytes.length > 0;
    }

    @Test
    public void str2bytes1() throws Exception {
        String str = "welcome to The New World";
        byte[] bytes = ByteUtils.str2bytes(str,"gbk");
        for (byte b: bytes){
            System.out.println(b);
        }
        assert bytes.length > 0;
    }

    @Test
    public void obj2bytes() throws Exception {
        StudentsInfo studentsInfo = new StudentsInfo(12,"sss");
        byte[] bytes = ByteUtils.obj2bytes(studentsInfo);
        for (byte b: bytes){
            System.out.println(b);
        }
        assert bytes.length > 0;
    }

    @Test
    public void byte2Obj() throws Exception {
        byte[] bytes = new byte[]{-84,-19,0,5,115,114,0,36,99,116,46,100,99,46,108,105,98,105,110,102,114,97,115,116,114,117,99,116,117,114,101,46,83,116,117,100,101,110,116,115,73,110,102,111,24,-9,-17,18,97,85,-101,-16,2,0,2,76,0,3,97,103,101,116,0,19,76,106,97,118,97,47,108,97,110,103,47,73,110,116,101,103,101,114,59,76,0,4,110,97,109,101,116,0,18,76,106,97,118,97,47,108,97,110,103,47,83,116,114,105,110,103,59,120,112,115,114,0,17,106,97,118,97,46,108,97,110,103,46,73,110,116,101,103,101,114,18,-30,-96,-92,-9,-127,-121,56,2,0,1,73,0,5,118,97,108,117,101,120,114,0,16,106,97,118,97,46,108,97,110,103,46,78,117,109,98,101,114,-122,-84,-107,29,11,-108,-32,-117,2,0,0,120,112,0,0,0,12,116,0,3,115,115,115};
        Object obj = ByteUtils.byte2Obj(bytes);
        System.out.println(obj.toString());
        assert obj != null;
    }

}