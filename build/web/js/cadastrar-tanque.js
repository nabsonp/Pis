function foco(){
    document.cadastroTanque.tipo.focus();
}

function salvar(){
    situacao = true;
    if (document.cadastroTanque.id.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.tipo.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.revest.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.cap.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.mat.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.vazao.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.comp.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.larg.value == "") {
        situacao = false;
    }
    
    if (document.cadastroTanque.alt.value == "") {
        situacao = false;
    }
    
    if  (situacao){
        alert("Cadastro realizado com sucesso!");
    } else{
        alert("Preencha todos os campos.");
    }
}

function cancelar(){
    window.open('AbrirTanques', '_self');
}

function verificaNumeros(id){
    var tamanho;
    var elemento = document.getElementById(id);
    if (isNaN(elemento.value)){
        tamanho = elemento.value.length;
        elemento.value = elemento.value.substring(0, tamanho-1);
    }
}

function adicionar(){
    
}