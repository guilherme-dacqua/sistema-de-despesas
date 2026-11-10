/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sysdespesas.negocio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author gfdal
 */
public class Despesa {

    private int id;
    private String descricao;
    private ArrayList<Parcela> parcelas;

    public Despesa(String descricao, double valorParcela, String vencimento, int qntParcelas) {
        this.descricao = descricao;
        this.criarParcelas(descricao, valorParcela, vencimento, qntParcelas);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    private void criarParcelas(String descricao, double valorParcela, String vencimento_, int qntParcelas){
        
        this.parcelas = new ArrayList<>();
        LocalDate vencimento = this.conversor(vencimento_);
        
        for(int i = 1; i <= qntParcelas; i++){
            var parcela = new Parcela(this.id, valorParcela, vencimento);
            this.parcelas.add(parcela);
            vencimento = vencimento.plusMonths(1);
//          -> daoParcela.create(parcela);
        }
    }
    
    public ArrayList<Parcela> getParcela(){
        return this.parcelas;
    }
    
    private LocalDate conversor(String data /*yyyy/MM/dd*/){
        String[] data_ = new String[3];
        data_ = data.split("-");
        
        int year  = Integer.parseInt(data_[0]);
        int month = Integer.parseInt(data_[1]);
        int day   = Integer.parseInt(data_[2]);
        
        return LocalDate.of(year, month, day);
    }
        
}