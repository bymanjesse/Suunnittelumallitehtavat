/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author JESSE
 */
public class ClockTimer extends Observable implements Runnable{
    private String time;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    public String getTime() {
        return time;
    }
    
    private void tick() {
        Date date = new Date();
        time = timeFormat.format(date);    
    }
    
    

    @Override
    public void run() {
        while(true) {
            tick();
            setChanged();
            notifyObservers(this);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
    
}
