<%@ page import="java.util.List" %>
<%@ page import='com.pavelk.cells.*' %>

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



<%

List<ResultCell> listIncorrectExtPsc = (List<ResultCell>) request.getAttribute("listIncorrectExtPSC");
out.println("sdfsdfsdfsdf");
if (listIncorrectExtPsc == null) out.println("<div >\n"  + "   <h5>There is not external cells with incorrect PSC</h5>\n" + "</div>");
        else{
            out.println("<ul >");
            for (ResultCell resultCell : listIncorrectExtPsc) {
            out.println("<li >" + resultCell.getCell().getCellName() +
                                                          " on " + resultCell.getCell().getRnc() + "RNC with PSC = " + resultCell.getCell().getPsc() +
                                                          " has external with PSC = " + resultCell.getExternalCell().getPsc() + " on "
                                                          + resultCell.getExternalCell().getRnc() + "RNC" + "</li>");
            out.println("</ul >");
            }
        }
%>

</div>
</body>
</html>