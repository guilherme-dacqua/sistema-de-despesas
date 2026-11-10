package br.com.sysdespesas.negocio;

import br.com.sysdespesas.persistencia.DaoParcela;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Parcela {
   
    private int idDespesa;
    private int idParcela;
    private double valor;
    private boolean status; // 0 -> não pago, 1 -> pago
    private LocalDate vencimento;
    private LocalDate dtPagamento;
    
    public Parcela(int idDespesa, double valor, LocalDate vencimento, int idParcela) {
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.vencimento = vencimento;
        this.idParcela = idParcela;
    }
    
    public Parcela(int idDespesa, double valor, LocalDate vencimento) {
        this(
                idDespesa,
                valor,
                vencimento,
                getProxId(idDespesa)
        );
    }
    
    public Parcela(int idDespesa, double valor, String vencimento) {
        this(
                idDespesa,
                valor,
                stringToLocalDate(vencimento)
        );
    }
    
    public Parcela(int idDespesa, double valor, String vencimento, String dtPagamento) {
        this(
                idDespesa,
                valor,
                vencimento
        );
        this.dtPagamento = stringToLocalDate(dtPagamento);
    }
    
    public Parcela(int idDespesa, int idParcela, double valor, String vencimento, String dtPagamento){
        this(
            idDespesa,
            valor,
            stringToLocalDate(vencimento),
            idParcela
        );
        this.dtPagamento = stringToLocalDate(dtPagamento);
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
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
    
    private static LocalDate stringToLocalDate(String vencimento) {
        
        String[] data = new String[3];
        data = vencimento.split("-");
        
        int year  = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day   = Integer.parseInt(data[2]);
        
        return LocalDate.of(year, month, day);
    }
    
    private static int getProxId(int idDespesa){
        int num = 0;
        var daoParcela = new DaoParcela();
        ArrayList<Parcela> parcelas = daoParcela.read(idDespesa);
                
        if(!parcelas.isEmpty()){
            int aux = 0;
            for(int i = 0; i < parcelas.size(); i++){
                aux = parcelas.get(i).getIdParcela();
                if(aux > num){ num = aux; }
            }
            num++;
        }
        
        return num;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean status = false;
        var parcela = (Parcela) obj;
        
        if(this.getIdDespesa() == parcela.getIdDespesa() &&
           this.getIdParcela() == parcela.getIdParcela()
        ){ status = true; }
        
        return status;
    }
    
    /*
        @Override
        public String toString(){
            return "" + this.getIdDespesa() + " | " + this.getVencimento() + " | " + this.getValor();
        }
    */
}
