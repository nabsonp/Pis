/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.modelo;

import java.sql.Date;

/**
 *
 * @author samsung
 */
public class VendaQuimico {
    
    private int codQuim, codForn, qtd;
    private float valorUni, total, pesoUni;
    private Date dia;

    public int getCodQuim() {
        return codQuim;
    }

    public void setCodQuim(int codQuim) {
        this.codQuim = codQuim;
    }

    public int getCodForn() {
        return codForn;
    }

    public void setCodForn(int codForn) {
        this.codForn = codForn;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public float getValorUni() {
        return valorUni;
    }

    public void setValorUni(float valorUni) {
        this.valorUni = valorUni;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPesoUni() {
        return pesoUni;
    }

    public void setPesoUni(float pesoUni) {
        this.pesoUni = pesoUni;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    
}
