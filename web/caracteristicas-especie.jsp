<%-- 
    Document   : caracteristicas-especie
    Created on : 15/08/2017, 18:32:04
    Author     : samsung
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Características</title>
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/caracteristicas-especie.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <script type="text/javascript" src = "js/menu.js"></script>
        <script type="text/javascript" src = "js/especies.js"></script>
        <script type="text/javascript" src = "js/caracteristicas-especie.js"></script>
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
                Especie esp = Especie.class.cast(request.getAttribute("esp"));
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirEspecies">Espécies</a> » <span id = "atual"><%=esp.getNome()%></span>
        </div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/peixe.png" id = "icone"/></label>
        <div class = "menuLateral">
            <ul id = "botoes">
                <li class = "titulo">
                    Espécies
                </li>
                <%
                    ArrayList<Especie> esps = ArrayList.class.cast(request.getAttribute("esps"));
                    String atual = "";
                    int cont = 0;
                    for (Especie e : esps) {
                        cont++;
                        if (cont < 10) {
                            if (e.getId() == esp.getId()) {
                                atual = "id = \"current\"";
                            } else {
                                atual = "";
                            }
                            out.println("<li>"
                                    + "<button " + atual + " onclick = \"abrirEspecie(" + e.getId() + ")\">" + e.getNome() + "</button>"
                                    + "</li>");
                        } else {
                            out.println("<li>"
                                    + "<button onclick = \"abrirEspecies()\">Ver mais...</button>"
                                    + "</li>");
                        }
                    }
                %>
            </ul>
        </div>
        <div id = "corpo">
            <h2><%=esp.getNome()%></h2><hr>
            <img id = "imagem" src = <%=esp.getImagem()%>>
            <table id = "caracteristica">
                <thead>
                    <tr>
                        <th class = "dadoss"> Dados </th> 
                        <th> Valor </th>
                    </tr>
                </thead> 
                <tbody>
                    <tr>
                        <td class = "dados">Nome científico</td> 
                        <td id = "nomeCient"><%=esp.getNomeCient()%></td>
                        <td class = "btts" id = "nomeCientBtts"><button><img onclick = "tornarEditavel('nomeCient', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados">Faixa de peso (kg)</td> 
                        <td id = "peso"><%=esp.getPesoMin()%> a <%=esp.getPesoMax()%></td>
                        <td class = "btts" id = "pesoBtts"><button><img onclick = "tornarEditaveis('peso', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Ração/Dia </td> 
                        <td id = "racaoDia"><%=esp.getRacaoDia()%></td>
                        <td class = "btts" id = "racaoDiaBtts"><button><img onclick = "tornarEditavel('racaoDia', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Frequência alimentar </td> 
                        <td id = "freqAlim"><%=esp.getFreqAlimMin()%> a <%=esp.getFreqAlimMax()%></td>
                        <td class = "btts" id = "freqAlimBtts"><button><img onclick = "tornarEditaveis('freqAlim', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Tamanho médio </td> 
                        <td id = "tam"><%=esp.getTamMin()%> a <%=esp.getTamMax()%></td>
                        <td class = "btts" id = "tamBtts"><button><img onclick = "tornarEditaveis('tam', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Temperatura </td> 
                        <td id = "temp"><span id="tempMin"><%=esp.getTemperaturaMin()%></span> a <span id="tempMax"><%=esp.getTemperaturaMax()%></span></td>
                        <td class = "btts" id = "tempBtts"><button><img onclick = "tornarEditaveis('temp', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Oxigênio (O<sub>2</sub>) </td> 
                        <td id = "oxi"><span id="oxiMin"><%=esp.getOxigenioMin()%></span> a <span id="oxiMax"><%=esp.getOxigenioMax()%></span></td>
                        <td class = "btts" id = "oxiBtts"><button><img onclick = "tornarEditaveis('oxi', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Gás carbônico (CO<sub>2</sub>) </td> 
                        <td id = "gasCarb"><span id="gasCarbMin"><%=esp.getGasCarbonicoMin()%></span> a <span id="gasCarbMax"><%=esp.getGasCarbonicoMax()%></span></td>
                        <td class = "btts" id = "gasCarbBtts"><button><img onclick = "tornarEditaveis('gasCarb', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Nitrito </td> 
                        <td id = "nitrito"><span id="nitriMin"><%=esp.getNitritoMin()%></span> a <span id="nitriMax"><%=esp.getNitritoMax()%></span></td>
                        <td class = "btts" id = "nitritoBtts"><button><img onclick = "tornarEditaveis('nitrito', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Nitrato </td> 
                        <td id = "nitrato"><span id="nitraMin"><%=esp.getNitratoMin()%></span> a <span id="nitraMin"><%=esp.getNitratoMax()%></span></td>
                        <td class = "btts" id = "nitratoBtts"><button><img onclick = "tornarEditaveis('nitrato', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                        <tr>
                            <td class = "dados"> Alcalinidade </td> 
                            <td id = "alcal"><span id="alcalMin"><%=esp.getAlcalinidadeMin()%></span> a <span id="alcalMax"><%=esp.getAlcalinidadeMax()%></span></td>
                        <td class = "btts" id = "alcalBtts"><button><img onclick = "tornarEditaveis('alcal', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                        <tr>
                    <tr>
                        <td class = "dados"> pH da água </td> 
                        <td id = "ph"><span id="phMin"><%=esp.getPhMin()%></span> a <span id="phMax"><%=esp.getPhMax()%></span></td>
                        <td class = "btts" id = "phBtts"><button><img onclick = "tornarEditaveis('ph', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                    <tr>
                        <td class = "dados"> Amônia da água </td> 
                        <td id = "amonia"><span id="amonMin"><%=esp.getAmoniaMin()%></span> a <span id="amonMax"><%=esp.getAmoniaMax()%></span></td>
                        <td class = "btts" id = "amoniaBtts"><button><img onclick = "tornarEditaveis('amonia', <%=esp.getId()%>)" src = "icones/editar.png" title = "Editar"/></button></td> 
                    </tr>
                </tbody>  
            </table>
            <input id = "excluir" type = "button" onclick="excluir(<%=esp.getId()%>)" value="Excluir <%=esp.getNome()%>"/>
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