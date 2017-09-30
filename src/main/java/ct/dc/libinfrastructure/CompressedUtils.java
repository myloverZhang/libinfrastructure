/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.exception.DeflateCompressLevelException;
import org.xerial.snappy.Snappy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.*;

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
        byte[] bytes = StreamUtils.inputStream2bytes(is);
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


    /**
     * DEFLATE 压缩
     * 同时使用了LZ777算法 和 哈夫曼编码
     * @param inputBytes
     * @Param level 0（不压缩），以及1(快速压缩)到9（慢速压缩）
     * @return
     */
    public static byte[] deflate(byte[] inputBytes,int level) throws DeflateCompressLevelException {
        if (level < 0 || level > 9)
            throw new DeflateCompressLevelException("压缩等级传入错误...,level 范围1~9");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(level);
        try {
            compressor.setInput(inputBytes);
            compressor.finish();
            byte[] buf = new byte[2048];
            while (!compressor.finished()){
                int count = compressor.deflate(buf);
                bos.write(buf,0,count);
            }
        }finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    /**
     * 解压
     * @param input
     * @return
     * @throws DataFormatException
     */
    public static byte[] inflate(byte[] input) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater inflater = new Inflater();
        try {
            inflater.setInput(input);
            byte[] buf = new byte[2048];
            while (!inflater.finished()){
                int count = inflater.inflate(buf);
                bos.write(buf,0,count);
            }
        }finally {
            inflater.end();
        }
        return bos.toByteArray();
    }

    /**
     * snappy 压缩
     * @param bytes
     * @return
     */
    public static byte[] snappyCompress(byte[] bytes) throws IOException {
        return Snappy.compress(bytes);
    }

    /**
     * snappy 解压
     * @param bytes
     * @return
     * @throws IOException
     */
    public static byte[] snappyDecompress(byte[] bytes) throws IOException {
        return Snappy.uncompress(bytes);
    }

}
