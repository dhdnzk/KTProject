<%@ page import="java.util.ArrayList"%>
<%@ page import="dao.EmployeeBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
    <title></title>

    <style type="text/css">

        .main_container {
            width: 600px;
            height: 300px;
            padding: 2%;
            border: 1px ridge black;
            margin: 10% auto 0 auto;
            text-align: center;
        }

        .main_container_middle {

            height: 75%;
            width: 100%;

        }

        .table-wrapper {

            overflow-x: hidden;
            overflow-y: auto;
            height: 100%;

        }

        table {

            width: 99%;
            border-collapse: collapse;

        }

        td {

            border-bottom: 1px solid #ccc;
            padding: 5px;

        }

        td + td {

            border-left: 1px solid #ccc;
            border-right: 1px solid #ccc;

        }

        td + th {

        }

        .main_container_middle table tr td {

            border: 1px solid;

        }

        .main_container_bottom table tr td{

            border: hidden;

        }

    </style>
</head>
<body>
<div class="main_container">
    <form action="/recordShowingServlet" method="post">
        <select name="search_mode">
            <option value="code">従業員コード</option>
            <option value="name">氏名</option>
            <option value="hurigana">フリガナ</option>
            <option value="section">所属</option>
        </select>
        <input type="search" name="search" />
        <input type="submit" value="検索" />
    </form>

    <%
        ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>)
                session.getAttribute("employeeList");

        ArrayList<String> departmentNameList = (ArrayList<String>)
                session.getAttribute("departmentNameList");

    %>

    <h1>Employee List</h1>

    <div class="main_container_middle">
        <div class="table-wrapper">
            <table>
                <tr>
                    <th>Employee list</th>
                    <th>ID</th>
                    <th>name</th>
                    <th>kana</th>
                    <th>SEX</th>
                    <th>Birthday</th>
                    <th>Department</th>
                </tr>
                <form action = "recordDeletionServlet" method = "POST">
                        <%for(EmployeeBean aList: employeeList){%>
                    <tr>
                        <td><input type="checkbox" name="code"
                                   value="<%=aList.getEmpCode()%>"></td>
                        <td><%=aList.getEmpCode() %></td>
                        <td><%=aList.getLName() + aList.getFName()%></td>
                        <td><%=aList.getLKana() + aList.getFKana()%></td>
                        <td><%=(aList.getSex() == 0 ? "男" : "女")%></td>
                        <td><%=aList.getBirth()%></td>
                        <td><%=departmentNameList.get(Integer.parseInt(aList.getSectionCode()) - 1)%></td>
                    </tr>
                        <%}%>
            </table>
        </div>
    </div>

    <div class = "sub1">
        <input type = submit value = "delete">
        </form>

        <form action = "menu" method = "POST">
            <input type = "submit" value = "back to main page"></form>
    </div>
</div>
</body>
</html>