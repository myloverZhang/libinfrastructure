/**
 * @author: wangzs
 * @createDate: 2017/03/15
 */

package ct.dc.libinfrastructure;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.List;
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
     * 字符串转int
     * @param intStr
     * @return
     */
    public static Integer str2int(String intStr){
        if (StringUtils.isNullOrWhiteSpace(intStr)) {
            throw new NumberFormatException("字符串不能为空或者null");
        }
        return Integer.valueOf(intStr);
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
     * 字符串转化成long
     *
     * @param longStr
     * @return
     */
    public static Long str2long(String longStr) {
        if (StringUtils.isNullOrWhiteSpace(longStr)) {
            throw new NumberFormatException("字符串不能为空或者null");
        }
        return Long.valueOf(longStr);
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
     * 字符串转化成bool
     *
     * @param boolStr
     * @return
     */
    public static Boolean str2bool(String boolStr) {
        if (StringUtils.isNullOrWhiteSpace(boolStr)) {
            throw new NumberFormatException("字符串不能为空或者null");
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
}
