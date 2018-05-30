<%-- 
    Document   : inserir-especie
    Created on : 02/09/2017, 09:49:09
    Author     : samsung
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Tanque"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Inserir Espécie</title>
        <link href = "css/inserir-especie.css" rel = "StyleSheet" />
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
        <script src="js/inserir-especie.js" type = "text/javascript"></script>
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
                Tanque tanque = Tanque.class.cast(request.getAttribute("tanque"));
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <a href = "AbrirTanques">Tanques</a> &raquo; <a href = "AbrirTanque?tanque=<%= tanque.getId()%>">Tanque <%= tanque.getId()%></a> &raquo; <a href = "AbrirEscolherEspecie">Selecionar Especie</a> &raquo; <span id = "atual">Inserir Espécie</span></div>
        <h1>Inserir Espécie</h1>
        <%
            Especie esp = Especie.class.cast(request.getAttribute("esp"));
            Date data = Date.class.cast(sessao.getAttribute("data"));
            DateFormat dataF = new SimpleDateFormat("dd/MM/yyyy");
        %>
        <form name="inserir" action="ConfirmarInsercaoEspecie" method="get">
            <fieldset>
                <h2>Por favor, faça a valiação da água antes de inserir a espécie</h2>
                <table class="caracteristica">
                    <tr>
                        <td>Horário: </td>
                        <td><input type="text" name="hora" id="hora" class="campo"/></td>
                        <td colspan="3"><input onkeyup="validarData(this)" id="agora" type="button" onclick="getAgora()" value="Agora"/></td>
                    </tr>
                    <tr>
                        <td>Temperatura: </td>
                        <td><input type="text" name="temperatura" id="temp" class="campo" onkeyup="validarDado('temp')"/></td>
                        <td><span id="errotemp"></span></td>
                        <td>Oxigênio: </td>
                        <td><input type="text" name="oxigenio" id="oxi" class="campo" onkeyup="validarDado('oxi')"/></td>
                        <td><span id="errooxi"></span></td>
                    </tr>
                    <tr>
                        <td>Amônia: </td>
                        <td><input type="text" name="amonia" id="amon" class="campo" onkeyup="validarDado('amon')"/></td>
                        <td><span id="erroamon"></span></td>
                        <td>Nitrito: </td>
                        <td><input type="text" name="nitrito" id="nitri" class="campo" onkeyup="validarDado('nitri')"/></td>
                        <td><span id="erronitri"></span></td>
                    </tr>
                    <tr>
                        <td>Nitrato: </td>
                        <td><input type="text" name="nitrato" id="nitra" class="campo" onkeyup="validarDado('nitra')"/></td>
                        <td><span id="erronitra"></span></td>
                        <td>Gás Carbônico: </td>
                        <td><input type="text" name="gasCarb" id="gasCarb" class="campo" onkeyup="validarDado('gasCarb')"/></td>
                        <td><span id="errogasCarb"></span></td>
                    </tr>
                    <tr>
                        <td>Alcalinidade: </td>
                        <td><input type="text" name="alcal" id="alcal" class="campo" onkeyup="validarDado('alcal')"/></td>
                        <td><span id="erroalcal"></span></td>
                        <td>pH: </td>
                        <td><input type="text" name="ph" id="ph"  class="campo" onkeyup="validarDado('ph')"/></td>
                        <td><span id="erroph"></span></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <h2>Dados de Inserção</h2>
                <table class="caracteristica">
                    <tr>
                        <td>Identificador da Especie: </td>
                        <td><input type="text" name="idEspecie" class="campo" value="<%= esp.getId()%>" readonly></td>
                        <td>Identificador do Tanque: </td>
                        <td><input type="text" name="idTanque" class="campo" value="<%= tanque.getId()%>" readonly></td>
                    </tr>
                    <tr>
                        <td>Data: </td>
                        <td><input readonly type="text" name="peixamento" class="campo" value="<%=dataF.format(data)%>" readonly></td>
                        <td>Quantidade: </td>
                        <td><input readonly value="<%= request.getAttribute("qtd") %>" type="text" name="qtd" id="qtd" class="campo" onkeyup="validarNum('qtd')"></td>
                    </tr>
                    <tr>
                        <td>Tamanho (cm): </td>
                        <td><input readonly value="<%= request.getAttribute("tam") %>" type="text" name="tam" id="tam" class="campo" onkeyup="validarNum('tam')"></td>
                        <td>Biomassa (g): </td>
                        <td><input readonly value="<%= request.getAttribute("biomassa") %>" type="text" name="bio" id="bio" class="campo" onkeyup="validarNum('bio')"></td>
                    </tr>
                    <tr>
                        <td>Peso (g): </td>
                        <td><input readonly value="<%= request.getAttribute("peso") %>" type="text" name="peso" id="peso" class="campo" onkeyup="validarNum('peso')"></td>
                        <td>Tipo: </td>
                        <td><input readonly value="<%= request.getAttribute("tipo") %>" type="text" name="tipo" class="campo"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <fieldset>
            <h2><%= esp.getNome()%></h2>
            <div id = "especie">
                <table class = "caracteristica" align = "center">
                    <tbody>
                        <tr>
                            <td class = "dados">Nome científico:</td> 
                            <td id = "nomeCient"><%=esp.getNomeCient()%></td>
                        </tr>
                        <tr>
                            <td class = "dados">Faixa de peso (kg):</td> 
                            <td id = "peso"><%=esp.getPesoMin()%> a <%=esp.getPesoMax()%></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Ração/Dia: </td> 
                            <td id = "racaoDia"><%=esp.getRacaoDia()%></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Frequência alimentar: </td> 
                            <td id = "freqAlim"><%=esp.getFreqAlimMin()%> a <%=esp.getFreqAlimMax()%></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Tamanho médio: </td> 
                            <td id = "tam"><%=esp.getTamMin()%> a <%=esp.getTamMax()%></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Temperatura: </td> 
                            <td id = "temp"><span id="tempMin"><%=esp.getTemperaturaMin()%></span> a <span id="tempMax"><%=esp.getTemperaturaMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Oxigênio (O<sub>2</sub>): </td> 
                            <td id = "oxi"><span id="oxiMin"><%=esp.getOxigenioMin()%></span> a <span id="oxiMax"><%=esp.getOxigenioMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Gás carbônico (CO<sub>2</sub>): </td> 
                            <td id = "gasCarb"><span id="gasCarbMin"><%=esp.getGasCarbonicoMin()%></span> a <span id="gasCarbMax"><%=esp.getGasCarbonicoMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Nitrito </td> 
                            <td id = "nitrito"><span id="nitriMin"><%=esp.getNitritoMin()%></span> a <span id="nitriMax"><%=esp.getNitritoMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Nitrato </td> 
                            <td id = "nitrato"><span id="nitraMin"><%=esp.getNitratoMin()%></span> a <span id="nitraMax"><%=esp.getNitratoMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Alcalinidade </td> 
                            <td id = "alcal"><span id="alcalMin"><%=esp.getAlcalinidadeMin()%></span> a <span id="alcalMax"><%=esp.getAlcalinidadeMax()%></span></td></tr>
                        <tr>
                        <tr>
                            <td class = "dados"> pH da água: </td> 
                            <td id = "ph"><span id="phMin"><%=esp.getPhMin()%></span> a <span id="phMax"><%=esp.getPhMax()%></span></td>
                        </tr>
                        <tr>
                            <td class = "dados"> Amônia da água: </td> 
                            <td id = "amonia"><span id="amonMin"><%=esp.getAmoniaMin()%></span> a <span id="amonMax"><%=esp.getAmoniaMax()%></span></td>
                        </tr>
                    </tbody>  
                </table>
                <img src = <%= esp.getImagem()%> />
            </div>
        </fieldset>
        <fieldset>
            <h2>Tanque <%= tanque.getId()%></h2>
            <table class = "caracteristica" align = "center">
                <tr> <td>Piscicultura:</td> <td colspan="3"><%=tanque.getPiscicultura()%></td> </tr>
                <tr> <td>Tipo:</td> <td colspan="3"><%=tanque.getTipo()%></td> </tr>
                <tr> <td>Material de Revestimento:</td> <td colspan="3"><%=tanque.getMaterial()%></td> </tr>
                <tr> <td>Capacidade (L):</td> <td colspan="3"><%=tanque.getCapacidade()%>L</td> </tr>
                <tr> <td>Vazão (L):</td> <td colspan="3"><%=tanque.getVazao()%></td></tr>
                <tr> <td>Peixamento:</td> <td colspan="3"><%=tanque.getDiaPeixam()%>/<%=tanque.getMesPeixam()%>/<%=tanque.getAnoPeixam()%></td> </tr>
                <tr> <td>Dimensões (CxLxP):</td> <td><%=tanque.getComp()%> x <%=tanque.getLargura()%> x <%=tanque.getProfund()%></td> </tr>
            </table>
        </fieldset>
        <input type="button" onclick="salvar()" value="Confirmar" id = "confirmar"/>
    </body>
</html>
