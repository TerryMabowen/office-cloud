package cn.mbw.oc.common.enums;

import cn.mbw.oc.common.throwable.ServiceException;
import cn.mbw.oc.common.kit.lang.EnumStatusUtil;

/**
 * @author Mabowen
 * @date 2019-12-20 17:08
 */
@Deprecated
public interface EnumStatus extends BaseEnumStatus<Integer> {

    default EnumStatus getEnumStatus(int value) {
        return getEnumStatus(this.getClass(), value);
    }

    static EnumStatus getEnumStatus(Class<?> typeClass, int value) {
        EnumStatus status = EnumStatusUtil.getStatusByValue(typeClass, value);
        if (status == null) {
            throw new ServiceException("枚举值未定义, typeClass = " + typeClass.getCanonicalName() + ", Value = " + value);
        } else {
            return status;
        }
    }
}
