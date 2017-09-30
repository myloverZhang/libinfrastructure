/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {
    private static final String PATTERN_NUM = "(\\d)*";
    private static final String PATTERN_EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

    private static final String PATTERN_IPV4 = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.)" +
            "{3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";

    private static final String PATTERN_ID_CARD_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    private static final String PATTERN_ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)" +
            "|3[0-1])\\d{3}([0-9]|X)$";


    /**
     * 字符串是否为空
     *
     * @return
     */
    public static Boolean isNullOrWhiteSpace(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static Boolean isNumeric(String str) {
        return str.matches(PATTERN_NUM);
    }

    /**
     * 判断是否是email
     *
     * @param str
     * @return
     */
    public static Boolean isEmail(String str) {
        final Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        return pattern.matcher(str).matches();
    }

    /**
     * 是否是合法的ipv4
     *
     * @param str
     * @return
     */
    public static Boolean isIPv4(String str) {
        final Pattern pattern = Pattern
                .compile(PATTERN_IPV4);
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否是合法的身份证
     *
     * @param str
     * @return
     */
    public static Boolean isIDdard(String str) {
        if (str.length() != 15 && str.length() != 18) {
            return false;
        }
        String patternStr = null;
        if (str.length() == 15) {
            patternStr = PATTERN_ID_CARD_15;
        } else {
            patternStr = PATTERN_ID_CARD_18;
        }
        final Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(str).matches();
    }
    private static String test(){
        return "";
    }
}
