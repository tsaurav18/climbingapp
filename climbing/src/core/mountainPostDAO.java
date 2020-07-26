package core;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.ConnectionPool;

public class mountainPostDAO {
	PrintStream out = System.out;
	 public String postAdd(String uid , String mountainfeed, JSONArray images ) throws SQLException, ClassNotFoundException, NamingException, ParseException{

	                Connection conn = null;
	                ResultSet rs = null;
	                PreparedStatement st= null;
	                String sql = null;
	try{
		conn = ConnectionPool.getInstance().getConn();
	   synchronized(this){
             sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES  ";
 	          sql+= "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME='postmountain'";
	    				st = conn.prepareStatement(sql);
	    				rs = st.executeQuery();
	    				if(!rs.next()) return "ER";
	    				int feedNo = rs.getInt(1);
	                    
				JSONObject jsonobj = (JSONObject)(new JSONParser()).parse(mountainfeed);
				jsonobj.put("no", feedNo);
				jsonobj.put("images", images);
	                        
				 sql = "INSERT INTO postmountain(id,content) VALUES(?,?)";
			
				st = conn.prepareStatement(sql); 
				
				 st.setString(1,uid);
				 
				 st.setString(2,jsonobj.toJSONString());
	                     
				 int cnnt = st.executeUpdate();
				 return (cnnt==0)?"ER":"OK";
	                }
			
	                       
	     }finally{
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(conn!=null)conn.close();
			

	 }
	}

	 
	 
	
	
	 
	 
	 
	 //main fetch
	 public String fetch() throws SQLException, ClassNotFoundException, NamingException{
			Connection conn = null;
			ResultSet rs= null;
			Statement st = null;			
			try{
			ConnectionPool cp = ConnectionPool.getInstance();
			
			 conn = cp.getConn();
			 String sql = "SELECT * FROM postmountain ORDER BY ts DESC LIMIT 20";
			 st = conn.createStatement();
//			st.setString(1, uid);
			String res ="[";
			int cnt =0;
			rs  = st.executeQuery(sql);
			while(rs.next()){						
			if(cnt++>0)res+=",";
				
			res+= rs.getString("content");

			}
			
			res+= "]";
//		out.println(res);
			return res;
			}finally{
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(conn!=null)conn.close();
			}
			
		}
	 //mymountainfetch func to render main.html
	 public String mountainfetch(String uid) throws SQLException, ClassNotFoundException, NamingException{
			Connection conn = null;
			ResultSet rs= null;
//			PreparedStatement st = null;
			Statement st = null;
			try{


			ConnectionPool cp = ConnectionPool.getInstance();
			
			 conn = cp.getConn();
			 String sql = "SELECT * FROM mountains WHERE id='demon@gmail.com' ORDER BY ts DESC LIMIT 20";
//			 st = conn.prepareStatement(sql);
			 st = conn.createStatement();
//			st.setString(1, uid);

			String res ="[";
			int cnt =0;
			
			
			rs  = st.executeQuery(sql);
			while(rs.next()){
				
				if(cnt++>0)res+=",";
				res+= rs.getString("content");
			
			}
			
			res+= "]";
//		out.println(res);
			return res;
			}finally{
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(conn!=null)conn.close();
			}
			
		}
	 
	 //record post added to databases
	 public String recordAdd(String uid , String mylistdata ) throws SQLException, ClassNotFoundException, NamingException, ParseException{

         Connection conn = null;
         ResultSet rs = null;
         PreparedStatement st= null;
         String sql = null;
try{
conn = ConnectionPool.getInstance().getConn();
synchronized(this){
  sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES  ";
    sql+= "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME='mylist'";
				st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				if(!rs.next()) return "ER";
				int no = rs.getInt(1);
             
		JSONObject jsonobj = (JSONObject)(new JSONParser()).parse(mylistdata);
		jsonobj.put("no", no);
		
                 
		 sql = "INSERT INTO mylist(id,content) VALUES(?,?)";
	
		st = conn.prepareStatement(sql); 
		
		 st.setString(1,uid);
		 
		 st.setString(2,jsonobj.toJSONString());
              
		 int cnnt = st.executeUpdate();
		 return (cnnt==0)?"ER":"OK";
         }
	
                
}finally{
	if(rs!=null)rs.close();
	if(st!=null)st.close();
	if(conn!=null)conn.close();
	

}
}
	 //delete post from mylistmain.html
	 
