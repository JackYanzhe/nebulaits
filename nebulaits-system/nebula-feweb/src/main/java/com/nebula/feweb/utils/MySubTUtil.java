package com.nebula.feweb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author zheyan.yan
 *
 * @param <T> 泛型
 */
public class MySubTUtil<T> {

	private static final Logger logger = LoggerFactory.getLogger(MySubTUtil.class);
   
	/**
	 * 截取list集合，返回list集合
	 * @param tList  (需要截取的集合)
	 * @param subNum  (每次截取的数量)
	 * @return
	 */
	public static<T>  List<List<T>> subList(List<T> tList, Integer subNum) {
		// 新的截取到的list集合
		List<List<T>> tNewList = new ArrayList<List<T>>();
		// 要截取的下标上限
		Integer priIndex = 0;
		// 要截取的下标下限
		Integer lastIndex = 0;
		// 每次插入list的数量
		// Integer subNum = 500;
		// 查询出来list的总数目
		Integer totalNum = tList.size();
		// 总共需要插入的次数
		Integer insertTimes = totalNum / subNum;
		List<T> subNewList = new ArrayList<T>();
		for (int i = 0; i <= insertTimes; i++) {
			// [0--20) [20 --40) [40---60) [60---80) [80---100)
			priIndex = subNum * i;
			lastIndex = priIndex + subNum;
			// 判断是否是最后一次
			if (i == insertTimes) {
				
				logger.info("最后一次截取："+priIndex + "," + lastIndex);
				//logger.info("--------------------------------------");
				subNewList = tList.subList(priIndex, tList.size());
			} else {
				// 非最后一次
				//logger.info(priIndex + "," + tList.size());
				//logger.info("***************************************");
				subNewList = tList.subList(priIndex, lastIndex);

			}
			if (subNewList.size() > 0) {
				//logger.info("开始将截取的list放入新的list中");
				tNewList.add(subNewList);
			}

		}

		return tNewList;

	}
	
	/**
	 * 截取list集合，返回map集合
	 * @param tList  (需要截取的集合)
	 * @param subNum  (每次截取的数量)
	 * @return
	 */
	public static<T>  Map<Integer, List<T>> subListToMap(List<T> tList, Integer subNum) {
		// 新的截取到的list集合
		//List<List<T>> tNewList = new ArrayList<List<T>>();
		Map<Integer, List<T>> newTlsMap = new HashMap<Integer, List<T>>();
		// 要截取的下标上限
		Integer priIndex = 0;
		// 要截取的下标下限
		Integer lastIndex = 0;
		// 每次插入list的数量
		// Integer subNum = 500;
		// 查询出来list的总数目
		Integer totalNum = tList.size();
		// 总共需要插入的次数
		Integer insertTimes = totalNum / subNum;
		List<T> subNewList = new ArrayList<T>();
		for (int i = 0; i <= insertTimes; i++) {
			// [0--20) [20 --40) [40---60) [60---80) [80---100)
			priIndex = subNum * i;
			lastIndex = priIndex + subNum;
			// 判断是否是最后一次
			if (i == insertTimes) {
				//logger.info(priIndex + "," + tList.size());
				//logger.info("--------------------------------------");
				subNewList = tList.subList(priIndex, tList.size());
			} else {
				// 非最后一次
				//logger.info("最后一次截取："+priIndex + "," + lastIndex);
				//logger.info("***************************************");
				subNewList = tList.subList(priIndex, lastIndex);

			}
			if (subNewList.size() > 0) {
				//logger.info("开始将截取的list放入新的list中");
				newTlsMap.put(i, subNewList);
			}

		}

		return newTlsMap;

	}
	
	public static<T> Page<T> getPage(Page<T> page,List<T> list) {
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		int count = list.size();
		 //起始
	    int startNum =(pageNo-1)*pageSize;
	    //结束
	    int endNum = pageNo*pageSize;
	    //判断
	    if (endNum>count) {
	    	//判断是否符合最后一页要求
	    	if (endNum<count+pageSize) {
				endNum = count;
			}else {
				return null;
			}
			
		}
		//对起始进行截取list操作
	    List<T> subList = list.subList(startNum, endNum);
	    page.setCount(count);
	    page.setList(subList);
		return page;
	}
	
	
	
	
	
	
	

}
