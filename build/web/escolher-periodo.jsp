<%-- 
    Document   : escolher-periodo
    Created on : 06/12/2017, 20:55:55
    Author     : samsung
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.Empresa"%>
<%@page import="java.sql.Date"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
    <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
    <title>Período</title>
    <link href = "css/escolher-periodo.css" rel = "StyleSheet" />
    <link href = "css/menu-rodape.css" rel = "StyleSheet" />
    <link href = "css/menu-lateral.css" rel = "StyleSheet" />
    <link href = "css/navegacao.css" rel = "StyleSheet" />
    <script src="js/menu.js"></script>
    <script type = "text/javascript" src="js/escolher-periodo.js"></script>
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
        Date dtCriacao = Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao();
        DateFormat dia = new SimpleDateFormat("dd");
        DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
        DateFormat ano = new SimpleDateFormat("yy");
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
    <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <span id = "atual">Período</span></div>
    <div id ="corpo">
        <h1>Período</h1>
        <form name="pesquisar" method="get" action="EscolherTanqueRel">
            <p>De <%= dia.format(dtCriacao)%> de <%= mes.format(dtCriacao)%> de <%= ano.format(dtCriacao)%> até a data atual.</p>
            <table id="corpo">
                <tr>
                    <td><label>Desde: </label></td>
                    <td><input id="inicio" name="inicio" type="text" onkeyup="validarData(this)" size="20" class="campo" maxlength="10"></td>
                </tr>
                <tr>
                    <td><label>Até: </label></td>
                    <td><input id="fim" name="fim" type="text" onkeyup="validarData(this)" size="20" class="campo" maxlength="10"></td>
                </tr>
            </table>            
            <input type="button" onclick="validar()" value="Enviar" id="confirmar" >
        </form>
    </div>
</body>
</html>
