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
 *
 * @author Jirka_
 */
public class GameTextArea extends TextArea implements Subscriber {

    public IHra game;

    public GameTextArea(IHra game) {
        this.game = game;
    }
    
    @Override
    public void update() {
        String text = game.getHerniPlan().getAktualniProstor().dlouhyPopis();
        this.appendText(text);
    }
    
}
