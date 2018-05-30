function abrirEspecie(id) {
    window.open('AbrirEspecie?id='+id, '_self');
}

function abrirCadastro() {
    window.open('cadastro-especie.jsp', '_self');
}

function excluir(id) {
    if (confirm("Dejesa excluir esta espécie?")) {
        window.open('ExcluirEspeciee?id='+id, '_self');
    }
}