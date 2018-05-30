<%-- 
    Document   : financeiro-impostos
    Created on : 06/09/2017, 14:40:38
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.PagamentoImpTaxAlu"%>
<%@page import="br.com.piscicultech.modelo.ImpTaxAlu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Impostos</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/financeiro-impostos.css" rel = "StyleSheet" />
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirFinanInsumos">Finaneiro</a> » <span id = "atual">Impostos, taxas e aluguéis</span>
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
                    <a href="AbrirFinanMaqEquip"><button>Máquinas e equipamentos</button></a>
                </li>
                <li>
                    <a href="AbrirFinanImpostos"><button id = "current">Impostos, taxas e aluguéis</button></a>
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
            <div id = "tituloETabela">
                <div class = "tituloBotao">
                    <h2>Impostos, taxas e aluguéis</h2><button title = "Adicionar" class = "adicionar"></button>
                    <hr>  
                </div>
                <table class = "impostos">
                    <thead>
                        <tr> <th> Nº </th> <th> Descrição </th> <th> Vencimento </th> <th> Valor </th> <th> Situação </th>  </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<ImpTaxAlu> itas = ArrayList.class.cast(request.getAttribute("itas"));
                            ArrayList<PagamentoImpTaxAlu> pitas = ArrayList.class.cast(request.getAttribute("pitas"));
                            int cont = 0;
                            double valor = 0, total = 0;
                            for (ImpTaxAlu ita : itas) {
                                for (PagamentoImpTaxAlu pita : pitas) {
                                    if (ita.getCodigo() == pita.getCodITA()) {
                                        valor = pita.getValor();
                                        total += valor;
                                    }
                                }
                                cont++;
                                out.println("<tr> <td>" + cont + "</td> <td>" + ita.getDescricao() + "</td> <td>" + ita.getVencimento() + "</td> <td>" + ita.getValor() + "</td> <td>" + ita.getSituacao() + "</td> <td class = \"btts\"><button><img  src = \"icones/lixeira.png\" title = \"Excluir\"/></button><button><img  src = \"icones/editar.png\" title = \"Editar\"/></button></td> </tr>");
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr> <td> TOTAL </td> <td colspan="3"> - </td> <td> <%= total %> </td> <td class = "btts"> aaaaaaa </td> </tr>
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
