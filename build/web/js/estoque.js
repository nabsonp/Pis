/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function abrirDia(data) {
    window.open("AbrirEstoque?data="+data.name, "_self");
}

function abrirEspecieEst(id) {
    window.open('AbrirEspecie?id='+id, '_self');
}