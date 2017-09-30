import java.io.ByteArrayInputStream;
import java.io.InputStream;

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


    public static byte[] inputStream2bytes(InputStream is){
        
    }
}
