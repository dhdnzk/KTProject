<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<td><input type="text" width="50" name="l_name"></td>
				<td><input type="text" width="50" name="f_name"></td>
			</tr>

			<tr>
				<td>Name(kana):</td>
				<td><input type="text" width="50" name="l_kana_name"></td>
				<td><input type="text" width="50" name="f_kana_name"></td>
			</tr>
			<tr>
				<td>SEX:</td>
				<td><input type="radio" name="sex" value="0">男性</td>
				<td><input type="radio" name="sex" value="1">女性</td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td>
					<input type="date" name="birthday" />
				</td>
				<%--<td>
					<select name ="birth_year">
						<%for(int i = 1950; i < 2018; i++) {%>
						<option value="<%= i %>"><%= i %></option>
						<%} %>
					</select>
					年
				</td>
				<td>
					<select name ="birth_month">
						<%for(int i = 1; i < 13; i++) {%>
						<option value="<%= i %>"><%= i %></option>
						<%} %>
					</select>
					月
				</td>
				<td>
					<select name ="birth_day">
						<%for(int i = 1; i < 31; i++) {%>
						<option value="<%= i %>"><%= i %></option>
						<%} %>
					</select>
					日
				</td>--%>
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
				<%--<td>
					<select name ="emp_year">
						<%for(int i = 1950; i < 2018; i++) {%>
						<option value="<%= i %>"><%= i %>
						</option>
						<%} %>
					</select>
					年
				</td>
				<td>
					<select name ="emp_month">
						<%for(int i = 1; i < 13; i++) {%>
						<option value="<%= i %>"><%= i %>
						</option>
						<%} %>
					</select>
					月
				</td>
				<td>
					<select name ="emp_day">
						<%for(int i = 1; i < 31; i++) {%>
						<option value="<%= i %>"><%= i %>
						</option>
						<%} %>
					</select>
					日
				</td>--%>
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