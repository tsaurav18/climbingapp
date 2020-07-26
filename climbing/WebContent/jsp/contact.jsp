<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta name=viewport content="width=device-width, initial-scale=1, user-scalable=0" charset="utf-8">
	  <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="https://formspree.io/"+${addr}  id ="contact-form" method="POST">
  <label>
    Your Name:
    <input type="text" name="name" id="inputName">
  </label>
  <label>
    Your Email:
    <input type="email" name="_replyto" id="inputEmail">
  </label>
  <label>
    Message:
    <textarea name="message" id="inputMessage"></textarea>
  </label>
  <input type="submit" value="Send">
</form>
</body>
</html>

<%
String addr =request.getParameter("id");
%> 



<script src="./js/jquery-1.12.0.min.js"></script>

<script>

$('#contact-form').submit(function(e){
		var name = document.getElementById("inputName");
		var email = document.getElementById("inputEmail");
		var message = document.getElementById("inputMessage");
		if(!name.value || !email.value || !message.value){
			alertify.error("please check your enteries");
		}else{
			$.ajax({
				url:"https://formspree.io/"+addr,
				method:"POST",
				data:$(this).serialize(),
				dataType: "json"
			});
			e.preventDefault();
			$(this).get(0).reset()
			alertify.success("message sent")
		}
	});
	
	</script>
