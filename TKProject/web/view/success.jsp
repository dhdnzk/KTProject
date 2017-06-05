<%@ page language="java" contentType="text/html; charset=Windows-31J"
		 pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/main_container.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
	<title>成功画面</title>

	<style type="text/css">

		div.main_container p {

			font-size: 40px;
			margin-top: 15%;
			margin-left: 25%;
			margin-right: 25%;

		}

		div.main_container input {

            margin-left: 60%;
		}

	</style>
</head>
<body>
<div class="main_container">
	<p><%= request.getAttribute("message") %></p>
	<form action="<%= request.getAttribute("link") %>" method="POST" >

		<input type="submit" name = "button" value="<%= request.getAttribute("link_view") %>">

	</form>
</div>
</body>
</html>