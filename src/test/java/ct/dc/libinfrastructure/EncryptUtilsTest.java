package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-20.
 */
public class EncryptUtilsTest {
    @Test
    public void md5Encrypt32() throws Exception {
        String content = "xklsd类似的dsfsdf好的sfsjdlfsdl";
        String res = EncryptUtils.md5Encrypt32(content);
        System.out.println("MD5:" + res);
        Assert.assertTrue(res.equals("6f390a4a8e7ad6ba8931ff0812bc5d65"));
    }

    @Test
    public void aes() throws Exception{
        String content = "jdbc:mysql://192.168.1.82:3306/wangzsdb?user=root&password=333dkx8s&serverTimezone=UTC";
        String password = "Y3231oU_8#k3429=";   //1234567812345678
        String encrypt = EncryptUtils.aesEncrypt(content, password);
        System.out.println("AES ENCRYPT:" + encrypt);
        String decrypt = EncryptUtils.aesDecrypt(encrypt,password);
        System.out.println("AES DECRYPT:" + decrypt);
        Assert.assertTrue(decrypt.equals(content));
    }

    @Test
    public void base64() throws Exception{
        String content = "jdbc:mysql://192.168.1.82:3306/wangzsdb?user=root&password=333dkx8s&serverTimezone=UTC";
        String encrypt = EncryptUtils.base64Encode(content);
        System.out.println("BASE64 ENCRYPT:" + encrypt);
        String decrypt = ConvertUtils.bytes2str(EncryptUtils.base64Decode(encrypt));
        System.out.println("BASE64 DECRYPT:" + decrypt);
        Assert.assertTrue(decrypt.equals(content));
    }
}