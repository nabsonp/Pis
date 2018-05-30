<%-- 
    Document   : conta-emp
    Created on : 14/12/2017, 22:00:31
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.FoneEmp"%>
<%@page import="br.com.piscicultech.modelo.FoneFunc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Empresa"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Empresa</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <link href = "css/conta-func.css" rel = "StyleSheet" />
        <script src="js/conta-func.js"></script>
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
                <li>
                    <a href = "escolher-periodo.jsp">Relatórios</a>
                </li>
            </ul>
            <%
                Funcionario func = Funcionario.class.cast(sessao.getAttribute("usuario"));
                String imagem = func.getImagem();
                String nome = func.getNome();
                String primeiroNome = nome;
                int i = -1;
                do {
                    i++;
                } while (nome.charAt(i) != ' ');
                primeiroNome = nome.substring(0, i);
                DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                Funcionario funcAdm = Funcionario.class.cast(request.getAttribute("adm"));
                Empresa emp = Empresa.class.cast(sessao.getAttribute("empresa"));
            %>
            <span id = "conta">
                <img title = "<%=nome%>" src = "<%=imagem%>" id = "user"/>
                <img id="seta" src = "icones/seta-para-baixo.png"/><br/>
                <label id = "admin"><%=primeiroNome%></label>
                <ul id = "opcoes">
                    <li onclick="abrirContaFunc()">Minha conta</li><br/>
                    <li id="atual" onclick="abrirContaEmp()">Empresa</li><br/>
                    <li onclick="sair()">Sair</li>
                </ul>
            </span>
        </nav>
        <div id="corpo">
            <h1><%= emp.getNome() %></h1>
            <form>
                <fieldset>
                    <legend>Dados da Empresa</legend>
                    <table id="dados" align="center">
                        <tr> <td>E-mail: </td> <td><%= emp.getEmail() %></td> <td>Criação: </td> <td><%= dt.format(emp.getDtCriacao()) %></td> </tr>
                        <%
                                    ArrayList<FoneEmp> fones = ArrayList.class.cast(request.getAttribute("fones"));
                                    if (!fones.isEmpty()) {
                                        out.println("<tr>");
                                        for (FoneEmp f : fones) {
                                            out.println("<td>Tipo: </td> <td><input class=\"campo\" value=\"" + f.getTipo() + "\"/></td> <td>Número: </td> <td><input class=\"campo\" value=\"" + f.getNumero() + "\" /></td>");
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                        <tr> <td>Número:</td> <td><%= emp.getNumEnd()%></td> <td>Logradouro:</td> <td><%= emp.getRua()%></td>  </tr>
                        <tr> <td>Bairro:</td> <td><%= emp.getBairro()%></td> <td>Cidade:</td> <td><%= emp.getCidade()%></td> </tr>
                        <tr>  <td>UF:</td> <td><%= emp.getUf()%></td> <td>CEP:</td> <td><%= emp.getCep()%></td> </tr>
                    </table>
                </fieldset>
                <fieldset>
                    <legend>Administrador</legend>
                    <table id="dados" align="center">
                        <tr> <td>Nome: </td> <td><%= funcAdm.getNome() %></td> <td>E-mail:</td> <td><input name="email" class="campo" value="<%= func.getEmail()%>" /></td> </tr>
                        <%
                                    ArrayList<FoneFunc> fonesFunc = ArrayList.class.cast(request.getAttribute("fonesFunc"));
                                    if (!fones.isEmpty()) {
                                        out.println("<tr>");
                                        for (FoneFunc f : fonesFunc) {
                                            out.println("<td>Tipo: </td> <td><input class=\"campo\" value=\"" + f.getTipo() + "\"/></td> <td>Número: </td> <td><input class=\"campo\" value=\"" + f.getNumero() + "\" /></td>");
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                    </table>
                </fieldset>
                    <button class="btn">Editar Dados</button>
            </form>
        </div>
    </body>
</html>
