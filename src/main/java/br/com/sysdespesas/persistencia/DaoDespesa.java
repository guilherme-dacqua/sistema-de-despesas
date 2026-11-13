package br.com.sysdespesas.persistencia;

import br.com.ferramentas.Persistencia;
import br.com.sysdespesas.negocio.Despesa;
import br.com.sysdespesas.negocio.Parcela;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DaoDespesa {

    private Persistencia arquivo;
    
    public DaoDespesa(){
        this.arquivo = new Persistencia("despesas.txt");
    }
    
    public ArrayList<Despesa> readAll(){
        ArrayList<Despesa> despesas = new ArrayList<>();
            
        try{
            this.arquivo.iniciarLeitura();
            String linha = "";
            while(linha != null){
                linha = this.arquivo.ler();
                if(linha != null && !linha.isEmpty()){
                    
                    String[] dados = new String[3];
                    dados = linha.split(";");
                    int id                      = Integer.parseInt(dados[0]);
                    String descricao            = dados[1];
                    ArrayList<Parcela> parcelas = new DaoParcela().read(id);
                    
                    Despesa despesa = new Despesa(id, descricao, parcelas);
                    despesas.add(despesa);
                }
            }
            this.arquivo.fecharLeitura();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
            
        return despesas;
    }
    
    public Despesa read (int id){
        ArrayList<Despesa> despesas  = this.readAll();
        Despesa despesa = null;
        
        for(int i = 0; i < despesas.size(); i++){
            if(despesas.get(i).getId() == id){
                despesa = despesas.get(i);
            }
        }

        return despesa;
    }
    
    public void create(Despesa despesa){
        ArrayList<Despesa> despesas = this.readAll();
        despesas.add(despesa);
        
        try{
            this.arquivo.iniciarEscrita();
                for(int i = 0; i < despesas.size(); i++){
                    var despesa_ = despesas.get(i);
                    
                    int id                      = despesa_.getId();
                    String descricao            = despesa_.getDescricao();
                    ArrayList<Parcela> parcelas = despesa_.getParcela();
                    String linha = "";
                    for(int j = 0; j < parcelas.size(); j++) {
                        linha += id + ";" + descricao + ";" + parcelas.get(j).getIdParcela();
                        if(j != parcelas.size() - 1){ linha += "\n"; }
                    }
                    this.arquivo.escrever(linha);
                }
            this.arquivo.fecharEscrita();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Despesa despesa){
        ArrayList<Despesa> despesas = this.readAll();
        
        for(int i = 0; i < despesas.size(); i++){
            if(despesas.equals(despesa)){
                despesas.remove(i);
                despesas.add(despesa);
            }
        }
        
        this.save(despesas);
    }
    
    public void delete(Despesa despesa){
        ArrayList<Despesa> despesas = this.readAll();
        
        for(int i = 0; i < despesas.size(); i++){
            if(despesas.get(i).equals(despesa)){
                despesas.remove(i);
            }
        }
        
        this.save(despesas);
    }
    
    private void save(ArrayList<Despesa> despesas){
        for(Despesa despesa : despesas){
            this.create(despesa);
        }
    }
}
