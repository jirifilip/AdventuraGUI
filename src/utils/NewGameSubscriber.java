/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import logika.IHra;

/**
 *
 * @author Jirka_
 */
public interface NewGameSubscriber extends Subscriber {
    
    void newGame(IHra hra);
    
}
