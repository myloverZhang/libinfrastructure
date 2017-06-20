/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类
 */
public class IpUtils {

    /**
     * 获取客户端ipv4，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request request对象
     * @return ipv4
     */
    public static String getClientIpV4(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * ip转换到int (当strIPAddress为null时，返回也为null)
     * @param strIPAddress
     * @return
     */
    public static Integer ip2int(String strIPAddress) {
        if(StringUtils.isNullOrWhiteSpace(strIPAddress) || !StringUtils.isIPv4(strIPAddress)){
            return null;
        }
        String[] arrayIP = strIPAddress.split("\\.");
        int sip1 = Integer.valueOf(arrayIP[0]);
        int sip2 = Integer.valueOf(arrayIP[1]);
        int sip3 = Integer.valueOf(arrayIP[2]);
        int sip4 = Integer.valueOf(arrayIP[3]);
        return sip4 * 256 * 256 * 256 + sip3 * 256 * 256 + sip2 * 256 + sip1;
    }

    /**
     * int转换到ip (当intIPAddress为null时，返回也为null)
     * @param intIPAddress
     * @return
     */
    public static String int2ip(Integer intIPAddress) {
        if(intIPAddress == null){
            return null;
        }
        //将目标整形数字intIPAddress转换为IP地址字符串    //-1062731518 192.168.1.2     //-1062731517 192.168.1.3
        int tempIPAddress;
        if (intIPAddress >= 0) {
            tempIPAddress = intIPAddress;
        } else {
            tempIPAddress = intIPAddress + 1;
        }
        int s1 = tempIPAddress / 256 / 256 / 256;
        int s21 = s1 * 256 * 256 * 256;
        int s2 = (tempIPAddress - s21) / 256 / 256;
        int s31 = s2 * 256 * 256 + s21;
        int s3 = (tempIPAddress - s31) / 256;
        int s4 = tempIPAddress - s3 * 256 - s31;
        if (intIPAddress < 0) {
            s1 = 255 + s1;
            s2 = 255 + s2;
            s3 = 255 + s3;
            s4 = 255 + s4;
        }
        return String.format("%d.%d.%d.%d", s4, s3, s2, s1);
    }

    /**
     * ip转化到long类型 (当strIPAddress为null时，返回也为null)
     * @param strIPAddress
     * @return
     */
    public static Long ip2long(String strIPAddress) {
        if(StringUtils.isNullOrWhiteSpace(strIPAddress) || !StringUtils.isIPv4(strIPAddress)){
            return null;
        }
        String[] ipArr = strIPAddress.split("\\.");
        if (ipArr.length < 4) {
            return 0L;
        }
        long p1 = Long.valueOf(ipArr[0]) * 256 * 256 * 256;
        long p2 = Long.valueOf(ipArr[1]) * 256 * 256;
        long p3 = Long.valueOf(ipArr[2]) * 256;
        long p4 = Long.valueOf(ipArr[3]);
        return p1 + p2 + p3 + p4;
    }

    /**
     * long转化到ip (当longIPAddress为null时，返回也为null)
     * @param longIPAddress
     * @return
     */
    public static String long2ip(Long longIPAddress) {
        if(longIPAddress == null){
            return null;
        }
        long seg1 = (longIPAddress & 0xff000000) >> 24;
        if (seg1 < 0)
            seg1 += 0x100;
        long seg2 = (longIPAddress & 0x00ff0000) >> 16;
        if (seg2 < 0)
            seg2 += 0x100;
        long seg3 = (longIPAddress & 0x0000ff00) >> 8;
        if (seg3 < 0)
            seg3 += 0x100;
        long seg4 = (longIPAddress & 0x000000ff);
        if (seg4 < 0)
            seg4 += 0x100;
        return String.format("%d.%d.%d.%d", seg1,seg2,seg3,seg4);
    }
}
