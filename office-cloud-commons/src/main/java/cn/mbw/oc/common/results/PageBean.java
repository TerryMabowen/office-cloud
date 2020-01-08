package cn.mbw.oc.common.results;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mabowen
 * @date 2020-01-08 15:19
 */
@Data
@ToString
@Accessors(chain = true)
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = -5644250102100789603L;
    // 默认的每页展示条数
    private static final long DEFAULT_PAGE_SIZE = 20L;

    // 当前第几页
    private long currentPageNum;
    // 每页展示条数
    private long pageSize = DEFAULT_PAGE_SIZE;
    // 总页数
    private long totalPage;
    // 总数据量
    private long totalCount;
    // 分页数据
    private List<T> data;

    public PageBean() {
    }

    public PageBean(long currentPageNum, long pageSize) {
        this.currentPageNum = currentPageNum;
        this.pageSize = pageSize;
    }

    // 计算总页数
    public long getPageCount() {
        // 方式一
//        if (totalCount == 0) {
//            this.totalPage = 1L;
//        } else if (this.totalCount % this.pageSize == 0) {
//            this.totalPage = this.totalCount / this.pageSize;
//        } else {
//            this.totalPage = this.totalCount / this.pageSize + 1L;
//        }
        // 方式二
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
        return this.totalPage;
    }

    // 是否是第一页
    public boolean isFirst() {
        return (this.currentPageNum == 1L) || (this.totalCount == 0);
    }

    // 是否是最后一页
    public boolean isLast() {
        return (this.totalCount == 0) || (this.currentPageNum >= getPageCount());
    }

    // 是否有下一页
    public boolean isHasNext() {
        return this.currentPageNum < getPageCount();
    }

    // 是否有上一页
    public boolean isHasPrev() {
        return this.currentPageNum > 1L;
    }

    // 下一页
    public long getNextPage() {
        if (this.currentPageNum >= getPageCount()) {
            return getPageCount();
        }
        return this.currentPageNum + 1L;
    }

    // 上一页
    public long getPrevPage() {
        if (this.currentPageNum <= 1L) {
            return 1L;
        }
        return this.currentPageNum - 1L;
    }
}
