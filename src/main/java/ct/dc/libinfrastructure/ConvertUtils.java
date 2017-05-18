/**
 * @author: wangzs
 * @createDate: 2017/03/15
 */

package ct.dc.libinfrastructure;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Objects;

/**
 * 类型转换工具
 */
public class ConvertUtils {

    /**
     * 字符串转化成int
     *
     * @param intStr
     * @param defaultValue
     * @return
     */
    public static Integer str2int(String intStr, Integer defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(intStr)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(intStr);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 字符串转化成long
     *
     * @param longStr
     * @param defaultValue
     * @return
     */
    public static Long str2long(String longStr, Long defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(longStr)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(longStr);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 字符串转化成bool
     *
     * @param boolStr
     * @param defaultValue
     * @return
     */
    public static Boolean str2bool(String boolStr, Boolean defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(boolStr)) {
            return defaultValue;
        }
        return Boolean.valueOf(boolStr);
    }

    /**
     * int转化成字符串
     *
     * @param num
     * @return
     */
    public static String int2str(Integer num) {
        if (num == null) {
            return null;
        }
        return String.valueOf(num);
    }

    /**
     * long转化成字符串
     *
     * @param num
     * @return
     */
    public static String long2str(Long num) {
        if (num == null) {
            return null;
        }
        return String.valueOf(num);
    }

    /**
     * boolean转化成字符串
     *
     * @param bool
     * @return
     */
    public static String bool2str(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return String.valueOf(bool);
    }

    /**
     * InputStream转换到StringBuffer
     *
     * @param is       InputStream
     * @param encoding 编码
     * @return
     */
    public static StringBuffer inputStream2StringBuffer(InputStream is, String encoding) throws IOException {
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

    /**
     * InputStream转换到二进制数组
     *
     * @param is inputstream
     * @return
     */
    public static byte[] inputStream2Bytes(InputStream is) throws IOException {
        byte[] bytes;
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            byte[] buff = new byte[64];
            int rc = 0;
            while ((rc = is.read(buff, 0, 64)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            bytes = swapStream.toByteArray();
        }
        return bytes;
    }

    /**
     * 二进制转流
     *
     * @param buf 二进制
     * @return
     */
    public static final ByteArrayInputStream bytes2inputStream(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * 从字符串转换到二进制数组
     *
     * @param str      字符串
     * @param encoding 编码
     * @return
     */
    public static byte[] str2bytes(String str, String encoding) throws UnsupportedEncodingException {
        if (StringUtils.isNullOrWhiteSpace(str) || StringUtils.isNullOrWhiteSpace(encoding)) {
            return null;
        }
        return str.getBytes(encoding);
    }

    /**
     * 从字符串转换到二进制数组
     *
     * @param str 字符串
     * @return
     */
    public static byte[] str2bytes(String str) throws UnsupportedEncodingException {
        return str2bytes(str, ConstantResource.ENCODING_UTF8);
    }

    /**
     * 从二进制数组转换到字符串转
     *
     * @param bytes    二进制数组
     * @param encoding 编码
     * @return
     */
    public static String bytes2str(byte[] bytes, String encoding) throws UnsupportedEncodingException {
        if (bytes == null || encoding == null) {
            return null;
        }
        return new String(bytes, encoding);
    }

    /**
     * 从二进制数组转换到字符串转
     *
     * @param bytes 二进制数组
     * @return
     */
    public static String bytes2str(byte[] bytes) throws UnsupportedEncodingException {
        return bytes2str(bytes, ConstantResource.ENCODING_UTF8);
    }

    /**
     * json序列化
     *
     * @param obj 序列化对象
     * @return
     */
    public static String jsonSerialize(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json反序列化
     *
     * @param jsonStr   json字符串
     * @param valueType 反序列化类型
     * @return
     */
    public static <T> T jsonDeserialize(String jsonStr, Class<T> valueType) {
        return JSON.parseObject(jsonStr, valueType);
    }

    /**
     * 二进制转成16进制
     *
     * @param bytes 二进制数组
     * @return
     */
    public static String bytes2hexstr(byte[] bytes) {
        StringBuffer strbuf = new StringBuffer(bytes.length * 2);
        int i;
        for (i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10)//小于十前面补零
                strbuf.append("0");
            strbuf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    /**
     * 16进制转成二进制
     *
     * @param hexStr 16进制字符串
     * @return
     */
    public static byte[] hexstr2bytes(String hexStr) {
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
}
