<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Results</title>
</head>
<body>
<div>
<%

                    List<String> listCfgmmlFiles = (List<String>) request.getAttribute("listCfgmmlFiles");

                    if (listCfgmmlFiles != null && !listCfgmmlFiles.isEmpty()) {
                        out.println("<ul >");
                        for (String s : listCfgmmlFiles) {
                            out.println("<li >" + s + "</li>");
                        }
                        out.println("</ul>");

                    } else out.println("<div >\n"  + "   <h5>There are no files</h5>\n" + "</div>");
%>
<form method="post" name = "RNCs">
 <label>Enter RNCs (comma separator, only number):
  <input type="text" name="rncs" ><br />
 </label>
 <button type="submit" >go</button>
</form>

</div>
</body>
</html>