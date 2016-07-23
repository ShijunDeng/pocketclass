package com.pocketclass.test;

import org.junit.Test;

import com.pocketclass.dao.impl.customer.CourseFavoDaoImpl;

public class CourFavoDaoImplTest {
	@Test
	public void test(){
		CourseFavoDaoImpl cfd=new CourseFavoDaoImpl();
		cfd.setCurrentPageTag(0);
		cfd.setPageRecordsNum(2);
		System.out.println(cfd.getRecordsNum("TWT946057490"));
		System.out.println(cfd.getTotalPagesNum("TWT946057490"));
	}

}
