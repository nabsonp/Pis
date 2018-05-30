/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.modelo;

import java.sql.Time;

/**
 *
 * @author samsung
 */
public class Arracoamento {
    
    public int idTanque, codRacao, dia, mes, ano;
    public Time hora;
    public float peso;

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public int getCodRacao() {
        return codRacao;
    }

    public void setCodRacao(int codRacao) {
        this.codRacao = codRacao;
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

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
}
