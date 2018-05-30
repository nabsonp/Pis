/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getAgora(){
    var data = new Date();
    document.getElementById("hora").value = (data.getHours()+1) + ":" + (data.getMinutes()+1) + ":" + (data.getSeconds()+1);
}

function validarNum(id) {
    var tamanho;
    var elemento = document.getElementById(id);
    if (isNaN(elemento.value)) {
        tamanho = elemento.value.length;
        elemento.value = elemento.value.substring(0, tamanho - 1);
    }
}

var erro = false;

function validarDado(dado) {
    validarNum(dado);
    if (parseFloat(document.getElementById(dado).value) < document.getElementById(dado + 'Min').textContent) {
        document.getElementById('erro' + dado).innerHTML = "<img width=\"20\" src = \"icones/exclamacao.png\" title=\"Valor abaixo do requerido pela espécie.\">";
        document.getElementById(dado).style.backgroundColor = "red";
    } else {
        document.getElementById('erro' + dado).innerHTML = "";
        document.getElementById(dado).style.backgroundColor = "white";
        if (parseFloat(document.getElementById(dado).value) > document.getElementById(dado + 'Max').textContent) {
            document.getElementById('erro' + dado).innerHTML = "<img width=\"20\" src = \"icones/exclamacao.png\" title=\"Valor acima do requerido pela espécie.\">";
            document.getElementById(dado).style.backgroundColor = "red";
        } else {
            document.getElementById('erro' + dado).innerHTML = "";
            document.getElementById(dado).style.backgroundColor = "white";
        }
    }

}

function salvar() {
    if (document.getElementById('errotemp').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('errooxi').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('erroamon').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('erronitri').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('erronitra').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('errogasCarb').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('erroalcal').textContent !== "") {
        erro = true;
    }
    
    if (document.getElementById('erroph').textContent !== "") {
        erro = true;
    }
    
    if (document.inserir.peixamento.value === "") {
        erro = true;
    }

    if (document.inserir.qtd.value === "") {
        erro = true;
    }

    if (document.inserir.tam.value === "") {
        erro = true;
    }

    if (document.inserir.peso.value === "") {
        erro = true;
    }

    if (document.inserir.bio.value === "") {
        erro = true;
    }

    if (document.inserir.hora.value === "") {
        erro = true;
    }

    if (document.inserir.temp.value === "") {
        erro = true;
    }

    if (document.inserir.oxi.value === "") {
        erro = true;
    }

    if (document.inserir.amon.value === "") {
        erro = true;
    }

    if (document.inserir.nitri.value === "") {
        erro = true;
    }

    if (document.inserir.nitra.value === "") {
        erro = true;
    }

    if (document.inserir.gasCarb.value === "") {
        erro = true;
    }

    if (document.inserir.alcal.value === "") {
        erro = true;
    }

    if (document.inserir.ph.value === "") {
        erro = true;
    }

    if (erro) {
        alert('Você não preencheu todos os campos ou não percebeu que a água não está favorável para a espécie por algum aspecto.');
        erro = false;
    } else {
        document.inserir.submit();
    }
}

