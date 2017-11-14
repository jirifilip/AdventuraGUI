/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import logika.IHra;

/**
 *
 * @author filj03
 */
public interface Subscriber {
    
    void update();
    
    void newGame(IHra hra);
    
}
