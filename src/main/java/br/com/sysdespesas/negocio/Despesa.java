/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sysdespesas.negocio;

import br.com.sysdespesas.persistencia.DaoDespesa;
import br.com.sysdespesas.persistencia.DaoParcela;
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
        this.id = getProxId();
        this.descricao = descricao;
        this.criarParcelas(descricao, valorParcela, vencimento, qntParcelas);
    }
    
    public Despesa(int id, String descricao, ArrayList<Parcela> parcelas) {
        this.id = id;
        this.descricao = descricao;
        this.parcelas = parcelas;
    }

    public int getId(){
        return this.id;
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
        var dao = new DaoParcela();
        
        for(int i = 1; i <= qntParcelas; i++){
            var parcela = new Parcela(this.id, valorParcela, vencimento);
            dao.create(parcela);
            this.parcelas.add(parcela);
            vencimento = vencimento.plusMonths(1);
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
    
    private static int getProxId(){
        int num = 0;
        ArrayList<Despesa> despesas = new DaoDespesa().readAll();
                
        if(!despesas.isEmpty()){
            int aux = 0;
            for(int i = 0; i < despesas.size(); i++){
                aux = despesas.get(i).getId();
                if(aux > num){ num = aux; }
            }
            num++;
        }
        
        return num;
    }
    
    public boolean equals(Object object){
        boolean status = false;
        var despesa = (Despesa) object;
        
        if(this.getId() == despesa.getId()){
            status = true;
        }
        
        return status;
    }
        
}