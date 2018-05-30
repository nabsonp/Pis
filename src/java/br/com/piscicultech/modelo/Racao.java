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
public class Racao {
    
    private int codigo;
    private String nome, marca;
    private float pesoTotal, tamPaleteMin, tamPaleteMax, UmidadeMax, proteinaMin, extratoEtereoMin, materiaFibrosaMax, materiaMineralMin, calcioMin, calcioMax, fosforoMin, vitaminaCMin;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getMateriaFibrosaMax() {
        return materiaFibrosaMax;
    }

    public void setMateriaFibrosaMax(float materiaFibrosaMax) {
        this.materiaFibrosaMax = materiaFibrosaMax;
    }

    public float getMateriaMineralMin() {
        return materiaMineralMin;
    }

    public void setMateriaMineralMin(float materiaMineralMin) {
        this.materiaMineralMin = materiaMineralMin;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public float getTamPaleteMin() {
        return tamPaleteMin;
    }

    public void setTamPaleteMin(float tamPaleteMin) {
        this.tamPaleteMin = tamPaleteMin;
    }

    public float getTamPaleteMax() {
        return tamPaleteMax;
    }

    public void setTamPaleteMax(float tamPaleteMax) {
        this.tamPaleteMax = tamPaleteMax;
    }

    public float getUmidadeMax() {
        return UmidadeMax;
    }

    public void setUmidadeMax(float UmidadeMax) {
        this.UmidadeMax = UmidadeMax;
    }

    public float getProteinaMin() {
        return proteinaMin;
    }

    public void setProteinaMin(float proteinaMin) {
        this.proteinaMin = proteinaMin;
    }

    public float getExtratoEtereoMin() {
        return extratoEtereoMin;
    }

    public void setExtratoEtereoMin(float extratoEtereoMin) {
        this.extratoEtereoMin = extratoEtereoMin;
    }

    public float getCalcioMin() {
        return calcioMin;
    }

    public void setCalcioMin(float calcioMin) {
        this.calcioMin = calcioMin;
    }

    public float getCalcioMax() {
        return calcioMax;
    }

    public void setCalcioMax(float calcioMax) {
        this.calcioMax = calcioMax;
    }

    public float getFosforoMin() {
        return fosforoMin;
    }

    public void setFosforoMin(float fosforoMin) {
        this.fosforoMin = fosforoMin;
    }

    public float getVitaminaCMin() {
        return vitaminaCMin;
    }

    public void setVitaminaCMin(float vitaminaCMin) {
        this.vitaminaCMin = vitaminaCMin;
    }
    
}
