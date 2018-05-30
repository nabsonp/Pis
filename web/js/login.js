function validar(){
    situacao = true;
    if (document.login.email.value == ""){
        situacao = false;
    }
    
    if (document.login.senha.value == ""){
        situacao = false;
    }
    
    if (!situacao){
        alert("Preencha todos os campos.");
    }
}

function foco(){
    document.login.email.focus();  
}