package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-16.
 */
public class StringUtilsTest {
    @Test
    public void isNullOrWhiteSpace() throws Exception {
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(""));
    }

    @Test
    public void isNumeric() throws Exception {
        Assert.assertTrue(StringUtils.isNumeric("1232131231"));
    }

    @Test
    public void isEmail() throws Exception {
        Assert.assertTrue(StringUtils.isEmail("sdfsdfds@163.com"));
    }

    @Test
    public void isIPv4() throws Exception {
        Assert.assertTrue(StringUtils.isIPv4("192.168.1.12"));
    }

}