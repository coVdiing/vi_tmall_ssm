package com.vi.tmall.util;

/**
 * 分页工具类，实际上使用了PageHelper就不需要这个了。
 * 不过可以熟悉一下手动分页的操作
 *
 * @author vi
 */
public class Page {
    private int start;    // 开始查询的记录序号
    private int count;    // 每页展示几条数据
    private int total;    // 总记录数
    private String param;    //自定义参数，可以加载

    public Page(int start, int count) {
        super();
        this.start = start;
        this.count = count;
    }

    public Page() {
        this.start = 0;
        this.count = 5;
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean isHasPrevious() {
        return !(start == 0);
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean isHasNext() {
        return !(start >= getLast());
    }

    /**
     * 获取最后一页的开始记录序号
     *
     * @return
     */
    public int getLast() {
        int last;
        if (total % count == 0)
            last = total - count;
        else {
            last = total - total % count;
        }
        return last < 0 ? 0 : last;
    }

    /**
     * 总页数
     */
    public int getTotalPage() {
        int totalPage;
        if (total % count == 0)
            totalPage = total / count;
        else
            totalPage = total / count + 1;
        if (totalPage == 0)
            totalPage = 1;
        return totalPage;
    }

	/**
	 * 计算当前页的页码
	 * @return
	 */
    public int getCurrent() {
        int current;
        if (start % count == 0)
            current = start / count + 1;
        else
            current = start / count + 2;
        return current == 0 ? 1 : current;
    }

    //getter,setter
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
