function abrirTanque(id) {
    window.open('AbrirTanque?tanque=' + id, '_self');
}

var data = new Date;

function abrirDia(data) {
    window.open("AbrirTanques?data="+data.name, "_self");
}

function cadastrar() {
    window.open('cadastrar-tanque.jsp', '_self');
}

function confirmar(id){
    if (confirm("Deseja excluir o tanque " + id + "?")) {
        window.open('ExcluirTanque?id=' + id, '_self');
    }
}