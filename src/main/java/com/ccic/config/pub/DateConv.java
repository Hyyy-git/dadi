package com.ccic.config.pub;


import com.ccic.config.exception.TException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by cohenkl on 2018/1/8.
 */
public class DateConv {
    public static void main(String args[]) {
        try {
            System.out.println(DateConv.formatDate("19860504", "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("……游艺场222……………………" + ex.getMessage());
        }
    }

    private static String errMsg = null;

    private DateConv() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));//夏令时问题
    }

    private static DateConv obj = new DateConv();

    public static DateConv instance() {
        return obj;
    }

    public static String format(String srcString, String filterChar, String strFormat) throws TException {
        if (srcString == null || srcString.equals("")) {
            return "";
        }
        String retStr = null;
        try {
            String newString = srcString.replaceAll("/", "").replaceAll("-", "");
            if (filterChar != null && !filterChar.equals("")) {
                newString = newString.replaceAll(filterChar, "");
            }
            String tmpFormat = "yyyyMMdd";
            if (newString.length() > 8) {
                tmpFormat = "yyyyMMdd HH:mm:ss";
            }
            DateFormat df = new SimpleDateFormat(tmpFormat);
            Date retDate = df.parse(newString);
            retStr = new SimpleDateFormat(strFormat).format(retDate);
        } catch (ParseException ex) {
            throw new TException(ex.getMessage());
        }
        return retStr;
    }

    public static String DateIntToString(int iDate) {
        iDate -= 1;
        if (iDate < 1) {
            return ("18991231");
        }
        int iCnt = 0;
        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        int curdays = 0;
        int passdays = 0;
        int i = 0;
        while (true) {
            if (i * 365 + iCnt >= iDate) {
                break;
            }
            iYear = i;
            curdays = iDate - (i * 365) - iCnt;
            if (((i + 1900) % 400 == 0) || ((i + 1900) % 4 == 0 && (i + 1900) % 100 != 0)) {
                iCnt++;
            }
            i++;
        }
        for (i = 1; i <= 12; i++) {
            iDay = curdays - passdays;
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                passdays += 31;
            } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                passdays += 30;
            } else if (((iYear + 1900) % 400 == 0) || ((iYear + 1900) % 4 == 0 && (iYear + 1900) % 100 != 0)) {
                passdays += 29;
            } else {
                passdays += 28;
            }
            iMonth = i;
            if (passdays >= curdays) {
                break;
            }
        }
        iYear += 1900;
        StringBuffer outstr = new StringBuffer();
        outstr.append(iYear);
        Integer iTmp = new Integer(iMonth);
        String strMonth = iTmp.toString();
        if (strMonth.length() < 2) {
            outstr.append("0");
            outstr.append(strMonth);
        } else {
            outstr.append(strMonth);
        }
        iTmp = new Integer(iDay);
        String strDay = iTmp.toString();
        if (strDay.length() < 2) {
            outstr.append("0");
            outstr.append(strDay);
        } else {
            outstr.append(strDay);
        }
        return outstr.toString();
    }

    public static String DateStringConv(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        String strRet = new String(strDate);
        return strRet.replaceAll("/", "").replaceAll("-", "");
    }

    public static String DateTimeToDate(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        String strRet = strDate.replaceAll("/", "").replaceAll("-", "");
        return strRet.substring(0, 8);
    }

    public static int DateStringToInt(String strDate) {
        String strTmpDate = new String(strDate);
        if (strTmpDate.length() != 8) {
            errMsg = strDate + " 日期数据不正确，正确格式[YYYYMMDD]";
            return (-1);
        }
        String strYear = strTmpDate.substring(0, 4);
        String strMonth = strTmpDate.substring(4, 6);
        String strDay = strTmpDate.substring(6);
        int iYear = Integer.parseInt(strYear);
        int iMonth = Integer.parseInt(strMonth);
        int iDay = Integer.parseInt(strDay);
        if (iYear < 1900 || iYear >= 2900) {
            errMsg = "日期的年份不正确";
            return (-1);
        }
        if (iMonth < 1 || iMonth > 12) {
            errMsg = "日期的月份应在0-12之间";
            return (-1);
        }
        if (iMonth == 1 || iMonth == 3 || iMonth == 5 || iMonth == 7 || iMonth == 8 || iMonth == 10 || iMonth == 12) {
            if (iDay < 1 || iDay > 31) {
                errMsg = "日期天数不正确";
                return (-1);
            }
        } else if (iMonth == 2) {
            // 闰年
            if ((iYear % 400 == 0) || (iYear % 4 == 0 && iYear % 100 != 0)) {
                if (iDay < 1 || iDay > 29) {
                    errMsg = "日期天数不正确";
                    return (-1);
                }
            } else {
                if (iDay < 1 || iDay > 28) {
                    errMsg = "日期天数不正确";
                    return (-1);
                }
            }
        } else {
            if (iDay < 1 || iDay > 30) {
                errMsg = "日期天数不正确";
                return (-1);
            }
        }
        int iDate = iDay;
        for (int i = 0; i < iMonth; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: {
                    iDate += 31;
                    break;
                }
                case 4:
                case 6:
                case 9:
                case 11: {
                    iDate += 30;
                    break;
                }
                case 2: {
                    // 闰年
                    if ((iYear % 400 == 0) || (iYear % 4 == 0 && iYear % 100 != 0)) {
                        iDate += 29;
                    } else {
                        iDate += 28;
                    }
                    break;
                }
            } // switch
        } // for iMonth
        for (int i = 0; i < iYear - 1900; i++) {
            if (((i + 1900) % 400 == 0) || ((i + 1900) % 4 == 0 && (i + 1900) % 100 != 0)) {
                iDate += 366;
            } else {
                iDate += 365;
            }
        }
        iDate += 1;
        return ((int) iDate);
    }

    public static String TimeToStrTime(long localtime, String strFormat) {
        SimpleDateFormat formatter = null;
        if (strFormat != null && strFormat.indexOf("-") >= 0) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
        Date myDate = new Date();
        myDate.setTime(localtime);
        String outDate = formatter.format(myDate);
        return outDate;
    }

    // 校验时间或日期格式 timeOrDate==true yyyyMMdd HH:mm:ss false:yyyyMMdd
    public static boolean checkDateFormat(String str, boolean bTimeOrDate) {
        try {
            if (bTimeOrDate) {
                checkDataTimt(str);
            } else {
                if (!Pattern.compile("^\\d{8}$").matcher(str).matches()) {
                    return false;
                }
                String format = "yyyyMMdd";
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                formatter.setLenient(false);// 严格规定时间格式
                formatter.parse(str);
            }
        } catch (TException io) {
            return false;
        } catch (ParseException io) {
            return false;
        }
        return true;
    }

    public static Date StrTimeToDate(String strTime) throws TException {
        // strTime不足时分秒的部分，需先补齐时分秒" HH:mm:ss"，再调用此方法
        // 时间串格式:"yyyyMMdd HH:mm:ss"
        String tmpTime = DateStringConv(strTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date myDate = null;
        try {
            // formatter.setLenient(false);// 严格规定时间格式
            checkDataTimt(tmpTime);
            myDate = formatter.parse(tmpTime);
        } catch (TException io) {
            throw new TException(io.getMessage());
        } catch (ParseException io) {
            throw new TException("格式化日期时间字符串到time型失败:" + strTime);
        }
        return myDate;
    }

    //isFilterHoliday  true:过滤非工作日  目前给批改特用
/*    public static String addDate(String strTime, String inputFormat, String outputFormat,
                                 boolean isFilterHoliday, int addDays) throws TException {
        SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
        formatter.setLenient(false);
        Calendar c = Calendar.getInstance();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        try {
            c.setTime(formatter.parse(strTime));
        } catch (ParseException e) {
            throw new EhomeException("按输入格式[" + inputFormat + "]进行日期格式化失败:" + strTime);
        }
        if (!isFilterHoliday || !checkHoliday(c)) {
            c.add(Calendar.DAY_OF_MONTH, addDays);
        } else {
            //先校验addDays的前一天
            c.add(Calendar.DAY_OF_MONTH, addDays > 0 ? addDays-1 : addDays+1);
            while (checkHoliday(c)) {
                c.add(Calendar.DAY_OF_MONTH, addDays > 0 ? 1 : -1);
            }
            c.add(Calendar.DAY_OF_MONTH, addDays > 0 ? 1 : -1);
        }
        return (new SimpleDateFormat(outputFormat).format(c.getTime()));
    }*/
    //校验是否是假期
   /* public static boolean checkHoliday(Calendar ic) throws TException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Calendar cc = Calendar.getInstance();
        String workDayList = InitClass.getInstance().getProp().getProperty("SYS_WORK_DAY_LIST", "");
        if (!workDayList.equals("")) {
            String[] workDayArr = workDayList.split(",");
            for (String workDay: workDayArr) {
                try {
                    cc.setTime(formatter.parse(workDay));
                } catch (ParseException e) {
                    throw new EhomeException("校验工作日配置文件日期格式应为[yyyyMMdd]");
                }
                if (cc.get(Calendar.YEAR) == ic.get(Calendar.YEAR) &&
                        cc.get(Calendar.MONTH) == ic.get(Calendar.MONTH) &&
                        cc.get(Calendar.DAY_OF_MONTH) == ic.get(Calendar.DAY_OF_MONTH)) {
                    return false;
                }
            }
        }

        String holidayList = InitClass.getInstance().getProp().getProperty("SYS_HOLIDAY_LIST", "");
        if (!holidayList.equals("")) {
            String[] holidayArr = holidayList.split(",");
            for (String holiday: holidayArr) {
                try {
                    cc.setTime(formatter.parse(holiday));
                } catch (ParseException e) {
                    throw new EhomeException("校验非工作日配置文件日期格式应为[yyyyMMdd]");
                }
                if (cc.get(Calendar.YEAR) == ic.get(Calendar.YEAR) &&
                        cc.get(Calendar.MONTH) == ic.get(Calendar.MONTH) &&
                        cc.get(Calendar.DAY_OF_MONTH) == ic.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        }
        if (ic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                ic.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }*/
    /**
     * 功能：获取工作日批改时间；
     * @return
     * @throws TException
     */
/*    public static boolean checkNotWorkDay() throws TException {
        Calendar c = Calendar.getInstance();
        return checkHoliday(c);
    }*/
    public static void checkDataTimt(String strDateTime) throws TException {
        if(strDateTime.length() == 20 && strDateTime.lastIndexOf(":00")==17){
            strDateTime=strDateTime.substring(0,17);
        }//特殊处理， 接口用户中有客户传输航班时间没有按要求传输（yyyy-MM-dd HH:mm）。以后逐步改掉

        if (strDateTime.length() != 17) {
            throw new TException("日期格式错误:" + strDateTime);
        } else {
            String strDate = strDateTime.substring(0, 8);
            String strTime = strDateTime.substring(9);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            try {
                formatter.setLenient(false);// 严格规定日期格式
                formatter.parse(strDate);
            } catch (ParseException io) {
                throw new TException("格式化日期时间字符串到time型失败:" + strTime);
            }
            if (strTime.indexOf(":") < 0) {
                throw new TException("日期格式错误:" + strDateTime);
            } else {
                String strHour = strTime.substring(0, strTime.indexOf(":"));
                String strMin = strTime.substring(strTime.indexOf(":") + 1, strTime.lastIndexOf(":"));
                String strSecond = strTime.substring(strTime.lastIndexOf(":") + 1);
                if (strHour.length()!=2 || Integer.parseInt(strHour) > 23 || strMin.length()!=2 || Integer.parseInt(strMin) > 59 || strSecond.length()!=2 || Integer.parseInt(strSecond) > 59) {
                    throw new TException("日期格式错误:" + strDateTime);
                }
            }
        }
    }

    public static long StrTimeToTime(String strTime) throws TException {
        return StrTimeToDate(strTime).getTime();
    }

    // 日期时间加减相应的秒数得到对应的日期时间串，天、时、分都换算成秒传入secs参数
    public static String dateTimeAdded(String strTime, int secs) throws TException {
        // 例子：dateTimeAdded("20091231 00:00:00", 24*3600);加一天
        long time = StrTimeToTime(strTime);
        String newtime = TimeToStrTime(time + (long) secs * 1000, "-");
        return newtime;
    }

    // 日期时间加减月数得到对应的日期时间串
    public static String dateTimeMonthAdded(String strTime, int months, boolean bFlag) throws TException {
        // bFlag:return结果为对应秒的前1秒 !bFlag:return结果为对应秒
        StrTimeToDate(strTime);// 校验入参的格式合法性
        String dateStr = strTime.substring(0, 8);
        String timeStr = strTime.substring(9);
        int iNewDate = IncMonth(DateStringToInt(dateStr), months);
        String newDateTime = DateIntToString(iNewDate) + " " + timeStr;
        if (bFlag) {
            return dateTimeAdded(newDateTime, -1).replaceAll("-", "");
        } else {
            return newDateTime;
        }
    }

    public static String dateTimeAddedByType(String strDateTime, int iNum, int dType, boolean bFlag) throws TException {
        if (dType == 1) {
            // 加天数
            int secs = iNum * 24 * 3600;
            if (bFlag) {
                secs -= 1;
            }
            return dateTimeAdded(strDateTime, secs);
        } else if (dType == 2) {
            // 加月数
            return dateTimeMonthAdded(strDateTime, iNum, bFlag);
        } else {
            throw new TException("[service]加减日期计算方法调用有误");
        }
    }

    public static String IncDay(String strStartDate, int iNum) {
        int iDate = DateStringToInt(strStartDate);
        if (iDate < 0) {
            errMsg = "日期数据不正确，正确格式[YYYYMMDD]";
            return ("18991231");
        }
        iDate = iDate + iNum;
        String strDate = DateIntToString(iDate);
        if (strDate.equals("")) {
            return ("18991231");
        }
        return (strDate);
    }

    public static String getNextSysDate(int nextday) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, nextday);
        return (new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }

    public static String getErrMsg() {
        return errMsg;
    }

    public static String getCurrSysTime() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    public static String getSysTime() {
        return (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
    }

    public static String getSysDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public static String getCurrSysDate() {
        return (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public static String getCharisCurrSysDate() {
        Locale locale = new Locale("en", "US");
        DateFormat format = DateFormat.getDateInstance(DateFormat.MONTH_FIELD, locale);
        String value = format.format(new Date()).replaceAll(",", "").toUpperCase();
        String[] values = value.split(" ");
        if (values[1].length() == 1) {
            values[1] = "0" + values[1];
        }
        return values[1] + " " + values[0] + " " + values[2];
        // return (new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
    }


    public static String formatDateTime(long dateTime, String format) {
        return (new SimpleDateFormat(format).format(dateTime));
    }

    public static String formatDateDate(Date dateTime, String format) {
        return (new SimpleDateFormat(format).format(dateTime));
    }

    // 根据保险的期限，加对应月后得到对应的月数对应的一天，即对应月的前一天
    public static String incMonthGetRelateDay(String startDate, int iNum) {
        return DateIntToString(IncMonth(DateStringToInt(startDate), iNum) - 1);
    }

    public static String IncMonth(String startDate, int iNum) {
        return DateIntToString(IncMonth(DateStringToInt(startDate), iNum));
    }

    public static int IncMonth(int iStartDate, int iNum) {
        String strDate = DateIntToString(iStartDate);
        if (strDate.equals(""))
            return (-1);
        String strYear = strDate.substring(0, 4);
        String strMonth = strDate.substring(4, 6);
        String strDay = strDate.substring(6);

        int iYear = Integer.parseInt(strYear);
        int iMonth = Integer.parseInt(strMonth);
        int iDay = Integer.parseInt(strDay);
        iMonth += iNum;
        while (iMonth > 12) {
            iMonth -= 12;
            iYear += 1;
        }
        while (iMonth <= 0) {
            iMonth += 12;
            iYear -= 1;
        }
        int iDate = 0;
        int iCnt = 0;
        while (true) {
            strMonth = String.valueOf(iMonth);
            if (strMonth.length() < 2) {
                strMonth = "0" + strMonth;
            }
            strDay = String.valueOf(iDay);
            if (strDay.length() < 2) {
                strDay = '0' + strDay;
            }
            String strTmp = iYear + strMonth + strDay;
            iDate = DateStringToInt(strTmp);
            if (iDate >= 0)
                break;
            iDay -= 1;
            if (iCnt > 3) {
                errMsg = "日期加月数计算对应的日期出错";
                return (-1);
            }
            iCnt++;
        }
        return (iDate);
    }

    // 根据dGetType计算两个日期时间型之间的间隔年、月、周、天数
    public static int calDateTimeSpace(String startDateTime, String endDateTime, int dGetType, boolean bFlag) throws TException {
        if (startDateTime == null || endDateTime == null || startDateTime.equals("") || endDateTime.equals("")) {
            throw new TException("计算日期差的入参都不能为空");
        }
        // bFlag:不满一天算一天 !bFlag:满一天了才算一天 同理：年、月、周也如此
        // dGetType:1年 2月 3周 4天
        switch (dGetType) {
            case (1): {
                // 年
                int iMonth = calDateTimeSpace(startDateTime, endDateTime, 2, bFlag);
                if (bFlag) {
                    return (int) (iMonth + 11) / 12;
                } else {
                    return (int) iMonth / 12;
                }
            }
            case (2): {
                // 月
                int iNum = 0;
                if (bFlag) {
                    while (true) {
                        String tmpDate = dateTimeMonthAdded(startDateTime, iNum, true);
                        if (tmpDate.compareTo(DateStringConv(endDateTime)) >= 0)
                            break;
                        iNum++;
                    }
                    return iNum;
                } else {
                    while (true) {
                        String tmpDate = dateTimeMonthAdded(startDateTime, iNum + 1, true);
                        if (tmpDate.compareTo(DateStringConv(endDateTime)) > 0)
                            break;
                        iNum++;
                    }
                    return iNum;
                }
            }
            case (3): {
                // 周
                int days = calDateTimeSpace(startDateTime, endDateTime, 4, bFlag);
                if (bFlag) {
                    return (int) (days + 6) / 7;
                } else {
                    return (int) days / 7;
                }
            }
            case (4): {
                // 天
                double days = (double) (StrTimeToTime(endDateTime) - StrTimeToTime(startDateTime) + 1000) / (24 * 3600 * 1000);
                if (bFlag) {
                    return (int) Math.ceil(days);
                } else {
                    return (int) Math.floor(days);
                }
            }
            default:
                throw new TException("server日期时间计算类型参数不匹配");
        }
    }

    // 计算日期之间的间隔数
    public static int GetDateSpace(int dCalTimeType, String strStartDate, String strEndDate, int dGetType, boolean bFlag) {
        return GetDateSpace(dCalTimeType, DateStringToInt(strStartDate), DateStringToInt(strEndDate), dGetType, bFlag);
    }

    // dCalTimeType:: 1 超过才算满期 2 一样才算满期 3 前一天就算满期
    public static int GetDateSpace(int dCalTimeType, int iStartDate, int iEndDate, int dGetType, boolean bFlag) {
        if (iStartDate == 0)
            return 0;
        if (dCalTimeType == 1) {
            iEndDate -= 2;
        } else if (dCalTimeType == 2) {
            iEndDate -= 1;
        } else if (dCalTimeType != 3) {
            return -2;
        }
        int intMonth = 0;

        if (bFlag) { // 不满一年算一年
            if (dGetType == 1) { // 取年数
                // 不足一个月算一个月;
                while (true) {
                    int tmpdate = IncMonth(iStartDate, intMonth) - 1;
                    if (tmpdate == -2) {
                        return -1;
                    }
                    if (iEndDate <= tmpdate)
                        break;
                    intMonth++;
                }
                // 不足一个年算一年;
                if ((intMonth % 12) == 0)
                    return (intMonth / 12); // 整年
                else
                    return (intMonth / 12 + 1); // 整年;
            } else if (dGetType == 2) { // 取月数
                // 不足一个月算一个月;
                while (true) {
                    int tmpdate = IncMonth(iStartDate, intMonth) - 1;
                    if (tmpdate == -2) {
                        return -1;
                    }
                    if (iEndDate <= tmpdate)
                        break;
                    intMonth++;
                }
                return intMonth;
            } else if (dGetType == 3) { // 取星期数
                int iDays = iEndDate - iStartDate + 1;
                return (int) (iDays + 6) / 7;
            } else if (dGetType == 4) { // 取天数
                int iDays = iEndDate - iStartDate + 1;
                return iDays;
            }
        } else {
            if (dGetType == 1) {
                // 满一个月才算一个月;
                while (true) {
                    int tmpdate = IncMonth(iStartDate, intMonth + 1) - 1;
                    if (tmpdate == -2) {
                        return -1;
                    }
                    if (iEndDate < tmpdate)
                        break;
                    intMonth++;
                }
                // 满一个年才算一年;
                if ((intMonth % 12) == 0)
                    return ((int) intMonth / 12); // 整年
                else
                    return ((int) intMonth / 12); // 整年
            } else if (dGetType == 2) {
                // 满一个月才算一个月;
                while (true) {
                    int tmpdate = IncMonth(iStartDate, intMonth + 1) - 1;
                    if (tmpdate == -2) {
                        return -1;
                    }
                    if (iEndDate < tmpdate)
                        break;
                    intMonth++;
                }
                return intMonth;
            } else if (dGetType == 3) { // 取星期数，满一个星期才算一个星期
                int iDays = iEndDate - iStartDate + 1;
                return (int) iDays / 7;
            } else if (dGetType == 4) { // 取天数，满一天才算一天
                int iDays = iEndDate - iStartDate;
                return iDays;
            }
        }
        return -1;
    }

    public static String getMonthFirstDay() {
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return today.substring(0, 6) + "01";
    }

    public static String getMonthLastDay() {
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String year = today.substring(0, 4);
        String month = today.substring(4, 6);
        String day = "31";
        if (month.equals("02")) {
            int iYear = Integer.parseInt(year);
            if (iYear % 400 == 0 || (iYear % 4 == 0 && iYear % 100 != 0)) {
                day = "29";
            } else {
                day = "28";
            }
        } else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
            day = "30";
        }
        return year + month + day;
    }
    public static String getLastMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        int day=calendar.get(Calendar.DATE);
        calendar.add(Calendar.DATE, -day);
        return (new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }
    public static String getTimeOfDateTime(String strDateTime){
        String strTime = "";
        if(strDateTime.length()>8 && strDateTime.lastIndexOf(":")>8){
            String newString = strDateTime.replaceAll("/", "").replaceAll("-", "");
            strTime = newString.substring(9);
        }
        return strTime;
    }

    //通用日期或时间各种格式的格式化，返回dateFormat约定的格式，如yyyyMMdd HH:mm:ss  //added by zhaohaibin on 2016-12-15
    public static String formatDate(String dateStr, String dateFormat) throws TException {
        if (dateStr == null || dateStr.equals("")) {
            return "";
        }
        HashMap<String, String> dateRegFormat = new HashMap<String, String>();
        dateRegFormat.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$",
                "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$",
                "yyyy-MM-dd-HH");//2014-03-12 12
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd");//2014-03-12
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}$", "yyyy-MM");//2014-03
        dateRegFormat.put("^\\d{4}$", "yyyy");//2014
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
        dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
        dateRegFormat.put("^\\d{8}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyyMMdd-HH-mm-ss");//20140312 12:05:34
        dateRegFormat.put("^\\d{8}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyyMMdd-HH-mm");//20140312 12:05
        dateRegFormat.put("^\\d{8}\\D+\\d{1,2}$", "yyyyMMdd-HH");//20140312 12
        dateRegFormat.put("^\\d{1,2}\\s*:\\s*\\d{1,2}\\s*:\\s*\\d{1,2}$",
                "yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
        dateRegFormat.put("^\\d{1,2}\\s*:\\s*\\d{1,2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
        dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)

        DateFormat formatter1 = new SimpleDateFormat(dateFormat);
        DateFormat formatter2 = null;
        String dateReplace = dateStr;
        String strSuccess = "";
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
                    String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    if (key.equals("^\\d{1,2}\\s*:\\s*\\d{1,2}\\s*:\\s*\\d{1,2}$")
                            || key.equals("^\\d{1,2}\\s*:\\s*\\d{1,2}$")) {//13:05:34 或 13:05 拼接当前日期
                        dateReplace = curDate + " " + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
                        dateReplace = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    dateReplace = dateReplace.replaceAll("\\D+", "-");
                    strSuccess = formatter1.format(formatter2.parse(dateReplace));
                    return strSuccess;
                }
            }
            throw new TException("输入日期格式[" + dateStr + "]未能匹配解析");
        } catch (TException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException("输入日期格式[" + dateStr + "]无效");
        }
    }
}
