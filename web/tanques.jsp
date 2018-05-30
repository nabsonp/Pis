<%-- 
    Document   : tanques
    Created on : 04/08/2017, 16:28:37
    Author     : samsung
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="br.com.piscicultech.modelo.TanqEsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Tanques</title>
        <link href = "css/tanques.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script type = "text/javascript" src="js/tanques.js"></script>
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <span id = "atual">Tanques</span></div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/calendario.png" id = "icone"/></label>
        <div class = "menuLateral">
            <ul id = "botoes">
                <%
                    ArrayList<Tanque> tanques = ArrayList.class.cast(request.getAttribute("tanques"));
                    DateFormat dia = new SimpleDateFormat("dd");
                    DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
                    DateFormat ano = new SimpleDateFormat("yy");
                    DateFormat dataF = new SimpleDateFormat("yyyy-MM-dd");
                    Date data = new Date(Calendar.getInstance().getTimeInMillis());
                    java.sql.Date prim = java.sql.Date.class.cast(request.getAttribute("primeiro"));
                %>
                <li class = "titulo">
                    Calendário
                </li>
                <li>
                    <%
                        if (prim != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(data);
                            String atual = dataF.format(Date.class.cast(sessao.getAttribute("data")));
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            for (Date dt = data; dt.after(prim);) {
                                if (atual.equals(dataF.format(dt))) {
                                    out.println("<button id = \"current\" name=\"" + df.format(dt) + "\" onclick = \"abrirDia(this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                } else {
                                    out.println("<button name=\"" + df.format(dt) + "\" onclick = \"abrirDia(this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                }
                                cal.add(Calendar.DATE, -1);
                                dt = cal.getTime();
                                dataF.format(dt);
                            }
                        }
                    %>
                </li>
            </ul>
        </div>
        <article id = "tanques">
            <%
                ArrayList<TanqEsp> tanqEsps = ArrayList.class.cast(request.getAttribute("tanqEsps"));
                float qtd = 0, bio = 0;
                if (tanqEsps != null) {
                    for (Tanque t : tanques) {
                        String situacao = "", classe = "", nomeEsp = "Vazio";
                        Especie esp = null;
                        for (TanqEsp te : tanqEsps) {
                            if (te.getIdTanque() == t.getId()) {
                                esp = Especie.class.cast(request.getAttribute(t.getId() + ""));
                                qtd = te.getQtd();
                                bio = te.getBiomassa();
                            }
                        }
                        if (esp == null) {
                            situacao = "Vazio";
                            classe = "tanqueVazio";
                        } else {
                            nomeEsp = esp.getNome();
                            if (t.isSituacao()) {
                                situacao = "OK";
                                classe = "tanqueOk";
                            } else {
                                situacao = "Atenção";
                                classe = "tanqueNo";
                            }
                        }
                        out.println("<span class = \"tanque " + classe + "\">"
                                + "<span class = \"id\" onclick = \"abrirTanque('" + t.getId() + "')\"> Tanque " + t.getId() + "</span>"
                                + "<div onclick = \"abrirTanque('" + t.getId() + "')\">"
                                + "<span class = \"dado\">Espécie: " + nomeEsp + "</span><br/>"
                                + "<span class = \"dado\">Criação: " + t.getPiscicultura() + "</span><br/>"
                                + "<span class = \"dado\">Qtd peixes: " + qtd + "</span><br/>"
                                + "<span class = \"dado\">Biomassa: " + bio + " KG</span><br/>"
                                + "<span class = \"dado\">Tamanho: " + t.getComp() + "m</span><br/>"
                                + "<span class = \"atv\">Situação:  " + situacao + "</span>"
                                + "</div>"
                                + "<button onclick = \"confirmar(" + t.getId() + ")\" title = \"Excluir\"></button>"
                                + "</span>");
                    }
                }
            %>
            <span class = "botao">
                <div>
                    <button id = "add" onclick = "cadastrar()" title = "Adicionar Tanque"></button>
                </div>
            </span>
        </article>
    </body>
</html>