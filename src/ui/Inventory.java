/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import logika.Batoh;
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.CommandBuilder;
import utils.NewGameSubscriber;
import utils.Subscriber;

/**
 *  Třída Inventory - představuje grafické zobrazení batohu
 * 
 * 
 *  Funguje jako inventář v řadě RPG her.
 *  Sbíráním předmětů z okolního prostoru se zaplňuje inventář a uvolňovat ho
 *  můžeme jejich vyhazováním.
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class Inventory extends FlowPane implements NewGameSubscriber {

    private final double ITEM_PREF_WIDTH = 50;
    private final double ITEM_PREF_HEIGHT = 50;
    
    protected final String DROP_COMMAND = "poloz";
    protected final String PICK_UP_COMMAND = "seber";
    
    protected IHra game;
    protected Main main;
    
    /**
    * Konstruktor Inventáře
    *
    *@param game hra, pro kterou vytváříme inventář
    *@param main hlavní okno aplikace, abychom mohli aktivovat
    * novou hru
    *@return
    */
    public Inventory(IHra game, Main main) {
        this.game = game;
        this.main = main;
        init();
    }
    
    /**
    * Inicializační metoda.
    * 
    * Zde slouží pro nastavení vzhledu komponenty.
    *
    */
    protected void init() {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setPrefWidth(250);
    }
    
    /**
    * Metoda pro vložení jedné Věci do komponenty a její zobrazení.
    * 
    * Z věci vytvoříme ItemDecorator, který poskytuje patřičnou funkcionalitu
    * a vložíme ho do komponenty. Rovněž mu nastavíme event
    * handler při kliknutí.
    * 
    *
    *@param item věc, kterou chceme do komponenty vložit
    *@return
    */
    protected void addItem(Vec item) {
        
        ItemDecorator imageView = new ItemDecorator(item);
        
        imageView.setOnMouseClicked(this::onItemClick);
        
        this.getChildren().add(imageView);
    }
    
    /**
    * Metoda, která slouží jako event handler pro kliknutí myši na
    * jednotlivou věc v inventáři.
    *
    * Při kliknutí na věc v inventáři ji chceme položit, a tak na instanci
    * hry zavoláme příkaz "poloz" s parametry podle jména věci.
    * 
    * K vytvoření textu příkazu se používá jednoduchá třída CommandBuilder a 
    * její statická metoda compose.
    * 
    *@param event události kliknutí
    *@return
    */
    protected void onItemClick(MouseEvent event) {
        ItemDecorator itemD = (ItemDecorator) event.getTarget();
        this.getChildren().remove(itemD);
        
        String command = CommandBuilder.compose(DROP_COMMAND, itemD.getItem().getJmeno());
        
        String commandString = game.zpracujPrikaz(command);
        
        main.getCenterText().appendCommandResult(commandString);
    }
    
    
    /**
    * Metoda, která slouží k aktualizaci stavu po signálu od Publishera.
    *
    * Když nám Publisher zašle takovýto příkaz, znamená to, že se obsah
    * inventáře změnil, a tak si z instance hry vezmeme znovu obsah batohu
    * a vykreslíme ho.
    * 
    *@param
    *@return
    */
    @Override
    public void update() {
        Collection<ItemDecorator> itemDecs = ItemDecorator.fromItems(
                game.getBackpack().getItems());
        
        this.getChildren().setAll(itemDecs);
        this.getChildren().forEach(n -> n.setOnMouseClicked(this::onItemClick));
    }

    /**
    * Metoda, kterou nám Publisher předá signál, že začala nová hra.
    *
    * Když nám Publisher zašle příkaz nové hry společně s instancí nové hry,
    * jen přiřadíme novou instanci hry naší původní a aktualizujeme stav.
    * 
    *@param hra instance nové hry
    *@return
    */
    @Override
    public void newGame(IHra hra) {
        this.game = hra;
        update();
    }
}
