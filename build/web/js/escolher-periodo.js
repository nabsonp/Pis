/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarData(elemento) {
    var tamanho;
    var valor = elemento.value;
    if (valor.length === 2 || valor.length === 5) {
        elemento.value = valor.concat("/");
    }
}

function validar() {
    var erro=false;
    var msgErro="";
    
    if (document.pesquisar.inicio.value === "") {
        erro = true;
        msgErro = "Informe uma data de início\n";
    } else {
        var dia = document.pesquisar.inicio.value.slice(0, 2);
        var mes = document.pesquisar.inicio.value.slice(3, 5);
        var ano = document.pesquisar.inicio.value.slice(5);
        if (document.pesquisar.inicio.value.length < 10) {
            erro = true;
            msgErro = msgErro + "Insira os valores do dia, do mês e do ano no campo início\n";
        } else {
            if (parseInt(dia) > 31 || parseInt(dia) < 0 || parseInt(mes) > 12 || parseInt(mes) < 0 || parseInt(ano) < 0) {
                erro = true;
                msgErro = msgErro + "Data de inicio inválida\n";
            }
        }
    }
    
    if (document.pesquisar.inicio.value === "") {
        var erro = true;
        var msgErro = "Informe uma data de fim\n";
    } else {
        var dia = document.pesquisar.fim.value.slice(0, 2);
        var mes = document.pesquisar.fim.value.slice(3, 5);
        var ano = document.pesquisar.fim.value.slice(5);
        if (document.pesquisar.fim.value.length < 10) {
            erro = true;
            msgErro = msgErro + "Insira os valores do dia, do mês e do ano no campo fim\n";
        } else {
            if (parseInt(dia) > 31 || parseInt(dia) < 0 || parseInt(mes) > 12 || parseInt(mes) < 0 || parseInt(ano) < 0) {
                erro = true;
                msgErro = msgErro + "Data de fim inválida\n";
            }
        }
    }
    
    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.pesquisar.submit();
    }
}