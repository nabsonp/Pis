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
public class VendaRacao {
    
    private int codRacao, codForn, qtd;
    private float valorUni, total, pesoUni;
    private Date data;

    public int getCodRacao() {
        return codRacao;
    }

    public void setCodRacao(int codRacao) {
        this.codRacao = codRacao;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
