/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http访问工具类
 */
public class HttpUtils {

    /**
     * 访问方式
     */
    private enum MethodEnums {
        /**
         * get访问
         */
        GET,
        /**
         * post访问
         */
        POST,
        /**
         * put访问
         */
        PUT,
        /**
         * delete访问
         */
        DELETE;
    }


    /**
     * get访问，返回字符串结果
     * @param urlStr
     * @param parms
     * @param encoding
     */
    public static String sendGet2String(String urlStr, HashMap<String,String> parms, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(urlStr)){
            return null;
        }
        String urlFull = getUrlWithParams(urlStr, parms, encoding);
        HttpURLConnection httpUrlConn = getHttpURLConn(urlFull, MethodEnums.GET);
        httpUrlConn.connect();
        try {
            StringBuffer buffer = getStringBuffer(httpUrlConn, encoding);
            return buffer.toString();
        }finally {
            httpUrlConn.disconnect();
        }
    }

    /**
     * get访问，返回字符串结果 (默认utf8编码)
     * @param urlStr
     * @param parms
     */
    public static String sendGet2String(String urlStr, HashMap<String,String> parms) throws IOException {
        return sendGet2String(urlStr, parms, ConstantResource.ENCODING_UTF8);
    }

    /**
     * get访问，返回InputStream
     * @param urlStr
     * @param parms
     * @param encoding
     */
    public static InputStream sendGet2IO(String urlStr, HashMap<String,String> parms, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(urlStr)){
            return null;
        }
        String urlFull = getUrlWithParams(urlStr, parms, encoding);
        HttpURLConnection httpUrlConn = getHttpURLConn(urlFull, MethodEnums.GET);
        httpUrlConn.connect();
        try {
            return httpUrlConn.getInputStream();
        }finally {
            httpUrlConn.disconnect();
        }
    }

    /**
     * post访问，返回InputStream (默认utf8编码)
     * @param urlStr
     * @param parms
     */
    public static InputStream sendPost2IO(String urlStr, HashMap<String,String> parms) throws IOException {
        return sendGet2IO(urlStr, parms, ConstantResource.ENCODING_UTF8);
    }


    /**
     * post访问，返回InputStream
     * @param urlStr
     * @param parms
     * @param encoding
     */
    public static InputStream sendPost2IO(String urlStr, String parms, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(urlStr)){
            return null;
        }
        HttpURLConnection httpUrlConn = getHttpURLConn(urlStr, MethodEnums.POST);
        httpUrlConn.connect();
        try {
            try(OutputStreamWriter out = new OutputStreamWriter(httpUrlConn.getOutputStream(), encoding)) {
                out.write(parms);
                out.flush();
                return httpUrlConn.getInputStream();
            }
        }finally {
            httpUrlConn.disconnect();
        }
    }

    /**
     * get访问，返回InputStream (默认utf8编码)
     * @param urlStr
     * @param parms
     */
    public static InputStream sendGet2IO(String urlStr, String parms) throws IOException {
        return sendPost2IO(urlStr, parms, ConstantResource.ENCODING_UTF8);
    }

    /**
     * post访问，返回InputStream (默认utf8编码)
     * @param urlStr
     * @param parms
     */
    public static String sendPost2String(String urlStr, String parms) throws IOException {
        return sendPost2String(urlStr, parms, ConstantResource.ENCODING_UTF8);
    }


    /**
     * post访问，返回InputStream
     * @param urlStr
     * @param parms
     * @param encoding
     */
    public static String sendPost2String(String urlStr, String parms, String encoding) throws IOException {
        if(StringUtils.isNullOrWhiteSpace(urlStr)){
            return null;
        }
        HttpURLConnection httpUrlConn = getHttpURLConn(urlStr, MethodEnums.POST);
        httpUrlConn.connect();
        try {
            try(OutputStreamWriter out = new OutputStreamWriter(httpUrlConn.getOutputStream(), encoding)) {
                out.write(parms);
                out.flush();
                StringBuffer buffer = getStringBuffer(httpUrlConn, encoding);
                return buffer.toString();
            }
        }finally {
            httpUrlConn.disconnect();
        }
    }

    /**
     * 获取待参数的url
     * @param urlStr
     * @param parms
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getUrlWithParams(String urlStr, HashMap<String, String> parms, String encoding) throws UnsupportedEncodingException {
        StringBuilder urlFull = new StringBuilder(urlStr);
        if(parms != null){
            if(urlFull.indexOf("?") < 0) {
                urlFull.append("?");
            }else{
                urlFull.append("&");
            }
            for(Map.Entry<String,String> entry : parms.entrySet()){
                String key = URLEncoder.encode(entry.getKey(), encoding);
                String value = URLEncoder.encode(entry.getValue(), encoding);
                urlFull.append(String.format("%s=%s&",key,value));
            }
        }
        return urlFull.toString();
    }

    /**
     * 将返回的输入流转换成字符串
     * @param httpUrlConn
     * @param encoding
     * @return
     * @throws IOException
     */
    private static StringBuffer getStringBuffer(HttpURLConnection httpUrlConn, String encoding) throws IOException {
        StringBuffer buffer = new StringBuffer();
        try(InputStream inputStream = httpUrlConn.getInputStream()) {
            return ConvertUtils.inputStream2StringBuffer(inputStream, encoding);
        }
    }

    /**
     * 获取url连接对象
     * @param urlStr
     * @param method
     * @return
     * @throws IOException
     */
    private static HttpURLConnection getHttpURLConn(String urlStr, MethodEnums method) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

        if(method == MethodEnums.GET) {
            httpUrlConn.setDoOutput(false);
        }else{
            httpUrlConn.setDoOutput(true);
        }
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);

        httpUrlConn.setRequestMethod(method.name().toUpperCase());
        return httpUrlConn;
    }
}
