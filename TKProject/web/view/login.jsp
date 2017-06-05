<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to TeamB</title>
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
    <form action="loginManagementServlet" method="post">
        <div class="container_title">
            <p>System</p>
        </div>
        <div class="container">
            <table>
            <tr>
                <td>
                    <p>user id</p>
                </td>
                <td>
                    <input type="text" name="id">
                </td>

            </tr>
            <tr>
                <td>
                    <p>password</p>
                </td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
        </table>
        <input type="submit" name="button" value="login">
        </div>
    </form>
</div>
</body>
</html>