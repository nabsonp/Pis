package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta name = \"Content-Type\" content = \"text/html\"; charset = \"UTF-8\" />\n");
      out.write("        <link rel=\"icon\" href=\"icones/icon-peixe.png\" type=\"image/png-icon\" />\n");
      out.write("        <title>Entrar</title>\n");
      out.write("        <link href = \"css/botoes-formulario.css\" rel = \"StyleSheet\" />\n");
      out.write("        <link href = \"css/login.css\" rel = \"StyleSheet\" />\n");
      out.write("        <script src = \"js/login.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"foco()\">\n");
      out.write("        <div>\n");
      out.write("            <img src = \"icones/logotipo.png\" id = \"logotipo\" />\n");
      out.write("            <span id = \"entrar\">Entrar</span>\n");
      out.write("            <form name = \"login\" action = \"Entrar\" method = \"post\">\n");
      out.write("                <table>\n");
      out.write("                    ");

                        HttpSession sessao = request.getSession();

                        if (sessao.getAttribute("usuario_logado") == null) {
                            sessao.setAttribute("usuario_logado", "false");
                        }

                        if (sessao.getAttribute("usuario_logado").equals("erro")) {
                            out.println("<tr><td colspan = '2' id = \"msgErro\">Login e/ou senha inválidos. Tente novamente.</td></tr>");
                        }
                    
      out.write("\n");
      out.write("                    <tr> <td><label>Email:</label></td> <td><input type = \"text\" name = \"email\"/></td> </tr>\n");
      out.write("                    <tr> <td><label>Senha:</label></td> <td><input type = \"password\" name = \"senha\" id = \"senha\"/></td> </tr>\n");
      out.write("                    <tr> <td colspan=\"2\"><a href = \"cadastrar-funcionario.jsp\">Cadastre-se aqui.</a>\n");
      out.write("                            <button type = \"submit\" class = \"botoes\" onclick = \"validar()\"><img src = \"icones/checked.png\" /><span>Entrar</span></button></td> </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
