<%-- 
    Document   : rel-tanques
    Created on : 06/12/2017, 22:48:03
    Author     : samsung
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page import="br.com.piscicultech.modelo.TanqEsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Relatórios de Tanques</title>
        <link href = "css/rel-tanques.css" rel = "StyleSheet" />
        <script src="js/rel-tanques.js"></script>
        <script src="js/relatorios.js"></script>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
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
                <li>
                    <a href = "AbrirTanques">Tanques</a>
                </li>
                <li>
                    <a href = "AbrirEspecies">Espécies</a>
                </li>
                <li>
                    <a href = "AbrirEstoque">Estoque</a>
                </li>
                <li class = "current">
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "escolher-periodo.jsp">Período</a> &raquo; <span id = "atual">Relatórios de Tanques</span></div>
        <nav id = "submenu">
            <%
                if (adm) {
                    out.println("<button onclick = \"abrirFinanceiros()\">Financeiros</button>");
                }
            %>
            <button id = "sub-current" onclick = "abrirTanques()">Tanques</button>
            <button onclick = "abrirEspeciesRel()">Espécies</button>
        </nav>

        <%
            ArrayList<TanqEsp> tanqEsps = ArrayList.class.cast(request.getAttribute("tanqEsps"));
            ArrayList<Tanque> tanques = ArrayList.class.cast(request.getAttribute("tanques"));
            DateFormat dia = new SimpleDateFormat("dd");
            DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
            DateFormat ano = new SimpleDateFormat("yy");
            Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
            Date fim = Date.class.cast(sessao.getAttribute("fim"));
            if (inicio != null && fim != null) {
                out.println("<h2>De " + dia.format(inicio) + " de " + mes.format(inicio) + " de " + ano.format(inicio) + " a " + dia.format(fim) + " de " + mes.format(fim) + " de " + ano.format(fim) + "</h2>");
            }
            out.println("<article id = \"tanques\">");
            if (tanques.isEmpty()) {
                out.println("<h1>Sem tanques neste período<h1>");
            } else {
                for (Tanque t : tanques) {
                    String nomeEsp = "Vazio";
                    Especie esp = null;
                    for (TanqEsp te : tanqEsps) {
                        if (te.getIdTanque() == t.getId()) {
                            esp = Especie.class.cast(request.getAttribute(t.getId() + ""));
                        }
                    }
                    if (esp != null) {
                        nomeEsp = esp.getNome();
                    }
                    out.println("<span class = \"tanque\">"
                            + "<span class = \"id\" onclick = \"abrirTanque('" + t.getId() + "')\"> Tanque " + t.getId() + "</span>"
                            + "<div onclick = \"abrirTanque('" + t.getId() + "')\">"
                            + "<span class = \"dado\">Espécie: " + nomeEsp + "</span><br/>"
                            + "<span class = \"dado\">Criação: " + t.getPiscicultura() + "</span><br/>"
                            + "<span class = \"dado\">Revestimento: " + t.getRevestimento() + "</span><br/>"
                            + "<span class = \"dado\">Material: " + t.getMaterial() + "</span><br/>"
                            + "<span class = \"dado\">Tamanho: " + t.getComp() + "m</span><br/>"
                            + "</div>"
                            + "</span>");
                }
            }
        %>
    </article>
</body>
</html>