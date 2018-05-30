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
public class TanQuim {
    
    private int idTanque, codQuimico;
    private Date dtInsercao;
    private Time horaInsercao;
    private float peso;

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public int getCodQuimico() {
        return codQuimico;
    }

    public void setCodQuimico(int codQuimico) {
        this.codQuimico = codQuimico;
    }

    public Date getDtInsercao() {
        return dtInsercao;
    }

    public void setDtInsercao(Date dtInsercao) {
        this.dtInsercao = dtInsercao;
    }

    public Time getHoraInsercao() {
        return horaInsercao;
    }

    public void setHoraInsercao(Time horaInsercao) {
        this.horaInsercao = horaInsercao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
}
