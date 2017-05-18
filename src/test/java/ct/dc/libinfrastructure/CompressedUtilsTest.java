package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-17.
 */
public class CompressedUtilsTest {
    @Test
    public void gzipSerializerCompress() throws Exception {

    }

    @Test
    public void gzipSerializerDecompress() throws Exception {
        String encoding = "utf-8";
        String sss= "jfakljlk4mlk好收到发端口呃是端口来看望就富士康了房间89jscvnowf\n\"sdf\r\t\\sdfskjlsjklvl";
        byte[] gzip = CompressedUtils.gzipSerializerCompress(sss, encoding);
        Assert.assertTrue(CompressedUtils.gzipSerializerDecompress(gzip, encoding).equals(sss));
    }

    @Test
    public void gzipSerializerDecompress1() throws Exception {

    }

    @Test
    public void gzipSerializerDecompress2() throws Exception {

    }

    @Test
    public void gzipSerializerDecompress3() throws Exception {

    }

}