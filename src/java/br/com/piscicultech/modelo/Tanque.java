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
public class Tanque{
    
    private int id, diaPeixam, mesPeixam, anoPeixam;
    private String tipo, piscicultura, revestimento, material;
    private boolean situacao;
    private float nitritoMin, nitritoMax, nitratoMin, nitratoMax, alcalinidadeMin, alcalinidadeMax;
    private Date dtCriacao;

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getPiscicultura() {
        return piscicultura;
    }

    public void setPiscicultura(String piscicultura) {
        this.piscicultura = piscicultura;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public float getNitritoMin() {
        return nitritoMin;
    }

    public void setNitritoMin(float nitritoMin) {
        this.nitritoMin = nitritoMin;
    }

    public float getNitritoMax() {
        return nitritoMax;
    }

    public void setNitritoMax(float nitritoMax) {
        this.nitritoMax = nitritoMax;
    }

    public float getNitratoMin() {
        return nitratoMin;
    }

    public void setNitratoMin(float nitratoMin) {
        this.nitratoMin = nitratoMin;
    }

    public float getNitratoMax() {
        return nitratoMax;
    }

    public void setNitratoMax(float nitratoMax) {
        this.nitratoMax = nitratoMax;
    }

    public float getAlcalinidadeMin() {
        return alcalinidadeMin;
    }

    public void setAlcalinidadeMin(float alcalinidadeMin) {
        this.alcalinidadeMin = alcalinidadeMin;
    }

    public float getAlcalinidadeMax() {
        return alcalinidadeMax;
    }

    public void setAlcalinidadeMax(float alcalinidadeMax) {
        this.alcalinidadeMax = alcalinidadeMax;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    private double capacidade, vazao, profund, largura, comp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaPeixam() {
        return diaPeixam;
    }

    public void setDiaPeixam(int diaPeixam) {
        this.diaPeixam = diaPeixam;
    }

    public int getMesPeixam() {
        return mesPeixam;
    }

    public void setMesPeixam(int mesPeixam) {
        this.mesPeixam = mesPeixam;
    }

    public int getAnoPeixam() {
        return anoPeixam;
    }

    public void setAnoPeixam(int anoPeixam) {
        this.anoPeixam = anoPeixam;
    }

    public String getRevestimento() {
        return revestimento;
    }

    public void setRevestimento(String revestimento) {
        this.revestimento = revestimento;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public double getVazao() {
        return vazao;
    }

    public void setVazao(double vazao) {
        this.vazao = vazao;
    }

    public double getProfund() {
        return profund;
    }

    public void setProfund(double profund) {
        this.profund = profund;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComp() {
        return comp;
    }

    public void setComp(double comp) {
        this.comp = comp;
    }
}
