package com.jdbc.helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.ResultSetMetaData;

public class Broker {
	// 静态加载块
	// static class F{
	// //在new Broker的时候会查找相关配置进行
	// }
	// 这些配置应该是从文件中获取先这样
	public static final String filePath = "src/main/resources/jdbc.properties";
	public static String url = "";
	public static String driver = "";
	public static String username = "";
	public static String password = "";
	public Connection conn = null;
	public PreparedStatement pst = null;

	private static Connection getConn() {
		Connection conn = null;
		try {
			url = PropertiesHelper.GetValueByKey(filePath, "url");
			driver = PropertiesHelper.GetValueByKey(filePath, "driverClassName");
			username = PropertiesHelper.GetValueByKey(filePath, "username");
			password = PropertiesHelper.GetValueByKey(filePath, "password");
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// insert update delete 操作
	private static int excute(String sql) {
		Connection conn = getConn();
		int i = 0;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	//查询方法
	public static List<Map<String, Object>>  query(String sql) throws Exception {
		Connection conn = getConn();
		PreparedStatement pstmt;
		ResultSet resultSet = null;
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		try {
			pstmt = conn.prepareStatement(sql);// 准备执行语句
			resultSet = pstmt.executeQuery();
			ResultSetMetaData metaData =(ResultSetMetaData) resultSet.getMetaData();  
	        int cols_len = metaData.getColumnCount();  
	        while(resultSet.next()){  
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for(int i=0; i<cols_len; i++){  
	                String cols_name = metaData.getColumnName(i+1);  
	                Object cols_value = resultSet.getObject(cols_name);  
	                if(cols_value == null){  
	                    cols_value = "";  
	                }  
	                map.put(cols_name, cols_value);  
	            }  
	            list.add(map);  
	        }  
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return list;
	}

	// //select
	// private static Integer getAll() {
	// Connection conn = getConn();
	// String sql = "select * from students";
	// PreparedStatement pstmt;
	// try {
	// pstmt = (PreparedStatement)conn.prepareStatement(sql);
	// ResultSet rs = pstmt.executeQuery();
	// int col = rs.getMetaData().getColumnCount();
	// System.out.println("============================");
	// while (rs.next()) {
	// for (int i = 1; i <= col; i++) {
	// System.out.print(rs.getString(i) + "\t");
	// if ((i == 2) && (rs.getString(i).length() < 8)) {
	// System.out.print("\t");
	// }
	// }
	// System.out.println("");
	// }
	// System.out.println("============================");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
}
