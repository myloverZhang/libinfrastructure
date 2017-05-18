/**
 * @author:      wangzs
 * @createDate:  2017/03/15
 */

package ct.dc.libinfrastructure;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具库
 * Created by will on 17-3-15.
 */
public class DateTimeUtils {

    /**
     * 北京时间的ZoneId
     */
    public static final ZoneId ZONE_ID_CN = ZoneId.of("UTC+08:00");

    /**
     * Utc的ZoneId
     */
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC+00:00");

    /**
     * 北京时间的ZoneOffset
     */
    public static final ZoneOffset ZONE_OFFSET_CN = ZoneOffset.of("+08:00");

    /**
     * utc的ZoneOffset
     */
    public static final ZoneOffset ZONE_OFFSET_UTC = ZoneOffset.of("+00:00");

    /**
     * 北京时间与utc时间的小时级差
     */
    public static final Integer ZONE_HOURS_DIFF_CN = 8;

    /**
     * 北京时间与utc时间的分钟级差
     */
    public static final Integer ZONE_MINS_DIFF_CN = ZONE_HOURS_DIFF_CN * 60;

    /**
     * 北京时间与utc时间的秒级差
     */
    public static final Long ZONE_SECS_DIFF_CN = ZONE_MINS_DIFF_CN * 60 * 1L;

    /**
     * 北京时间与utc时间的毫秒级差
     */
    public static final Long ZONE_MS_DIFF_CN = ZONE_SECS_DIFF_CN * 1000;

    /**
     * 北京时间与utc时间的纳秒级差
     */
    public static final Long ZONE_NANOS_DIFF_CN = ZONE_MS_DIFF_CN * 1000000;

