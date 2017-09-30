package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-20.
 */
public class EncryptUtilsTest {
    @Test
    public void sha1Encrypt() throws Exception {
        String source = "Welcome The New World";
        String sha1 = EncryptUtils.sha1Encrypt(source);
        System.out.println(sha1);
        assert sha1.equals("7d30554e49359f9af6fc4d42b533720588731339");
    }

    @Test
    public void sha2Encrypt() throws Exception {
        String source = "Welcome The New World";
        String sha256 = EncryptUtils.sha2Encrypt(source);
        System.out.print(sha256);
        assert sha256.equals("069ff984add13f8a6b8ae6f29b83eaf27dc6551fc9378f54d3b808c129e18a8c");
    }

    @Test
    public void desEncrypt() throws Exception {
        String source = "Welcome to China";
        String tokenKey = "12345678";
        String encryptStr = EncryptUtils.desEncrypt(tokenKey,source);
        System.out.println(encryptStr);
        assert encryptStr.equals("tmrlut0mU+RvNex8oFEH//65WbfUZC/L");
    }

    @Test
    public void desDecrypt() throws Exception {
        String source = "tmrlut0mU+RvNex8oFEH//65WbfUZC/L";
        String tokenKey = "12345678";
        String decryptStr = EncryptUtils.desDecrypt(tokenKey,source);
        System.out.println(decryptStr);
        assert decryptStr.equals("Welcome to China");
    }

    @Test
    public void md5FileEncrypt32() throws Exception {
        String filePath = "E:\\test.txt";
        String mdtStr = EncryptUtils.md5FileEncrypt32(filePath);
        System.out.println(mdtStr);
        assert mdtStr.length()>0;
    }

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
        String decrypt = ByteUtils.bytes2str(EncryptUtils.base64Decode(encrypt));
        System.out.println("BASE64 DECRYPT:" + decrypt);
        Assert.assertTrue(decrypt.equals(content));
    }
}