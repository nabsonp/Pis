<%-- 
    Document   : rel-especies
    Created on : 09/12/2017, 01:51:25
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Relatório de Espécies</title>
        <link href = "css/escolher-especie.css" rel = "StyleSheet" />
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/relatorios.js" type = "text/javascript"></script>
    </head>
    <body>
        <nav id = "menu">
            <ul>
                <img src = "icones/logotipo.png" id = "logoCabecalho"/>
                <li>
                    <a href = "Home.jsp"><img src = "icones/home.png" width = 25/></a>
                </li>
                <%
                    HttpSession sessao = request.getSession();
                    if (sessao.getAttribute("usuario") == null) {
                        String url = "/sessao-expirada.html";
                        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
                        despachante.forward(request, response);
                    }
                    boolean adm = Boolean.class.cast(sessao.getAttribute("ADM"));
                    if (adm) {
                        out.println("<li>"
                                + "<a href = \"AbrirFinanInsumos\">Financeiro</a>"
                                + "</li>");
                    }
                %>
                <li class = "current">
                    <a href = "AbrirTanques">Tanques</a>
                </li>
                <li>
                    <a href = "AbrirEspecies">Espécies</a>
                </li>
                <li>
                    <a href = "AbrirEstoque">Estoque</a>
                </li>
                <li>
                    <a href = "escolher-periodo.jsp">Relatórios</a>
                </li>
            </ul>
            <%
                String imagem = Funcionario.class.cast(sessao.getAttribute("usuario")).getImagem();
                String nome = Funcionario.class.cast(sessao.getAttribute("usuario")).getNome();
                String primeiroNome = nome;
                int i = -1;
                do {
                    i++;
                } while (nome.charAt(i) != ' ');
                primeiroNome = nome.substring(0, i);
                String idTanque = String.valueOf(request.getAttribute("idTanque"));
            %>
            <span id = "conta">
                <img title = "<%=nome%>" src = "<%=imagem%>" id = "user"/>
                <img id="seta" src = "icones/seta-para-baixo.png"/><br/>
                <label id = "admin"><%=primeiroNome%></label>
                <ul id = "opcoes">
                    <li id="atual" onclick="abrirContaFunc()">Minha conta</li><br/>
                    <li onclick="abrirContaEmp()">Empresa</li><br/>
                    <li onclick="sair()">Sair</li>
                </ul>
            </span>
        </nav>
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "escolher-periodo.jsp">Período</a> &raquo; <span id = "atual">Relatórios de Espécies</span></div>
        <h1>Escolher Espécie</h1>
        <div id = "especies">
            <%
                ArrayList<Especie> esps = ArrayList.class.cast(request.getAttribute("esps"));
                for (Especie e : esps) {
                    out.println("<div class = \"especieIndividual\">"
                            + "<span class = \"dados\" onclick = \"abrirRelEspecie('" + e.getId() + "')\">"
                            + "<img src = \"" + e.getImagem() + "\" />"
                            + "<br><br>Nome: " + e.getNome() + "<br><br>"
                            + "</span>"
                            + "<span class = \"lixo\">"
                            + "</span>"
                            + "</div>");
                }
            %>

        </div>
    </body>
</html>