	 public String delete(String uno) throws SQLException, ClassNotFoundException, NamingException, ParseException{
			
			Connection conn = null;			
			PreparedStatement st = null;
			try{
				conn = ConnectionPool.getInstance().getConn();			
				String sql = "DELETE FROM mylist WHERE no = ?";				
				st = conn.prepareStatement(sql); 				
				 st.setString(1, uno);			
				 int cnnt = st.executeUpdate();
				 return (cnnt==0)?"ER":"OK";					}	
			finally{
				
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			}
			
		}
	 
 //delete post from mountain for admin uses 
	 
	 public String mountainDelete(String uno) throws SQLException, ClassNotFoundException, NamingException, ParseException{
			
			Connection conn = null;			
			PreparedStatement st = null;
			try{
				conn = ConnectionPool.getInstance().getConn();			
				String sql = "DELETE FROM mountains WHERE no = ?";				
				st = conn.prepareStatement(sql); 				
				 st.setString(1, uno);			
				 int cnnt = st.executeUpdate();
				 return (cnnt==0)?"ER":"OK";					}	
			finally{
				
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			}
			
		}
	 
	 
	 
	 
	 
	 
	 
	 //list post fetech from datbase to render mylistmain
	
	 public String  mylistFetch(String uid) throws SQLException, ClassNotFoundException, NamingException{
			Connection conn = null;
			ResultSet rs= null;
			PreparedStatement st = null;
			
			try{


			ConnectionPool cp = ConnectionPool.getInstance();
			
			 conn = cp.getConn();
			 String sql = "SELECT * FROM mylist WHERE id=? ORDER BY ts DESC LIMIT 20";
			 st = conn.prepareStatement(sql);
			st.setString(1, uid);
//			st.setFetchSize(5);
//			st.setMaxRows(5);
			String res ="[";
			int cnt =0;
			
			
			rs  = st.executeQuery();
			while(rs.next()){
						
				if(cnt++>0)res+=",";
				res+= rs.getString("content");

			}
			
			res+= "]";
//			out.println(res);
			return res;
			}finally{
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(conn!=null)conn.close();
			}
			
		}
	 
	 public String mountainAdd(String uid , String content, JSONArray images ) throws SQLException, ClassNotFoundException, NamingException, ParseException{

         Connection conn = null;
         ResultSet rs = null;
         PreparedStatement st= null;
         String sql = null;
try{
conn = ConnectionPool.getInstance().getConn();
synchronized(this){
  sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES  ";
    sql+= "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME='mountains'";
				st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				if(!rs.next()) return "ER";
				int feedNo = rs.getInt(1);
             
		JSONObject jsonobj = (JSONObject)(new JSONParser()).parse(content);
		jsonobj.put("no", feedNo);	
		jsonobj.put("images", images);
                 
		 sql = "INSERT INTO mountains(content, id) VALUES(?,?)";
	
		st = conn.prepareStatement(sql); 

		 st.setString(1,jsonobj.toJSONString());
		 st.setString(2, uid);
              
		 int cnnt = st.executeUpdate();
		 return (cnnt==0)?"ER":"OK";
         }
	
                
}finally{
	if(rs!=null)rs.close();
	if(st!=null)st.close();
	if(conn!=null)conn.close();
	

}
}
	 
	 
	 //for admin use
	 
	 
	 public String listmaindelete(String uno) throws SQLException, ClassNotFoundException, NamingException, ParseException{
			
			Connection conn = null;			
			PreparedStatement st = null;
			try{
				conn = ConnectionPool.getInstance().getConn();			
				String sql = "DELETE FROM postmountain WHERE no = ?";				
				st = conn.prepareStatement(sql); 				
				 st.setString(1, uno);			
				 int cnnt = st.executeUpdate();
				 return (cnnt==0)?"ER":"OK";					}	
			finally{
				
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			}
			
		}
	 
	 
}
