<%--
  Created by IntelliJ IDEA.
  User: bumskim
  Date: 2017. 6. 9.
  Time: AM 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Registration By Excel</title>

    <style type="text/css">
        .main_container {
            width: 600px;
            height: 300px;
            padding: 2%;
            border: 1px ridge black;
            margin: 10% auto 0 auto;
            text-align: center;
        }
        div.container{
            margin: 0 auto;
        }

        div.container_title {
            margin: 0 auto;
            font-size: 30px;
        }

        form {
            margin: 50px auto;
        }
    </style>

</head>
<body>
<div class="main_container">
    <div class="container_title">
        <p>Registration by Excel file</p>
    </div>
    <div class="container">
        <form method="post" action="AddEmployeeByExcelFile"
              enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="submit" value="submit" />
        </form>
        <div>
            <input type="button" value="backToMenuPage" onclick="location.href='/menu'">
        </div>
    </div>
</div>
</body>
</html>
