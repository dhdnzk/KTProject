<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>登録完了画面</title>

<link rel ="stylesheet"href="style.css"type="text/css">
</head>
<body>
	<h1 align="center">従業員:<%= request.getAttribute("message") %>さんの登録完了しました。</h1>

<div align="center">
	<a href=<%= request.getAttribute("link") %>>戻る</a>
</div>

</body>
</html>