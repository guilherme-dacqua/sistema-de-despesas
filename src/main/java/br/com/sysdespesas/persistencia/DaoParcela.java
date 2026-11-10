package br.com.sysdespesas.persistencia;

import br.com.ferramentas.Persistencia;
import br.com.sysdespesas.negocio.Parcela;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DaoParcela {

    private Persistencia arquivo;
    
    public DaoParcela(){
        this.arquivo = new Persistencia("teste.txt");
    }
    
    public ArrayList<Parcela> readAll(){
        ArrayList<Parcela> parcelas = new ArrayList<>();
            
        try{
            this.arquivo.iniciarLeitura();
            String linha = "";
            while(linha != null){
                linha = this.arquivo.ler();
                if(linha != null){
                    String[] dados = new String[6];
                    dados = linha.split(";");
                    int idDespesa_              = Integer.parseInt(dados[0]);
                    int idParcela               = Integer.parseInt(dados[1]);
                    double valor                = Double.parseDouble(dados[2]);
                    boolean status              = dados[3] == "0" ? false : true;
                    String vencimento           = dados[4];
                    String dtPagamento          = dados[5];
                    
                    var parcela = new Parcela(idDespesa_, idParcela, valor, vencimento, dtPagamento);
                    parcela.setStatus(status);
                    parcelas.add(parcela);
                }
            }
            this.arquivo.fecharLeitura();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
            
        return parcelas;
    }
    
    public ArrayList<Parcela> read (int idDespesa){
        ArrayList<Parcela> parcelas  = new ArrayList<>();
        ArrayList<Parcela> parcelas_ = new ArrayList<>();

        parcelas = this.readAll();

        for(int i = 0; i < parcelas.size(); i++){
            if(parcelas.get(i).getIdDespesa() == idDespesa){
                parcelas_.add(parcelas.get(i));
            }
        }

        return parcelas_;
    }
    
    public void create(Parcela parcela){
        ArrayList<Parcela> parcelas = this.readAll();
        parcelas.add(parcela);
        
        try{
            this.arquivo.iniciarEscrita();
                for(int i = 0; i < parcelas.size(); i++){
                    Parcela parcela_ = parcelas.get(i);
                    
                    int idDespesa         = parcela_.getIdDespesa();
                    int idParcela         = parcela_.getIdParcela();
                    double valor          = parcela_.getValor();
                    String status         = parcela_.isStatus() == true ? "1" : "0";
                    LocalDate vencimento  = parcela_.getVencimento();
                    LocalDate dtPagamento = parcela_.getDtPagamento();
                    
                    String linha = idDespesa + ";" + idParcela + ";" + valor + ";" + status + ";" + vencimento + ";" + dtPagamento;
                    
                    this.arquivo.escrever(linha);
                }
            this.arquivo.fecharEscrita();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Parcela parcela){
        ArrayList<Parcela> parcelas = new ArrayList<>();
        parcelas = this.readAll();
        
        for(int i = 0; i < parcelas.size(); i++){
            if(parcelas.get(i) == parcela){
                parcelas.remove(i);
                parcelas.add(parcela);
            }
        }
        
        this.save(parcelas);
    }
    
    public void delete(Parcela parcela){
        ArrayList<Parcela> parcelas = new ArrayList<>();
        for(int i = 0; i < parcelas.size(); i++){
            if(parcelas.get(i).equals(parcela)){
                parcelas.remove(i);
            }
        }
         
    }
    
    private void save(ArrayList<Parcela> parcelas){
        for(int i = 0; i < parcelas.size(); i++){
            this.create(parcelas.get(i));
        }
    }
}
