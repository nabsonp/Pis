<%-- 
    Document   : erro
    Created on : 03/09/2017, 11:17:41
    Author     : samsung
--%>


<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Erro</title>
        <link href = "css/erro.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/erro.js"></script>
    </head>
    <body>
        <div id = "corpo">
            <h1>ERRO</h1>
            <%
                String erro = request.getParameter("erro");
            %>
            <p><%=erro%></p>
            <span onclick = "voltar()">Voltar</span>
        </div>
    </body>
</html>