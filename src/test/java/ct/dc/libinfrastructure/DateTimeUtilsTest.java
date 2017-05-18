package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;

/**
 * Created by will on 17-3-17.
 */
public class DateTimeUtilsTest {

    @Test
    public void cnDatetime2secsTs() throws Exception {
        Long secs = 1489728547L;
        LocalDateTime dt = DateTimeUtils.secsTs2cnDatetime(secs);
        Assert.assertTrue(DateTimeUtils.cnDatetime2secsTs(dt).equals(secs));
    }

    @Test
    public void utcDatetime2secsTs() throws Exception {
        Long secs = 1489728547L;
        LocalDateTime dt = DateTimeUtils.secsTs2utcDatetime(secs);
        Assert.assertTrue(DateTimeUtils.utcDatetime2secsTs(dt).equals(secs));
    }

    @Test
    public void cnDatetime2msTs() throws Exception {
        Long ms = 1489728547L * 1000 + 323;
        LocalDateTime dt = DateTimeUtils.msTs2cnDatetime(ms);
        Assert.assertTrue(DateTimeUtils.cnDatetime2msTs(dt).equals(ms));
    }

    @Test
    public void utcDatetime2msTs() throws Exception {
        Long ms = 1489728547L * 1000 + 323;
        LocalDateTime dt = DateTimeUtils.msTs2utcDatetime(ms);
        Assert.assertTrue(DateTimeUtils.utcDatetime2msTs(dt).equals(ms));
    }

    @Test
    public void cnDatetime2nanoTs() throws Exception {
        Long nanos = (1489728547L * 1000 + 323) * 1000000 + 2354;
        LocalDateTime dt = DateTimeUtils.nanoTs2cnDatetime(nanos);
        Assert.assertTrue(DateTimeUtils.cnDatetime2nanoTs(dt).equals(nanos));
    }

    @Test
    public void utcDatetime2nanoTs() throws Exception {
        Long nanos = (1489728547L * 1000 + 323) * 1000000 + 2354;
        LocalDateTime dt = DateTimeUtils.nanoTs2utcDatetime(nanos);
        Assert.assertTrue(DateTimeUtils.utcDatetime2nanoTs(dt).equals(nanos));
    }


    @Test
    public void secsTs2utcDatetime() throws Exception {
        long ts = 1489728547L;
        LocalDateTime dt = DateTimeUtils.secsTs2utcDatetime(ts).plusHours(DateTimeUtils.ZONE_HOURS_DIFF_CN);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7;
        Assert.assertTrue(status);
    }

    @Test
    public void msTs2utcDatetime() throws Exception {
        long ts = 1489728547042L;
        LocalDateTime dt = DateTimeUtils.msTs2utcDatetime(ts).plusHours(DateTimeUtils.ZONE_HOURS_DIFF_CN);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7 &&
                dt.getNano() == 42*1000000;
        Assert.assertTrue(status);
    }

    @Test
    public void nanoTs2utcDatetime() throws Exception {
        long ts = 1489728547042002591L;
        LocalDateTime dt = DateTimeUtils.nanoTs2utcDatetime(ts).plusHours(DateTimeUtils.ZONE_HOURS_DIFF_CN);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7;
        Assert.assertTrue(status);
    }

    @Test
    public void instant2cnDatetime() throws Exception {
        LocalDateTime dt1 = DateTimeUtils.instant2cnDatetime(Instant.now());
        LocalDateTime dt2 = LocalDateTime.now();
        boolean status = dt1.getYear() == dt2.getYear() && dt1.getMonthValue() == dt2.getMonthValue() &&
                dt1.getDayOfMonth() == dt2.getDayOfMonth() && dt1.getHour() == dt2.getHour() &&
                dt1.getMinute() == dt2.getMinute();
        Assert.assertTrue(status);
    }

    @Test
    public void instant2utcDatetime() throws Exception {
        LocalDateTime dt1 = DateTimeUtils.instant2utcDatetime(Instant.now());
        LocalDateTime dt2 = LocalDateTime.now(DateTimeUtils.ZONE_ID_UTC);
        boolean status = dt1.getYear() == dt2.getYear() && dt1.getMonthValue() == dt2.getMonthValue() &&
                dt1.getDayOfMonth() == dt2.getDayOfMonth() && dt1.getHour() == dt2.getHour() &&
                dt1.getMinute() == dt2.getMinute();
        Assert.assertTrue(status);
    }

    @Test
    public void date2int() throws Exception {
        int dateint = 20170101;
        LocalDate dt = DateTimeUtils.int2date(dateint);
        Assert.assertTrue(DateTimeUtils.date2int(dt).equals(dateint));
    }

