/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩序列化工具类
 */
public class CompressedUtils {

    /**
     * gzip解压
     *
     * @param inStr
     * @return
     * @throws IOException
     */
    public static byte[] gzipSerializerCompress(String inStr, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(inStr) || StringUtils.isNullOrWhiteSpace(encoding)){
            return null;
        }
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try(GZIPOutputStream gzip = new GZIPOutputStream(out)) {
                gzip.write(inStr.getBytes(encoding));
            }
            return out.toByteArray();
        }
    }

    /**
     * gzip解压
     *
     * @param is
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String gzipSerializerDecompress(InputStream is, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(encoding)){
            return null;
        }
        byte[] bytes = ConvertUtils.inputStream2Bytes(is);
        return gzipSerializerDecompress(bytes, encoding);
    }

    /**
     * gzip解压 (默认编码UTF-8)
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String gzipSerializerDecompress(InputStream is) throws IOException {
        return CompressedUtils.gzipSerializerDecompress(is, ConstantResource.ENCODING_UTF8);
    }


    /**
     * gzip解压
     *
     * @param bytes
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String gzipSerializerDecompress(byte[] bytes, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(encoding)){
            return null;
        }
        byte[] buffer = new byte[256];
        int n;
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                while ((n = ungzip.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
                return out.toString(encoding);
            }
        }
    }

    /**
     * gzip解压 (默认编码UTF-8)
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    public static String gzipSerializerDecompress(byte[] bytes) throws IOException {
        return gzipSerializerDecompress(bytes, ConstantResource.ENCODING_UTF8);
    }
}
