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
public class VendaEquipamento {
    
    private int codEquip, codForn, qtd;
    private float valorUni, total;
    private Date dtVenda;

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    public int getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(int codEquip) {
        this.codEquip = codEquip;
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
    
}
