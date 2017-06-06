<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<title>registration</title>

	<style type="text/css">
		.main_container {
			width: 600px;
			height: 300px;
			padding: 2%;
			border: 1px ridge black;
			margin: 10% auto 0 auto;
			text-align: center;
		}

	</style>

</head>
<body>
<div class="main_container">
	<p>registration form!</p>
	<form action="recordInsertingServlet" method="POST" >
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" width="50" name="l_name" required></td>
				<td><input type="text" width="50" name="f_name" required></td>
			</tr>

			<tr>
				<td>Name(kana):</td>
				<td><input type="text" width="50" name="l_kana_name"></td>
				<td><input type="text" width="50" name="f_kana_name"></td>
			</tr>
			<tr>
				<td>SEX:</td>
				<td><input type="radio" name="sex" value="0" checked>男性</td>
				<td><input type="radio" name="sex" value="1">女性</td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td>
					<input type="date" name="birthday" />
				</td>
			</tr>
			<tr>
				<td>所属部署:</td>
				<td>
					<select type="select" name="section_code">
						<%
							ArrayList<String[]> departmentList =
									(ArrayList<String[]>) request.getSession().getAttribute("departmentList");
							for (int i = 0; i < departmentList.size(); i++) {%>
						<option value="<%= departmentList.get(i)[0] %>"><%= departmentList.get(i)[1] %></option>
						<%}	%>
					</select>
				</td>
			</tr>
			<tr>
				<td>入社日:</td>
				<td>
					<input type="date" name="emp_join" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="button" value="backToMenuPage">
					<input type="submit" name="button" value="registration">
					<input type="reset"  name="button" value="clear">
				</td>
			<tr>
		</table>
	</form>
</div>
</body>
</html>