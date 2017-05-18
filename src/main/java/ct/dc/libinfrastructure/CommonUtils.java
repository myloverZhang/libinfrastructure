/**
 * @author:      wangzs
 * @createDate:  2017/03/16
 */

package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.exception.ReflectInstanceException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通用工具库
 */
public class CommonUtils {

    /**
     * 获取堆栈错误
     * @param ex
     * @return
     */
    public static String getStackErrors(Throwable ex){
        Writer writer = new StringWriter();
        try(PrintWriter pw = new PrintWriter(writer)) {
            ex.printStackTrace(pw);
        }
        return  writer.toString();
    }

    /**
     * 通过类名反射获取实例
     * @param className
     * @param <T>
     * @return
     * @throws ReflectInstanceException
     */
    public static <T> T getInstance(String className) throws ReflectInstanceException {
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ReflectInstanceException(e);
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new ReflectInstanceException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectInstanceException(e);
        }
    }

    /**
     * 通过类名反射获取实例
     * @param className
     * @param <T>
     * @param types 构造类型
     * @param objs 参数
     * @return
     * @throws ReflectInstanceException
     */
    public static <T> T getInstance(String className, Class[] types,  Object[] objs) throws ReflectInstanceException {
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            Constructor ctor = clazz.getConstructor(types);
            return (T)ctor.newInstance(objs);
        } catch (ClassNotFoundException e) {
            throw new ReflectInstanceException("找不到类名", e);
        } catch (NoSuchMethodException e) {
            throw new ReflectInstanceException("没有这个构造器", e);
        }catch (InstantiationException e) {
            throw new ReflectInstanceException("实例化异常",e);
        } catch (IllegalAccessException e) {
            throw new ReflectInstanceException("访问权限异常",e);
        } catch (InvocationTargetException e) {
            throw new ReflectInstanceException("构造异常",e);
        }
    }
}
