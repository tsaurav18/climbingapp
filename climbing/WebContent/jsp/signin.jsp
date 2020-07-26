<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@page import="core.*" %> 
    <%@ page import="core.UserDAO" %>
<%
    request.setCharacterEncoding("UTF-8");
	
//user 정보가 이미 session에 존재하는 경우
		String sid = (String)session.getAttribute("uid");
			if(sid!= null){
		out.print("EX");
		return;
		
			}
	String uid = request.getParameter("id");
	String upass = request.getParameter("ps");
	
	UserDAO dao = new UserDAO();
	String code = dao.signin(uid, upass);
	

	if(code != "NA" && code != "PS"){
		/* session.setAttribute("uid", uid); */
		
		session.setAttribute("usrobj", code);
		
		
	}
	out.print(code);
	
	

%>
