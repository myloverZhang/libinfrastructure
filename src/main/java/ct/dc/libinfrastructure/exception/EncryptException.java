package ct.dc.libinfrastructure.exception;

/**
 * 加密解密异常
 * Created by will on 17-3-20.
 */
public class EncryptException extends Exception {

    public EncryptException(String message){
        super(message);
    }

    public EncryptException(Exception ex){
        super(ex);
    }

    public EncryptException(String message, Exception ex){
        super(message, ex);
    }
}
