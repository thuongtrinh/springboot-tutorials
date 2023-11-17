package com.txt.security.registration.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    public static String formatDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
        return df.format(date);
    }

    public static Date parseDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr))
            return null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr))
            return null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime getDateTimeNowUTC() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static LocalDateTime getDateTimeNowUTC_VN() {
        OffsetDateTime offsetDateTime = Instant.now().atOffset(ZoneOffset.UTC);
        ZonedDateTime zonedDateTimeVN = offsetDateTime.atZoneSameInstant(ZoneId.of("UTC+7"));
        return zonedDateTimeVN.toLocalDateTime();
    }

    public static Date toDateUTC(LocalDateTime localDateTime) {
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }

    public static LocalDateTime parseDateTimeUTC(String dateStr) {
        if (StringUtils.isBlank(dateStr))
            return null;
        try {
            Instant instant = Instant.parse(dateStr);
            LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime parseDateTimeUTC_VN(String dateStr) {
        if (StringUtils.isBlank(dateStr))
            return null;
        try {
            Instant instant = Instant.parse(dateStr);
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC+7"));
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            log.debug("parseDateTimeUTC VN encounter an error {} with {}", e.getMessage(), dateStr);
        }
        return null;
    }

    public static Date parseDateUTC_VN(String dateStr) {
        try {
            LocalDateTime localDateTime = parseDateTimeUTC_VN(dateStr);
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            log.debug("parseDateUTC VN encounter an error {} with {}", e.getMessage(), dateStr);
        }

        return null;
    }

    public static Date parseDateTime(Object obj) {
        try {
            if (obj == null) return null;
            String dateStr = String.valueOf(obj);
            SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseToString(Date date, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String[] getPartsOfDate(String date) {
        if (StringUtils.isNotEmpty(date)) {
            String[] parts = date.split("/");
            return parts;
        }
        return null;
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) return "";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static LocalDate fromStrDate(String yyyyMMdd) {
        if (yyyyMMdd == null || yyyyMMdd.trim().isEmpty()) return LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYYMMDD);
        return LocalDate.parse(yyyyMMdd, dtf);
    }

    public static String getCurrentDateTimeTZ() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
    }

    public static LocalDateTime covertDateVNtoDateTimeTZ(LocalDateTime localDateTimeVn, boolean isStartDate) {
        if (isStartDate) {
            return localDateTimeVn.minusDays(1).withHour(17).withMinute(0).withSecond(0).withNano(0);
        } else {
            return localDateTimeVn.withHour(16).withMinute(59).withSecond(59).withNano(999999999);
        }
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            //convert String to LocalDate
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        if (StringUtils.isBlank(dateTimeStr)) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            //convert String to LocalDateTime
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        if (StringUtils.isBlank(dateTimeStr)) return null;
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        if (date == null) return "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(date);
    }

    public static Date addDate(Date date, int numberOfDates) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + numberOfDates);
        return cal.getTime();
    }

    public static Date getCurrentWithoutTime() {
        Date now = new Date();
        String str = formatDate(now, YYYYMMDD);
        now = parseDate(str, YYYYMMDD);
        return now;
    }

    public static Instant getCurrentTimeISOInstant() {
        ZoneId zoneId = ZoneId.of("UTC");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId).withNano(0);
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    public static Date getCurrentDateWithTimeZone(String timeZone) {
        ZoneId zoneId = ZoneId.of(timeZone);
        LocalDateTime localDateTime = LocalDateTime.now(zoneId).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    public static Date getDateStartDayUTC(LocalDateTime dateTime) {
        LocalDateTime ldt = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        return date;
    }

    public static Date getDateEndDayUTC(LocalDateTime dateTime) {
        LocalDateTime ldt = dateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        return date;
    }

    public static String formatStr(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }

    public static Date removeTime(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        } catch (Exception ex) {
            log.debug("removeTime encounter an error {} with {}", ex.getMessage(), date);
        }
        return null;
    }
}
