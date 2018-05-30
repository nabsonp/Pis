<%-- 
    Document   : conta-func
    Created on : 14/12/2017, 16:27:18
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Exercao"%>
<%@page import="br.com.piscicultech.modelo.Cargo"%>
<%@page import="br.com.piscicultech.modelo.FoneFunc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Minha Conta</title>
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
        <div id="corpo">
            <h1><%= func.getNome()%></h1>
            <img src="<%=imagem%>" id="imgFunc" />
            <form>
                <fieldset>
                    <legend>Dados pessoais</legend>
                    <table id="dados" align="center">
                        <tr> <td>CPF:</td> <td><input size="11" name="cpf" class="campo" value="<%= func.getCpf()%>" /></td> <td>RG:</td> <td><input size="9" name="rg" class="campo" value="<%= func.getRg()%>" /></td> <td>Sexo:</td> <td><input size="1" name="sexo" class="campo" value="<%= func.getSexo()%>" /></td> </tr>
                        <tr> <td>Nascimento:</td> <td><input name="dtNascimento" class="campo" value="<%= dt.format(func.getDtNascimento())%>" /></td> <td>E-mail:</td> <td><input name="email" class="campo" value="<%= func.getEmail()%>" /></td> </tr>
                        <tr> <td>Número:</td> <td><input name="numEnd" class="campo" value="<%= func.getNumEnd()%>" /></td> <td>Logradouro:</td> <td><input name="rua" class="campo" value="<%= func.getRua()%>" /></td>  </tr>
                        <tr> <td>Bairro:</td> <td><input name="bairro" class="campo" value="<%= func.getBairro()%>" /></td> <td>Cidade:</td> <td><input name="cidade" class="campo" value="<%= func.getCidade()%>" /></td> </tr>
                        <tr>  <td>UF:</td> <td><input name="uf" class="campo" value="<%= func.getUf()%>" /></td> <td>CEP:</td> <td><input name="cep" class="campo" value="<%= func.getCep()%>" /></td> </tr>
                                <%
                                    ArrayList<FoneFunc> fones = ArrayList.class.cast(request.getAttribute("fones"));
                                    Cargo cargo = Cargo.class.cast(request.getAttribute("cargo"));
                                    Exercao exe = Exercao.class.cast(request.getAttribute("exercao"));
                                    if (!fones.isEmpty()) {
                                        out.println("<tr>");
                                        for (FoneFunc f : fones) {
                                            out.println("<td>Tipo: </td> <td><input class=\"campo\" value=\"" + f.getTipo() + "\"/></td> <td>Número: </td> <td><input class=\"campo\" value=\"" + f.getNumero() + "\" /></td>");
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                    </table>
                </fieldset>
                <fieldset>
                    <legend>Dados profissionais</legend>
                    <table align="center">
                        <tr> <td>Cargo:</td> <td><%= cargo.getNome() %></td> </tr>
                        <tr> <td>Pagamento: </td> <td>R$<%= cargo.getPagamento() %>0</td> </tr>
                        <tr> <td>Desde:</td> <td> <%= dt.format(exe.getInicio()) %> </td> </tr>
                        <tr> <td>Descrição: </td> <td coslpan="3"><%= cargo.getDescricao() %></td> </tr>
                    </table>
                </fieldset>
                    <button class="btn">Editar Dados</button>
            </form>
        </div>
    </body>
</html>
