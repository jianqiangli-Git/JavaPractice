package com.learnJDBC.Controller;

import java.util.List;

import com.learnJDBC.Model.Godness;
import com.learnJDBC.dao.Dao;

/**
 * 这是控制层，调用业务层dao层的方法，对数据库进行增删改查操作
 * @author Tang
 *
 */
public class GodnessAction {
	public static void main(String[] args) {
		Dao dao = new Dao();
		Godness g = new Godness();
		g.setAge(12);
		g.setId(0);
		g.setUser_name("小南");
//		dao.add(g);
		
		dao.update(g);
//		Godness godness = dao.get(2);
//		System.out.println(godness);
		
//		dao.del(2);
		
		List<Godness> gList = dao.query();
		for (Godness godness : gList) {
			System.out.println(godness);
		}
		
		
		
	}
	
	
	
}
