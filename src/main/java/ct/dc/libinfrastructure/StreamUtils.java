package ct.dc.libinfrastructure;

import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Created by CTWLPC on 2017/9/25.
 */
public class StreamUtils {
    /**
     * 二进制数组转成输入流
     * @param buf
     * @return
     */
    public static ByteArrayInputStream bytes2inputStream(byte[] buf){
        ByteArrayInputStream is = new ByteArrayInputStream(buf);
        return is;
    }


    /**
     * 输入流转成二进制数组
     * @param is
     * @return
     */
    public static byte[] inputStream2bytes(InputStream is) throws IOException {
        byte[] bytes;
        try(ByteOutputStream bos = new ByteOutputStream()) {
            byte[] buff = new byte[64];
            int src = 0;
            while ((src = is.read(buff, 0, 64)) > 0){
                bos.write(buff,0,src);
            }
            bytes = bos.getBytes();
        }
        return bytes;
    }

    /**
     * 输入流转成stringbuffer
     * @param is
     * @param encoding
     * @return
     */
    public static StringBuffer inputStream2stringBuffer(InputStream is,String encoding) throws IOException {
        if (StringUtils.isNullOrWhiteSpace(encoding)) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        try (InputStreamReader inputStreamReader = new InputStreamReader(is, encoding)) {
            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
            }
        }
        return buffer;
    }
}
