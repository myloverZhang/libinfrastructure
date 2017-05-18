package ct.dc.libinfrastructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @auther will
 * @createDate 17-4-4.
 */
public class ConfigUtilsTest {
    @Test
    public void getConfig() throws Exception {
        System.out.println(RuntimeSystemUtils.getProjectRootPath());
        ConfigUtils utils = new ConfigUtils("sss");
        System.out.println(utils.getConfig("a", "null-null"));
    }
}