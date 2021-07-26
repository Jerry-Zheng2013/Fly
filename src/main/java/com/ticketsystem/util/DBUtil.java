package com.ticketsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DBUtil {
	private Connection conn;
	public Statement stmt;
  	private DataSource ds;

	/**
		执行查询
	*/
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		rs = stmt.executeQuery(sql);
		//System.out.println ("执行查询");
		return rs;
	}
	
	/**
	执行更新
	*/
	public int executeUpdate(String sql) throws SQLException {
		int ret = 0;
		ret = stmt.executeUpdate(sql);
		System.out.println ("执行更新");
		return ret;
	}

	/**
		打开数据库
	*/
	public void open() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket?useSSL=false&serverTimezone=UTC","qfly","qfly");
			stmt=conn.createStatement();
			//System.out.println("打开数据库连接");
		} catch (Exception ex) {
			System.err.println("打开数据库时出错: " + ex.getMessage());
		}
	}

	/**
		关闭数据库，将连接返还给连接池
	*/
	public void close() {
		try{
			conn.close();
			//System.out.println ("释放连接");
		} catch (SQLException ex) {
			System.err.println("返还连接池出错: " + ex.getMessage());
		}
	}
}
