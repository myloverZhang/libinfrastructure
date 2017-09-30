package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by will on 17-3-21.
 */
public class ConvertUtilsTest {
    @Test
    public void str2int() throws Exception {
        String str = "1234566";
        int number = ConvertUtils.str2int(str,0);
        System.out.println(number);
        assert number == 1234566;
    }

    @Test
    public void str2int1() throws Exception {
        String str = "1234566";
        int number = ConvertUtils.str2int(str);
        System.out.println(number);
        assert number == 1234566;
    }

    @Test
    public void str2long() throws Exception {
        String str = "1234566";
        long number = ConvertUtils.str2long(str,0l);
        System.out.println(number);
        assert number == 1234566;
    }

    @Test
    public void str2long1() throws Exception {
        String str = "1234566";
        long number = ConvertUtils.str2long(str);
        System.out.println(number);
        assert number == 1234566;
    }

    @Test
    public void str2bool() throws Exception {
        String str = "true";
        Boolean bool = ConvertUtils.str2bool(str,false);
        System.out.println(bool);
        assert bool;
    }

    @Test
    public void str2bool1() throws Exception {
        String str = "true";
        Boolean bool = ConvertUtils.str2bool(str);
        System.out.println(bool);
        assert bool;
    }

    @Test
    public void int2str() throws Exception {
        int number = 123456;
        String str = ConvertUtils.int2str(number);
        System.out.println(str);
        assert str.equalsIgnoreCase("123456");
    }

    @Test
    public void long2str() throws Exception {
        long number = 12345678L;
        String str = ConvertUtils.long2str(number);
        System.out.println(str);
        assert str.equalsIgnoreCase("12345678");
    }

    @Test
    public void bool2str() throws Exception {
        boolean flag = true;
        String str = ConvertUtils.bool2str(flag);
        System.out.println(str);
        assert str.equalsIgnoreCase("true");
    }


}