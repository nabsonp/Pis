<%-- 
    Created on : 14/08/2017, 19:56:34
    Document   : cadastrar-funcionario
    Author     : samsung
--%>

<%@page import="br.com.piscicultech.modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta name = "Content-Type" content = "text/html"; charset = "UTF-8" />
        <link rel="icon" href="icones/icon-peixe.png" type="image/png-icon" />
        <title>Cadastrar Funcionário</title>
        <link href = "css/cadastrar-funcionario.css" rel = "StyleSheet" />
        <link href = "css/menu-rodape.css" rel = "StyleSheet" />
        <link href = "css/botoes-formulario.css" rel = "StyleSheet" />
        <script src="js/cadastrar-funcionario.js"></script>
        <script src="js/menu.js"></script>
    </head>
    <body onload="foco()">
            <div>
                <h1>Cadastro de Funcionário</h1>
                <hr width = 900px/>
            </div>
            <form name = "cadastroFunc" action = "CadastrarFuncionario" method="get">
                <table id = "formCad" align = center>
                    <tr> <td><label>CPF: </label></td> <td><input type = "text" placeholder = "   .   .   -" size = 11 maxlength = 14 name = "cpf" /><label> RG: </label> <input type = "text" size = 8 maxlength = 8 name = "rg" /><label></td> <td colspan = 2> <label> Nome: </label><input type = "text" placeholder = "Nome completo" size = 45 maxlength = 50 name = "nome" /></td></tr>
                    <tr> <td><label>Nascimento: </label></td> <td align = left><input type = "date" name = "dtNasc"/></td><td colspan="2"><label>Sexo: </label><input type = "radio" name = "sexo" value = "M"/> <label>Masculino</label><input type = "radio" name = "sexo" value = "F"/> <label>Feminino</label></td></tr>
                    <tr> <td><label>Email: </label></td> <td colspan="3"><input type = "text" size = 16 maxlength = 50 name = "email"/></td></tr>
                    <tr> <td><label>Senha: </label></td> <td colspan="3"><input type = "password" size = 15 maxlength = 15 name = "senha" /></td></tr>
                    <tr> <td><label>Confirmar Senha: </label></td> <td colspan="3"><input type = "password" size = 15 maxlength = 15 name = "confirmSenha" /></td></tr>
                    <tr> <td><label>Logradouro: </label></td> <td><input type = "text" size = 30 maxlength = 50 name = "rua"/></td><td><label>Bairro: </label><input type = "text" size = 30 maxlength = 50 name = "bairro"/></td><td><label> N°: </label><input type = "text" size = 3 maxlength = 3 name = "num"  id = "num" onkeyup="verificaNumeros('num')" /></td></tr>
                    <tr> <td><label>Cidade: </label></td> <td><input type = "text" size = 20 maxlength = 20 name = "cid" /><label> Estado: </label></td><td colspan = 2><input type = "text" size = 20 maxlength = 50 name = "uf" /><label> CEP: </label><input type = "text" size = 8 maxlength = 8 name = "cep"  id = "cep" onkeyup="verificaNumeros('cep')" /></td></tr>
                    <tr> <td colspan = 4><input type = "file" name = "imagem"></td> </tr>
                    <tr> <td colspan="4" align = "right"><button class = "botoes" onclick = "validar()"><img src = "icones/checked.png" /><span>Salvar</span></button><button type= "reset" class = "botoes" onclick="cancelar()"><img src = "icones/cancelar.png"/><span>Cancelar</span></button></td> </tr>
                </table>
            </form>
    </body>
</html>