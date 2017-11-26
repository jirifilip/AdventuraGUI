package utils;

import logika.IHra;

/**
 *  Interface Subscriber - slouží pro odebírání změn stavu
 *  u Publishera.
 * 
 * 
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public interface Subscriber {
    
    
    /**
     * Metoda update - aktualizuje stav Subscribera.
     * 
     */
    void update();
    
}
