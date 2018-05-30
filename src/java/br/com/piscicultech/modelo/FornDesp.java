/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.modelo;

/**
 *
 * @author samsung
 */
public class FornDesp {
    
    private int codDesp, codForn, qtd, dia, mes, ano;
    private double precoUni, total;

    public int getCodDesp() {
        return codDesp;
    }

    public void setCodDesp(int codDesp) {
        this.codDesp = codDesp;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPrecoUni() {
        return precoUni;
    }

    public void setPrecoUni(double precoUni) {
        this.precoUni = precoUni;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
