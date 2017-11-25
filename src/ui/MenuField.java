/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *  Třída MenuField - GUI prvek pro navigační lištu.
 * 
 * 
 * Umožňuje začít hru znovu, ukončit nebo spustit nápovědu.
 * 
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class MenuField extends MenuBar {
    
    private Main main;
    
    /**
    * Konstruktor MenuField
    *
    *@param main hlavní okno aplikace
    *@return
    */
    public MenuField(Main main) {
        this.main = main;
        init();
    }
    
    /**
    * Inicializační  metoda
    *
    * Vytvoříme jednotlivé položky menu a přiřadíme jim event handlery.
    * 
    * @return
    */
    private void init() {
        Menu menuFile = new Menu("Adventura");
        
        MenuItem itemNewGame = new MenuItem("Nová hra" /*, new ImageView(Main.class.getResourceAsStream("zddroje/ikona.png")*/);
        MenuItem itemEndGame = new MenuItem("Konec");
        
        Menu menuHelp = new Menu("Help");
        MenuItem itemAbout = new MenuItem("O programu");
        MenuItem itemHelp = new MenuItem("Nápověda");
        
        menuHelp.getItems().addAll(itemAbout, itemHelp);
        menuFile.getItems().addAll(itemNewGame, itemEndGame);
        
        itemNewGame.setAccelerator(KeyCombination.keyCombination("CTRL+N"));
        itemHelp.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            WebView webView = new WebView();
            
            webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 500, 500));
            stage.show();
        });
        
        itemAbout.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            alert.setTitle("O adventuře");
            alert.setHeaderText("Toto je má adventura");
            alert.setContentText("Grafická verze adventury");
            alert.initOwner(main.getPrimaryStage());
            
            alert.showAndWait();
        });
        
        this.getMenus().addAll(menuFile, menuHelp);
        
        itemEndGame.setOnAction(e -> System.exit(0));
        itemNewGame.setOnAction(e -> {
            main.newGame();
        });
        
    }
    
}
