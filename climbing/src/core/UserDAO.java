package core;

import java.io.PrintStream;
import java.sql.*;


import javax.naming.NamingException;

import org.json.simple.JSONObject;

import util.*;

public class UserDAO {
	PrintStream out = System.out;
	public String signin(String uid, String upass) throws SQLException, ClassNotFoundException, NamingException{
		Connection conn = null;
		ResultSet rs= null;
		PreparedStatement st = null;
		try{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysns", "root", "kalutan18*");

		ConnectionPool cp = ConnectionPool.getInstance();
		
		 conn = cp.getConn();
		 String sql = "select id, password from user where id=?";
		 st = conn.prepareStatement(sql);
		st.setString(1, uid);
		String code ="OK";
		rs  = st.executeQuery();
		
		if(!rs.next()){
			code="NA";
			
		}else if(!rs.getString("password").equals(upass)){
			code= "PS";
			
		}else{
//			code=="ok";
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("id", rs.getString("id"));
			jsonobj.put("ps", rs.getString("password"));
			code = jsonobj.toJSONString();
		
		}
		return code;
		}finally{
		if(rs!=null)rs.close();
		if(st!=null)st.close();
		if(conn!=null)conn.close();
		}
		
	}
	public String signup(String uid, String upass, String uname , String ugender, String udob) throws SQLException, ClassNotFoundException{
		String code;
//		PrintStream out = System.out;
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/climbing", "root", "tsaurav18");
		
		String sql = "SELECT id FROM user WHERE id=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, uid);
		
		ResultSet rs  = st.executeQuery();
		
		if(rs.next()){
		code = "EX";
			
		}else{
			st.close();
			 sql = "INSERT INTO user(id, password, name,gender,dob) VALUES(?, ?, ?,?,?)";
			 /* sql = "insert into user(id, password, name) VALUES(";
				sql+= "'"+uid+"',";
				sql+= "'"+upass+"',";
				sql+= "'"+uname+"'";
				sql+=")";
				int cnt = st.executeUpdate(sql)
				 */
			st = conn.prepareStatement(sql); 
			
			 st.setString(1, uid);
			 st.setString(2, upass);
			 st.setString(3, uname);
			 st.setString(4,ugender);
			 st.setString(5, udob);
			 int cnnt = st.executeUpdate();
			 String res= (cnnt==0)?"ER":"OK";
			 code= res;
			
		}
		rs.close();
		st.close();
		conn.close();
		return code;
		
		
	}
	
	public String fetch(String myid) throws SQLException, ClassNotFoundException, NamingException{
		Connection conn = null;
		ResultSet rs= null;
		PreparedStatement st = null;
		try{

		ConnectionPool cp = ConnectionPool.getInstance();
		
		 conn = cp.getConn();
		 String sql = "SELECT id, name, score FROM user WHERE id !=? ORDER BY ts DESC LIMIT 20";
		 st = conn.prepareStatement(sql);
		st.setString(1, myid);
		String res ="[";
		int cnt =0;
		
		rs  = st.executeQuery();
		while(rs.next()){
			if(cnt++>0)res+=",";
			res+= "{";          //json 객체 array
			res+= "\"id\":\"" +rs.getString("id")+"\",";
			res+= "\"name\":\"" +rs.getString("name")+"\",";
			res+= "\"score\":\"" +rs.getString("score")+"\"";
			res+="}";      //[{"id": ""lee2gmail.com", "name":"saurav"}] 이런 형식으로 만들어진다.
		
		}
		res+= "]";
		out.println(res);
		return res;
		}finally{
		if(rs!=null)rs.close();
		if(st!=null)st.close();
		if(conn!=null)conn.close();
		}
		
	}
	
	public String form(String uemail, String upass) throws SQLException, ClassNotFoundException{
		String code;
//		PrintStream out = System.out;
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tsaurav18");
		
		String sql = "SELECT email FROM user WHERE email=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, uemail);
		
		ResultSet rs  = st.executeQuery();
		
		if(rs.next()){
		code = "EX";
			
		}else{
			st.close();
			 sql = "INSERT INTO user(email, password) VALUES(?,?)";
			 /* sql = "insert into user(id, password, name) VALUES(";
				sql+= "'"+uid+"',";
				sql+= "'"+upass+"',";
				sql+= "'"+uname+"'";
				sql+=")";
				int cnt = st.executeUpdate(sql)
				 */
			st = conn.prepareStatement(sql); 
			
			 st.setString(1, uemail);
			 st.setString(2, upass);
		
			
			 int cnnt = st.executeUpdate();
			 String res= (cnnt==0)?"ER":"OK";
			 code= res;
			 out.print(uemail);
			
				
		}
		
	
		rs.close();
		st.close();
		conn.close();
	
		return code;
		
	}
	
	
	
}
