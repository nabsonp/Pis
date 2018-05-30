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
public class TanqEsp {
    
    private int idTanque, idEspecie, qtd;
    private float biomassa;
    private String situacao, tipo;
    private Date dtPeixam;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

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

    public Date getDtPeixam() {
        return dtPeixam;
    }

    public void setDtPeixam(Date dtPeixam) {
        this.dtPeixam = dtPeixam;
    }

    public float getBiomassa() {
        return biomassa;
    }

    public void setBiomassa(float biomassa) {
        this.biomassa = biomassa;
    }
}
