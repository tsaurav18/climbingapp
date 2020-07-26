
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import= "core.*" %>
<%@page import="core.UserDAO"%>

<%
     request.setCharacterEncoding("UTF-8");
    String uid = request.getParameter("id");
    String upass = request.getParameter("ps");
    String uname = request.getParameter("nick");
    String ugen = request.getParameter("gend");
    String udob = request.getParameter("dob");
    
    UserDAO dao = new UserDAO();
    String code = dao.signup(uid, upass, uname, ugen,udob);
   
      out.print(code);







%>
