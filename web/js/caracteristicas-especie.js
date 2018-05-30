function abrirEspecies() {
    window.open('AbrirEspecies', '_self');
}

var id, esp;

function tornarEditavel(id, esp) {
    this.id = id;
    this.esp = esp;
    var atual = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML = "<input placeholder = \"" + atual + "\" id = \"edit\" type = \"text\">";
    document.getElementById(id + "Btts").innerHTML = "<button><img onclick = \"editarValor()\" src = \"icones/salvar.png\" title = \"Salvar\"/></button><button><img onclick = \"cancelar()\" src = \"icones/cancel.png\" title = \"Cancelar\"/></button>";
    document.getElementById("edit").focus();
}

function tornarEditaveis(id, esp) {
    this.id = id;
    this.esp = esp;
    var atualMin = document.getElementById(id+"Min").innerHTML;
    var atualMax = document.getElementById(id+"Max").innerHTML;
    document.getElementById(id).innerHTML = "<input placeholder = \"" + atualMin + "\" id = \"" + id + "Min\" type = \"text\"> a <input placeholder = \"" + atualMax + "\" id = \"" + id + "Max\" type = \"text\">";
    document.getElementById(id + "Btts").innerHTML = "<button><img onclick = \"editarValores()\" src = \"icones/salvar.png\" title = \"Salvar\"/></button><button><img onclick = \"cancelar()\" src = \"icones/cancel.png\" title = \"Cancelar\"/></button>";
    document.getElementById(id + "Min").focus();
}

function editarValores() {
    window.open('EditarEspecie?id=' + esp + "&" + id + "Min=" + document.getElementById(id + "Min").value + "&" + id + "Max=" + document.getElementById(id + "Max").value, '_self');
}

function editarValor() {
    window.open('EditarEspecie?id=' + esp + "&" + id + "=" + document.getElementById("edit").value, '_self');
}

function cancelar() {
    window.location.reload();
}