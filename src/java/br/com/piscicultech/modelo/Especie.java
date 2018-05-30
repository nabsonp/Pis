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
public class Especie {
    
    private int id, freqAlimMin, freqAlimMax;
    private String nome, nomeCient, imagem;
    private float tamMin, tamMax, pesoMin, pesoMax, racaoDia, temperaturaMin, 
            temperaturaMax, oxigenioMin, oxigenioMax, phMin, phMax, amoniaMin, 
            amoniaMax, gasCarbonicoMin, gasCarbonicoMax, nitritoMin, nitritoMax,
            nitratoMin, nitratoMax, alcalinidadeMin, alcalinidadeMax;

    public float getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(float pesoMin) {
        this.pesoMin = pesoMin;
    }

    public float getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(float pesoMax) {
        this.pesoMax = pesoMax;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFreqAlimMin() {
        return freqAlimMin;
    }

    public void setFreqAlimMin(int freqAlimMin) {
        this.freqAlimMin = freqAlimMin;
    }

    public int getFreqAlimMax() {
        return freqAlimMax;
    }

    public void setFreqAlimMax(int freqAlimMax) {
        this.freqAlimMax = freqAlimMax;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCient() {
        return nomeCient;
    }

    public void setNomeCient(String nomeCient) {
        this.nomeCient = nomeCient;
    }

    public float getTamMin() {
        return tamMin;
    }

    public void setTamMin(float tamMin) {
        this.tamMin = tamMin;
    }

    public float getTamMax() {
        return tamMax;
    }

    public void setTamMax(float tamMax) {
        this.tamMax = tamMax;
    }

    public float getRacaoDia() {
        return racaoDia;
    }

    public void setRacaoDia(float racaoDia) {
        this.racaoDia = racaoDia;
    }

    public float getTemperaturaMin() {
        return temperaturaMin;
    }

    public void setTemperaturaMin(float temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }

    public float getTemperaturaMax() {
        return temperaturaMax;
    }

    public void setTemperaturaMax(float temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }

    public float getOxigenioMin() {
        return oxigenioMin;
    }

    public void setOxigenioMin(float oxigenioMin) {
        this.oxigenioMin = oxigenioMin;
    }

    public float getOxigenioMax() {
        return oxigenioMax;
    }

    public void setOxigenioMax(float oxigenioMax) {
        this.oxigenioMax = oxigenioMax;
    }

    public float getPhMin() {
        return phMin;
    }

    public void setPhMin(float phMin) {
        this.phMin = phMin;
    }

    public float getPhMax() {
        return phMax;
    }

    public void setPhMax(float phMax) {
        this.phMax = phMax;
    }

    public float getAmoniaMin() {
        return amoniaMin;
    }

    public void setAmoniaMin(float amoniaMin) {
        this.amoniaMin = amoniaMin;
    }

    public float getAmoniaMax() {
        return amoniaMax;
    }

    public void setAmoniaMax(float amoniaMax) {
        this.amoniaMax = amoniaMax;
    }

    public float getGasCarbonicoMin() {
        return gasCarbonicoMin;
    }

    public void setGasCarbonicoMin(float gasCarbonicoMin) {
        this.gasCarbonicoMin = gasCarbonicoMin;
    }

    public float getGasCarbonicoMax() {
        return gasCarbonicoMax;
    }

    public void setGasCarbonicoMax(float gasCarbonicoMax) {
        this.gasCarbonicoMax = gasCarbonicoMax;
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
    
}