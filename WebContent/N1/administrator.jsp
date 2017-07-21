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






    <div class="toggles">
	   <form action="/connection/ConOrDie" method="post" >

<a class="btn btn-large btn-gray btn-radius" href="#">DB</a>
	      <div class="toggle-border">
	      <%if(t.isIn()){ %>
     <input type="checkbox" id="one" name="in" value="1" checked="checked"/>
	         <%}else if(t.isIn()!=true){ %>
	          <input type="checkbox" id="one" name="in" value="1" />
	          <%} %>
	         <label for="one">
	            <div class="handle"></div>
	         </label>
	      </div>
<a class="btn btn-large btn-gray btn-radius" href="#">XML</a>
	      <div class="toggle-border">
	      <%if(t.isXml()){ %>
	         <input type="checkbox" id="two" name="xml" value="1" checked="checked" />
	          <%}else if(t.isXml()!=true){ %>
	          <input type="checkbox" id="two" name="xml" value="1" />
	          <%} %>
	         <label for="two">
	            <div class="handle"></div>
	         </label>
	      </div>




<div class="box">
	<a class="button" href="#popup1">SUBMIT</a>
</div>

<div id="popup1" class="overlay">
	<div class="popup">
		<h2>Are You Sure?</h2>
		<a class="close" href="#">&times;</a>
	<button>yes</button>
		<div class="content">
			本当に送信しても大丈夫ですか？
		</div>
	</div>
</div>




	   </form>


   </div>
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

<br><br>





<div class="box">
	<a class="button" href="#popup2">ADMIN END</a>
</div>

<div id="popup2" class="overlay">
	<div class="popup">
		<h2>Are You Sure?</h2>
		<a class="close" href="#">&times;</a>
	<li><a href="#" class="round green" onclick="document.form1.submit();return false;">ADMIN END<span class="round">管理者画面を終了します</span></a>

 <form name="form1" method="POST" action="/connection/AdminEnd" >


</form>
</li>

	</div>
</div>




<div class="box">
	<a class="button" href="#popup3">DB RESET</a>
</div>

<div id="popup3" class="overlay">
	<div class="popup">
		<h2>Are You Sure?</h2>
		<a class="close" href="#">&times;</a>
	<li><a href="#" class="round red" onclick="document.form2.submit();return false;">DB RESET<span class="round">DBを全て消します</span></a>

 <form name="form2" method="POST" action="/connection/DBdeleteServlet" >


</form>
</li>
		<div class="content">
			本当にDBリセットしても大丈夫ですか？
		</div>
	</div>
</div>




<div class="box">
	<a class="button" href="#popup4">SAVE DB</a>
</div>

<div id="popup4" class="overlay">
	<div class="popup">
		<h2>Are You Sure?</h2>
		<a class="close" href="#">&times;</a>
<li><a href="#" class="round yellow" onclick="document.form3.submit();return false;">SAVE DB<span class="round">DBをCSVで保存します</span></a>

 <form name="form3" method="POST" action="/connection/CsvMaker" >


</form>
</li>

	</div>
</div>









</body>
</html>