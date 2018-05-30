/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function abrirTanques() {
    window.open('EscolherTanqueRel', '_self');
}

function abrirEspeciesRel() {
    window.open('EscolherEspecieRel', '_self');
}

function abrirTanqueBiometrico(id) {
    window.open('GerarRelatorioBiometrico?tanque=' + id, '_self');
}

function abrirAgua(id) {
    window.open('GerarRelatorioAgua?tanque=' + id, '_self');
}

function abrirRelEspecie(id) {
    window.open('GerarRelatorioEspecie?esp=' + id, '_self');
}