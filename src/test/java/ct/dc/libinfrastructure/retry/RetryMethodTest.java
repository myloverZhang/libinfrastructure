package ct.dc.libinfrastructure.retry;

import org.junit.Test;

import static org.junit.Assert.*;

class A extends RetryArgs{
    private int i = 0;

    /**
     * 执行方法
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        System.out.println("execute = " + i);
        System.out.println("num = " + 10/i++);
    }

    /**
     * 执行方法
     *
     * @throws Exception
     */
    @Override
    public Integer exec2res() throws Exception {
        System.out.println("exec2res = " + i);
        return 10%i++;
    }
}

/**
 * @auther will
 * @createDate 17-5-3.
 */
public class RetryMethodTest {
    @Test
    public void execute() throws Exception {
        A a = new A();
        a.setSleepMs(2000);
        RetryMethod.execute(a);
    }

    @Test
    public void exec2res() throws Exception {
        A a = new A();
        RetryMethod.exec2res(a);
    }

}