<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        .main_container {
            width: 600px;
            height: 300px;
            padding: 2%;
            border: 1px ridge black;
            margin: 10% auto 0 auto;
            text-align: center;
        }
        .container_title {
            font-size: 40px;
            margin: 70px auto 70px auto;
        }

        .container_buttons {

            margin: 0 auto;


        }

        .container_button2 {
            margin: 0 auto;
        }

        .container_submit_button {
            text-align: right;
            margin: 30px 70px 0 0;
        }

        td {
            width: 150px;
            margin: 0 auto;
        }

        table {
            margin: 0 auto;
        }

    </style>
</head>
<body>
<div class="main_container">
    <div class="container_title">
        <p>
            Menu
        </p>
    </div>
    <div class="container_buttons">
        <table>
            <tr>
                <td>
                    <form action="recordInsertingServlet" method="post">
                            <input type="submit" name="button"
                                   value="toRegistrationPage">
                    </form>
                </td>
                <td>
                    <div class="container_button2">
                    <form action="recordShowingServlet" method="get">
                            <input type="submit" name="button"
                                   value="showAllList">
                    </form>
                    </div>
                </td>
                <td>
                    <input type="button"
                           onclick="location.href='/AddEmployeeByExcelFile'"
                           value="addEmployeeByExcelFile"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="container_submit_button">
        <form action="loginManagementServlet" method="post">
            <input type="submit" name="button" value="logout">
        </form>
    </div>
</div>
</body>
</html>