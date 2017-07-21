<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="casestudy.Beans" %>
<%Beans t = (Beans) application.getAttribute("beans"); %>

<html>
	<head>
		<title>N1</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/2.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<h1>N1</h1>
				<p>New Next Nippon Navigation<br />
				</p>
			</header>

		<!-- Signup Form -->
		<a class="btn btn-medium btn-blue btn-radius" href="./radar/radar.html">RADAR CONTROL</a>


		<a href="#" class="btn btn-medium btn-green btn-radius" onclick="document.form1.submit();return false;">ADMINISTRATOR</a>
				<a class="btn btn-medium btn-orange btn-radius" href="../index.html">CLOSE</a>
		 <form name="form1" method="POST" action="/connection/MakeScope" >
		</form>

			<%if(t!=null){ %>
				<%if(t.isIn()){ %>
				<a class="btn btn-small btn-blue btn-radius" href="#">DB insert ok</a>
				<%}else if(t.isIn()!=true){ %>
				<a class="btn btn-small btn-red btn-radius" href="#">DB not insert</a>
				<%} %>

				<%if(t.isXml()){ %>
				<a class="btn btn-small btn-blue btn-radius" href="#">Xml ok</a>
				<%}else if(t.isXml()!=true){ %>
				<a class="btn btn-small btn-red btn-radius" href="#">Xml bad</a>
				<%} %>

			<%}else if(t == null){ %>
			<a class="btn btn-small btn-red btn-radius" href="#">DB not connected</a>
			<a class="btn btn-small btn-red btn-radius" href="#">Xml bad</a>
			<%} %>














		<!-- Scripts -->
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>