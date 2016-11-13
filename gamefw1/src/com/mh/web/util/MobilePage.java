package com.mh.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.servlet.ModelAndView;
@SuppressWarnings("all")
public class MobilePage<T> {
	public Map<String, List<T>> toPage(List<T> list,int pageNum,int pageSize){
		int start = 0;		
		int end = pageNum == 1 ? list.size() : pageSize;
		Map<String, List<T>> map = new TreeMap<String, List<T>>();
		for (int i = 1; i <= pageNum; i++) {
			List item = new ArrayList();
			for (int j = start; j < end; j++) {
				item.add(list.get(j));
			}
			start += pageSize;
			if(pageNum == 1 || i == pageNum - 1){
				end = list.size();
			}else{
				end += pageSize;
			}
			map.put("page" + i, item);
		}
		return map;
	}
	
	public void toPageSlot(List<T> list,int pageNum,ModelAndView model){
		Map<String, List<T>> map = new TreeMap<String, List<T>>();
		int pageSize = list.size() / pageNum;//每页展示记录条数
		int start = 0;		//list拆分起始坐标
		int end = 0;		//list拆分结束坐标
		if(pageSize % 2 == 0){ //如果每页记录数为双数
			
		}else if(list.size() % 4 == 0){//如果每页记录数为单数  并且总记录数又能整除4  36 48...
			pageSize = list.size() / 4;
			if(pageSize % 2 == 0){
				pageSize -= 2;
			}else{
				pageSize -= 1;
			}
		}else{
			pageSize = list.size() / 4;//如果每页记录数为单数 则拆分为4页  剩下的补在第5页
		}
		end = pageSize;
		for (int i = 1; i <= pageNum; i++){
			List item = new ArrayList();
			for (int j = start; j < end; j++) {
				item.add(list.get(j));
			}
			start += pageSize;
			if(i == pageNum - 1 && list.size() % pageNum != 0){
				end = list.size();
			}else{
				end += pageSize;
			}
			map.put("page" + i, item);
		}
		model.addObject("map", map);
	}
}
