package com.ticketsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {
	
	Logger log = LogManager.getLogger(DBUtil.class);
	
	private Connection conn;
	public Statement stmt;
  	private DataSource ds;

	/**
		执行查询
	*/
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		rs = stmt.executeQuery(sql);
		return rs;
	}
	
	/**
	执行更新
	*/
	public int executeUpdate(String sql) throws SQLException {
		int ret = 0;
		ret = stmt.executeUpdate(sql);
		log.info ("执行更新");
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
			//log.info("打开数据库连接");
		} catch (Exception ex) {
			log.error("打开数据库时出错: " + ex.getMessage());
		}
	}

	/**
		关闭数据库，将连接返还给连接池
	*/
	public void close() {
		try{
			conn.close();
			//log.info ("释放连接");
		} catch (SQLException ex) {
			log.error("返还连接池出错: " + ex.getMessage());
		}
	}
}
