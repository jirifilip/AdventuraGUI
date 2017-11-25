/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.control.TextArea;
import logika.IHra;
import utils.Subscriber;

/**
 *  Třída GameTextArea - představuje oblast pro výpis výstupu příkazů
 *  Používá se pro tisknutí textu, není možno ji editovat.
 *
 *  Tato třída je grafikého rozhraní pro adventuru Červená karkulka
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class GameTextArea extends TextArea implements Subscriber {

    public IHra game;

    /**
    * Metoda hledá nejvyšší hodnotu (maximum) v poli celých čísel.
    *
    *@param pole Pole celých čísel, které se bude prohledávat
    *@return Vrací celé číslo s nejvyšší hodnotou
    */
    public GameTextArea(IHra game) {
        this.game = game;
        
        setText(game.vratUvitani());
        setEditable(false);
    }
    
    /**
    * Metoda aktualizuje objekt po signálu od Publishera
    * 
    * Signál od Publishera představuje změnu místnosti, a tak k textu připojí
    * její popis.
    * 
    * 
    *@param
    *@return
    */
    @Override
    public void update() {
        String text = game.getHerniPlan().getAktualniProstor().dlouhyPopis();
        appendCommandResult(text);
    }
    
    /**
    * Metoda pro vkládání výsledku příkazu do textové oblasti.
    *
    *@param result výsledek příkazu, který chceme vypsat
    *@return
    */
    public void appendCommandResult(String result) {
        this.appendText("\n\n" + result);
    }
    
}
