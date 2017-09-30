/**
 * @author: wangzs
 * @createDate: 2017/03/15
 */

package ct.dc.libinfrastructure;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.security.krb5.internal.PAData;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http访问工具类
 */
public class HttpUtils {


    /**
     * 默认请求头
     */
    private final static HashMap<String,String> DEFAULT_HEADER = new HashMap(){
        {
            put("Accept","application/json");
            put("Content-Type","application/json;charset=UTF-8");
        }
    };
    /**
     * 无参 get请求
     * @param uri
     * @return
     */
    public static String get(String uri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
    }

    /**
     * 带参 get请求
     * @param uri
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String uri, Map<String, String> params) throws IOException {
        return get(getUri(uri, params));
    }
    /**
     * 带参 get请求
     * @param uri
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String uri, Map<String, String> params,Map<String,String> headers) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getMethod = new HttpGet(getUri(uri,params));
        getMethod.setHeaders(createHeaders(headers));
        CloseableHttpResponse response = client.execute(getMethod);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    /**
     * post
     * @param uri
     * @param params
     * @param headers
     * @return
     */
    public static String post(String uri,Map<String,Object> params, Map<String,String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(new StringEntity(JsonUtils.jsonSerialize(params)));
        postMethod.setHeaders(createHeaders(headers));
        CloseableHttpResponse response = httpClient.execute(postMethod);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    /**
     * post
     * @param uri
     * @param content
     * @param headers
     * @return
     * @throws IOException
     */
    public static String post(String uri,String content,Map<String,String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri);
        post.setEntity(new StringEntity(content));
        post.setHeaders(createHeaders(headers));
        CloseableHttpResponse response = httpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        return result;
}

    /**
     * post
     * @param uri
     * @param content
     * @return
     * @throws IOException
     */
    public static String post(String uri,String content) throws IOException {
        return post(uri,content,DEFAULT_HEADER);
    }

    /**
     * 不自定义头的参数
     * 默认header
     * Accept : application/json
     * ContentType:application/json;charset=UTF-8
     * @param uri
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String uri,Map<String,Object> params) throws IOException {
        return post(uri,params,DEFAULT_HEADER);
    }

    /**
     * 无参post请求
     * @param uri
     * @return
     */
    public static String post(String uri) throws IOException {
        return post(uri,new HashMap(),DEFAULT_HEADER);
    }


//    /**
//     * 创建entity
//     * @param params
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    private static UrlEncodedFormEntity createEntity(Map<String,Object> params) throws UnsupportedEncodingException {
//        List<NameValuePair> nameValuePairs = new ArrayList<>();
//        for (Map.Entry<String,Object> entry:params.entrySet()){
//            NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(),entry.getValue().toString());
//            nameValuePairs.add(nameValuePair);
//        }
//        return new UrlEncodedFormEntity(nameValuePairs);
//    }
    /**
     * 创建头信息
     * @param maps
     * @return
     */
    private static Header[] createHeaders(Map<String,String> maps){
        Header[] hears = new Header[maps.size()];
        int count = 0;
        for(Map.Entry<String,String> entry:maps.entrySet()){
            hears[count++] = new BasicHeader(entry.getKey(),entry.getValue());
        }
        return hears;
    }
    /**
     * 组装get 请求的uri
     * @param uri
     * @param params
     * @return
     */
    private static String getUri(String uri,Map<String,String> params){
        boolean flag = true;
        for (Map.Entry<String, String> map : params.entrySet()) {
            if (flag) {
                uri = String.format("%s?%s=%s", uri, map.getKey(), map.getValue());
                flag = false;
            } else {
                uri = String.format("%s&%s=%s", uri, map.getKey(), map.getValue());
            }
        }
        return uri;
    }
}
