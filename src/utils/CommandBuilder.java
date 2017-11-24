/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;

/**
 *
 * @author Jirka_
 */
public class CommandBuilder {
    
    /**
     *
     * @param args
     * @return
     */
    public static String compose(String... args) {
        return Arrays.stream(args).reduce((a, b) -> a + " " + b).get();
    }
    
}
