var barraAberta = false;
var busca;
var situacao;

function pesquisar(){
    busca = document.getElementById("pesquisar");
    
    if (!barraAberta) { 
        busca.style.width = "200px";
        busca.focus();
        barraAberta = true;
    }else{
        if (busca.value == "") {
            busca.style.width = "0px";
            barraAberta = false;
        }else{
            if (busca.value == "Tambaqui") {
                situacao = true;
            window.open("../pesquisa/resultado.html");
            }else{
                situacao = false;
            window.open("../pesquisa/resultado.html");
            }
        }
    }
}

function sair(){
    if (confirm("Sair da conta?")){
        window.open("Sair", "_self");
    }
}

function abrirContaFunc() {
    window.open("AbrirContaFunc", "_self");
}

function abrirContaEmp() {
    window.open("AbrirContaEmpresa", "_self");
}