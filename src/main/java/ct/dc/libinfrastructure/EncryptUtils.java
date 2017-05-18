/**
 * @author: wangzs
 * @createDate: 2017/03/15
 */


package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.exception.EncryptException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 加密工具库
 */
public class EncryptUtils {

    /**
     * md5 32位加密 (当md5库环境有异常时，返回null)
     *
     * @param inStr
     * @return
     */
    public static final String md5Encrypt32(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] byteArray = new byte[0];
        try {
            byteArray = ConvertUtils.str2bytes(inStr);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        byte[] md5Bytes = md5.digest(byteArray);
        return ConvertUtils.bytes2hexstr(md5Bytes).toLowerCase();
    }

    private final static byte[] AES_IV = new byte[]{             //算法参数
            -12, 35, -25, 65, 45, -87, 95, -22, -15, 45, 55, -66, 32, 5 - 4, 84, 55
    };

    private static AlgorithmParameterSpec paramSpecAes;    //算法参数
    private static Cipher ecipherAes;                      //加密算法
    private final static String AES_ALGORITHM = "AES";

    static {
        try {
            //使用iv中的字节作为IV来构造一个 算法参数。
            paramSpecAes = new IvParameterSpec(AES_IV);
            //生成一个实现指定转换的 Cipher 对象
            ecipherAes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密，使用指定数据源生成密钥，使用用户数据作为算法参数进行AES加密
     * @param msg 加密的数据
     * @param password 密钥
     * @return
     */
    public static String aesEncrypt(String msg, String password) throws EncryptException {
        String str = "";
        try {
            Key keyAes = new SecretKeySpec(ConvertUtils.str2bytes(password), AES_ALGORITHM);
            //用密钥和一组算法参数初始化此 cipher
            ecipherAes.init(Cipher.ENCRYPT_MODE, keyAes, paramSpecAes);
            byte[] bytes = ConvertUtils.str2bytes(msg);
            //加密并转换成16进制字符串
            byte[] buf = ecipherAes.doFinal(bytes);
            str = base64Encode(buf);
        } catch (BadPaddingException e) {
            throw new EncryptException("BadPaddingException", e);
        } catch (InvalidKeyException e) {
            throw new EncryptException("InvalidKeyException", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new EncryptException("InvalidAlgorithmParameterException", e);
        } catch (IllegalBlockSizeException e) {
            throw new EncryptException("IllegalBlockSizeException", e);
        } catch (UnsupportedEncodingException e) {
            throw new EncryptException("UnsupportedEncodingException", e);
        }
        return str;
    }

    /**
     * 解密，对生成的16进制的字符串进行解密
     * @param value 解密的数据
     * @return
     */
    public static String aesDecrypt(String value, String password) throws EncryptException {
        try {
            Key keyAes = new SecretKeySpec(ConvertUtils.str2bytes(password), AES_ALGORITHM);
            ecipherAes.init(Cipher.DECRYPT_MODE, keyAes, paramSpecAes);
            byte[] bytes = base64Decode(value);
            return ConvertUtils.bytes2str(ecipherAes.doFinal(bytes));
        } catch (BadPaddingException e) {
            throw new EncryptException("BadPaddingException", e);
        } catch (InvalidKeyException e) {
            throw new EncryptException("InvalidKeyException", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new EncryptException("InvalidAlgorithmParameterException", e);
        } catch (IllegalBlockSizeException e) {
            throw new EncryptException("IllegalBlockSizeException", e);
        } catch (IOException e) {
            throw new EncryptException("IOException", e);
        }
    }

    /**
     * 将原始数据编码为base64编码
     * @param data 需要加密的数据
     */
    public static String base64Encode(byte[] data) {
        return new BASE64Encoder().encode(data);
    }

    /**
     * 将原始数据编码为base64编码
     * @param data 需要加密的数据
     */

    public static String base64Encode(String data) throws UnsupportedEncodingException {
        return base64Encode(ConvertUtils.str2bytes(data));
    }

    /**
     * 将base64编码的数据解码成原始数据
     * @param data 需要解密的数据
     */
    public static byte[] base64Decode(String data) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(data)){
            return null;
        }
        return new BASE64Decoder().decodeBuffer(data);
    }
}
