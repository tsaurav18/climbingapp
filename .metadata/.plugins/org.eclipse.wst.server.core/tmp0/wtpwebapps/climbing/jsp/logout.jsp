

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    session.invalidate();
    out.print("logout successfully");

%>