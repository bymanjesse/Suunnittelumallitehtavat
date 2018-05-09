package komposiitti;

import komposiitti.komponentit.RAM;
import komposiitti.komponentit.GPU;
import komposiitti.komponentit.Komponentti;
import komposiitti.komponentit.Kotelo;
import komposiitti.komponentit.Emolevy;
import komposiitti.komponentit.CPU;


/**
 *
 * @author JESSE
 */
public class TietokoneTehdas implements IF_TietokoneTehdas {

    @Override
    public Komponentti luoKotelo() {
        return new Kotelo(79.95);
    }

    @Override
    public Komponentti luoEmolevy() {
        return new Emolevy(85.95);
    }

    @Override
    public Komponentti luoCPU() {
        return new CPU(249.95);
    }

    @Override
    public Komponentti luoGPU() {
        return new GPU(379.95);
    }

    @Override
    public Komponentti luoRAM() {
        return new RAM(109.95);
    }

    
}