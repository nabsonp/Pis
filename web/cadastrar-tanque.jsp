<%-- 
    Document   : cadastrar-tanque
    Created on : 13/08/2017, 20:16:42
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Cadastrar Tanque</title>
        <link href = "css/cadastrar-tanque.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <script src="js/cadastrar-tanque.js"></script>
        <script src="js/menu.js"></script>
    </head>
    <body onload="foco()">
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "AbrirTanques">Tanques</a> &raquo; <span id = "atual">Cadastrar Tanque</span></div>
        <div>
            <h1>Cadastro</h1>
            <hr width = 900px/>
        </div>
        <form name = "cadastroTanque" action = "CadastrarTanque" method="get">
            <table id = "formCad" align = center>
                <tr> <td><label>Tipo:</label></td> <td colspan="3"><input type = "text" placeholder = "Escavado, Alvenaria, etc." size = 55 name = "tipo" /></td></tr>
                <tr> <td><label>Material:</label></td> <td colspan="3"><input type = "text" placeholder = "Argamassa, rede, etc." size = 55 name = "mat" /></td></tr>
                <tr> <td><label>Revestimento:</label></td> <td colspan="3"><input type = "text" placeholder = "PVC, nenhum, etc." size = 55 name = "revest" /></td></tr>
                <tr> <td><label>Capacidade (L):</label></td> <td colspan="3"><input type = "text" size = 55 name = "cap"  id = "cap" onkeyup="verificaNumeros('cap')" /></td></tr>
                <tr> <td><label>Vazão (L/H):</label></td> <td align = left><input type = "text" size = 16 name = "vazao"  id = "vazao" onkeyup="verificaNumeros('vazao')" /></td> </tr>
                <tr> <td><label>Comprimento (M):</label></td> <td colspan="3"><input type = "text" size = 16 name = "comp"  id = "comp" onkeyup="verificaNumeros('comp')"/></td></tr>
                <tr> <td><label>Largura (M):</label></td> <td colspan="3"><input type = "text" size = 16 name = "larg"  id = "larg" onkeyup="verificaNumeros('larg')" /></td></tr>
                <tr> <td><label>Profundidade (M):</label></td> <td colspan="3"><input type = "text" size = 16 name = "prof"  id = "prof" onkeyup="verificaNumeros('prof')" /></td></tr>
                <tr> <td colspan="4" align = "right"><button class = "botoes" onclick = "salvar()"><img src = "icones/checked.png" /><span>Salvar</span></button><button type = "reset" class = "botoes" onclick="cancelar()"><img src = "icones/cancelar.png"/><span>Cancelar</span></button></td> </tr>
            </table>
        </form>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"> <td><a href = "../diversos/pagina-inicial.html">Página Inicial</a></td> <td><a href = "../financeiro/financeiro-insumos.html">Financeiro</a></td> <td><a href = "tanques.html">Tanques</a></td> <td><a href = "../especie/especies.html">Espécies</a></td> <td><a href = "../estoque/estoque.html">Estoque</a></td> <td><a href = "../relatorios/rel-financeiros.html">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-insumos.html">Insumos</a></td> <td><a href = "../tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "../especie/cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "../relatorios/rel-financeiros.html">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-tanques.html">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>