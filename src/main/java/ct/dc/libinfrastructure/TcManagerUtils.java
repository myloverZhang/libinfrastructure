package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.common.UserInfo;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by CTWLPC on 2017/9/30.
 */
public class TcManagerUtils {
    private static String CONFIG_FILE_PATH = "config/tcManager.properties";
    private static String TC_MANAGER_URI;
    private static String USER_INFO_WITH_COOKIE_METHOD;
    private static final HashMap<String,String> TEXT_XML_HEADER  = new HashMap(){
        {
            put("Content-Type","text/xml;charset=utf-8");
        }
    };

    static {
        String path = String.format("%s/%S",System.getProperty("user.dir"),CONFIG_FILE_PATH);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TC_MANAGER_URI = properties.getProperty("tcManager.uri").toString();
            USER_INFO_WITH_COOKIE_METHOD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Header>\n" +
                    "    <CredenceSoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <PlatformID>%d</PlatformID>\n" +
                    "      <Credential>%s</Credential>\n" +
                    "    </CredenceSoapHeader>\n" +
                    "  </soap:Header>\n" +
                    "  <soap:Body>\n" +
                    "    <GetCookieAdminUserInfo xmlns=\"http://tempuri.org/\">\n" +
                    "      <cookieValue>%s</cookieValue>\n" +
                    "    </GetCookieAdminUserInfo>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务后台cookie
     */
    public static final String ADMIN_USER_INFO_COOKIE_NAME = "adminuserinfo";

    /**
     * 验证是否是管理后台cookie 用户
     * @param platformID
     * @param credential
     * @param request
     * @return
     */
    public static boolean getUserInfoWithCookie(int platformID, String credential, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return false;
        String cookieValue = "";
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(ADMIN_USER_INFO_COOKIE_NAME))
                cookieValue = cookie.getValue();
            break;
        }
        if (cookieValue.equals(""))
            return false;
        String content = String.format(USER_INFO_WITH_COOKIE_METHOD,platformID,credential,cookieValue);
        try {
            String result = HttpUtils.post(TC_MANAGER_URI,content,TEXT_XML_HEADER);
            UserInfo info = getUserInfo(result);
            if (info!=null&&info.getId()>0)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (URISyntaxException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 获取同城游用户信息
     * @param platformID
     * @param credential
     * @param request
     * @return
     */
    public static UserInfo getTcUserInfo(int platformID, String credential,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        String cookieValue = "";
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(ADMIN_USER_INFO_COOKIE_NAME))
                cookieValue = cookie.getValue();
            break;
        }
        if (cookieValue.equals(""))
            return null;
        String content = String.format(USER_INFO_WITH_COOKIE_METHOD,platformID,credential,cookieValue);
        try {
            String result = HttpUtils.post(TC_MANAGER_URI,content,TEXT_XML_HEADER);
            UserInfo info = getUserInfo(result);
            if (info!=null&&info.getId()>0)
                return info;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (URISyntaxException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param content
     * @return
     */
    private static UserInfo getUserInfo(String content){
        System.out.println(content);
        String baseStr = "soap:Envelope/soap:Body/GetCookieAdminUserInfoResponse/GetCookieAdminUserInfoResult";
        Digester digester = new Digester();
        digester.addObjectCreate(baseStr,"ct.dc.libinfrastructure.common.UserInfo");
        digester.addSetProperties(baseStr);
        digester.addBeanPropertySetter(String.format("%s/InUseBeginTime",baseStr),"inUseBeginTime");
        digester.addBeanPropertySetter(String.format("%s/ID",baseStr),"id");
        digester.addBeanPropertySetter(String.format("%s/UserName",baseStr),"userName");
        digester.addBeanPropertySetter(String.format("%s/RoleID",baseStr),"roleId");
        digester.addBeanPropertySetter(String.format("%s/SiteID",baseStr),"siteId");
        digester.addBeanPropertySetter(String.format("%s/MacFlag",baseStr),"macFlag");
        digester.addBeanPropertySetter(String.format("%s/Status",baseStr),"status");
        digester.addBeanPropertySetter(String.format("%s/TrueName",baseStr),"trueName");
        digester.addBeanPropertySetter(String.format("%s/OverdueTime",baseStr), "overDueTime");
        try {
            return  (UserInfo)digester.parse(new ByteArrayInputStream(content.getBytes(ConstantResource.ENCODING_UTF8)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        }
    }
}
