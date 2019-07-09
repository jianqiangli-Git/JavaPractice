package com.learnJDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.learnJDBC.Model.Godness;
import com.learnJDBC.db.DBUtil;
import com.mysql.cj.exceptions.RSAException;

/**
 * 逻辑业务层，主要对数据库进行CURD操作
 * @author Tang
 *
 */
public class Dao {	
	/*添加女神*/
	public void add(Godness g){
		Connection connec = DBUtil.getConnec();
		try {
			Statement statement = connec.createStatement();
			System.out.println("数据库连接成功");
			String sql = "insert godness(id,user_name,age) values(?,?,?)"; //? 是占位符
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setInt(1, g.getId());
			pStatement.setString(2, g.getUser_name()); //给占位符赋值
			pStatement.setInt(3, g.getAge());
			System.out.println("sql："+sql);
			pStatement.execute(); //执行 sql 语句
			System.out.println("插入成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*根据女神id删除女神*/
	public void del(int id) {
		Connection connec = DBUtil.getConnec();
		String sql = "delete from godness where id = ?";
		try {
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setInt(1, id);
			System.out.println("sql："+sql);
			pStatement.execute();
			System.out.println("删除成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*根据女神id更新女神信息*/
	public void update(Godness g) {
		Connection connec = DBUtil.getConnec();
		String sql = "update godness set user_name=?,id=?,age=? where id = ?";
		try {
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setString(1, g.getUser_name());
			pStatement.setInt(2, g.getId());
			pStatement.setInt(3, g.getAge());
			pStatement.setInt(4, g.getId());
			System.out.println("sql："+sql);
			pStatement.execute();
			System.out.println("更新成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*查询女神的列表(List集合)*/
	public List<Godness> query() {
		Connection connec = DBUtil.getConnec();
		String sql = "select * from godness";
		List<Godness> gs = new ArrayList<Godness>();
		try {
			PreparedStatement ps = connec.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Godness g = new Godness();
				g.setAge(rs.getInt("age"));
				g.setId(rs.getInt("id"));
				g.setUser_name(rs.getString("user_name"));
				
				gs.add(g);
			}
			return gs;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*根据id查询女神*/
	public Godness get(int id) {
		Connection connec = DBUtil.getConnec();
		String sql = "select * from godness where id=?";
		try {
			PreparedStatement ps = connec.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println("sql："+sql);
			ResultSet rs = ps.executeQuery();
			
			Godness g = new Godness();
			if (rs.next()!= false) {
				g.setAge(rs.getInt("age"));
				g.setUser_name(rs.getString("user_name"));
				g.setId(rs.getInt("id"));
				
				return g;				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*使用Map集合自定义查询条件返回女神列表(List集合)*/
	public List<Godness> query_by_param(List<Map<String, String>> param) {
		Connection connec = DBUtil.getConnec();
		String s = "select * from godness where 1 = 1"; //使用where来添加多个查询条件,1=1是个tip  
		StringBuilder sql = new StringBuilder(s);//使用StringBuilder创建可变字符串
		List<Godness> godnesses = new ArrayList<Godness>();
		for (Map<String, String> map : param) {
			String sql_add = " and " + map.get("name")+" "+map.get("relation")+" "+map.get("value");
			sql.append(sql_add);
		}
		
		System.out.println("sql语句："+sql.toString());//System.out.println("SQL: "+sql);
		try {
			PreparedStatement pStatement = connec.prepareStatement(sql.toString());
			ResultSet rSet = pStatement.executeQuery();
			
			while (rSet.next()) {
				Godness god = new Godness();
				god.setId(rSet.getInt("id"));
				god.setAge(rSet.getInt("age"));
				god.setUser_name(rSet.getString("user_name"));
				
				godnesses.add(god);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return godnesses;
	}
}
