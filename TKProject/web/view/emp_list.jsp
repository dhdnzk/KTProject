<%@ page import="java.util.ArrayList"%>
<%@ page import="dao.EmployeeBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        table, td, th {

            border: 1px solid;

        }

        div.sub1 {

            display: inline-flex;

        }

    </style>
</head>
<body>
<div class="main_container">
<%
    ArrayList<EmployeeBean> list = (ArrayList<EmployeeBean>) session.getAttribute("list");
%>

    <h1>Employee List</h1>

    <table>
        <tr>
            <th>Employee list</th>
            <th>ID</th>
            <th>Employee code</th>
            <th>L name</th>
            <th>L kana</th>
            <th>SEX</th>
            <th>Birthday</th>
            <th>Section code</th>
        </tr>
        <form action = "recordDeletionServlet" method = "POST">
                <%for(EmployeeBean aList: list){%>
            <tr>
                <td><input type="radio" name="code"
                           value="<%=aList.getEmpCode()%>"></td>
                <td><%=aList.getEmpCode() %></td>
                <td><%=aList.getLName() + aList.getFName()%></td>
                <td><%=aList.getLKana() + aList.getFKana()%></td>
                <td><% if (aList.getSex() == 0) {%>
                    ’j
                    <%} else {%>
                    —
                    <%}%></td>
                <td><%=aList.getBirth()%></td>
                <td><%=aList.getSectionCode()%></td>
                <td><%=aList.getEmpDate()%></td>
            </tr>
                <%}%>
    </table>

    <div class = "sub1">
        <input type = submit value = "delete">
        </form>

        <form action = "menu" method = "POST">
            <input type = "submit" value = "back to main page"></form>
    </div>
</div>
</body>
</html>