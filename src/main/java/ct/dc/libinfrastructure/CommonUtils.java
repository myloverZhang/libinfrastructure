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
}
