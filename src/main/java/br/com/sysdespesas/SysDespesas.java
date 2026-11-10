/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.sysdespesas;

import br.com.sysdespesas.negocio.Parcela;
import br.com.sysdespesas.persistencia.DaoParcela;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author gfdal
 */
public class SysDespesas {

    public static void main(String[] args) {
        
        Parcela parcela = new Parcela(3, 25.50, "2026-11-13", "1969-09-22");
        var dao = new DaoParcela();
        dao.create(parcela);
        
    }
}
