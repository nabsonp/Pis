<%-- 
    Document   : dados-tanque
    Created on : 06/08/2017, 11:10:40
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Quimico"%>
<%@page import="br.com.piscicultech.modelo.TanQuim"%>
<%@page import="br.com.piscicultech.modelo.VerificacaoEsp"%>
<%@page import="br.com.piscicultech.modelo.Verificacao"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.piscicultech.modelo.Racao"%>
<%@page import="br.com.piscicultech.modelo.Arracoamento"%>
<%@page import="br.com.piscicultech.modelo.TanqEsp"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <%
            Tanque tanque = Tanque.class.cast(request.getAttribute("tanque"));
            Especie esp = Especie.class.cast(request.getAttribute("esp"));
            int id = tanque.getId();
        %>
        <title>Tanque <%=id%></title>
        <link href = "css/dados-tanque.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/modal.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/tanques.js" type = "text/javascript"></script>
        <script src="js/dados-tanque.js" type = "text/javascript"></script>
        <script src="js/inserir-especie.js" type = "text/javascript"></script>
    </head>
    <body>
        <%
            ArrayList<Racao> racoes = ArrayList.class.cast(request.getAttribute("racoes"));
            if (esp != null) {
                out.println("<div class=\"modal\" id=\"agua\">"
                        + "<div class=\"modal\">"
                        + "<div class=\"modal-container\">"
                        + "<form name=\"inserir\" action=\"RealizarVerificacaoGeral\" method=\"get\">"
                        + "<a class=\"fecharModal\" href=\"#\"><img src=\"icones/cancelar.png\" /></a>"
                        + "<fieldset>"
                        + "<h2 class=\"modal\">Avaliação geral da água</h2>"
                        + "<table class=\"avaliar\" align=\"center\">"
                        + "<tr>"
                        + "<td>Tanque: </td>"
                        + "<td colspan=\"2\"><input type=\"text\" size=\"1\" name=\"idTanque\" class=\"campo\" value=\"" + id + "\" readonly/></td>"
                        + "<td>Espécie: </td>"
                        + "<td colspan=\"2\"><input type=\"text\" size=\"1\" name=\"idEspecie\" class=\"campo\" value=\"" + esp.getId() + "\" readonly/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Temperatura: </td>"
                        + "<td><input type=\"text\" name=\"temperatura\" id=\"temp\" class=\"campo\" onkeyup=\"validarNum('temp')\"/></td>"
                        + "<td><span id=\"errotemp\"></span></td>"
                        + "<td>Oxigênio: </td>"
                        + "<td><input type=\"text\" name=\"oxigenio\" id=\"oxi\" class=\"campo\" onkeyup=\"validarNum('oxi')\"/></td>"
                        + "<td><span id=\"errooxi\"></span></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Amônia: </td>"
                        + "<td><input type=\"text\" name=\"amonia\" id=\"amon\" class=\"campo\" onkeyup=\"validarNum('amon')\"/></td>"
                        + "<td><span id=\"erroamon\"></span></td>"
                        + "<td>Nitrito: </td>"
                        + "<td><input type=\"text\" name=\"nitrito\" id=\"nitri\" class=\"campo\" onkeyup=\"validarNum('nitri')\"/></td>"
                        + "<td><span id=\"erronitri\"></span></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Nitrato: </td>"
                        + "<td><input type=\"text\" name=\"nitrato\" id=\"nitra\" class=\"campo\" onkeyup=\"validarNum('nitra')\"/></td>"
                        + "<td><span id=\"erronitra\"></span></td>"
                        + "<td>Gás Carbônico: </td>"
                        + "<td><input type=\"text\" name=\"gasCarb\" id=\"gasCarb\" class=\"campo\" onkeyup=\"validarNum('gasCarb')\"/></td>"
                        + "<td><span id=\"errogasCarb\"></span></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Alcalinidade: </td>"
                        + "<td><input type=\"text\" name=\"alcal\" id=\"alcal\" class=\"campo\" onkeyup=\"validarNum'alcal')\"/></td>"
                        + "<td><span id=\"erroalcal\"></span></td>"
                        + "<td>pH: </td>"
                        + "<td><input type=\"text\" name=\"ph\" id=\"ph\" class=\"campo\" onkeyup=\"validarNum('ph')\"/></td>"
                        + "<td><span id=\"erroph\"></span></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Horário: </td>"
                        + "<td><input placeholder=\"  :  :\" maxlength=\"8\" onkeyup=\"validarData(this)\" type=\"text\" name=\"hora\" id=\"hora\" class=\"campo\"/></td>"
                        + "<td colspan=\"2\"><input type=\"button\" onclick=\"getHoraAtual('hora')\" id=\"agora\" value=\"Agora\"/></td>"
                        + "</tr>"
                        + "</table>"
                        + "<input type=\"button\" onclick=\"verificar()\" value=\"Confirmar\" id = \"confirmar\"/>"
                        + "</fieldset>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "</div>");

                out.println("<div class=\"modal\" id=\"arracoar\">"
                        + "<div class=\"modal\">"
                        + "<div class=\"modal-container\">"
                        + "<form name=\"arrac\" action=\"Arracoar\" method=\"get\">"
                        + "<a class=\"fecharModal\" href=\"#\"><img src=\"icones/cancelar.png\" /></a>"
                        + "<fieldset>"
                        + "<h2 class=\"modal\">Arraçoar</h2>"
                        + "<table class=\"avaliar\" align=\"center\">"
                        + "<tr>"
                        + "<td>Tanque: </td>"
                        + "<td><input type=\"text\" size=\"1\" name=\"idTanque\" class=\"campo\" value=\"" + id + "\" readonly/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Ração: </td>"
                        + "<td><select class = \"campo\" name = \"nomeRacao\">");
                if (racoes != null) {
                    for (Racao r : racoes) {
                        out.println("<option>"
                                + r.getNome()
                                + "</option>");
                    }
                }
                out.println("</select></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Peso: </td>"
                        + "<td><input type=\"text\" size=5 name=\"peso\" id=\"peso\" class=\"campo\" onkeyup=\"validarNum('peso')\"/> g</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Horário: </td>"
                        + "<td><input placeholder=\"  :  :\" maxlength=\"8\" onkeyup=\"validarData(this)\" type=\"text\" name=\"hora\" id=\"horaArr\" class=\"campo\"/>"
                        + " <input type=\"button\" onclick=\"getHoraAtual('horaArr')\" id=\"agora\" value=\"Agora\"/></td>"
                        + "</tr>"
                        + "</table>"
                        + "<input type=\"button\" onclick=\"verificarArr()\" value=\"Confirmar\" id = \"confirmar\"/>"
                        + "</fieldset>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "</div>");

                out.println("<div class=\"modal\" id=\"avalBio\">"
                        + "<div class=\"modal\">"
                        + "<div class=\"modal-container\">"
                        + "<form name=\"verBio\" action=\"RealizarExameBiometrico\" method=\"get\">"
                        + "<a class=\"fecharModal\" href=\"#\"><img src=\"icones/cancelar.png\" /></a>"
                        + "<fieldset>"
                        + "<h2 class=\"modal\">Realizar Exame Biométrico</h2>"
                        + "<table class=\"avaliar\" align=\"center\">"
                        + "<tr>"
                        + "<td>Tanque: </td>"
                        + "<td><input type=\"text\" size=\"1\" name=\"idTanque\" class=\"campo\" value=\"" + id + "\" readonly/></td>"
                        + "<td>Espécie: </td>"
                        + "<td colspan=\"2\"><input type=\"text\" size=\"1\" name=\"idEspecie\" class=\"campo\" value=\"" + esp.getId() + "\" readonly/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Horário: </td>"
                        + "<td><input placeholder=\"  :  :\" maxlength=\"8\" onkeyup=\"validarData(this)\" type=\"text\" name=\"hora\" id=\"horaBio\" class=\"campo\"/></td>"
                        + "<td colspan=\"2\"><input type=\"button\" onclick=\"getHoraAtual('horaBio')\" id=\"agora\" value=\"Agora\"/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Tamanho médio: </td>"
                        + "<td><input type=\"text\" size=5 name=\"tamanhoMedio\" id=\"tamanhoMedio\" class=\"campo\" onkeyup=\"validarNum('tamanhoMedio')\"/>cm</td>"
                        + "<td>Peso médio: </td>"
                        + "<td><input type=\"text\" size=5 name=\"pesoMedio\" id=\"pesoMedio\" class=\"campo\" onkeyup=\"validarNum('pesoMedio')\"/>g</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Mortos: </td>"
                        + "<td><input type=\"text\" size=5 name=\"mortos\" id=\"mortos\" class=\"campo\" onkeyup=\"validarNum('mortos')\"/></td>"
                        + "<td>Nascidos: </td>"
                        + "<td><input type=\"text\" size=5 name=\"nascidos\" id=\"nascidos\" class=\"campo\" onkeyup=\"validarNum('nascidos')\"/></td>"
                        + "</tr>"
                        + "</table>"
                        + "<input type=\"button\" onclick=\"verificarVerBio()\" value=\"Confirmar\" id = \"confirmar\"/>"
                        + "</fieldset>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
                ArrayList<Quimico> quis = ArrayList.class.cast(request.getAttribute("quis"));
                out.println("<div class=\"modal\" id=\"inserirIns\">"
                        + "<div class=\"modal\">"
                        + "<div class=\"modal-container\">"
                        + "<form name=\"inserirIns\" action=\"InserirInsumo\" method=\"get\">"
                        + "<a class=\"fecharModal\" href=\"#\"><img src=\"icones/cancelar.png\" /></a>"
                        + "<fieldset>"
                        + "<h2 class=\"modal\">Inserção de Insumo</h2>"
                        + "<table class=\"avaliar\" align=\"center\">"
                        + "<tr>"
                        + "<td>Tanque: </td>"
                        + "<td><input type=\"text\" size=\"1\" name=\"idTanque\" class=\"campo\" value=\"" + id + "\" readonly/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Ração: </td>"
                        + "<td><select class = \"campo\" name = \"nomeQuim\">");
                if (quis.isEmpty()) {
                    out.println("<option>Sem produtos químicos</option>");
                } else {
                    for (Quimico q : quis) {
                        out.println("<option>"
                                + q.getNome()
                                + "</option>");
                    }
                }
                out.println("</select></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Horário: </td>"
                        + "<td><input placeholder=\"  :  :\" maxlength=\"8\" onkeyup=\"validarData(this)\" type=\"text\" name=\"hora\" id=\"horaQui\" class=\"campo\"/></td>"
                        + "<td colspan=\"2\"><input type=\"button\" onclick=\"getHoraAtual('horaQui')\" id=\"agora\" value=\"Agora\"/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Quantidade em gramas: </td>"
                        + "<td><input type=\"text\" size=5 name=\"peso\" id=\"peso\" class=\"campo\" onkeyup=\"validarNum('tamanhoMedio')\"/></td>"
                        + "</tr>"
                        + "</table>"
                        + "<input type=\"button\" onclick=\"verificarIns()\" value=\"Confirmar\" id = \"confirmar\"/>"
                        + "</fieldset>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }
        %>
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "AbrirTanques">Tanques</a> &raquo; <span id = "atual">Tanque <%=id%></span></div>
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
                %>
                <li class = "titulo">
                    Calendário
                </li>
                <li>
                    <%
                        Date dtAtual = Date.class.cast(sessao.getAttribute("data"));
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        if (tanque.getDtCriacao() != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(data);
                            String atual = dataF.format(dtAtual);
                            for (Date dt = data; dt.after(tanque.getDtCriacao());) {
                                if (atual.equals(dataF.format(dt))) {
                                    out.println("<button id = \"current\" name=\"" + df.format(dt) + "\" onclick = \"abrirDia(this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                } else {
                                    out.println("<button name=\"" + df.format(dt) + "\" onclick = \"abrirDiaTanque(this," + tanque.getId() + ")\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
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
        <div class = "menuLateralT">
            <img src="icones/tanque.png" id = "iconeT"/>
            <ul id = "botoes">
                <li class = "titulo">
                    Tanques
                </li>
                <%
                    String atual = "";
                    if (tanques != null) {
                        for (Tanque t : tanques) {
                            if (t.getId() == id) {
                                atual = "id = \"current\"";
                            } else {
                                atual = "";
                            }
                            out.println("<li>"
                                    + "<button " + atual + " onclick = \"abrirTanque(" + t.getId() + ")\">Tanque " + t.getId() + "</button>"
                                    + "</li>");
                        }
                    }
                %>
            </ul>
        </div>
        <div id = "corpo">
            <h1>Tanque <%=id%></h1>
            <%
                if (esp != null /*&& df.format(data).equals(df.format(dtAtual))*/) {
                    out.println("<h2 id = \"agua\">Água</h2><a href=\"#agua\" class=\"abrirModal\">Realizar avaliação geral</a><hr>");
                } else {
                    out.println("<h2 id = \"agua\">Água</h2><hr>");
                }
            %>
            <table class = "dados" id = "agua">
                <tr id="tituloAgua" class = "titulo"> <td class = "btts"></td> <td>Propriedade</td> <td>Valor</td> <td>Padrão</td> <td>Horário</td> <td>Funcionário</td> <td>Situação</td> </tr>
                <%
                    ArrayList<Verificacao> ves = ArrayList.class.cast(request.getAttribute("ves"));
                    String situacao = "";
                    if (esp == null) {
                        out.println("<tr id = \"Temperatura\" class = \"vazio\"><td class = \"btts\"></td></td><td>Temperatura</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Oxigenio\" class = \"vazio\"><td class = \"btts\"></td></td><td>Oxigênio</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Amonia\" class = \"vazio\"><td class = \"btts\"></td></td><td>Amônia</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Nitrito\" class = \"vazio\"><td class = \"btts\"></td><td>Nitrito</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Nitrato\" class = \"vazio\"><td class = \"btts\"></td></td><td>Nitrato</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Gas Carbonico\" class = \"vazio\"><td class = \"btts\"></td><td>Gás Carbônico</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"Alcalinidade\" class = \"vazio\"><td class = \"btts\"></td></td><td>Alcalinidade</td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>"
                                + "<tr id = \"pH\" class = \"vazio\"><td class = \"btts\"></td></td><td>pH</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"></tr>");
                    } else {
                        if (!ves.isEmpty()) {
                            for (Verificacao da : ves) {
                                if (da.isSituacao()) {
                                    situacao = "OK";
                                } else {
                                    situacao = "Atenção";
                                }
                                String nomeDado = da.getNome();
                                if (da.getNome().equals("gasCarb")) {
                                    nomeDado = "GAS CARBONICO";
                                }
                                if (da.getNome().equals("alcal")) {
                                    nomeDado = "ALCALINIDADE";
                                }
                                // AJEITAR O CSS DE QUANDO CADASTRA ALGUM DADO
                                if (da.isSituacao()) {
                                    out.println("<tr id = \"" + da.getNome() + "\"><td class = \"btts\"></td><td>" + nomeDado.toUpperCase() + "</td> <td>" + da.getValor() + "</td> <td>-</td> <td>" + da.getHora() + "</td> <td>" + da.getCpfFunc() + "</td> <td>" + situacao + "</td> <td class = \"btts\"><button onclick = \"addDado('" + da.getNome() + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button><button onclick = \"editarDado('" + da.getNome() + "','" + id + "')\"><img  src = \"icones/editar.png\" title = \"Editar\"/></button><button onclick = \"excluirDado('" + da.getNome() + "','" + id + "')\"><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button></td> </tr>");
                                } else {
                                    out.println("<tr id = \"" + da.getNome() + "\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Este valor está fora dos padrões especificados pela espécie.\"/><td>" + nomeDado.toUpperCase() + "</td> <td>" + da.getValor() + "</td> <td>-</td> <td>" + da.getHora() + "</td> <td>" + da.getCpfFunc() + "</td> <td>" + situacao + "</td> <td class = \"btts\"><button onclick = \"addDado('" + da.getNome() + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button><button onclick = \"editarDado('" + da.getNome() + "','" + id + "')\"><img  src = \"icones/editar.png\" title = \"Editar\"/></button><button onclick = \"excluirDado('" + da.getNome() + "','" + id + "')\"><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button></td> </tr>");
                                }
                            }
                        } else {
                            out.println("<tr id = \"Temperatura\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Temperatura</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button><img onclick = \"addDado('Temperatura','" + id + "')\" src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Oxigenio\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Oxigênio</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Oxigenio','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Amonia\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Amônia</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Amonia','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Nitrito\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Nitrito</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Nitrito','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Nitrato\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Nitrato</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Nitrato','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Gas Carbonico\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Gás Carbônico</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Gas Carbonico','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"Alcalinidade\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>Alcalinidade</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('Alcalinidade','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>"
                                    + "<tr id = \"pH\" class = \"erro\"><td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Campo obrigatório.\"/></td><td>pH</td> <td></td> <td></td> <td></td> <td></td> <td></td> <td class = \"btts\"><button onclick = \"addDado('pH','" + id + "')\"><img  src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td> </tr>");
                        }
                    }
                %>
            </table>
            <div class = "tituloETabela">
                <%
                    TanqEsp tanqEsp = TanqEsp.class.cast(request.getAttribute("tanqEsp"));
                    if (esp == null) {
                        //if (df.format(data).equals(df.format(dtAtual))) {
                            out.println("<h2 align=\"center\">Espécie</h2><a href=\"javascript:inserirEspecie(" + id + ")\" class = \"abrirModal\">Inserir Espécie</a><hr>"
                                    + "<div id=\"semEspecie\">Insira uma espécie.</div>");
                        //} else {
                        //    out.println("<h2 align=\"center\">Espécie</h2>"
                        //            + "<div id=\"semEspecie\">Vazio</div>");
                        //}
                    } else {
                        out.println("<h2 align=\"center\"><a id=\"esp\" href=\"javascript:abrirEspecie(" + esp.getId() + ")\">" + esp.getNome() + "</h2></a><hr></div>");
                        out.println("<div id = \"especie\">"
                                + "<table class = \"caracteristica\" align = \"center\">"
                                + "<tbody>"
                                + "<tr>"
                                + "<td class = \"dados\">Nome científico:</td>"
                                + "<td id = \"nomeCient\">" + esp.getNomeCient() + "</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td class = \"dados\">Biomassa:</td>"
                                + "<td id = \"nomeCient\">" + tanqEsp.getBiomassa() + "Kg</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td class = \"dados\">Quantidade:</td>"
                                + "<td id = \"nomeCient\">" + tanqEsp.getQtd() + "</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td class = \"dados\"> Ração/Dia: </td>"
                                + "<td id = \"racaoDia\">" + esp.getRacaoDia() + "Kg</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td class = \"dados\"> Frequência alimentar: </td>"
                                + "<td id = \"freqAlim\">" + esp.getFreqAlimMin() + " a " + esp.getFreqAlimMax() + "</td>"
                                + "</tr>"
                                + "</tbody>"
                                + "</table>"
                                + "<img id=\"espec\" src = \"especies/tambaqui.png\" />"
                                + "</div>");
                    }
                    out.println("</table>");

                    if (esp == null) {
                        out.println("<h2 align=\"center\">Biometria</h2><hr>");
                        out.println("<div id=\"semEspecie\">Insira uma espécie.</div>");
                    } else {
                        //if (df.format(data).equals(df.format(dtAtual))) {
                            out.println("<h2 align=\"center\">Biometria</h2><a href=\"#avalBio\" class=\"abrirModal\">Realizar avaliação biométrica</a><hr>");
                        /*} else {
                            out.println("<h2 align=\"center\">Biometria</h2><hr/>");
                        }*/
                        out.println("<table  class = \"dados\" align = \"center\">");
                        ArrayList<VerificacaoEsp> verEsps = ArrayList.class.cast(request.getAttribute("verEsps"));
                        if (verEsps.isEmpty()) {
                            out.println("<div id=\"semEspecie\">Nenhum exame biometrico realizado.</div></div>");
                        } else {
                            out.println("<tr class = \"titulo\"> <td>Tamanho médio</td> <td>Peso médio</td> <td>Mortos</td> <td>Nascidos</td> <td>Data</td> <td>Hora</td> <td>Quantidade total</td> </tr>");
                            for (VerificacaoEsp v : verEsps) {
                                out.println("<tr> <td>" + v.getTamanhoMedio() + "</td> <td>" + v.getPeso() + "</td> <td>" + v.getMortos() + "</td> <td>" + v.getNascidos() + "</td> <td>" + dia.format(v.getDtVerif()) + "/" + mes.format(v.getDtVerif()) + "</td> <td>" + v.getHora() + "</td> <td>" + v.getQtd() + "</td> </tr>");
                            }
                        }
                        out.println("</table>");
                    }

                    ArrayList<Arracoamento> arrs = ArrayList.class.cast(request.getAttribute("arrs"));
                    int qtd = 0;
                    int vzs = 0;
                    if (esp == null) {
                        out.println("<h2 align=\"center\">Arraçoamento</h2><hr>");
                        out.println("<div id=\"semEspecie\">Insira uma espécie.</div>");
                    } else {
                        //if (df.format(data).equals(df.format(dtAtual))) {
                            out.println("<h2 align=\"center\">Arraçoamento</h2><a href=\"#arracoar\" class = \"abrirModal\" title = \"Arraçoar\">Arraçoar</a><hr>");
                        /*} else {
                            out.println("<h2 align=\"center\">Arraçoamento</h2><hr/>");
                        }*/
                        if (arrs.isEmpty()) {
                            out.println("<div id=\"semEspecie\">Este tanque não foi arraçoado nesta data.</div>");
                        } else {
                            out.println("<table class = \"dados\" id = \"arracoamento\">"
                                    + "<tr class = \"titulo\"> <td class = \"btts\">  <td>Vezes de arraçoamento</td> <td>Ração</td> <td>Marca</td> <td>Horário</td> <td>Quantidade (kg)</td> </tr>");
                            int min = esp.getFreqAlimMin();
                            int max = esp.getFreqAlimMax();
                            Racao rac = null;
                            for (Arracoamento a : arrs) {
                                for (Racao r : racoes) {
                                    if (r.getCodigo() == a.getCodRacao()) {
                                        rac = r;
                                    }
                                }
                                qtd++;
                                if (qtd > max) {
                                    out.println("<tr class=\"erro\" id = \"" + qtd + "\"> <td class = \"aviso\"><img class = \"atencao\" src = \"icones/exclamacao.png\" title = \"Foi excedido o máximo de vezes para se arraçoar este tanque.\"/></td> <td>" + qtd + "</td> <td>" + rac.getNome() + "</td> <td>" + rac.getMarca() + "</td> <td>" + a.getHora() + "</td> <td>" + a.getPeso() + "</td> </tr>");
                                } else {
                                    out.println("<tr id = \"" + qtd + "\"> <td class = \"btts\"> </td> <td>" + qtd + "</td> <td>" + rac.getNome() + "</td> <td>" + rac.getMarca() + "</td> <td>" + a.getHora() + "</td> <td>" + a.getPeso() + "</td> </tr>");
                                }
                            }
                            while (qtd < vzs) {
                                qtd++;
                                out.println("<tr id = \"" + qtd + "\"> <td>" + qtd + "</td> <td></td> <td></td> <td></td> <td></td> </tr>");
                            }
                            out.println("</table>");
                        }
                    }

                    ArrayList<TanQuim> tqs = ArrayList.class.cast(request.getAttribute("tqs"));
                    if (esp == null) {
                        out.println("<h2 align=\"center\">Inserção de Insumo</h2><hr>");
                        out.println("<div id=\"semEspecie\">Insira uma espécie.</div>");
                    } else {
                        //if (df.format(data).equals(df.format(dtAtual))) {
                            out.println("<h2 align=\"center\">Inserção de Insumo</h2><a  href=\"#inserirIns\" class = \"abrirModal\" >Inserir Insumo</a><hr>");
                        /*} else {
                            out.println("<h2 align=\"center\">Inserir Insumo</h2>");
                        }*/
                        if (tqs.isEmpty()) {
                            out.println("<div id=\"semEspecie\">Não forma inseridos insumos neste tanque</div>");
                        } else {
                            out.println("<table align = \"center\" class = \"dados\">"
                                    + "<tr class = \"titulo\"> <td class = \"btts\">  <td>Químico</td> <td>Data de Inserção</td> <td>Horário</td> <td>Quantidade (g)</td> </tr>");
                            for (TanQuim tq : tqs) {
                                out.println("<tr> <td class = \"btts\"></td> <td>" + tq.getCodQuimico() + "</td> <td>" + tq.getDtInsercao() + "</td> <td>" + tq.getHoraInsercao() + "</td> <td>" + tq.getPeso() + "</td> </tr>");
                            }
                            out.println("</table>");
                        }
                    }
                %>
                <h2 id = "dadosTanque">Dados do Tanque</h2><hr>
                <table class = "dados" id = "dadosDoTanque">
                    <tr class = "titulo"> <td>Dado</td> <td colspan="3">Valor</td> </tr>
                    <tr> <td>Piscicultura</td> <td colspan="3"><%=tanque.getPiscicultura()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Tipo</td> <td colspan="3"><%=tanque.getTipo()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Material de Revestimento</td> <td colspan="3"><%=tanque.getMaterial()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Capacidade (L)</td> <td colspan="3"><%=tanque.getCapacidade()%>L</td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Vazão (L)</td> <td colspan="3"><%=tanque.getVazao()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Peixamento</td> <td colspan="3"><%=tanque.getDiaPeixam()%>/<%=tanque.getMesPeixam()%>/<%=tanque.getAnoPeixam()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                    <tr> <td>Dimensões (CxLxP)</td> <td><%=tanque.getComp()%></td> <td><%=tanque.getLargura()%></td> <td><%=tanque.getProfund()%></td> <td class = "btts"><button><img  src = "icones/lixeira.png" title = "Excluir"/></button><button><img  src = "icones/editar.png" title = "Editar"/></button><button><img  src = "icones/adicionar.png" title = "Adicionar"/></button></td> </tr>
                </table>
                <div id = "topo"><a href = "#menu">Voltar ao Topo<img src = "icones/seta-para-cima-preta.png"/></a></div>
            </div>
    </body>
</html>