    @Test
    public void time2int() throws Exception {
        LocalTime dt = DateTimeUtils.int2time(1);
        boolean status = dt.getHour() == 0 && dt.getMinute() == 0 && dt.getSecond() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void int2date() throws Exception {
        LocalDate dt = DateTimeUtils.int2date(20170201);
        boolean status = dt.getYear() == 2017 && dt.getMonth().getValue() == 2 && dt.getDayOfMonth() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void int2datetime() throws Exception {
        LocalDateTime dt = DateTimeUtils.int2datetime(20170201,2301);
        boolean status = dt.getYear() == 2017 && dt.getMonth().getValue() == 2 && dt.getDayOfMonth() == 1 &&
                dt.getHour() == 0 && dt.getMinute() == 23 && dt.getSecond() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void getTimestampMs() throws Exception {
        System.out.println("当前毫秒级时间戳：" + DateTimeUtils.getTimestampMs());
        Assert.assertTrue(true);
    }

    @Test
    public void secsTs2cnDatetime() throws Exception {
        long ts = 1489728547L;
        LocalDateTime dt = DateTimeUtils.secsTs2cnDatetime(ts);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7;
        Assert.assertTrue(status);
    }

    @Test
    public void msTs2cnDatetime() throws Exception {
        long ts = 1489728547042L;
        LocalDateTime dt = DateTimeUtils.msTs2cnDatetime(ts);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7 &&
                dt.getNano() == 42*1000000;
        Assert.assertTrue(status);
    }

    @Test
    public void nanoTs2cnDatetime() throws Exception {
        long ts = 1489728547042002591L;
        LocalDateTime dt = DateTimeUtils.nanoTs2cnDatetime(ts);
        boolean status = dt.getYear() == 2017 && dt.getMonthValue() == 3 && dt.getDayOfMonth() == 17 &&
                dt.getHour() == 13 && dt.getMinute() == 29 && dt.getSecond() == 7 &&
                dt.getNano() == 42002591;
        Assert.assertTrue(status);
    }

    @Test
    public void getDurationMs() throws Exception {
        Long ms = 33337923243L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/10/01 10:00:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusNanos(ms * 1000000);
        Long ms2 = DateTimeUtils.getDurationMs(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(ms2.equals(ms));
    }

    @Test
    public void getDurationSecs() throws Exception {
        Long secs = 33336633243L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/02/01 00:00:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusSeconds(secs);
        Long secs2 = DateTimeUtils.getDurationSecs(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(secs2.equals(secs));
    }

    @Test
    public void getDurationNano() throws Exception {
        Long nanos = 33333336633243L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/02/01 00:23:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusNanos(nanos);
        Long nanos2 = DateTimeUtils.getDurationNano(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(nanos2.equals(nanos));
    }

    @Test
    public void getDurationMins() throws Exception {
        Long mins = 33L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/02/01 00:23:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusMinutes(mins);
        Long mins2 = DateTimeUtils.getDurationMins(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(mins2.equals(mins));
    }

    @Test
    public void getDurationHours() throws Exception {
        Long hours = 33L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/02/01 00:23:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusHours(hours);
        Long hours2 = DateTimeUtils.getDurationHours(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(hours2.equals(hours));
    }

    @Test
    public void getDurationDays() throws Exception {
        Long days = 33L;
        LocalDateTime dt1 = DateTimeUtils.str2datetime("2017/02/01 00:23:01", "yyyy/MM/dd HH:mm:ss");
        LocalDateTime dt2 = dt1.plusDays(days);
        Long days2 = DateTimeUtils.getDurationDays(dt1.toInstant(ZoneOffset.UTC), dt2.toInstant(ZoneOffset.UTC));
        Assert.assertTrue(days2.equals(days));
    }

    @Test
    public void str2datetime() throws Exception {
        LocalDateTime dt = DateTimeUtils.str2datetime("2017/02/01 00:23:01", "yyyy/MM/dd HH:mm:ss");
        boolean status = dt.getYear() == 2017 && dt.getMonth().getValue() == 2 && dt.getDayOfMonth() == 1 &&
                dt.getHour() == 0 && dt.getMinute() == 23 && dt.getSecond() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void datetime2str() throws Exception {
        LocalDateTime datetime = LocalDateTime.now();
        String dtStr = DateTimeUtils.datetime2str(datetime, "yyyyMMddHHmmss");
        int year = datetime.getYear();
        int month = datetime.getMonthValue();
        int day = datetime.getDayOfMonth();
        int hour = datetime.getHour();
        int min = datetime.getMinute();
        int sec = datetime.getSecond();
        long num = (year * 10000L + month * 100 + day) * 1000000 + (hour * 10000 + min * 100 + sec);
        Assert.assertTrue(Long.valueOf(dtStr).equals(num));
    }

    @Test
    public void str2date() throws Exception {
        LocalDate date = DateTimeUtils.str2date("2017/11/01", "yyyy/MM/dd");
        boolean status = date.getYear() == 2017 && date.getMonth().getValue() == 11 && date.getDayOfMonth() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void date2str() throws Exception {
        LocalDateTime datetime = LocalDateTime.now();
        String dateStr = DateTimeUtils.date2str(datetime.toLocalDate(), "yyyyMMdd");
        int year = datetime.getYear();
        int month = datetime.getMonthValue();
        int day = datetime.getDayOfMonth();
        Assert.assertTrue(Integer.valueOf(dateStr).equals(year * 10000 + month * 100 + day));
    }

    @Test
    public void str2time() throws Exception {
        LocalTime time = DateTimeUtils.str2time("01:20:01", "HH:mm:ss");
        boolean status = time.getHour() == 1 && time.getMinute() == 20 && time.getSecond() == 1;
        Assert.assertTrue(status);
    }

    @Test
    public void time2str() throws Exception {
        LocalDateTime datetime = LocalDateTime.now();
        String timeStr = DateTimeUtils.time2str(datetime.toLocalTime(), "HHmmss");
        int hour = datetime.getHour();
        int min = datetime.getMinute();
        int sec = datetime.getSecond();
        Assert.assertTrue(Integer.valueOf(timeStr).equals(hour * 10000 + min * 100 + sec));
    }

}