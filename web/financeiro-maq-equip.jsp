<%-- 
    Document   : financeiro-maq-equip
    Created on : 06/09/2017, 14:29:26
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.VendaEquipamento"%>
<%@page import="br.com.piscicultech.modelo.Conserto"%>
<%@page import="br.com.piscicultech.modelo.Equipamento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Máquinas e equipamentos</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/financeiro-maq-equip.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script type="text/javascript" src = "js/financeiros.js"></script>
        <script type="text/javascript" src = "js/menu.js"></script>
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirFinanInsumos">Finaneiro</a> » <span id = "atual">Máquinas e equipamentos</span></span>
        </div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/cifrao.png" id = "icone"/></label>
        <div class = "menuLateral">    
            <ul id = "botoes">
                <li class = "titulo">
                    Financeiros
                </li>
                <li>
                    <a href="AbrirFinanInsumos"><button>Insumos</button></a>
                </li>
                <li> 
                    <a href = "AbrirFinanContas"><button>Contas coletivas da fazenda</button></a>
                </li>
                <li>
                    <a href="AbrirFinanPeixes"><button>Peixes</button></a>
                </li>
                <li>
                    <a href="AbrirFinanMaoDeObra"><button>Mão de obra</button></a>
                </li>
                <li>
                    <a href="AbrirFinanMaqEquip"><button id = "current">Máquinas e equipamentos</button></a>
                </li>
                <li>
                    <a href="AbrirFinanImpostos"><button>Impostos, taxas e aluguéis</button></a>
                </li>
                <li>
                    <a href="AbrirFinanVendas"><button>Vendas</button></a>
                </li>
            </ul>
        </div>
        <div id = "corpo">
            <div>
                <span class = "mesAnoDespesas">Mês: 
                    <select class = "comboMeses">
                        <option>Janeiro</option>
                        <option>Fevereiro</option>  
                        <option>Março</option>  
                        <option>Abril</option>  
                        <option>Maio</option>  
                        <option>Junho</option>  
                        <option>Julho</option>  
                        <option>Agosto</option>  
                        <option>Setembro</option>  
                        <option>Outrubro</option>  
                        <option>Novembro</option>  
                        <option>Dezembro</option>  
                    </select>
                </span>
                <span class = "mesAnoDespesas">Ano: 
                    <select class = "comboMeses">
                        <option>2020</option>
                        <option>2019</option>
                        <option>2018</option>
                        <option>2017</option>
                        <option>2016</option>  
                        <option>2015</option>  
                        <option>2014</option>  
                        <option>2013</option>  
                    </select>
                </span>
                <span class = "mesAnoDespesas2">Despesas totais: 
                    <input type="text" disabled name="despesas"  placeholder = "R$" >
                </span>
            </div>
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "combu">Combustíveis e lubrificantes</h2><button title = "Adicionar" class = "adicionar1"></button>
                    <hr>  
                </div>
                <table class = "combu">
                    <thead>
                        <tr><th> Nº </th><th> Nome </th><th> Quantidade </th> <th> Valor(R$/UNI) </th> <th> Valor </th> </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Equipamento> equis = ArrayList.class.cast(request.getAttribute("equis"));
                            ArrayList<Conserto> cons = ArrayList.class.cast(request.getAttribute("cons"));
                            ArrayList<VendaEquipamento> vens = ArrayList.class.cast(request.getAttribute("vens"));
                            String comLub = "";
                            int contComLub = 0;
                            double valor = 0, totalComLub = 0;
                            String consertos = "";
                            int contConsertos = 0;
                            double totalConsertos = 0;
                            String equipa = "";
                            int contEquipa = 0;
                            double totalEquipa = 0;
                            String refIns = "";
                            int contRefIns = 0;
                            double totalRefIns = 0;
                            String nomeEquip = "Indisponível";
                            for (Equipamento e : equis) {
                                for (Conserto c : cons) {
                                    valor = c.getValor();
                                }
                                if (e.getTipo().equals("COMBUSTIVEL") || e.getTipo().equals("LUBRIFICANTE")) {
                                    contComLub++;
                                    totalComLub += valor * e.getQtd();
                                    comLub = comLub + "<tr> <td>" + contComLub + "</td> <td>" + e.getNome() + "</td> <td>" + e.getQtd() + "</td> <td>" + valor + "</td> <td>" + (valor * e.getQtd()) + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td> </tr>";
                                } else {
                                    if (e.getTipo().equals("MAQUINA") || e.getTipo().equals("EQUIPAMENTO")) {
                                        totalEquipa += valor * e.getQtd();
                                        contEquipa++;
                                        equipa = equipa + "<tr> <td>" + contEquipa + "</td> <td>" + e.getNome() + "</td> <td>" + e.getQtd() + "</td> <td>" + valor + "</td> <td>" + (valor * e.getQtd()) + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td> </tr>";
                                    }
                                }
                            }
                            int qtd = 0;
                            for (Conserto c : cons) {
                                for (Equipamento e : equis) {
                                    if (c.getCodEquip() == e.getCodigo()) {
                                        nomeEquip = e.getNome();
                                        qtd = e.getQtd();
                                    }
                                }
                                if (c.getTipo().equals("REVISAO") || c.getTipo().equals("CONSERTO")) {
                                    totalConsertos += c.getValor() * qtd;
                                    contConsertos++;
                                    consertos = consertos + "<tr> <td>" + contConsertos + "</td> <td>" + nomeEquip + "</td> <td>" + c.getInicio() + "</td> <td>" + c.getHora() + "</td> <td>" + (qtd * c.getValor()) + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td> </tr>";
                                } else {
                                    if (c.getTipo().equals("REFORMA")) {
                                        totalRefIns += c.getValor() * qtd;
                                        contRefIns++;
                                        refIns = refIns + "<tr> <td>" + contRefIns + "</td> <td>" + nomeEquip + "</td> <td>" + c.getInicio() + "</td> <td>" + c.getFim() + "</td> <td>" + (qtd * c.getValor()) + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td> </tr>";
                                    }
                                }
                            }
                            out.println(comLub);
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td> TOTAL </td> <td colspan="3"> - </td> <td> <%= totalComLub %> </td> <td class = "btts">aaaaaaa</td> </tr>
                    </tfoot>
                </table>
            </div>
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "revisoes">Revisões e consertos</h2><button title="Adicionar" class = "adicionar2"></button>
                    <hr>  
                </div>
                <table class = "revisoes">
                    <thead>
                        <tr><th> Nº </th> <th> Descrição </th> <th> Data(dd/mm/yyyy) </th> <th> Hora(hh:mm) </th> <th> Valor(R$) </th> </tr>
                    </thead>
                    <tbody>
                        <%= consertos%>
                    </tbody>
                    <tfoot>
                        <tr> <td> TOTAL </td> <td colspan="3"> - </td> <td><%= totalConsertos %></td> <td class = "btts">aaaaaaa</td> </tr>
                    </tfoot>
                </table>
            </div>
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "maquinas">Máquinas e equipamentos</h2><button title="Adicionar" class = "adicionar3"></button>
                    <hr>  
                </div>
                <table class = "maquinas">
                    <thead>
                        <tr> <th> Nº </th> <th> Nome </th> <th> Quantidade </th> <th> Valor(R$/UNI) </th> <th> Valor </th> </tr>
                    </thead> 
                    <tbody>
                        <%= equipa%>
                    </tbody>
                    <tfoot>
                        <tr> <td> TOTAL </td> <td colspan="3"> - </td> <td> <%= totalEquipa %> </td> <td class = "btts">aaaaaaa</td> </tr>
                    </tfoot>
                </table>
            </div>
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "reformas">Reformas de instalações</h2><button class = "adicionar4" title="Adicionar"></button>
                    <hr>  
                </div>
                <table class = "reformas">
                    <thead>
                        <tr> <th> Nº </th> <th> Descrição </th> <th> Início(dd/mm/yyyy) </th> <th> Término(dd/mm/yyyy) </th> <th> Valor(R$) </th> </tr>
                    </thead> 
                    <tbody>
                        <%= refIns%>
                    </tbody>
                    <tfoot>
                        <tr> <td> TOTAL </td> <td colspan="3"> - </td> <td><%= totalRefIns %></td> <td class = "btts">aaaaaaa</td> </tr>
                    </tfoot>
                </table>
            </div>
            <div id = "topo"><a href = "#menu">Voltar ao Topo<img src = "icones/seta-para-cima-preta.png"/></a></div>
        </div>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "../Icones/logotipo.png" width = 110>
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
