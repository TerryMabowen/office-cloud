package cn.mbw.oc.dal.entry.base;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.util.Date;

/**
 * @author Mabowen
 * @date 2019-12-18 19:30
 */
@Data
public class BaseEntry {
    private Integer status = 1;

    private Date createdAt;

    @Ignore
    private String createdBy;

    private Date updatedAt;

    @Ignore
    private String updatedBy;

    public Date getCretedAt() {
        return new Date();
    }

    public Date getUpdatedAt() {
        return new Date();
    }
}
