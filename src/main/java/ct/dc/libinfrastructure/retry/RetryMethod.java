/**
 * @auther wangzs
 * @createDate 17-5-3
 */

package ct.dc.libinfrastructure.retry;

/**
 * 重试工具类
 */
public class RetryMethod {

    /**
     * 重试执行
     * @param args
     * @throws Exception
     */
    public static final void execute(RetryArgs args) throws Exception {
        int i = 0;
        while(i < args.getRetryNum()){
            i++;
            try {
                args.execute();
                if(!args.isNeedRetry()) {
                    return;
                }
            }catch(Exception ex){
                if(!args.isErrorRetry() || i >= args.getRetryNum()){
                    throw ex;
                }
            }

            if(args.getSleepMs() > 0) {
                try {
                    Thread.sleep(args.getSleepMs());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 重试执行
     * @param args
     * @throws Exception
     */
    public static final<T> T exec2res(RetryArgs args) throws Exception {
        int i = 0;
        while(i < args.getRetryNum()){
            i++;
            try {
                T res = args.exec2res();
                if(!args.isNeedRetry()) {
                    return res;
                }
            }catch(Exception ex){
                if(!args.isErrorRetry() || i >= args.getRetryNum()){
                    throw ex;
                }
            }

            if(args.getSleepMs() > 0) {
                try {
                    Thread.sleep(args.getSleepMs());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
