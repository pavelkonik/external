/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-06-19 10:07:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.pavelk.cells.*;

public final class selectRnc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Select RNC</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div>\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write(" <button onclick=\"location.href='/result'\" >View CFGMML files</button>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<form action = \"/selectRnc\" method=\"post\" name = \"RNCs\">\r\n");
      out.write(" <label>Enter RNCs (comma separator, only number):\r\n");
      out.write("  <input type=\"text\" name=\"rncs\" ><br />\r\n");
      out.write(" </label>\r\n");
      out.write(" <button type=\"submit\" >go</button>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


List<ResultCell> listIncorrectExtPsc = (List<ResultCell>) request.getAttribute("listIncorrectExtPSC");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write(" <button onclick=\"location.href='/'\" >go to access data</button>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
