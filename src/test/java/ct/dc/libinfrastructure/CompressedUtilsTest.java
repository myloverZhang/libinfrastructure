package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-17.
 */
public class CompressedUtilsTest {
    @Test
    public void gzipSerializerCompress() throws Exception {
        String str = "Welcome to The New World";
        byte[] bytes = CompressedUtils.gzipSerializerCompress(str,"utf-8");
        for (byte b:bytes){
            System.out.print(String.valueOf(b) + ",");
        }
        assert bytes.length > 0;
    }

    @Test
    public void gzipSerializerDecompress() throws Exception {
        byte[] bytes = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        String str = CompressedUtils.gzipSerializerDecompress(bytes);
        System.out.println(str);
        assert str.equalsIgnoreCase("Welcome to The New World");
    }

    @Test
    public void gzipSerializerDecompress1() throws Exception {
        byte[] bytes = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        String str = CompressedUtils.gzipSerializerDecompress(bytes, "gbk");
        System.out.println(str);
        assert str.equalsIgnoreCase("Welcome to The New World");
    }

    @Test
    public void gzipSerializerDecompress2() throws Exception {
        byte[] bytes = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        InputStream is = new ByteArrayInputStream(bytes);
        String str = CompressedUtils.gzipSerializerDecompress(is,"utf-8");
        System.out.println(str);
        assert str.equalsIgnoreCase("Welcome to The New World");
    }

    @Test
    public void gzipSerializerDecompress3() throws Exception {
        byte[] bytes = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        InputStream is = new ByteArrayInputStream(bytes);
        String str = CompressedUtils.gzipSerializerDecompress(is);
        System.out.println(str);
        assert str.equalsIgnoreCase("Welcome to The New World");
    }

    @Test
    public void deflate() throws Exception {
        byte[] source = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        byte[] des = CompressedUtils.deflate(source,9);
        for (byte b:des){
            System.out.print(String.valueOf(b) + ",");
        }
        assert des.length > 0;
    }

    @Test
    public void inflate() throws Exception {
        byte[] source = new byte[]{120,-38,-109,-17,-26,96,-128,0,110,-1,-77,-98,-25,-50,-5,-122,106,-100,12,-25,56,-23,17,-6,-63,91,55,-100,-29,-68,-2,41,79,70,-122,-98,79,113,124,18,64,21,0,19,-34,12,-13};
        byte[] des = CompressedUtils.inflate(source);
        for (byte b:des){
            System.out.print(String.valueOf(b) + ",");
        }
        assert des.length > 0;
    }

    @Test
    public void snappyCompress() throws Exception {
        byte[] bytes = new byte[]{31,-117,8,0,0,0,0,0,0,0,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        byte[] des = CompressedUtils.snappyCompress(bytes);
        for (byte b:des){
            System.out.print(String.valueOf(b) + ",");
        }
        assert des.length > 0;
    }

    @Test
    public void snappyDecompress() throws Exception {
        byte[] bytes = new byte[]{44,12,31,-117,8,0,9,1,-124,11,79,-51,73,-50,-49,77,85,40,-55,87,8,-55,72,85,-16,75,45,87,8,-49,47,-54,73,1,0,-116,-14,94,14,24,0,0,0};
        byte[] des = CompressedUtils.snappyDecompress(bytes);
        for (byte b:des){
            System.out.print(String.valueOf(b) + ",");
        }
        assert des.length > 0;
    }


}