/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author filj03
 */
public interface Publisher {
    
    void subscribe(Subscriber subscriber);
    void off(Subscriber subscriber);
    void publish();
    
}
