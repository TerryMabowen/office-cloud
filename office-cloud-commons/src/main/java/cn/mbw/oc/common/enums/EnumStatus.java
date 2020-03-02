package cn.mbw.oc.common.enums;

import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.common.utils.EnumStatusUtils;

/**
 * @author Mabowen
 * @date 2019-12-20 17:08
 */
public interface EnumStatus extends BaseEnumStatus<Integer> {

    default EnumStatus getEnumStatus(int value) {
        return getEnumStatus(this.getClass(), value);
    }

    /** @deprecated */
    @Deprecated
    default EnumStatus getEnumStatus(Class<?> typeClass, Integer value) {
        EnumStatus status = EnumStatusUtils.getStatusByValue(typeClass, value);
        if (status == null) {
            throw new ServiceException("枚举值未定义, typeClass = " + typeClass.getCanonicalName() + ", Value = " + value);
        } else {
            return status;
        }
    }

    static EnumStatus getEnumStatus(Class<?> typeClass, int value) {
        EnumStatus status = EnumStatusUtils.getStatusByValue(typeClass, value);
        if (status == null) {
            throw new ServiceException("枚举值未定义, typeClass = " + typeClass.getCanonicalName() + ", Value = " + value);
        } else {
            return status;
        }
    }
}
