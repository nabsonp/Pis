<%-- 
    Document   : comprar-especie
    Created on : 14/12/2017, 23:44:40
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.piscicultech.modelo.Fornecedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Comprar peixes</title>
        <link href = "css/comprar-especie.css" rel = "StyleSheet" />
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/relatorios.js" type = "text/javascript"></script>
        <script src="js/comprar-especie.js" type = "text/javascript"></script>
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
                String idTanque = String.valueOf(request.getAttribute("idTanque"));
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
        <h1>Comprar Peixes</h1>
        <form action="ComprarEspecie" method="get" name="comp">
            <fieldset>
                <legend>Dados da compra</legend>
                <table align="center">
                    <tr> <td>Fornecedor:</td> <td>
                            <select class="campo" name="nomeForn">
                                <%
                                    Date data = new Date(Calendar.getInstance().getTimeInMillis());
                                    ArrayList<Fornecedor> forns = ArrayList.class.cast(request.getAttribute("forns"));
                                    DateFormat dataF = new SimpleDateFormat("dd/MM/yyyy");
                                    for (Fornecedor f : forns) {
                                        out.println("<option>" + f.getNome() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr> <td>Espécie: </td> <td><input readonly class="campo" name="idEsp" id="idEsp" value="<%= request.getAttribute("idEsp") %>" /></td> 
                    </tr>
                    <tr>
                        <td>Tanque: </td> <td><input readonly class="campo" name="idTanq" id="idTanq" value="<%= request.getAttribute("idTan") %>" /></td> 
                    </tr>
                    <tr>
                        <td>Data:</td> <td><input readonly class="campo" name="dtVenda" id="dtVenda" value="<%= dataF.format(data) %>" /></td>
                    </tr>
                    <tr> 
                        <td>Tipo: </td> <td><select class="campo" name="tipo"><option>Alevinos</option><option>Juvenil</option><option>Adulto</option><option>Engorda</option></select></td> 
                    </tr>
                    <tr>
                        <td> Quantidade: </td> <td><input onkeyup="validarNum(this)" class="campo" name="qtd" /></td>
                    </tr>
                    <tr>
                        <td>Tamanho médio:</td> <td><input onkeyup="validarNum(this)" class="campo" name="tamanhoMedio" /></td>
                    </tr>
                    <tr>
                        <td>Peso médio:</td> <td><input onkeyup="validarNum(this)" class="campo" name="pesoMedio" /></td>
                    </tr>
                    <tr>
                        <td>Valor unitário:</td> <td><input onkeyup="validarNum(this)" class="campo" name="valorUni" /></td>
                    </tr>
                    <tr>
                        <td>Total:</td> <td><input onkeyup="validarNum(this)" class="campo" name="total" /></td>
                    </tr>
                </table>
                    <input type="button" class="btn" onclick="validar()" value="Confirmar">
            </fieldset>
        </form>
    </body>
</html>
