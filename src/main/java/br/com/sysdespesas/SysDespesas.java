/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.sysdespesas;

import br.com.sysdespesas.negocio.Despesa;
import java.time.LocalDate;

/**
 *
 * @author gfdal
 */
public class SysDespesas {

    public static void main(String[] args) {

        var despesa = new Despesa("Seguro de Vida", 22.50, LocalDate.of(2026, 12, 31), 24);
        
    }
}
