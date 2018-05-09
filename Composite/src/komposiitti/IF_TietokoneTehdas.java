package komposiitti;

import komposiitti.komponentit.Komponentti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JESSE
 */
public interface IF_TietokoneTehdas {
    public abstract Komponentti luoKotelo();
    public abstract Komponentti luoEmolevy();
    public abstract Komponentti luoCPU();
    public abstract Komponentti luoGPU();
    public abstract Komponentti luoRAM();
    
}
