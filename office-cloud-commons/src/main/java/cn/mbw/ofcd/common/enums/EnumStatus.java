package cn.mbw.ofcd.common.enums;

import cn.mbw.ofcd.common.throwable.ServiceException;
import cn.mbw.ofcd.common.util.EnumStatusUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mabowen
 * @date 2019-12-20 17:08
 */
public interface EnumStatus extends BaseEnumStatus<Integer> {
    /** @deprecated */
    @Deprecated
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

    static boolean isInGroup(int value, EnumStatus[] group) {
        EnumStatus[] var2 = group;
        int var3 = group.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            EnumStatus item = var2[var4];
            if (((Integer)item.getValue()).equals(value)) {
                return true;
            }
        }

        return false;
    }

    static boolean isInGroup(EnumStatus status, EnumStatus[] group) {
        if (status == null) {
            return false;
        } else {
            EnumStatus[] var2 = group;
            int var3 = group.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                EnumStatus item = var2[var4];
                if (item == status) {
                    return true;
                }
            }

            return false;
        }
    }

    static Set<Integer> getValueInGroup(EnumStatus[] group) {
        if (group != null && group.length != 0) {
            Set<Integer> values = new HashSet();
            EnumStatus[] var2 = group;
            int var3 = group.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                EnumStatus item = var2[var4];
                values.add(item.getValue());
            }

            return values;
        } else {
            return Collections.emptySet();
        }
    }
}
