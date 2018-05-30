<%-- 
    Document   : cadastro-especie
    Created on : 15/08/2017, 14:04:27
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Cadastrar Espécie</title>
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <link href = "css/cadastro-especie.css" rel = "StyleSheet" />
        <link rel= "stylesheet" type="text/css" href="css/navegacao.css">
        <script type= "text/javascript" src = "js/menu.js"></script>
        <script type= "text/javascript" src = "js/cadastro-especie.js"></script>
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirEspecies">Espécie</a> » <span id = "atual">Cadastrar espécie </span></span>
        </div>
        <h1>Cadastro</h1>
        <hr> 
        <div id = "informacoes-peixe">
            <form name = "informacoes" method = "get" action = "CadastrarEspecie">
                <table id = "form">
                    <tr><td><label>Nome popular: </label></td><td colspan="3"><input type="text" name="nome" size = "33" maxlength = 50></td><td><label>Nome científico: </label></td><td colspan="3"><input type="text" name="nomeCient"  size = "40" maxlength = 50></td></tr>
                    <tr> <td><label>Ração diária (Kg): </label></td> <td><input type = "text" size = 3 name = "racaoDia"/></td> <td colspan="2"><label> Frequencia alimentar: </label> </td> <td><input type = "text" size = 3 name = "freqAlimMin"/> <label>a</label> <input type = "text" size = 3 name = "freqAlimMax"/></td> </tr>
                    <tr><td><label>Peso (g): </label></td><td> <input type = "text" size = 3 name = "pesoMin"/> a <input type = "text" size = 3 name = "pesoMax"/></td> <td colspan="2"><label>Tamanho (cm): </label></td> <td><input type = "text" size = 3 name = "tamMin"/> <label>a</label> <input type = "text" size = 3 name = "tamMax"/></td>  </tr>
                    <tr> <td><label>Temperatura (ºC): </label></td><td> <input type = "text" size = 3 name = "temperaturaMin"/> <label>a</label> <input type = "text" size = 3 name = "temperaturaMax"/></td> <td colspan="2"><label>Oxigênio (mg/L): </label></td> <td><input type = "text" size = 3 name = "oxigenioMin"/> <label>a</label> <input type = "text" size = 3 name = "oxigenioMax"/></td> </tr>
                    <tr> <td><label>pH: </label> </td><td> <input type = "text" size = 3 name = "phMin"/> <label>a</label> <input type = "text" size = 3 name = "phMax"/></td> <td colspan="2"><label>CO<sub>2</sub> (mg/L):</label> </td> <td><input type = "text" size = 3 name = "gasCarbonicoMin"/> <label>a</label> <input type = "text" size = 3 name = "gasCarbonicoMax"/></td> </tr>
                    <tr> <td><label>Nitrito (mg/L): </label> </td><td> <input type = "text" size = 3 name = "nitritoMin"/> <label>a</label> <input type = "text" size = 3 name = "nitritoMax"/></td> <td colspan="2"><label>Nitrato (mg/L):</label> </td> <td><input type = "text" size = 3 name = "nitratoMin"/> <label>a</label> <input type = "text" size = 3 name = "nitratoMax"/></td> </tr>
                    <tr> <td>Amônia (mg/L): </td> <td><input type = "text" size = 3 name = "amoniaMin"/> <label>a</label> <input type = "text" size = 3 name = "amoniaMax"/></td> </tr>
                    <tr> <td><label>Imagem: </label></td> <td colspan="4"><input type = "file" name = "imagem"/></td> </tr>
                    <tr> <td colspan="4" align = "center"><button class = "botoes" onclick = "salvar()"><img src = "icones/checked.png" /><span>Salvar</span></button><button type = "reset" class = "botoes" onclick="cancelar()"><img src = "icones/cancelar.png"/><span>Cancelar</span></button></td> </tr>
                </table>
            </form>
        </div>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"> 
                    <td><a href = "../diversos/pagina-inicial.html">Página Inicial</a></td> <td><a href = "../financeiro/financeiro-insumos.html">Financeiro</a></td> <td><a href = "../tanques/tanques.html">Tanques</a></td> <td><a href = "especies.html">Espécies</a></td> <td><a href = "../estoque/estoque.html">Estoque</a></td> <td><a href = "../relatorios/rel-financeiros.html">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-insumos.html">Insumos</a></td> <td><a href = "../tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "../relatorios/rel-financeiros.html">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-tanques.html">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>