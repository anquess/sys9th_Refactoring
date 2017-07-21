<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="casestudy.Beans" %>
<%@ page import="java.io.File" %>
<%Beans t = (Beans) application.getAttribute("beans"); %>


<html>
	<head>
		<title>N1</title>
		<meta charset="utf-8" />


	<link rel="stylesheet" href="./assets/css/button.css" />
	<link rel="stylesheet" href="./assets/css/toggle.css" />
	<link rel="stylesheet" href="./assets/css/dbbutton.css" />
	<link rel="stylesheet" href="assets/css/2.css" />
	<link rel="stylesheet" href="assets/css/popup.css" />


	</head>
<body>


<h1>DBリセットできました</h1>


<li><a href="administrator.jsp" class="round green" ">TO ADMIN<span class="round">管理者画面に戻ります</span></a>

</li>
<li><a href="#" class="round green" onclick="document.form2.submit();return false;">ADMIN END<span class="round">管理者画面を終了します</span></a>

 <form name="form2" method="POST" action="/connection/AdminEnd" >


</form>
</li>







</body>
</html>