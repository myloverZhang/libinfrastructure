package ct.dc.libinfrastructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CTWLPC on 2017/10/10.
 */
public class RandomUtilsTest {
    @Test
    public void fixedRandomAllString() throws Exception {
        for (int i = 0; i < 50; i ++){
            System.out.println(RandomUtils.fixedRandomAllString(12));
        }
        assert true;
    }

    @Test
    public void unfixedRandomAllString() throws Exception {
        for (int i = 0; i < 50; i ++){
            System.out.println(RandomUtils.unfixedRandomAllString(12,2));
        }
        assert true;
    }

    @Test
    public void fixedRandomLetterString() throws Exception {
        for (int i = 0; i < 50; i ++){
            System.out.println(RandomUtils.fixedRandomLetterString(12));
        }
        assert true;
    }

    @Test
    public void unfixedRandomLetterString() throws Exception {
        for (int i = 0; i < 50; i ++){
            System.out.println(RandomUtils.unfixedRandomLetterString(12,2));
        }
        assert true;
    }

    @Test
    public void unFixedRandomNumberString() throws Exception {
        for (int i = 0; i < 12; i ++){
            System.out.println(RandomUtils.unFixedRandomNumberString(12,2));
        }
        assert true;
    }

    @Test
    public void fixedRandomNumberString() throws Exception {
        String randomNumberStr = RandomUtils.fixedRandomNumberString(12);
        System.out.println(randomNumberStr);
        assert randomNumberStr.length() == 12;
    }

    @Test
    public void unfixedRandomString() throws Exception {
        for (int i = 0; i < 100;i++){
            System.out.println(RandomUtils.unfixedRandomString("abc",10,2));

        }
        assert true;
    }

    @Test
    public void fixedRandomString() throws Exception {
        String randomStr = RandomUtils.fixedRandomString("abc",10);
        System.out.println(randomStr);
        assert randomStr.length() == 10;
    }

    @Test
    public void randomNumber() throws Exception {
        for (int i = 0;i < 10;i++){
           System.out.println(RandomUtils.randomNumber(1,100));
        }
        assert true;
    }

}