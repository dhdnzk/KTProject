<%@ page language="java" contentType="text/html; charset=Windows-31J"
		 pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
	<title>エラー画面</title>
	<style>

		div.main_container {
			width: 600px;
			height: 300px;
			padding: 2%;
			border: 1px ridge black;
			margin-left: 22%;
			margin-top: 10%;
			text-align: center;
		}

		div.main_container p {

			font-size: 40px;
			margin-top: 15%;
			margin-left: 25%;
			margin-right: 25%;

		}

		div.container{

			text-align: left;
			margin-left: 30%;
		}

		div.container_title {
			margin-top: 7%;
			font-size: 30px;
		}

	</style>
</head>
<body>
<div class="main_container">

	<div class="container_title">
		<p><%= request.getAttribute("message") %></p>
	</div>

	<div class="container">
		<form action="<%= request.getAttribute("link") %>" method="POST" >

			<input type="submit" name = "button" value="<%= request.getAttribute("link_view") %>">

		</form>
	</div>

</div>
</body>
</html>