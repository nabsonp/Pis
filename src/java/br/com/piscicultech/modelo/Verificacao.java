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
public class Verificacao {
    
    private int idTanque;
    private String cpfFunc, nome, obs;
    private Time hora;
    private Date dtVerif;
    private float valor;
    private boolean situacao;

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public Date getDtVerif() {
        return dtVerif;
    }

    public void setDtVerif(Date dtVerif) {
        this.dtVerif = dtVerif;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
}
