<%-- 
    Document   : financeiro-insumos
    Created on : 26/08/2017, 12:00:13
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Empresa"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.piscicultech.modelo.VendaRacao"%>
<%@page import="br.com.piscicultech.modelo.VendaQuimico"%>
<%@page import="br.com.piscicultech.modelo.Quimico"%>
<%@page import="br.com.piscicultech.modelo.Racao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <title>Insumos</title>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link rel = "icon" href = "icones/icon-peixe.png" type = "image/png-icon">
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <link href = "css/financeiro-insumos.css" rel = "StyleSheet" />
        <link href = "css/navegacao.css" rel = "StyleSheet" />
        <script type="text/javascript" src = "js/financeiros.js"></script>
        <script type="text/javascript" src = "js/financeiro-insumos.js"></script>
        <script type="text/javascript" src = "js/menu.js"></script>
        <link href = "css/modal.css" rel = "StyleSheet" />
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
            <span class = "navegacao"><a href = "Home.jsp">Página Inicial</a> » <a href = "AbrirFinanInsumos">Finaneiro</a> » <span id = "atual">Insumos</span>
        </div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/cifrao.png" id = "icone"/></label>
        <div class = "menuLateral">    
            <ul id = "botoes">
                <li class = "titulo">
                    Financeiros
                </li>
                <li>
                    <a href="AbrirFinanInsumos"><button id = "current">Insumos</button></a>
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
                    <a href="AbrirFinanImpostos"><button>Impostos, taxas e aluguéis</button></a>
                </li>
                <li>
                    <a href="AbrirFinanVendas"><button>Vendas</button></a>
                </li>
            </ul>
        </div>
        <div class = "menuLateralT">
            <ul id = "botoes">
                <%
                    DateFormat dia = new SimpleDateFormat("dd");
                    DateFormat mes = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
                    DateFormat ano = new SimpleDateFormat("yy");
                    DateFormat dataF = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date data = new java.util.Date(Calendar.getInstance().getTimeInMillis());
                    java.util.Date prim = new java.util.Date(Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao().getTime());
                %>
                <li class = "titulo">
                    Calendário
                </li>
                <li>
                    <%
                        if (prim != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(data);
                            String atual = dataF.format(java.util.Date.class.cast(sessao.getAttribute("data")));
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            for (java.util.Date dt = data; dt.after(prim);) {
                                if (atual.equals(dataF.format(dt))) {
                                    out.println("<button id = \"current\" name=\"" + df.format(dt) + "\" onclick = \"abrirFinan('Insumos' ,this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                } else {
                                    out.println("<button name=\"" + df.format(dt) + "\" onclick = \"abrirFinan('Insumos' ,this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                }
                                cal.add(Calendar.DATE, -1);
                                dt = cal.getTime();
                                dataF.format(dt);
                            }
                        }
                    %>
                </li>
            </ul>
        </div>
        <div id = "corpo">
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "racoes">Rações e Alimentos diversos</h2><button onclick="cadastrar('racao')" title = "Comprar ração" class = "adicionar1"></button>
                    <hr>  
                </div>
                <table class = "racao" align="center">
                    <thead>
                        <tr class = "titulo"> <th>Nº</th> <th>Nome</th> <th>Tipo</th>  <th>Fornecedor</th> <th>Preço (R$)</th> <th>Data de Compra</th> <th>Peso unitário</th> <th> Peso Total</th> </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Racao> racs = ArrayList.class.cast(request.getAttribute("racs"));
                            ArrayList<VendaRacao> vendRacao = ArrayList.class.cast(request.getAttribute("vendaRacao"));
                            ArrayList<VendaQuimico> vendQuim = ArrayList.class.cast(request.getAttribute("vendaQuim"));
                            int numRac = 0;
                            if (vendRacao.isEmpty()) {
                                out.println("<tr><td colspan=\"8\">Vazio</td></tr>");
                                out.println("</table>");
                            } else {
                                for (VendaRacao vendRac : vendRacao) {
                                    Racao rac = new Racao();
                                    for (Racao r : racs) {
                                        if (vendRac.getCodRacao() == r.getCodigo()) {
                                            rac = r;
                                        }
                                    }
                                    numRac++;
                                    out.println("<tr> <td>" + numRac + "</td> <td>" + rac.getNome() + "</td> <td>Ração</td><td>" + vendRac.getCodForn() + "</td> <td>" + vendRac.getValorUni() + "</td> <td>" + vendRac.getData() + "</td> <td>" + vendRac.getPesoUni() + "</td>  <td>" + rac.getPesoTotal() + "</td> </tr>");
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class = "tituloETabela">
                <div class = "tituloBotao">
                    <h2 class = "produtos">Produtos químicos</h2><button onclick="cadastrar('quimico')" class = "adicionar1" title = "Comprar produto químico"></button>
                    <hr>
                </div>
                <table align="center" class = "racao">
                    <thead>
                        <tr> <th> Nº </th> <th> Nome do produto </th> <th> Quantidade </th> <th> Valor(UNI) </th> <th> Peso(UNI) </th> <th> Fornecedor </th> <th> Data da compra(Dia) </th> </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Quimico> quis = ArrayList.class.cast(request.getAttribute("quis"));
                            int numQ = 0;
                            if (vendQuim.isEmpty()) {
                                out.println("<tr><td colspan=\"8\">Vazio</td></tr>");
                                out.println("</table>");
                            } else {
                                for (VendaQuimico vendQui : vendQuim) {
                                    Quimico qui = new Quimico();
                                    for (Quimico q : quis) {
                                        if (vendQui.getCodQuim() == q.getCodigo()) {
                                            qui = q;
                                        }
                                    }
                                    numQ++;
                                    out.println("<tr> <td>" + qui.getNome() + "</td> <td>Químico</td> <td>" + vendQui.getCodForn() + "</td> <td>" + vendQui.getValorUni() + "</td> <td>" + vendQui.getDia() + "</td> <td>" + vendQui.getPesoUni() + "</td> <td>" + qui.getPesoTotal() + "</td> </tr>");
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>