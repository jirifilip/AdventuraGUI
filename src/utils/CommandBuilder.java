package utils;

import java.util.Arrays;

/**
 *  Třída CommandBuilder - umožňuje sestavovat příkazy pro hru pomocí slova
 *  příkazu a parametru. Jedná se o jednoduchou pomocnou třídu, která slouží
 *  pro zpřehlednění kódu.
 * 
 * 
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class CommandBuilder {
    
    /**
     * Statická metoda sloužící pro spojení slov příkazů do
     * jednoho textového řetězce pomocí mezery.
     * 
     * @param args argumenty, pro příkaz, které chceme spojit
     */
    public static String compose(String... args) {
        return Arrays.stream(args).reduce((a, b) -> a + " " + b).get();
    }
    
}
