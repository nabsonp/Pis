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
public class Alimentacao {
    
    private int idEsp, codRacao;
    private String faseDeVida;

    public int getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(int idEsp) {
        this.idEsp = idEsp;
    }

    public int getCodRacao() {
        return codRacao;
    }

    public void setCodRacao(int codRacao) {
        this.codRacao = codRacao;
    }

    public String getFaseDeVida() {
        return faseDeVida;
    }

    public void setFaseDeVida(String faseDeVida) {
        this.faseDeVida = faseDeVida;
    }
    
}
