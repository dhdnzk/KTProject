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
            margin-left: 38%;
            margin-top: 10%
        }

        .container_buttons {

            margin-left: 30%;
            margin-right: 30%;


        }

        .container_button2 {
            margin-left: 60%;
        }

        .container_submit_button {

            margin-top: 8%;
            margin-left: 67%;

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
                    <form action="recordShowingServlet" method="post">
                            <input type="submit" name="button"
                                   value="showAllList">
                    </form>
                    </div>
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