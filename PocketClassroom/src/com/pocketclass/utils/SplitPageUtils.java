package com.pocketclass.utils;
/**
 * 分页帮助类
 * @author zhanghan
 * @version 1.0 <br/>
 */
public class SplitPageUtils {

	public static final String FIRSTPAGE = "first";
	public static final String PREVIOUSPAGE = "previous";
	public static final String NEXTPAGE = "next";
	public static final String LASTPAGE = "last";
	
	private int pageRows = 5;//每页显示的记录数
	private int totalRows = 0; //总记录数
	private int totalPages = 1; //总页数
	private int currentPage = 1; //当前页码，默认为1
	private int firstPage = 1; //首次打开网页显示的页码，默认为1
	
	public int getPageRows() {
		return pageRows;
	}
	public void setPageRows(int pageRows) {
		if(pageRows==0)throw new ArithmeticException();//每页显示的记录数为0时，抛出异常
		this.pageRows = pageRows;
		totalPages = (totalRows % pageRows == 0)?(totalRows / pageRows):(totalRows / pageRows +1);
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		totalPages = (totalRows % pageRows == 0)?(totalRows / pageRows):(totalRows / pageRows +1);
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	
	public int  confirmPage(String flag)
	{
		int newPage = this.currentPage;
		if(flag != null)
		{
			if(flag.equals(SplitPageUtils.FIRSTPAGE))
			{
				newPage = 1;
			}
			else if(flag.equals(SplitPageUtils.LASTPAGE))
			{
				newPage = this.totalPages;
			}
			else if(flag.equals(SplitPageUtils.NEXTPAGE))
			{
				newPage=(this.totalPages==this.currentPage)?this.currentPage:this.currentPage+1;
			}
			else if(flag.equals(SplitPageUtils.PREVIOUSPAGE))
			{
				 newPage=(this.firstPage==this.currentPage)?this.currentPage:this.currentPage-1;
			}
			else
			{
				  int tpage=Integer.parseInt(flag.trim());
                  newPage=tpage;
			}
		}
		else
		{
			newPage=this.currentPage;
		}
		 this.setCurrentPage(newPage);
		return newPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
}
