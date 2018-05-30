<%-- 
    Document   : rel-especie
    Created on : 09/12/2017, 14:22:14
    Author     : samsung
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.VerificacaoEsp"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Relatório de Espécie</title>
        <link href = "css/rel-tanque-agua.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/rel-especie.js"></script>
        <script type = "text/javascript" src="js/relatorios.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
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
                Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
                Date fim = Date.class.cast(sessao.getAttribute("fim"));
                Especie esp = Especie.class.cast(request.getAttribute("esp"));
                int id = esp.getId();
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "escolher-periodo.jsp">Período</a> &raquo;  <a href = "EscolherEspecieRel">Relatórios de Espécies</a> &raquo; <span id = "atual">Relatórios do <%=esp.getNome()%></span></div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/peixe.png" id = "icone"/></label>
        <div class = "menuLateral">
            <ul id = "botoes">
                <li class = "titulo">
                    Espécies
                </li>
                <%
                    ArrayList<Especie> esps = ArrayList.class.cast(request.getAttribute("esps"));
                    String atual = "";
                    if (esps != null) {
                        for (Especie e : esps) {
                            if (e.getId() == id) {
                                atual = "id = \"current\"";
                            } else {
                                atual = "";
                            }
                            out.println("<li><button " + atual + " onclick = \"abrirRelEspecie(" + e.getId() + ")\">" + e.getNome() + "</button></li>");
                        }
                    }
                %>
            </ul>
        </div>

        <div id = "corpo">
            <nav id = "submenu">
                <%
                    if (adm) {
                        out.println("<button onclick = \"abrirFinanceiros()\">Financeiros</button>");
                    }
                %>
                <button onclick = "abrirTanques()">Tanques</button>
                <button id = "sub-current" onclick = "abrirEspeciesRel()">Espécies</button>
            </nav>
            <h1><%= esp.getNome()%></h1>
            <%
                ArrayList<VerificacaoEsp> ves = ArrayList.class.cast(request.getAttribute("ves"));
                DateFormat dia = new SimpleDateFormat("dd");
                DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
                DateFormat mesNum = new SimpleDateFormat("MM");
                DateFormat ano = new SimpleDateFormat("yy");
                if (inicio != null && fim != null && esp != null) {
                    out.println("<h3>De " + dia.format(inicio) + " de " + mes.format(inicio) + " de " + ano.format(inicio) + " a " + dia.format(fim) + " de " + mes.format(fim) + " de " + ano.format(fim) + "</h3>");
                    if (ves.isEmpty()) {
                        out.println("<h1 id=\"vazio\">Sem verificações para esse intervalo de tempo.</h1>");
                    } else {
                        String datas = "";
                        String mort = "", nat = "", qtd = "", tam = "", peso = "";
                        for (VerificacaoEsp ve : ves) {
                            mort = mort + ve.getMortos() + ",";
                            nat = nat + ve.getNascidos() + ",";
                            qtd = qtd + ve.getQtd() + ",";
                            tam = tam + ve.getTamanhoMedio() + ",";
                            peso = peso + ve.getPeso() + ",";
                            datas = datas + "\"" + dia.format(ve.getDtVerif()) + "/" + mesNum.format(ve.getDtVerif()) + " (" + ve.getHora() + ")\",";
                        }
                        datas.substring(0, (datas.length() - 2));
                        nat.substring(0, (nat.length() - 2));
                        mort.substring(0, (mort.length() - 2));
                        qtd.substring(0, (qtd.length() - 2));
                        tam.substring(0, (tam.length() - 2));
                        peso.substring(0, (peso.length() - 2));
                        out.println("<div><span class = \"titulo\"><h2 class = \"titulo\">TAXAS BIOLÓGICAS</h2><canvas id = \"TaxasBio\"></canvas></div>");
                        out.println("<script>"
                                + "var ctx = document.getElementById(\"TaxasBio\").getContext('2d');"
                                + "new Chart(ctx, {"
                                + "type: 'line',"
                                + "data: {"
                                + "labels: [" + datas + "],"
                                + "datasets: [{"
                                + "label: 'Natalidade', "
                                + "data: [" + nat + "],"
                                + "borderColor: ["
                                + "    'rgba(0,255,0,1)'"
                                + "],"
                                + "backgroundColor: ["
                                + "    'rgba(255,255,255, 0)'"
                                + "],"
                                + "borderWidth: 1"
                                + "},"
                                + "{"
                                + "label: 'Mortalidade',"
                                + "data: [" + mort + "],"
                                + "backgroundColor: ["
                                + "'rgba(255,255,255, 0)'"
                                + "],"
                                + "borderColor: ["
                                + "'rgba(255,0,0,1)'"
                                + "],"
                                + "borderWidth: 1"
                                + "},"
                                + "{"
                                + "label: 'Quantidade Total',"
                                + "data: [" + qtd + "],"
                                + "backgroundColor: ["
                                + "'rgba(255,255,255, 0)'"
                                + "],"
                                + "borderColor: ["
                                + "'rgba(32,178,170,05)'"
                                + "],"
                                + "borderWidth: 1"
                                + "}]},options:{}});"
                                + "</script>");
                        out.println("<div><span class = \"titulo\"><h2 class = \"titulo\">TAXAS DE DESENVOLVIMENTO</h2><canvas id = \"TaxasDes\"></canvas></div>");
                        out.println("<script>"
                                + "var ctx = document.getElementById(\"TaxasDes\").getContext('2d');"
                                + "new Chart(ctx, {"
                                + "type: 'line',"
                                + "data: {"
                                + "labels: [" + datas + "],"
                                + "datasets: [{"
                                + "label: 'Peso Médio', "
                                + "data: [" + peso + "],"
                                + "borderColor: ["
                                + "'rgba(75,0,130,1)'"
                                + "],"
                                + "backgroundColor: ["
                                + "'rgba(255,255,255, 0)'"
                                + "],"
                                + "borderWidth: 1"
                                + "},"
                                + "{"
                                + "label: 'Tamanho Médio',"
                                + "data: [" + tam + "],"
                                + "backgroundColor: ["
                                + "'rgba(255,255,255, 0)'"
                                + "],"
                                + "borderColor: ["
                                + "'rgba(255,215,0,1)'"
                                + "],"
                                + "borderWidth: 1"
                                + "}]},options:{}});"
                                + "</script>");
                        datas = "";
                        out.println("<div id = \"topo\"><a href = \"#menu\">Voltar ao Topo<img src = \"icones/seta-para-cima-preta.png\"/></a></div>");
                    }
                }
            %>
        </div>
    </body>
</html>
