package ct.dc.libinfrastructure;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by CTWLPC on 2017/9/25.
 */
public class JsonUtils {
    /**
     * 将object 类型序列化成json字符串
     * @param object
     * @return
     */
    public static String jsonSerialize(Object object){
        return JSON.toJSONString(object);
    }


    /**
     * json字符串反序列化
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T jsonObjDeserialize(String jsonStr, Class<T> clazz){
        return JSON.parseObject(jsonStr,clazz);
    }


    /**
     * jsonStr 数组 反序列化
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>  List<T> jsonListDeserialize(String jsonStr, Class<T> clazz){
        return JSON.parseArray(jsonStr,clazz);
    }
}