    /**
     * 0时间戳的日期时间
     */
    public static final LocalDateTime DATETIME_19700101 = LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0);

    /**
     * 将日期转换成int
     *
     * @param date
     * @return
     */
    public static Integer date2int(LocalDate date) {
        if (date == null) {
            return 0;
        }
        return date.getYear() * 10000 + date.getMonth().getValue() * 100 + date.getDayOfMonth();
    }

    /**
     * 将时间转换成int
     *
     * @param time
     * @return
     */
    public static Integer time2int(LocalTime time) {
        if (time == null) {
            return 0;
        }
        return time.getHour() * 10000 + time.getMinute() * 100 + time.getSecond();
    }

    /**
     * 将int转化成日期
     *
     * @param dateint
     * @return
     */
    public static LocalDate int2date(Integer dateint) {
        if (dateint == null) {
            return null;
        }
        int year = dateint / 10000;
        int month = dateint / 100 - year * 100;
        int day = dateint % 100;
        return LocalDate.of(year, month, day);
    }

    /**
     * 将int转化成时间
     *
     * @param timeint
     * @return
     */
    public static LocalTime int2time(Integer timeint) {
        if (timeint == null) {
            return null;
        }
        int hour = timeint / 10000;
        int min = timeint / 100 - hour * 100;
        int sec = timeint % 100;
        return LocalTime.of(hour, min, sec);
    }

    /**
     * 将int转换成日期时间
     *
     * @param dateint
     * @param timeint
     * @return
     */
    public static LocalDateTime int2datetime(Integer dateint, Integer timeint) {
        if (dateint == null) {
            return null;
        }
        int year = dateint / 10000;
        int month = dateint / 100 - year * 100;
        int day = dateint % 100;
        int hour = 0;
        int min = 0;
        int sec = 0;
        if (timeint != null) {
            hour = timeint / 10000;
            min = timeint / 100 - hour * 100;
            sec = timeint % 100;
        }
        return LocalDateTime.of(year, month, day, hour, min, sec);
    }

    /**
     * 获取当前时间戳(毫秒)
     *时间戳
     * @return
     */
    public static Long getTimestampMs() {
        return System.currentTimeMillis();
    }


    /**
     * 根据北京时间datetime获取时间戳（秒）
     *
     * @param datetime
     * @return
     */
    public static Long cnDatetime2secsTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_CN);
        return getDurationSecs(begin, end);
    }

    /**
     * 根据utc datetime获取时间戳（秒）
     *
     * @param datetime
     * @return
     */
    public static Long utcDatetime2secsTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_UTC);
        return getDurationSecs(begin, end);
    }

    /**
     * 根据北京时间datetime获取时间戳（毫秒）
     *
     * @param datetime
     * @return
     */
    public static Long cnDatetime2msTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_CN);
        return getDurationMs(begin, end);
    }

    /**
     * 根据utc datetime获取时间戳（毫秒）
     *
     * @param datetime
     * @return
     */
    public static Long utcDatetime2msTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_UTC);
        return getDurationMs(begin, end);
    }

    /**
     * 根据北京时间datetime获取时间戳（纳秒）
     *
     * @param datetime
     * @return
     */
    public static Long cnDatetime2nanoTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_CN);
        return getDurationNano(begin, end);
    }

    /**
     * 根据Utc datetime获取时间戳（纳秒）
     *
     * @param datetime
     * @return
     */
    public static Long utcDatetime2nanoTs(LocalDateTime datetime) {
        Instant begin = DATETIME_19700101.toInstant(ZONE_OFFSET_UTC);
        Instant end = datetime.toInstant(ZONE_OFFSET_UTC);
        return getDurationNano(begin, end);
    }

    /**
     * 从时间戳(秒)转化到北京时间datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime secsTs2cnDatetime(Long ts) {
        if (ts == null || ts.toString().length() > 10) {
            return null;
        }
        ts += ZONE_SECS_DIFF_CN;
        return DATETIME_19700101.plusSeconds(ts);
    }

    /**
     * 从时间戳(秒)转化到Utc datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime secsTs2utcDatetime(Long ts) {
        if (ts == null || ts.toString().length() > 10) {
            return null;
        }
        return DATETIME_19700101.plusSeconds(ts);
    }

    /**
     * 从时间戳(毫秒)转化到北京时间datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime msTs2cnDatetime(Long ts) {
        if (ts == null || ts.toString().length() > 13) {
            return null;
        }
        ts += ZONE_MS_DIFF_CN;
        return DATETIME_19700101.plusNanos(ts * 1000000);
    }

    /**
     * 从时间戳(毫秒)转化到utc datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime msTs2utcDatetime(Long ts) {
        if (ts == null || ts.toString().length() > 13) {
            return null;
        }
        return DATETIME_19700101.plusNanos(ts * 1000000);
    }

    /**
     * 从时间戳（纳秒）转化到北京时间datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime nanoTs2cnDatetime(Long ts) {
        if (ts == null) {
            return null;
        }
        ts += ZONE_NANOS_DIFF_CN;
        return DATETIME_19700101.plusNanos(ts);
    }

    /**
     * 从时间戳（纳秒）转化到utc datetime (转化不成功返回null)
     *
     * @param ts
     * @return
     */
    public static LocalDateTime nanoTs2utcDatetime(Long ts) {
        if (ts == null) {
            return null;
        }
        return DATETIME_19700101.plusNanos(ts);
    }

    /**
     * 计算两个时间的差值(毫秒)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationMs(Instant begin, Instant end) {
        Duration timeElapsed = Duration.between(begin, end);
        return timeElapsed.toMillis();
    }

    /**
     * 计算两个时间的差值(秒)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationSecs(Instant begin, Instant end) {
        return getDurationMs(begin, end) / 1000;
    }

    /**
     * 计算两个时间的差值(纳秒)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationNano(Instant begin, Instant end) {
        Duration timeElapsed = Duration.between(begin, end);
        return timeElapsed.toNanos();
    }

    /**
     * 计算两个时间的差值(分钟)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationMins(Instant begin, Instant end) {
        Duration timeElapsed = Duration.between(begin, end);
        return timeElapsed.toMinutes();
    }

    /**
     * 计算两个时间的差值(小时)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationHours(Instant begin, Instant end) {
        Duration timeElapsed = Duration.between(begin, end);
        return timeElapsed.toHours();
    }

    /**
     * 计算两个时间的差值(天)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDurationDays(Instant begin, Instant end) {
        Duration timeElapsed = Duration.between(begin, end);
        return timeElapsed.toDays();
    }

    /**
     * 从Instant转换到北京时间LocalDateTime (如果instant为null，则返回为null)
     *
     * @param instant
     * @return
     */
    public static LocalDateTime instant2cnDatetime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZONE_OFFSET_CN);
    }

    /**
     * 从Instant转换到Utc的LocalDateTime (如果instant为null，则返回为null)
     *
     * @param instant
     * @return
     */
    public static LocalDateTime instant2utcDatetime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZONE_ID_UTC);
    }

    /**
     * 字符串转换成datetime
     *
     * @param dtStr
     * @param format
     * @return
     */
    public static LocalDateTime str2datetime(String dtStr, String format) {
        if (StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return LocalDateTime.parse(dtStr, formatter);
    }

    /**
     * datetime转换成字符串
     *
     * @param dt
     * @param format
     * @return
     */
    public static String datetime2str(LocalDateTime dt, String format) {
        if (StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return dt.format(formatter);
    }


    /**
     * 字符串转换成date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static LocalDate str2date(String dateStr, String format) {
        if (StringUtils.isNullOrWhiteSpace(dateStr) || StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * date转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2str(LocalDate date, String format) {
        if (StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return date.format(formatter);
    }

    /**
     * 字符串转换成time
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static LocalTime str2time(String timeStr, String format) {
        if (StringUtils.isNullOrWhiteSpace(timeStr) || StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return LocalTime.parse(timeStr, formatter);
    }

    /**
     * time转换成字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String time2str(LocalTime time, String format) {
        if (StringUtils.isNullOrWhiteSpace(format)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        formatter.withZone(ZONE_ID_CN);
        return time.format(formatter);
    }
}
