package com.lip.study;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JDBCTest {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String passwrod = "wenfex123";
		String userName = "bijiedev";
		int num = 100000;
		String url="";
		if(args==null||args.length==0)
		{
			url = "jdbc:mysql://101.200.75.108:3306/pnk?useUnicode=true&amp;characterEncoding=UTF-8";
		}
		else
		{

			if(args[0].equals("rds"))
			{
				url = "jdbc:mysql://rdspzi8uk259p4ez429x.mysql.rds.aliyuncs.com:3306/pnk?useUnicode=true&amp;characterEncoding=UTF-8";
			}
			else if(args[0].equals("2"))
			{
				userName="bijiedev";
				passwrod="bijiedev";
				url = "jdbc:mysql://121.196.244.121:3306/pnk?useUnicode=true&amp;characterEncoding=UTF-8";
			}
			else {
				url = "jdbc:mysql://101.200.75.108:3306/pnk?useUnicode=true&amp;characterEncoding=UTF-8";
			}
			try {
				num=Integer.parseInt(args[1]);
			} catch (Exception e) {
				num=100000;
			}
		}
		String baseInsert = "insert into test(name,value1,value2,value3,value4,value5,value6,value7,value8,value9) values";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, passwrod);
			PreparedStatement ps = null;
			long start = System.currentTimeMillis();
			System.out.println("");
			for (int i = 0; i < num; i++) {
				double value=Math.random();
				String insert = baseInsert + "('lip'" +","+value + ","+value +","+value +","+value +","+value +","+value +","+value +","+value +","+value +")";
				ps = conn.prepareStatement(insert);
				ps.executeUpdate();
			}
			long end = System.currentTimeMillis();
			System.out.println("耗时：" + (end - start));
			// 关闭声明
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// 关闭链接对象
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}