<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> --%>
  <%--   <%@page contentType="text/html; charset=UTF-8" %> --%>
  <%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>



  
    <%@ page import="core.*"%>
<% 

request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
 String uemail= request.getParameter("id");
String upass= request.getParameter("ps");
/* 
out.print(uemail);
out.print(upass); */
UserDAO dao = new UserDAO();
out.print(dao.form(uemail, upass));

%>

