package com.learnJDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.learnJDBC.Model.Godness;
import com.learnJDBC.db.DBUtil;
import com.mysql.cj.exceptions.RSAException;

/**
 * �߼�ҵ��㣬��Ҫ�����ݿ����CURD����
 * @author Tang
 *
 */
public class Dao {
	
	/*����Ů��*/
	public void add(Godness g){
		Connection connec = DBUtil.getConnec();
		try {
			Statement statement = connec.createStatement();
			System.out.println("���ݿ����ӳɹ�");
			String sql = "insert godness(id,user_name,age) values(?,?,?)";
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setInt(1, g.getId());
			pStatement.setString(2, g.getUser_name());
			pStatement.setInt(3, g.getAge());
			System.out.println("sql��"+sql);
			pStatement.execute();
			System.out.println("����ɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/*����Ů��idɾ��Ů��*/
	public void del(int id) {
		Connection connec = DBUtil.getConnec();
		String sql = "delete from godness where id = ?";
		try {
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setInt(1, id);
			System.out.println("sql��"+sql);
			pStatement.execute();
			System.out.println("ɾ���ɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*����Ů��id����Ů����Ϣ*/
	public void update(Godness g) {
		Connection connec = DBUtil.getConnec();
		String sql = "update godness set user_name=?,id=?,age=? where id = ?";
		try {
			PreparedStatement pStatement = connec.prepareStatement(sql);
			pStatement.setString(1, g.getUser_name());
			pStatement.setInt(2, g.getId());
			pStatement.setInt(3, g.getAge());
			pStatement.setInt(4, g.getId());
			System.out.println("sql��"+sql);
			pStatement.execute();
			System.out.println("���³ɹ���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public Godness get(int id) {
		Connection connec = DBUtil.getConnec();
		String sql = "select * from godness where id=?";
		try {
			PreparedStatement ps = connec.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println("sql��"+sql);
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
}