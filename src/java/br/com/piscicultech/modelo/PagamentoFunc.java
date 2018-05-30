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
public class PagamentoFunc {
    
    private String cpfFunc, cnpjEmps;
    private double vlrPago;
    private Date dtPag;

    public Date getDtPag() {
        return dtPag;
    }

    public void setDtPag(Date dtPag) {
        this.dtPag = dtPag;
    }

    public String getCpfFunc() {
        return cpfFunc;
    }

    public void setCpfFunc(String cpfFunc) {
        this.cpfFunc = cpfFunc;
    }

    public String getCnpjEmps() {
        return cnpjEmps;
    }

    public void setCnpjEmps(String cnpjEmps) {
        this.cnpjEmps = cnpjEmps;
    }

    public double getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(double vlrPago) {
        this.vlrPago = vlrPago;
    }
    
}
