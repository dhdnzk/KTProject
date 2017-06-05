<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">

        div.main_container {
            width: 600px;
            height: 300px;
            padding: 2%;
            border: 1px ridge black;
            margin-left: 22%;
            margin-top: 10%;
        }

        div.container_title {
            margin-top: 17%;
            margin-left: 30%;
            font-size: 28px;
        }

        div.container_logout_button {
            margin-left: 43%;

        }

    </style>
</head>
<body>
<div class="main_container">
    <form action="login.jsp" method="post">
        <div class="container_title">
            <p>
                See you next time!
            </p>
        </div>
        <div class="container_logout_button">
            <input type="submit" name="button" value="loginPage">
        </div>
    </form>
</div>
</body>
</html>