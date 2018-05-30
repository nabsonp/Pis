<%-- 
    Document   : financeiro-mao-de-obra
    Created on : 06/09/2017, 14:15:05
    Author     : samsung
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="br.com.piscicultech.modelo.Cargo"%>
<%@page import="br.com.piscicultech.modelo.Exercao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Mão de obra</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/financeiro-mao-de-obra.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirFinanInsumos">Finaneiro</a> » <span id = "atual">Mão de obra</span></span>
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
                    <a href="AbrirFinanMaoDeObra"><button id = "current">Mão de obra</button></a>
                </li>
                <li>
                    <a href="AbrirFinanMaqEquip"><button>Máquinas e equipamentos</button></a>
                </li>
                <li>
                    <a href="AbrirFinanImpostos"><button>Impostos, taxas e aluguéis</button></a>
                </li>
                <li>
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
            <div id ="tituloETabela">
                <div class = "tituloBotao">
                    <h2>Mão de obra</h2><button onclick="adicionar()" class = "adicionar" title = "Adicionar"></button>
                    <hr>  
                </div>
                <table class = "mao-de-obra">
                    <thead>
                        <tr> <th>Nº</th>  <th>Nome</th><th>Cargo</th><th>Função</th><th>Início</th> <th>Salário</th> </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Funcionario> funcs = ArrayList.class.cast(request.getAttribute("funcs"));
                            ArrayList<Exercao> exs = ArrayList.class.cast(request.getAttribute("exs"));
                            ArrayList<Cargo> cars = ArrayList.class.cast(request.getAttribute("cars"));
                            int cont = 1;
                            float total = 0;
                            String cargo = "Indisponível", funcao = "Indisponível";
                            double salario = 0.0;
                            Date inicio = null;
                            DateFormat dia = null, mes = null, ano = null;
                            for (Funcionario f : funcs) {
                                for (Exercao e : exs) {
                                    if (e.getCpfFunc().equals(f.getCpf())) {
                                        for (Cargo c : cars) {
                                            if (c.getCodigo() == e.getCodCargo()) {
                                                funcao = c.getDescricao();
                                                cargo = c.getNome();
                                                salario = c.getPagamento();
                                                total += c.getPagamento();
                                                inicio = e.getInicio();
                                                dia = new SimpleDateFormat("dd");
                                                mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
                                                ano = new SimpleDateFormat("yy");
                                            }
                                        }
                                    }
                                }
                                out.println("<tr><td>" + cont + "</td> <td>" + f.getNome() + "</td> <td>" + cargo + "</td> <td>" + funcao + "</td> <td>" + dia.format(inicio) + " de " + mes.format(inicio) + " de " + ano.format(inicio) + "</td> <td>" + salario + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td></tr> ");
                                cont++;
                            }
                        %>
                        <tr>

                        </tr>
                    </tbody>
                    <tfoot>
                        <tr> <td> TOTAL </td> <td colspan = "4"> - </td> <td><%= total%></td> </tr>
                    </tfoot>
                </table>
            </div>
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
