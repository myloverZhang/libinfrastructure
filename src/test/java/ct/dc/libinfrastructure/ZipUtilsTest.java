package ct.dc.libinfrastructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CTWLPC on 2017/10/12.
 */
public class ZipUtilsTest {
    @Test
    public void zipCompress() throws Exception {
        ZipUtils.zipCompress("E:\\shusf","E:\\","zzzzB.zip");
        assert true;
    }

    @Test
    public void zipDecompress() throws Exception {
        ZipUtils.zipDecompress("E:\\zzzza.zip","E:\\zzzza",false);
        assert true;
    }

}