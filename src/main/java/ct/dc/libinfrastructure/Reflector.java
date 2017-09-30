package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.common.AccessRankEnum;
import ct.dc.libinfrastructure.exception.ReflectInstanceException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by CTWLPC on 2017/9/21.
 */
public class Reflector {
    private static HashMap<Integer, String> rankDetails = new HashMap<Integer, String>();

    static {
        rankDetails.put(0, new String(""));
        rankDetails.put(1, "public");
        rankDetails.put(2, "protected");
        rankDetails.put(3, "private");
    }

    /**
     * 获取类的属性
     *
     * @param className
     * @param extendsField
     * @param type
     * @return
     */
    public static String[] listClassFields(String className, boolean extendsField, AccessRankEnum type) {
        Class clazz = getClassType(className);
        HashSet<String> set = new HashSet<>();
        Field[] fields = extendsField ? clazz.getFields() : clazz.getDeclaredFields();
        for (Field field : fields) {
            String rank = rankDetails.get(type.ordinal());
            if (field.toString().startsWith(rank)) {
                set.add(field.getName());
            }
        }

        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类的方法名
     *
     * @param className
     * @param extendsMethod
     * @param type
     * @return
     */
    public static String[] listClassMethods(String className, boolean extendsMethod, AccessRankEnum type) {
        Class clazz = getClassType(className);
        Method[] methods = extendsMethod ? clazz.getMethods() : clazz.getDeclaredMethods();
        HashSet<String> methodName = new HashSet<>();
        for (Method method : methods) {
            String rank = rankDetails.get(type.ordinal());
            if (method.toString().startsWith(rank)) {
                methodName.add(method.getName());
            }
        }
        return methodName.toArray(new String[methodName.size()]);
    }

    /**
     * 给实例的某个属性设置属性
     *
     * @param obj
     * @param att
     * @param value
     * @param type
     */
    public static void setAttribute(Object obj, String att, Object value, Class<?> type) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Class theClazz = obj.getClass();
        char[] chars = att.toCharArray();
        chars[0] -= 32;
        String methodName = String.format("set%s",String.valueOf(chars));
        theClazz.getMethod(methodName,type).invoke(obj,value);
    }

    /***
     * 加载类
     * @param className
     * @return
     */
    public static Class getClassType(String className) {
        Class thisClass = null;
        try {
            thisClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return thisClass;
    }

    /**
     * 初始化类的实例
     *
     * @param className
     * @param paramsType
     * @param paramValues
     * @param <T>
     * @return
     */
    public static <T> T createInstance(String className, Class[] paramsType, Object[] paramValues) throws ReflectInstanceException {
        Class theClazz = getClassType(className);
        try {
            if (paramsType == null || paramValues == null || paramsType.length + paramsType.length == 0)
                return (T) theClazz.newInstance();
            Constructor constructor = theClazz.getConstructor(paramsType);
            return (T)constructor.newInstance(paramValues);
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

    /**
     * 调用实例方法
     * @param className
     * @param methodName
     * @param constructorTypes
     * @param constructorValue
     * @param methodParamsType
     * @param methodParamValues
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Object invoke(String className,String methodName,Class[] constructorTypes,Object[] constructorValue,
                                Class[] methodParamsType,Object[] methodParamValues)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class theClass = getClassType(className);
        Method method = theClass.getMethod(methodName,methodParamsType);
        Object obj = new Object();
        if (constructorTypes == null || constructorValue == null || constructorTypes.length + constructorValue.length == 0){
            obj = theClass.newInstance();
        }else {
            obj = theClass.getConstructor(constructorTypes).newInstance(constructorValue);
        }
        Object result = method.invoke(obj, methodParamValues);
        return result;
    }

    /**
     * 方法调用
     * @param className
     * @param methodName
     * @param methodParamsType
     * @param methodParamValues
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Object invoke(String className,String methodName, Class[] methodParamsType,Object[] methodParamValues)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class theClass = getClassType(className);
        Method method = theClass.getMethod(methodName,methodParamsType);
        return method.invoke(theClass.newInstance(),methodParamValues);
    }

    /**
     * 调用静态方法
     * @param className
     * @param methodName
     * @param methodParamsType
     * @param methodParamsValue
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static Object invokeStaticMethod(String className, String methodName, Class[] methodParamsType, Object[] methodParamsValue)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class theClazz = getClassType(className);
        Method method = theClazz.getMethod(methodName,methodParamsType);
        return method.invoke(null,methodParamsValue);
    }

}
