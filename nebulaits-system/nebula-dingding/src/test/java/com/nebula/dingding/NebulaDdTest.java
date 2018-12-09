package com.nebula.dingding;

import org.junit.Test;

public class NebulaDdTest {

	@Test
	public void testAdd() throws Exception {
		int num =1;
		int tims = 30;
	    int pageNum = 1;
		for (int i = 0; i < 30; i++) {
			pageNum = num +1;
			num++;
			System.out.println(pageNum+","+num);
		}
		
	}
}
