/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;


import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author JESSE
 */
public class DigitalClock implements Observer{
    
    private ClockTimer timer;
    
    public DigitalClock(ClockTimer timer) {
        this.timer = timer;
        timer.addObserver(this);
    }
    

    @Override
    public void update(Observable o, Object o1) {
        if(o == timer) {
            draw();
        }
    }
    
    private void draw() {
        System.out.println(timer.getTime());
    }
}
