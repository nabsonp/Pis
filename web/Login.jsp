<%-- 
    Document   : Login
    Created on : 01/08/2017, 15:25:43
    Author     : samsung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Entrar</title>
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <link href = "css/login.css" rel = "StyleSheet" />
        <script src = "js/login.js"></script>
    </head>
    <body onload="foco()">
        <div>
            <img src = "icones/logotipo.png" id = "logotipo" />
            <span id = "entrar">Entrar</span>
            <form name = "login" action = "Entrar" method = "post">
                <table>
                    <%
                        HttpSession sessao = request.getSession();

                        if (sessao.getAttribute("usuario_logado") == null) {
                            sessao.setAttribute("usuario_logado", "false");
                        }

                        if (sessao.getAttribute("usuario_logado").equals("erro")) {
                            out.println("<tr><td colspan = '2' id = \"msgErro\">Login e/ou senha inválidos. Tente novamente.</td></tr>");
                        }
                    %>
                    <tr> <td><label>Email:</label></td> <td><input type = "text" name = "email"/></td> </tr>
                    <tr> <td><label>Senha:</label></td> <td><input type = "password" name = "senha" id = "senha"/></td> </tr>
                    <tr> <td colspan="2"><a href = "cadastrar-funcionario.jsp">Cadastre-se aqui.</a>
                            <button type = "submit" class = "botoes" onclick = "validar()"><img src = "icones/checked.png" /><span>Entrar</span></button></td> </tr>
                </table>
            </form>
        </div>
    </body>
</html>
