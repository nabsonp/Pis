/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var edit = true;

function inserirEspecie(id) {
    window.open('AbrirEscolherEspecie?idTanque=' + id, '_self');
}

function addDado(id) {
    document.getElementById(id).className = "";
    document.getElementById(id).innerHTML = "<form method='get' action='InserirDado'><td class = \"aviso\"></td> <td>" + id + "</td> <td><input id = \"valor\" name = \"valor\" type = \"text\" class = \"alter\"></td> <td></td> <td><input name = \"hora\" type = \"text\" class = \"alter\"></td> <td></td> <td></td> <td class = \"btts\"><button type = \"submit\"><img src = \"icones/adicionar.png\" title = \"Adicionar\"/></button></td></form>";
    document.getElementById("valor").focus();
}

function abrirDiaTanque(data, id) {
    window.open("AbrirTanque?tanque="+id+"&data="+data.name, "_self");
}

var erro = false, msgErro = "";

function validarData(campo) {
    var valor = campo.value;
    if (valor.length === 2 || valor.length === 5) {
        campo.value = valor.concat(":");
    }
}

function getHoraAtual(id){
    var data = new Date();
    document.getElementById(id).value = (data.getHours()+1) + ":" + (data.getMinutes()+1) + ":" + (data.getSeconds()+1);
}

function abrirEspecie(id) {
    window.open("AbrirEspecie?id="+id);
}

function verificar() {
    erro = false, msgErro = "";
    
    if (document.inserir.hora.value === "") {
        erro = true;
        msgErro = "Informe o horário\n";
    } else {
        var hora = document.inserir.hora.value.slice(0, 2);
        var min = document.inserir.hora.value.slice(3, 5);
        var seg = document.inserir.hora.value.slice(5);
        if (document.inserir.hora.value.length < 7) {
            erro = true;
            msgErro = msgErro + "Insira os valores da hora, dos minutos e dos segundos no campo \"Hora\"\n";
        } else {
            if (parseInt(hora) > 24 || parseInt(min) > 60 || parseInt(seg) > 60) {
                erro = true;
                msgErro = msgErro + "Hora inválida\n";
            }
        }
    }

    if (document.inserir.temp.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor da temperatura\n";
    }

    if (document.inserir.oxi.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor do xigênio\n";
    }

    if (document.inserir.amon.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor da amônia\n";
    }

    if (document.inserir.nitri.value === "") {
        erro = true;
        msgErro = msgErro + "Informe valor do nitrito\n";
    }

    if (document.inserir.nitra.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor do nitrato\n";
    }

    if (document.inserir.gasCarb.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor do gás carbônico\n";
    }

    if (document.inserir.alcal.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor da alcalinidade\n";
    }

    if (document.inserir.ph.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor do ph\n";
    }

    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.inserir.submit();
    }
}

function verificarArr() {
    erro = false, msgErro = "";
    
    if (document.arrac.hora.value === "") {
        erro = true;
        msgErro = "Informe o horário\n";
    } else {
        var hora = document.arrac.hora.value.slice(0, 2);
        var min = document.arrac.hora.value.slice(3, 5);
        var seg = document.arrac.hora.value.slice(5);
        if (document.arrac.hora.value.length < 7) {
            erro = true;
            msgErro = msgErro + "Insira os valores da hora, dos minutos e dos segundos no campo \"Hora\"\n";
        } else {
            if (parseInt(hora) > 24 || parseInt(min) > 60 || parseInt(seg) > 60) {
                erro = true;
                msgErro = msgErro + "Hora inválida\n";
            }
        }
    }
    
    if (document.arrac.peso.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o valor da quantidade em gramas de arração.\n";
    }
    
    if (document.arrac.nomeRacao.value === "") {
        erro = true;
        msgErro = msgErro + "Por favor, cadastre uma arração na aba Financeiros.\n";
    }
    
    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.arrac.submit();
    }
    
}

function verificarVerBio(){
    
    erro = false, msgErro = "";
    
    if (document.verBio.hora.value === "") {
        erro = true;
        msgErro = "Informe o horário\n";
    } else {
        var hora = document.verBio.hora.value.slice(0, 2);
        var min = document.verBio.hora.value.slice(3, 5);
        var seg = document.verBio.hora.value.slice(5);
        if (document.verBio.hora.value.length < 7) {
            erro = true;
            msgErro = msgErro + "Insira os valores da hora, dos minutos e dos segundos no campo \"Hora\"\n";
        } else {
            if (parseInt(hora) > 24 || parseInt(min) > 60 || parseInt(seg) > 60) {
                erro = true;
                msgErro = msgErro + "Hora inválida\n";
            }
        }
    }
    
    if (document.verBio.pesoMedio.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o o peso médio das espécies.\n";
    }
    
    if (document.verBio.tamanhoMedio.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o tamanho médio dos peixes.\n";
    }
    
    if (document.verBio.mortos.value === "") {
        erro = true;
        msgErro = msgErro + "Informe quantos peixes mortos foram encontrados no tanque.\n";
    }
    
    if (document.verBio.nascidos.value === "") {
        erro = true;
        msgErro = msgErro + "Informe quantos alevinos foram contados no tanque.\n";
    }
    
    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.verBio.submit();
    }
    
}

function verificarIns() {
    
    erro = false, msgErro = "";
    
    if (document.inserirIns.hora.value === "") {
        erro = true;
        msgErro = "Informe o horário\n";
    } else {
        var hora = document.inserirIns.hora.value.slice(0, 2);
        var min = document.inserirIns.hora.value.slice(3, 5);
        var seg = document.inserirIns.hora.value.slice(5);
        if (document.inserirIns.hora.value.length < 7) {
            erro = true;
            msgErro = msgErro + "Insira os valores da hora, dos minutos e dos segundos no campo \"Hora\"\n";
        } else {
            if (parseInt(hora) > 24 || parseInt(min) > 60 || parseInt(seg) > 60) {
                erro = true;
                msgErro = msgErro + "Hora inválida\n";
            }
        }
    }
    
    if (document.inserirIns.peso.value === "") {
        erro = true;
        msgErro = msgErro + "Informe o o peso médio das espécies.\n";
    }
    
    if (erro) {
        alert('Foram encontrados alguns problemas:\n' + msgErro);
        msgErro = "";
        erro = false;
    } else {
        document.inserirIns.submit();
    }
    
}