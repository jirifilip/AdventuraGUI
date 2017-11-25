/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Collection;
import javafx.scene.input.MouseEvent;
import logika.Batoh;
import logika.HerniPlan;
import logika.IHra;
import logika.PrikazSeber;
import logika.Vec;
import main.Main;
import utils.CommandBuilder;

/**
 *  Třída RoomInventory - představuje grafické zobrazení věcí v Prostoru
 * 
 * 
 *  Dědí od inventáře, protože funguje skoro stejně.
 *  Výjimku tvoří to, že namísto batohu hráče zobrazujeme "batoh" Prostoru.
 *  Přetížíme proto některé metody pro aktualizaci stavu a kliknutí na jednu
 *  položku, zbytek funguje stejně jako rodičovská třída. 
 *
 * 
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class RoomInventory extends Inventory {

    /**
    * Konstruktor Inventáře Prostoru
    *
    *@param game hra, pro kterou vytváříme inventář
    *@param main hlavní okno aplikace, abychom mohli aktivovat
    * novou hru
    *@return
    */
    public RoomInventory(IHra game, Main main) {
        super(game, main);
    }

    /**
    * Metoda, která slouží k aktualizaci stavu po signálu od Publishera.
    *
    * Když nám Publisher zašle takovýto příkaz, znamená to, že se obsah
    * místnosti změnil (někdo vyhodil nebo vzal předmět),
    * a tak si z instance hry vezmeme znovu obsah batohu
    * a vykreslíme ho.
    * 
    *@param
    *@return
    *@Override
    */
    public void update() {
        Collection<ItemDecorator> itemDecs = ItemDecorator.fromItems(
                game.getHerniPlan().getAktualniProstor().getItems());
        
        this.getChildren().setAll(itemDecs);
        this.getChildren().forEach(n -> n.setOnMouseClicked(this::onItemClick));
    }

    /**
    * Metoda, která slouží jako event handler pro kliknutí myši na
    * jednotlivou věc v obsahu Prostoru.
    *
    * Při kliknutí na věc v Prostoru ji chceme vzít, a tak na instanci
    * hry zavoláme příkaz "seber" s parametry podle jména věci.
    * 
    * K vytvoření textu příkazu se používá jednoduchá třída CommandBuilder a 
    * její statická metoda compose.
    * 
    *@param event události kliknutí
    *@return
    *@Override
    */
    protected void onItemClick(MouseEvent event) {
        ItemDecorator itemD = (ItemDecorator) event.getTarget();
        Vec item = itemD.getItem();
        
        String command = CommandBuilder.compose(PICK_UP_COMMAND, item.getJmeno());
        
        String commandString = game.zpracujPrikaz(command);
        
        main.getCenterText().appendCommandResult(commandString);
    }
    
    
    
    
    
}
