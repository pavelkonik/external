<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select RNC</title>
</head>
<body>
<div>

<form action = "/selectRnc" method="post" name = "RNCs">
 <label>Enter RNCs (comma separator, only number):
  <input type="text" name="rncs" ><br />
 </label>
 <button type="submit" >go</button>
</form>

</div>
</body>
</html>