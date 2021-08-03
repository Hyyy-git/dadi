package com.ccic.config.paginator;

/**
 * @descreption 分页处理器
 */
public class Paginator implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	/**
	 * default page number
	 */
	private static final int DEFAULT_PAGE_NUMBER = 1;
	/**
	 * default page size
	 */
	private static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * maximum size per page
	 */
	private static final int MAX_PAGE_SIZE = Integer.MAX_VALUE;
	/**
	 * page number
	 */
	private int pageNum = DEFAULT_PAGE_NUMBER;
	/**
	 * page size
	 */
	private int pageSize = MAX_PAGE_SIZE;

	private int limit = 0;
	private int offset = 0;
	private String action;

	/**
	 * Default constructor
	 */
	public Paginator() {
	}

	public Paginator(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	/**
	 * @return the {@link #pageNum}
	 */
	public int getPageNum() {
		if(limit != 0){
			return offset/limit + 1;
		}
		return pageNum;
	}

	/**
	 * @param pageNum the {@link #pageNum} to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = (pageNum > 0) ? pageNum : DEFAULT_PAGE_NUMBER;
	}

	/**
	 * @return the {@link #pageSize}
	 */
	public int getPageSize() {
		if(limit != 0){
			return limit;
		}
		return pageSize;
	}

	/**
	 * @param pageSize the {@link #pageSize} to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize > 0) ? pageSize : DEFAULT_PAGE_SIZE;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the {@link #action}
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the {@link #action} to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	public enum Direction {
		/**
		 * 升序
		 */
		ASC,
		/**
		 * 降序
		 */
		DESC
	}

}
