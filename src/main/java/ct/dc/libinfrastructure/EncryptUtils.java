/**
 * @author: wangzs
 * @createDate: 2017/03/15
 */


package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.exception.EncryptException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

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
            byteArray = ByteUtils.str2bytes(inStr);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        byte[] md5Bytes = md5.digest(byteArray);
        return ByteUtils.bytes2hexStr(md5Bytes).toLowerCase();
    }


    /**
     * 十六进制
     */
    public static final char[] HEX_DIGITS = {'0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f'};
    /**
     * md5 加密文件
     *
     * @param filePath
     * @return
     */
    public static String md5FileEncrypt32(String filePath) throws IOException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        File file = new File(filePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);) {
            int n;
            while ((n = fis.read(bytes)) != -1) {
                bos.write(bytes, 0, n);
            }
        }
        byte[] md5bytes = md5.digest(bos.toByteArray());
        return ByteUtils.bytes2hexStr(md5bytes);
    }

    private final static byte[] AES_IV = new byte[]{             //算法参数
            -12, 35, -25, 65, 45, -87, 95, -22, -15, 45, 55, -66, 32, 5 - 4, 84, 55
    };

    private static AlgorithmParameterSpec paramSpecAes;    //算法参数
    private static Cipher ecipherAes;                      //加密算法
    private final static String AES_ALGORITHM = "AES";
    private final static Object AES_LOCK = new Object();

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


    public static String sha1Encrypt(String str){
        if(str==null||str.length()==0){
            return null;
        }
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                buf[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    /**
     * sha2 加密
     * @param str
     * @return
     */
    public static String sha2Encrypt(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] shaBytes = messageDigest.digest();
            int j = shaBytes.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = shaBytes[i];
                buf[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                buf[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * @param tokenKey
     * @param source
     * @return
     */
    public static String desEncrypt(String tokenKey, String source) {
        String result = "";
        byte[] data = source.getBytes();
        SecretKeyFactory secretKeyFactory;
        try {
            secretKeyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec desKeySpec = new DESKeySpec(tokenKey.getBytes());
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            SecureRandom secureRandom = new SecureRandom();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
            data = cipher.doFinal(data);
            result = Base64.getEncoder().encodeToString(data);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return result;
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
            return result;
        } catch (InvalidKeySpecException ex) {
            ex.printStackTrace();
            return result;
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
            return result;
        } catch (IllegalBlockSizeException ex) {
            ex.printStackTrace();
            return result;
        } catch (BadPaddingException ex) {
            ex.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * des解密
     *
     * @param tokenKey
     * @param str
     * @return
     */
    public static String desDecrypt(String tokenKey, String str) {
        String result = "";
        SecretKeyFactory secretKeyFactory;
        byte[] data = Base64.getDecoder().decode(str);
        try {
            secretKeyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec desKeySpec = new DESKeySpec(tokenKey.getBytes());
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            result =new String(cipher.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 加密，使用指定数据源生成密钥，使用用户数据作为算法参数进行AES加密
     *
     * @param msg      加密的数据
     * @param password 密钥
     * @return
     */
    public static String aesEncrypt(String msg, String password) throws EncryptException {
        String str = "";
        byte[] buf;
        try {
            Key keyAes = new SecretKeySpec(ByteUtils.str2bytes(password), AES_ALGORITHM);
            synchronized (AES_LOCK) {
                //用密钥和一组算法参数初始化此 cipher
                ecipherAes.init(Cipher.ENCRYPT_MODE, keyAes, paramSpecAes);
                byte[] bytes = ByteUtils.str2bytes(msg);
                //加密并转换成16进制字符串
                buf = ecipherAes.doFinal(bytes);
            }
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
     *
     * @param value 解密的数据
     * @return
     */
    public static String aesDecrypt(String value, String password) throws EncryptException {
        try {
            Key keyAes = new SecretKeySpec(ByteUtils.str2bytes(password), AES_ALGORITHM);
            synchronized (AES_LOCK) {
                ecipherAes.init(Cipher.DECRYPT_MODE, keyAes, paramSpecAes);
                byte[] bytes = base64Decode(value);
                return ByteUtils.bytes2str(ecipherAes.doFinal(bytes));
            }
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
     *
     * @param data 需要加密的数据
     */
    public static String base64Encode(byte[] data) {
        return new BASE64Encoder().encode(data);
    }

    /**
     * 将原始数据编码为base64编码
     *
     * @param data 需要加密的数据
     */

    public static String base64Encode(String data) throws UnsupportedEncodingException {
        return base64Encode(ByteUtils.str2bytes(data));
    }

    /**
     * 将base64编码的数据解码成原始数据
     *
     * @param data 需要解密的数据
     */
    public static byte[] base64Decode(String data) throws IOException {
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return null;
        }
        return new BASE64Decoder().decodeBuffer(data);
    }


}
