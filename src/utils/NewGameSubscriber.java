package utils;

import logika.IHra;

/**
 *  Interface NewGameSubscriber - dědí od Subscribera a
 *  přidává metodu newGame(), která slouží pro signalizování
 *  začátku nové hry.
 * 
 * 
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public interface NewGameSubscriber extends Subscriber {
    
    /**
     * Metoda newGame - signalizuje začátek nové hry
     * 
     * @param hra instance nové hry.
     */
    void newGame(IHra hra);
    
}
