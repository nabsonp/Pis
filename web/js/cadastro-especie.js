function foco(){	
	document.informacoes.nomePopular.focus();
}

function salvar(){
    situacao = true;
    if (document.informacoes.nomePopular.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.nomeCientifico.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.faixaDePeso.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.biomassa.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.racaoPorDia.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.frequenciaAlimentar.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.tamanhoMedio.value == ""){
        situacao = false;
    }
    
    if (document.informacoes.larguraMedia.value == ""){
       situacao = false;    
    }

    if (document.condicoes.luminosidade.value == "") {
       situacao = false;
    }

    if (document.condicoes.oxigenio.value == "") {
       situacao = false;
    }

    if (document.condicoes.ph.value == "") {
       situacao = false;
    }

    if (document.condicoes.gasCarbonico.value == "") {
       situacao = false;
    }
    
    if (situacao) {
        alert("Cadastro realizado com sucesso.");
        window.location.reload();
    }else{
        alert("Preencha todos os campos.");
        foco();
    }
}

function cancelar(){
    window.open('AbrirEspecies', '_self')
}

function verificaNumeros(id){
    var tamanho;
    var elemento = document.getElementById(id);
    if (isNaN(elemento.value)){
        tamanho = elemento.value.length;
        elemento.value = elemento.value.substring(0, tamanho-1);
    }
}