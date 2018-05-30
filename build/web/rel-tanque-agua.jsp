<%-- 
    Document   : rel-tanque-agua
    Created on : 07/12/2017, 11:57:26
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="br.com.piscicultech.modelo.Verificacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Relatório de Tanque</title>
        <link href = "css/rel-tanque-agua.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script src="js/relatorios.js"></script>
        <script src="js/rel-tanque-agua.js"></script>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
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
                int id = Integer.parseInt(String.valueOf(request.getAttribute("idTanque")));
                Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
                Date fim = Date.class.cast(sessao.getAttribute("fim"));
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "escolher-periodo.jsp">Período</a> &raquo; <span id = "atual">Relatórios de Água do Tanque <%= id%></span></div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/tanque.png" id = "icone"/></label>
        <div class = "menuLateral">
            <ul id = "botoes">
                <li class = "titulo">
                    Tanques
                </li>
                <%
                    ArrayList<Tanque> tanques = ArrayList.class.cast(request.getAttribute("tanques"));
                    String atual = "";
                    if (tanques != null) {
                        for (Tanque t : tanques) {
                            if (t.getId() == id) {
                                atual = "id = \"current\"";
                            } else {
                                atual = "";
                            }
                            out.println("<li><button " + atual + " onclick = \"abrirTanque(" + t.getId() + ")\">Tanque " + t.getId() + "</button></li>");
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
                <button id = "sub-current" onclick = "abrirTanques()">Tanques</button>
                <button onclick = "abrirEspeciesRel()">Espécies</button>
            </nav>
            <nav id = "submenuTanque">
                <ul>
                    <li><button onclick = "abrirTanqueBiometrico(<%=id%>)">Biometria</button></li>
                    <li class = "current"><button onclick = "abrirAgua(<%=id%>)">Água</button></li>
                </ul>
            </nav>
            <%
                Especie esp = Especie.class.cast(request.getAttribute("esp"));
                String nomeEsp;
                if (esp == null) {
                    nomeEsp = "Sem espécie";
                } else {
                    nomeEsp = esp.getNome();
                }
            %>
            <h1>Tanque <%= id%> - <%= nomeEsp%> </h1>
            <%
                ArrayList<Verificacao> ves = ArrayList.class.cast(request.getAttribute("ves"));
                DateFormat dia = new SimpleDateFormat("dd");
                DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
                DateFormat mesNum = new SimpleDateFormat("MM");
                DateFormat ano = new SimpleDateFormat("yy");
                if (inicio != null && fim != null && esp != null) {
                    out.println("<h3>De " + dia.format(inicio) + " de " + mes.format(inicio) + " de " + ano.format(inicio) + " a " + dia.format(fim) + " de " + mes.format(fim) + " de " + ano.format(fim) + "</h3>");
                    if (ves.isEmpty()) {
                        out.println("<h1 id=\"vazio\">Sem verificações para esse intervalo de tempo.</h1>");
                    } else {
                        String[] nomes = new String[8];
                        nomes[0] = "alcal";
                        nomes[1] = "amonia";
                        nomes[2] = "gasCarb";
                        nomes[3] = "nitrato";
                        nomes[4] = "nitrito";
                        nomes[5] = "oxigenio";
                        nomes[6] = "ph";
                        nomes[7] = "temperatura";
                        String datas = "";
                        for (int is = 0; is < 8; is++) {
                            String aux = "", minimo = "", maximo = "", dado = "";
                            float min = 0, max = 0;
                            for (Verificacao ve : ves) {
                                if (ve.getNome().equals(nomes[is])) {
                                    aux = aux + ve.getValor() + ",";
                                    datas = datas + "\"" + dia.format(ve.getDtVerif()) + "/" + mesNum.format(ve.getDtVerif()) + " (" + ve.getHora() + ")\",";
                                    switch (is) {
                                        case 0:
                                            min = esp.getAlcalinidadeMin();
                                            max = esp.getAlcalinidadeMax();
                                            dado = "Alcalinidade";
                                            break;
                                        case 1:
                                            min = esp.getAmoniaMin();
                                            max = esp.getAmoniaMax();
                                            dado = "Amônia";
                                            break;
                                        case 2:
                                            min = esp.getGasCarbonicoMin();
                                            max = esp.getGasCarbonicoMax();
                                            dado = "Gás Carbônico";
                                            break;
                                        case 3:
                                            min = esp.getNitratoMin();
                                            max = esp.getNitratoMax();
                                            dado = "Nitrato";
                                            break;
                                        case 4:
                                            min = esp.getNitritoMin();
                                            max = esp.getNitritoMax();
                                            dado = "Nitrito";
                                            break;
                                        case 5:
                                            min = esp.getOxigenioMin();
                                            max = esp.getOxigenioMax();
                                            dado = "Oxigênio";
                                            break;
                                        case 6:
                                            min = esp.getPhMin();
                                            max = esp.getPhMax();
                                            dado = "pH";
                                            break;
                                        case 7:
                                            min = esp.getTemperaturaMin();
                                            max = esp.getTemperaturaMax();
                                            dado = "Temperatura";
                                            break;
                                    }
                                    minimo = minimo + min + ",";
                                    maximo = maximo + max + ",";
                                }
                            }
                            datas.substring(0, (datas.length() - 2));
                            minimo.substring(0, (minimo.length() - 2));
                            maximo.substring(0, (maximo.length() - 2));
                            aux.substring(0, (aux.length() - 2));
                            out.println("<div><span class = \"titulo\"><h2 class = \"titulo\">" + dado.toUpperCase() + "</h2><canvas id = \"" + dado + "\"></canvas></div>");
                            out.println("<script>"
                                    + "var ctx = document.getElementById(\"" + dado + "\").getContext('2d');"
                                    + "new Chart(ctx, {"
                                    + "type: 'line',"
                                    + "data: {"
                                    + "labels: [" + datas + "],"
                                    + "datasets: [{"
                                    + "label: '" + dado + "', "
                                    + "data: [" + aux + "],"
                                    + "borderColor: ["
                                    + "    'rgba(135,206,235,1)'"
                                    + "],"
                                    + "backgroundColor: ["
                                    + "    'rgba(135,206,235, 0.2)'"
                                    + "],"
                                    + "borderWidth: 1"
                                    + "},"
                                    + "{"
                                    + "label: 'Mínimo',"
                                    + "data: [" + minimo + "],"
                                    + "backgroundColor: ["
                                    + "'rgba(255,255,255, 1)'"
                                    + "],"
                                    + "borderColor: ["
                                    + "'rgba(255,69,0,1)'"
                                    + "],"
                                    + "borderWidth: 1"
                                    + "},"
                                    + "{"
                                    + "label: 'Máxmo',"
                                    + "data: [" + maximo + "],"
                                    + "backgroundColor: ["
                                    + "'rgba(255,255,255, 0.2)'"
                                    + "],"
                                    + "borderColor: ["
                                    + "'rgba(255,0,0,05)'"
                                    + "],"
                                    + "borderWidth: 1"
                                    + "}]},options:{}});"
                                    + "</script>");
                            datas = "";
                        }
                    }
                    out.println("<div id = \"topo\"><a href = \"#menu\">Voltar ao Topo<img src = \"icones/seta-para-cima-preta.png\"/></a></div>");
                }
            %>
        </div>
    </body>
</html>