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
public class Empresa {
    
    private String cnpj, nome, email, rua, bairro, cep, cidade, uf;
    private double fundoInvestimento;
    private int numEnd;
    private Date dtCriacao;

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public double getFundoInvestimento() {
        return fundoInvestimento;
    }

    public void setFundoInvestimento(double fundoInvestimento) {
        this.fundoInvestimento = fundoInvestimento;
    }

    public int getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }
    
}
