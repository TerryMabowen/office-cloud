package cn.mbw.oc.spy.spi.enums;

import cn.mbw.oc.common.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Mabowen
 * @date 2019-12-18 19:59
 */
@Getter
@AllArgsConstructor
public enum EnumDataType implements EnumStatus {
    /*
     * 整数
     */
    INT(1, "整数"),

    /*
     * 单精度浮点数
     */
    FLOAT(2, "单精度浮点数"),

    /*
     * 双精度浮点数
     */
    DOUBLE(3, "双精度浮点数"),

    /*
     * 字符串
     */
    STRING(4, "字符串"),

    /*
     * 时间类型(yyyy-MM-dd)
     */
    DATE(5, "时间"),

    /*
     * 布尔值类型
     */
    BOOL(6, "布尔");

    private Integer value;
    private String desc;
}
