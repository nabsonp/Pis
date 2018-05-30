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
public class PagamentoImpTaxAlu {
    
    private int codITA, dia, mes, ano;
    private String cnpjEmp;
    private double valor;

    public int getCodITA() {
        return codITA;
    }

    public void setCodITA(int codITA) {
        this.codITA = codITA;
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

    public String getCnpjEmp() {
        return cnpjEmp;
    }

    public void setCnpjEmp(String cnpjEmp) {
        this.cnpjEmp = cnpjEmp;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
