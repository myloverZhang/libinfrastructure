/**
 * @auther wangzs
 * @createDate 17-5-3
 */

package ct.dc.libinfrastructure.retry;

/**
 * 重试方法参数
 */
public abstract class RetryArgs {
    private boolean errorRetry = true;
    private int retryNum = 3;
    private int sleepMs = 0;

    /**
     * 获取重试间隔毫秒数（默认为0）
     * @return
     */
    public int getSleepMs() {
        return sleepMs;
    }

    /**
     * 设置重试间隔毫秒数（默认为0）
     * @param sleepMs
     */
    public void setSleepMs(int sleepMs) {
        this.sleepMs = sleepMs;
    }

    /**
     * 获取重试次数（默认3次）
     * @return
     */
    public int getRetryNum() {
        return retryNum;
    }

    /**
     * 设置重试次数（默认3次）
     * @param retryNum
     */
    public void setRetryNum(int retryNum) {
        this.retryNum = retryNum;
    }

    /**
     * 是否需要错误重试(默认重试)
     * @return
     */
    public boolean isErrorRetry() {
        return errorRetry;
    }

    /**
     * 设置是否需要错误重试(默认重试)
     * @param errorRetry
     */
    public void setErrorRetry(boolean errorRetry) {
        this.errorRetry = errorRetry;
    }

    /**
     * 执行方法
     * @throws Exception
     */
    public abstract void execute() throws Exception;

    /**
     * 执行方法
     * @throws Exception
     */
    public abstract<T> T exec2res() throws Exception;

    /**
     * 正常情况下是否需要重试
     * @return
     */
    public boolean isNeedRetry(){
        return false;
    }
}
