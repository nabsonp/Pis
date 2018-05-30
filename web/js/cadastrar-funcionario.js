/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function foco(){
    document.cadastroFunc.cpf.focus();
}

function validar(){
    situacao = true;
    if (document.cadastroFunc.cpf.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.rg.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.nome.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.cpf.value[0] == false && document.cadastroFunc.cpf.value[1] == false) {
        situacao = false;
    }
    
    if (document.cadastroFunc.dtNasc.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.email.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.senha.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.confirmSenha.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.num.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.bairro.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.cid.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.uf.value == "") {
        situacao = false;
    }
    
    if (document.cadastroFunc.cep.value == "") {
        situacao = false;
    }
    
    if  (situacao){
        alert("Cadastro realizado com sucesso!");
    } else{
        alert("Preencha todos os campos.");
    }
}

function verificaNumeros(id){
    var tamanho;
    var elemento = document.getElementById(id);
    if (isNaN(elemento.value)){
        tamanho = elemento.value.length;
        elemento.value = elemento.value.substring(0, tamanho-1);
    }
}

function cancelar(){
    window.open('/Login.jsp', '_self');
}