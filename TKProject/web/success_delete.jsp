<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>削除完了画面</title>
<style type ="text/css">
.del{text-align: center;}
</style>
</head>
<body>
<h1 class=del>削除完了画面</h1>
<br>
<h2 class =del>
<%
String message = (String) request.getAttribute("message"); 
%>
の削除を完了しました。
</h2>
<form class ="del" action="emp_list.jsp" method ="POST">
<input type ="submit"  name="button" value="従業員一覧画面">
</form>
</body>
</html>