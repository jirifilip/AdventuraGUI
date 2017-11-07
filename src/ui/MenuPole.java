/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author filj03
 */
public class MenuPole extends MenuBar {
    
    public MenuPole() {
        init();
    }
    
    private void init() {
        Menu menuFile = new Menu("Adventura");
        
        MenuItem itemNewGame = new MenuItem("Nová hra" /*, new ImageView(Main.class.getResourceAsStream("zddroje/ikona.png")*/);
        MenuItem itemEndGame = new MenuItem("Konec");
        
        
        itemNewGame.setAccelerator(KeyCombination.keyCombination("CTRL+N"));
        
        menuFile.getItems().addAll(itemNewGame, itemEndGame);
        
        this.getMenus().addAll(menuFile);
        
        itemEndGame.setOnAction(e -> System.exit(0));
        
    }
    
}
