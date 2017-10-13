package ct.dc.libinfrastructure;

import java.util.Random;

/**
 * Created by CTWLPC on 2017/10/10.
 */
public class RandomUtils {

    public static final String ALL_CHAR
            = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_CHAR
            = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER_CHAR
            = "0123456789";


    /**
     * 数字加字母组成的固定长度随机字符串
     * @param length
     * @return
     */
    public static String fixedRandomAllString(long length){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i < length;i ++){
            Random random = new Random();
            stringBuffer.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return stringBuffer.toString();
    }

    /**
     * 数字加字母组成的随机长度的随机字符串
     * @return
     */
    public static String unfixedRandomAllString(int maxLength, int minLength){
        return fixedRandomAllString(randomNumber(maxLength,minLength));
    }
    /**
     * 固定长度随机字母字符串
     * @param length
     * @return
     */
    public static String fixedRandomLetterString(long length){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i < length;i++){
            Random random = new Random();
            stringBuffer.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
        }
        return stringBuffer.toString();
    }

    /**
     * 随机长度的 字母字符串
     * @param maxLength
     * @param minLength
     * @return
     */
    public static String unfixedRandomLetterString(int maxLength,int minLength){
        return fixedRandomLetterString(randomNumber(maxLength,minLength));
    }

    /**
     * 固定长度随机数字字符串
     * @param length
     * @return
     */
    public static String fixedRandomNumberString(long length){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length;i++){
            Random random = new Random();
            stringBuffer.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
        }
        return stringBuffer.toString();
    }



    /**
     * 随机长度随机数字字符串
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String unFixedRandomNumberString(int maxLength,int minLength){
        return fixedRandomNumberString(randomNumber(maxLength, minLength));
    }
    /**
     * 固定长度随机给定字符串
     * @param chars
     * @param length
     * @return
     */
    public static String fixedRandomString(String chars,int length){
        Random random = new Random();
        StringBuffer  stringBuffer = new StringBuffer();
        for (int i = 0; i < length;i++){
            int index = random.nextInt(chars.length());
            stringBuffer.append(chars.charAt(index));
        }
        return stringBuffer.toString();
    }

    /**
     * 不定长的随机字符串
     * @param chars
     * @param maxLength
     * @return
     */
    public static String unfixedRandomString(String chars,int maxLength, int minLength){
        return fixedRandomString(chars,randomNumber(maxLength,minLength));
    }
    /**
     * 生成随机数
     * @param max
     * @param min
     * @return
     */
    public static int randomNumber(int max,int min){
        Random random = new Random();
        return (random.nextInt(max) % (max - min + 1) + min);
    }
}
