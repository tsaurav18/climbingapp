
<%@page import="org.json.simple.JSONObject"%>
 <%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="core.*" %>
<%@ page import="util.*" %>

<%
 request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("id");
	String mylistdata = request.getParameter("mylistdata");
	mountainPostDAO dao = new mountainPostDAO();
	String code =dao.recordAdd(uid, mylistdata);
    out.print(code);

%>
