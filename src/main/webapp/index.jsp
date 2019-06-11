<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body >

<div >
    <div >
        <h2>Access ot Server</h2>
    </div>

   <form action = "/result" method="post" >
        <label>Server IP:
            <input type="text" name="serverIP" ><br />
        </label>
        <label>Name:
            <input type="text" name="user"><br />
        </label>
        <label>Password:
            <input type="password" name="pass" ><br />
        </label>
        <button type="submit" >Connect</button>
    </form>
</div>
</div>


</body>
</html>