package ct.dc.libinfrastructure;

import java.io.*;

/**
 * Created by CTWLPC on 2017/9/25.
 */
public class ByteUtils {
    /**
     * 二进制数组转换成16进制字符串
     * @param bytes 二进制数组
     * @return
     */
    public static String bytes2hexStr(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        for (i = 0;i < bytes.length;i++){
            if (((int)bytes[i] & 0xff)<0x10){
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toString((int)bytes[i] & 0xff, 16));
        }
        return stringBuffer.toString();
    }

    /**
     * 十六进制字符串转二进制数组
     * @param hexStr 十六进制字符串
     * @return
     */
    public static byte[] hexStr2bytes(String hexStr){
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 二进制数组转换成字符串
     * @param bytes 二进制数组
     * @param encoding 编码方式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String bytes2str(byte[] bytes,String encoding) throws UnsupportedEncodingException {
        if (bytes == null || bytes.length == 0)
            return null;
        return new String(bytes,encoding);
    }

    /**二进制数组转换成字符串
     *
     * @param bytes 二进制数组
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String bytes2str(byte[] bytes) throws UnsupportedEncodingException {
        return bytes2str(bytes,ConstantResource.ENCODING_UTF8);
    }
    /**
     * 字符串转成二进制数组
     * @param str  被转换字符串
     * @param encoding 编码方式
     * @return
     */
    public static byte[] str2bytes(String str, String encoding) throws UnsupportedEncodingException {
        if (StringUtils.isNullOrWhiteSpace(str) || StringUtils.isNullOrWhiteSpace(encoding))
            return null;
        return str.getBytes(ConstantResource.ENCODING_UTF8);
    }


    /**
     * 字符串转换成二进制数组
     * @param str 字符串
     * @return
     */
    public static byte[] str2bytes(String str) throws UnsupportedEncodingException {
        return str2bytes(str,ConstantResource.ENCODING_UTF8);
    }

    /**
     * 将object 类型转成而二进制数组
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] obj2bytes(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        return bos.toByteArray();
    }

    /**
     * 二进制数组转换成实体类
     * @param bytes
     * @param <T>
     * @return
     */
    public static <T> T byte2Obj(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T)ois.readObject();
    }


}
