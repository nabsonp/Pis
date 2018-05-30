<%-- 
    Document   : estoque
    Created on : 26/08/2017, 09:05:52
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Empresa"%>
<%@page import="br.com.piscicultech.modelo.VendaEquipamento"%>
<%@page import="br.com.piscicultech.modelo.VendaQuimico"%>
<%@page import="br.com.piscicultech.modelo.VendaRacao"%>
<%@page import="br.com.piscicultech.modelo.Equipamento"%>
<%@page import="br.com.piscicultech.modelo.Quimico"%>
<%@page import="br.com.piscicultech.modelo.Racao"%>
<%@page import="br.com.piscicultech.modelo.TanqEsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.piscicultech.modelo.Especie"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Estoque</title>
        <link href = "css/estoque.css" rel = "StyleSheet" />
        <script src="js/estoque.js"></script>
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/menu-lateral.css" rel = "StyleSheet" />
        <script src="js/menu.js"></script>
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
                <li class = "current">
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
        <div id = "navega"><a href = "Home.jsp">Página Inicial</a> &raquo; <span id = "atual">Estoque</span></div>
        <input type = "checkbox" id = "check"><label for = "check" id = "lab"><img src="icones/calendario.png" id = "icone"/></label>
        <div class = "menuLateral">
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
                                    out.println("<button id = \"current\" name=\"" + df.format(dt) + "\" onclick = \"abrirDia(this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
                                } else {
                                    out.println("<button name=\"" + df.format(dt) + "\" onclick = \"abrirDia(this)\">" + dia.format(dt) + " de " + mes.format(dt) + " de " + ano.format(dt) + "</button>");
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
            <div class = "estoque">
                <span class = "titulo"><h2 align="center">Peixes</h2></span><hr>
                <table class = "dados" align="center">
                    <tr class = "titulo"> <td>Espécie</td> <td>Quantidade</td> <td>Tanque</td> </tr>
                    <%
                        ArrayList<Especie> esps = ArrayList.class.cast(request.getAttribute("esps"));
                        ArrayList<TanqEsp> tes = ArrayList.class.cast(request.getAttribute("tes"));
                        int qtd = 0, tanque = 0;
                        for (Especie e : esps) {
                            for (TanqEsp te : tes) {
                                if (e.getId() == te.getIdEspecie()) {
                                    qtd += te.getQtd();
                                    tanque = te.getIdTanque();
                                }
                            }
                            out.print("<tr> <td>" + e.getNome() + "</td> <td>" + qtd + "</td> <td>" + tanque + "</td> </tr>");
                            qtd = 0;
                            tanque = 0;
                        }
                    %>
                </table>
            </div>
            <div class = "estoque">
                <span class = "titulo"><h2 align="center">Insumos</h2></span><hr>
                <table class = "dados" align="center">
                    <tr class = "titulo"> <td>Nome</td> <td>Tipo</td>  <td>Fornecedor</td> <td>Preço (R$)</td> <td>Data de Compra</td> <td>Peso unitário</td> <td> Peso Total</td> </tr>
                    <%
                        ArrayList<Racao> racs = ArrayList.class.cast(request.getAttribute("racs"));
                        ArrayList<Quimico> quis = ArrayList.class.cast(request.getAttribute("quis"));
                        ArrayList<VendaRacao> vendRacao = ArrayList.class.cast(request.getAttribute("vendaRacao"));
                        ArrayList<VendaQuimico> vendQuim = ArrayList.class.cast(request.getAttribute("vendaQuim"));
                        if (vendRacao.isEmpty() && vendQuim.isEmpty()) {
                            out.println("<tr><td colspan=\"7\">Vazio</td></tr>");
                            out.println("</table>");
                        } else {
                            for (VendaRacao vendRac : vendRacao) {
                                Racao rac = new Racao();
                                for (Racao r : racs) {
                                    if (vendRac.getCodRacao() == r.getCodigo()) {
                                        rac = r;
                                    }
                                }
                                out.println("<tr> <td>" + rac.getNome() + "</td> <td>Ração</td><td>" + vendRac.getCodForn() + "</td> <td>" + vendRac.getValorUni() + "</td> <td>" + vendRac.getData() + "</td> <td>" + vendRac.getPesoUni() + "</td>  <td>" + rac.getPesoTotal() + "</td> </tr>");
                            }
                            for (VendaQuimico vendQui : vendQuim) {
                                Quimico qui = new Quimico();
                                for (Quimico q : quis) {
                                    if (vendQui.getCodQuim() == q.getCodigo()) {
                                        qui = q;
                                    }
                                }
                                out.println("<tr> <td>" + qui.getNome() + "</td> <td>Químico</td> <td>" + vendQui.getCodForn() + "</td> <td>" + vendQui.getValorUni() + "</td> <td>" + vendQui.getDia() + "</td> <td>" + vendQui.getPesoUni() + "</td> <td>" + qui.getPesoTotal() + "</td> </tr>");
                            }
                            out.println("</table>");
                        }
                    %>
            </div>
            <div class = "estoque">
                <span class = "titulo"><h2 align="center">Equipamentos, Máquinas e Combustíveis</h2>
                    </span><hr>
                <table class = "dados" align="center">
                    <tr class = "titulo"> <td>Nome</td> <td>Tipo</td> <td>Fornecedor</td> <td>Compra</td> <td>Preço (R$)</td> <td>Quantidade</td> </tr>
                    <%
                        ArrayList<Equipamento> equis = ArrayList.class.cast(request.getAttribute("equis"));
                        ArrayList<VendaEquipamento> vendEquis = ArrayList.class.cast(request.getAttribute("vendaEqui"));
                        if (vendEquis.isEmpty()) {
                            out.println("<tr><td colspan=\"6\">Vazio</td></tr>");
                            out.println("</table>");
                        } else {
                            for (VendaEquipamento vendEq : vendEquis) {
                                Equipamento eq = new Equipamento();
                                for (Equipamento e : equis) {
                                    if (vendEq.getCodEquip() == e.getCodigo()) {
                                        eq = e;
                                    }
                                }
                                out.println("<tr> <td>" + eq.getNome() + "</td> <td>" + eq.getTipo() + "</td>  <td>" + vendEq.getCodForn() + "</td> <td>" + vendEq.getDtVenda() + "</td> <td>" + vendEq.getValorUni() + "</td> <td>" + eq.getQtd() + "</td> </tr>");
                            }
                            out.println("</table>");
                        }
                    %>

            </div>            
        </div>
        <footer id = "rodape">
            <span id = "logoRodape">
                <img src = "icones/logotipo.png" width = 110>
            </span>
            <table id = "links" align = center>
                <tr class = "titulo"> <td><a href = "../diversos/pagina-inicial.html">Página Inicial</a></td> <td><a href = "../financeiro/financeiro-insumos.html">Financeiro</a></td> <td><a href = "../tanques/tanques.html">Tanques</a></td> <td><a href = "../especie/especies.html">Espécies</a></td> <td><a href = "../estoque/estoque.html">Estoque</a></td> <td><a href = "../relatorios/rel-financeiros.html">Relatórios</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-insumos.html">Insumos</a></td> <td><a href = "../tanques/cadastrar-tanque.html">Cadastrar Tanque</a></td> <td><a href = "../especie/cadastro-especie.html">Cadastrar Espécies</a></td> <td></td> <td><a href = "../relatorios/rel-financeiros.html">Financeiros</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-contas-coletivas.html">Contas coletivas da fazenda</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-tanques.html">Tanques</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-mao-de-obra.html">Mão de obra</a></td> <td></td> <td></td> <td></td> <td><a href = "../relatorios/rel-especies.html">Espécies</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-maquinas-equipamentos.html">Máquinas e equipamentos</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-impostos.html">Impostos, taxas e aluguéis</a></td> </tr>
                <tr> <td></td> <td><a href = "../financeiro/financeiro-vendas.html">Vendas</a></td> </tr>
            </table>
        </footer>
    </body>
</html>