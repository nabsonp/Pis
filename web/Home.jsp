<%-- 
    Document   : Home
    Created on : 01/08/2017, 22:51:08
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>PisciculTech</title>
        <link href = "css/pagina-inicial.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
    </head>
    <body>
        <nav id = "menu">
            <ul>
                <img src = "icones/logotipo.png" id = "logoCabecalho"/>
                <li class = "current">
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
        <img src="icones/logo-peixe.png" id = "peixe"/>
        <div id = "textoPrincipal">
            <div id = "bv">Bem Vindo</div>
            <div id = "descricao">PisciculTech é uma <br/>Aplicação Web voltada<br/>para Piscicultura Super-<br/>-Intensiva.</div>
            <div id = "msg">Aproveite!</div>
        </div>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"> <td><a href = "pagina-inicial.html">Página Inicial</a></td> <td><a href = "financeiro/financeiro-insumos.html">Financeiro</a></td> <td><a href = "tanques/tanques.html">Tanques</a></td> <td><a href = "especie/especies.html">Espécies</a></td> <td><a href = "estoque/estoque.html">Estoque</a></td> <td><a href = "relatorios/rel-financeiros.html">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-insumos.html">Insumos</a></td> <td><a href = "tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "especie/cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "relatorios/rel-financeiros.html">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "relatorios/rel-tanques.html">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "relatorios/rel-especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro/financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>