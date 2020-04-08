package cn.mbw.oc.common.util.date;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-04-08 22:07
 */
public class DateKit {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * TODO
     * @author Mabowen
     * @date 22:12 2020-04-08
     * @param
     * @return
     */
    public static String format(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parse(String str, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = PATTERN;
        }

        if (StringUtils.isBlank(str)) {

        }
    }
}
