<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/main_container.css" />
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">


        div.container_title {
            font-size: 40px;
            margin-left: 38%;
            margin-top: 10%
        }

        div.container_buttons {

            margin-left: 30%;
            margin-right: 30%;


        }

        div.container_button2 {
            margin-left: 60%;
        }

        div.container_submit_button {

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