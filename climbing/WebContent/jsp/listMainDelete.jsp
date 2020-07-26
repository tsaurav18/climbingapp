<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import= "core.*" %>


<%
       request.setCharacterEncoding("UTF-8");
         mountainPostDAO dao= new mountainPostDAO();
       out.print(dao.listmaindelete(request.getParameter("no")));
     
 	


%>
 