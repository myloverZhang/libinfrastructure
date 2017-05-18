package ct.dc.libinfrastructure.exception;

/**
 * 反射实例异常
 * Created by will on 17-3-20.
 */
public class ReflectInstanceException extends Exception {

    public ReflectInstanceException(String message){
        super(message);
    }

    public ReflectInstanceException(Exception ex){
        super(ex);
    }

    public ReflectInstanceException(String message, Exception ex){
        super(message, ex);
    }
}
