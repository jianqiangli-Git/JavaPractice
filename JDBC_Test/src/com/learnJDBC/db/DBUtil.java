package com.learnJDBC.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库的工具类，主要是处理数据库连接，想玩提供返回连接的 Connection 对象。
 * @author Tang
 *
 */

public class DBUtil {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/godness_test?serverTimezone=GMT";
	private static final String USER="root";
	private static final String PASSWORD="123456";
	
	private static Connection connect = null;
	
	static {    //静态块在类加载的时候就执行，只执行一次，执行顺序先于构造方法。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnec() {   //静态方法可以直接
		return connect;
	}	
}
