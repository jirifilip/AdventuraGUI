package utils;

/**
 *  Interface Publisher - pro návrhový vzor Publisher - Subscriber. Umožňuje
 *  přidávání a mazání odběru a publikování změn.
 * 
 * 
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public interface Publisher {
    
    /**
     * Metoda subscribe - složí pro přidání nového odběratele
     * 
     * @param subscriber nový odběratel.
     */
    void subscribe(Subscriber subscriber);
    
    /**
     * Metoda off - slouží pro smazání existujícího odběratele.
     * 
     * @param subscriber existující odběratel ke smazání.
     */
    void off(Subscriber subscriber);
    
    
    /**
     * Metoda publish - slouží pro signalizaci provedení změny stavu.
     * 
     */
    void publish();
    
}
