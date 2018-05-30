/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.modelo;

import java.util.Date;

/**
 *
 * @author samsung
 */
public class Funcionario {
    
    private String cpf, rg, nome, imagem, sexo, email, senha, ultimaSessao, rua, bairro, cep, cidade, uf;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    private int numEnd;

    public String getUltimaSessao() {
        return ultimaSessao;
    }

    public void setUltimaSessao(String ultimaSessao) {
        this.ultimaSessao = ultimaSessao;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    private Date dtNascimento, inicioTrab, fimTrab;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }

    public Date getInicioTrab() {
        return inicioTrab;
    }

    public void setInicioTrab(Date inicioTrab) {
        this.inicioTrab = inicioTrab;
    }

    public Date getFimTrab() {
        return fimTrab;
    }

    public void setFimTrab(Date fimTrab) {
        this.fimTrab = fimTrab;
    }

}
