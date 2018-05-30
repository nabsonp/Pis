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
public class VendaEspecie {
    
    private int idEspecie, codForn, qtd;
    private float valorUni, total, biomassa, pesoMedio, tamanhoMedio;
    private String tipo;
    private Date dtVenda;

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
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

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
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

    public float getBiomassa() {
        return biomassa;
    }

    public void setBiomassa(float biomassa) {
        this.biomassa = biomassa;
    }

    public float getPesoMedio() {
        return pesoMedio;
    }

    public void setPesoMedio(float pesoMedio) {
        this.pesoMedio = pesoMedio;
    }

    public float getTamanhoMedio() {
        return tamanhoMedio;
    }

    public void setTamanhoMedio(float tamanhoMedio) {
        this.tamanhoMedio = tamanhoMedio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
