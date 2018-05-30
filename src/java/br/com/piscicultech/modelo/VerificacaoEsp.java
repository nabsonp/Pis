/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author samsung
 */
public class VerificacaoEsp {
    
    private int idTanque, idEspecie, qtd, mortos, nascidos;
    private float peso, tamanhoMedio;
    private String cpfFunc;
    private Time hora;
    private Date dtVerif;

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getMortos() {
        return mortos;
    }

    public void setMortos(int mortos) {
        this.mortos = mortos;
    }

    public int getNascidos() {
        return nascidos;
    }

    public void setNascidos(int nascidos) {
        this.nascidos = nascidos;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTamanhoMedio() {
        return tamanhoMedio;
    }

    public void setTamanhoMedio(float tamanhoMedio) {
        this.tamanhoMedio = tamanhoMedio;
    }

    public String getCpfFunc() {
        return cpfFunc;
    }

    public void setCpfFunc(String cpfFunc) {
        this.cpfFunc = cpfFunc;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getDtVerif() {
        return dtVerif;
    }

    public void setDtVerif(Date dtVerif) {
        this.dtVerif = dtVerif;
    }
    
}
