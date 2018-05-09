/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposiitti;

import komposiitti.komponentit.Komponentti;
/**
 *
 * @author JESSE
 */
public class Tietokone {
    private final IF_TietokoneTehdas tehdas;
    private Komponentti kotelo, emolevy, cpu, gpu, ram;
    
    public Tietokone(IF_TietokoneTehdas tehdas) {
        this.tehdas = tehdas;
        assemble();
    }
    
    private void assemble() {
        kotelo = tehdas.luoKotelo();
        emolevy = tehdas.luoEmolevy();
        cpu = tehdas.luoCPU();
        gpu = tehdas.luoGPU();
        ram = tehdas.luoRAM();
        
        emolevy.lisää(cpu);
        emolevy.lisää(gpu);
        emolevy.lisää(ram);
        kotelo.lisää(emolevy);
    }
    
    public double getHinta() {
        return kotelo.getHinta();
    }
    
}
