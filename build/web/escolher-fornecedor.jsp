<%-- 
    Document   : escolher-fornecedor
    Created on : 17/11/2017, 12:14:12
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Fornecedor"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.VendaRacao"%>
<%@page import="br.com.piscicultech.modelo.Racao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Escolher fornecedor</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script type="text/javascript" src = "js/financeiros.js"></script>
        <script type="text/javascript" src = "js/menu.js"></script>
        <script type="text/javascript" src = "js/escolher-fornecedor.js"></script>
        <link href = "css/escolher-produto-venda.css" rel = "StyleSheet" />
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
                        out.println("<li class = \"current\">"
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
        <div id = "navega">
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirFinanInsumos">Finaneiro - Insumos</a> » <span id = "atual">Escolher insumo a comprar</span>
        </div>
        <div id = "corpo">
            <%
                String tipo = request.getParameter("tipo");
                ArrayList<Fornecedor> forns = ArrayList.class.cast(request.getAttribute("forns"));
                String prod = String.valueOf(request.getAttribute("prod"));
                if (forns.isEmpty()) {
                    out.println("<h1>Não há fornecedores</h1>");
                } else {
                    out.println("<h1 align=\"center\">Fornecedores</h1><hr/>");
                    out.println("<table class = \"dados\">");
                    out.println("<tr class = \"titulo\"> <td>Nome</td> <td>E-mail</td> <td>Endereço</td> </tr>");
                    for (Fornecedor f : forns) {
                        out.println("<tr onclick=\"escolherFornecedor(" + f.getCodigo() + ", " + prod + ", \"" + tipo + "\")\"> <td>" + f.getNome() + "</td> <td>" + f.getEmail() + "</td> <td>" + f.getRua() + ", " + f.getNumEnd() + " - " + f.getBairro() + "</td> </tr>");
                    }
                }
                out.println("</table>");
            %>
        </div>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"><td><a href = "../diversos/pagina-inicial.html">Página Inicial</a></td> <td><a href = "financeiro-insumos.html">Financeiro</a></td> <td><a href = "../tanques/tanques.html">Tanques</a></td> <td><a href = "../especie/especies.html">Espécies</a></td> <td><a href = "../estoque/estoque.html">Estoque</a></td> <td><a href = "../relatorios/rel-financeiros.html">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-insumos.html">Insumos</a></td> <td><a href = "../tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "../especie/cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "../relatorios/rel-financeiros.html">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-tanques.html">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>