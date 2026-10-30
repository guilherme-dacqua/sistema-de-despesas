/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sysdespesas.negocio;

import java.time.LocalDate;

/**
 *
 * @author gfdal
 */
public class Parcela {
   
    private int idDespesa;
    private double valor;
    private boolean status;
    private LocalDate vencimento;
    private LocalDate dtPagamento;

    public Parcela(int idDespesa, double valor, LocalDate vencimento) {
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(LocalDate dtPagamento) {
        this.dtPagamento = dtPagamento;
    }
    
    @Override
    public String toString(){
        return "" + this.getIdDespesa() + " | " + this.getVencimento() + " | " + this.getValor();
    }
    
}
