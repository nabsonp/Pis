/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarNum(elemento) {
    var tamanho;
    if (isNaN(elemento.value)) {
        tamanho = elemento.value.length;
        elemento.value = elemento.value.substring(0, tamanho - 1);
    }
}

function validar() {
    
    erro = false, msgErro = "";
    
    if (document.comp.qtd.value === "") {
        erro = true;
        msgErro = msgErro + "Informe a quantidade de peixes.\n";
    }
    
    if (document.comp.tamanhoMedio.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o o tamanho médio dos peixes.\n";
    }
    
    if (document.comp.pesoMedio.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o peso médio dos peixes.\n";
    }
    
    if (document.comp.valorUni.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor unitário.\n";
    }
    
    if (document.comp.total.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor total da compra.\n";
    }
    
    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.comp.submit();
    }
    
}