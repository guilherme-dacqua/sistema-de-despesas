/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sysdespesas.negocio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author gfdal
 */
public class Despesa {

  //private int id
    private String descricao;
    private Parcela parcela;

    public Despesa(String descricao, double valorParcela, LocalDate vencimento, int qntParcelas) {
        this.descricao = descricao;
        this.criarParcelas(descricao, valorParcela, vencimento, qntParcelas);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    private void criarParcelas(String descricao, double valorParcela, LocalDate vencimento, int qntParcelas){
        
        for(int i = 1; i <= qntParcelas; i++){
            parcela = new Parcela(this.descricao, valorParcela, vencimento);
            vencimento = vencimento.plusMonths(1);
            System.out.println(this.parcela);
            // daoParcela.create(parcela);
        }
    }
        
}