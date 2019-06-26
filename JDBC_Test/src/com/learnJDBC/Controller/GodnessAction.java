package com.learnJDBC.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.learnJDBC.Model.Godness;
import com.learnJDBC.dao.Dao;

/**
 * 这是控制层，调用业务层dao层的方法，对数据库进行增删改查操作
 * @author Tang
 *
 */
public class GodnessAction {
	public void add(String username,int age) {
		Dao dao = new Dao();
		Godness g = new Godness();
		g.setAge(12);
		g.setUser_name(username);
		dao.add(g);
	}
	
	public void update(Godness g){
		Dao dao = new Dao();
		dao.update(g);
	}

	public void del(int id) {
		Dao dao = new Dao();
		dao.del(id);
	}

	public Godness get(int id) {
		Dao dao = new Dao();
		Godness godness = dao.get(id);
		return godness;
	}
	
	public List<Godness> query() {
		Dao dao = new Dao();
		List<Godness> gList = dao.query();
		return gList;
	}
		
	public List<Godness> query_by_param(String condition) {
		Dao dao = new Dao();
		List<Map<String,String>> param = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "user_name");
		map.put("relation", "=");
		map.put("value", "'小南'");
		param.add(map);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "age");
		map2.put("relation", ">");
		map2.put("value", "10");
		param.add(map2);
		List<Godness> godnesses = dao.query_by_param(param);
		return godnesses;
	}						
}
		
	

