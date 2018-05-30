<%-- 
    Document   : especies
    Created on : 15/08/2017, 13:58:12
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html; charset = UTF-8" />
        <title>Espécies</title>
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script type="text/javascript" src = "js/menu.js"></script>
        <script type="text/javascript" src = "js/especies.js"></script>
        <link href = "css/especies.css" rel = "StyleSheet">
    </head>
    <body>
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
                <li class = "current">
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
        <div id = "navega">
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <span id = "atual">Espécie</span>
        </div>
        <div id = "especies">
            <%
                ArrayList<Especie> esps = ArrayList.class.cast(request.getAttribute("esps"));
                for (Especie e : esps) {
                    out.println("<div class = \"especieIndividual\">"
                            + "<span class = \"dados\" onclick = \"abrirEspecie('" + e.getId() + "')\">"
                            + "<img src = \"" + e.getImagem() + "\" />"
                            + "<br>Nome: " + e.getNome() + "<br><br>"
                            + "</span>"
                            + "</div>");
                }
            %>

            <div class = "especieIndividual1">
                <button class = "buttonAdc" onclick = "abrirCadastro()"><img src = "icones/adicionar.png"></button>
            </div>
        </div>

        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"> 
                    <td><a href = "../diversos/pagina-inicial.html">Página Inicial</a></td> <td><a href = "../financeiro/financeiro-insumos.html">Financeiro</a></td> <td><a href = "../tanques/tanques.html">Tanques</a></td> <td><a href = "especies.html">Espécies</a></td> <td><a href = "#">Estoque</a></td> <td><a href = "#">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-insumos.html">Insumos</a></td> <td><a href = "../tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "#">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "#">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>
