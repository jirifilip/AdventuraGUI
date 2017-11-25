/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import utils.Publisher;
import utils.Subscriber;

/**
 *  Třída NextRoom - reprezentace GUI pro výběr, do jaké místnosti se hráč
 *  chce dál vydat
 * 
 *  Slouží jako Publisher pro ostatní komponenty.
 * 
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class NextRoom extends FlowPane implements Publisher {
    
    private ComboBox roomsComboBox = new ComboBox();
    private Button goButton = new Button("Jdi do místnosti");
    private List<Button> buttonList = new ArrayList<>();
    
    private HerniPlan gameMap;
    
    private List<Subscriber> subscriberList = new ArrayList<>();
    
    /**
    * Konstruktor NextRoom
    *
    *@param gameMap
    *@return
    */
    public NextRoom(HerniPlan gameMap) {
        this.gameMap = gameMap;
        init();
    }
    
    private void onRoomClicked(Prostor nextRoom) {
        
        gameMap.setAktualniProstor(nextRoom);
        
        init();
        
        publish();
    }
    
    public void newGame(IHra game) {
        gameMap = game.getHerniPlan();
        
        init();
    }
    
    private void init() {
        Collection<Prostor> roomList = gameMap.getAktualniProstor().getVychody();
        
        this.getChildren().removeAll(buttonList);
        
        roomList.forEach(room -> {
            Button btn = new Button(room.getNazev());
            btn.setOnAction(e -> onRoomClicked(room));
            buttonList.add(btn);
            
            this.getChildren().add(btn);
        });
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void off(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    @Override
    public void publish() {
        subscriberList.forEach(Subscriber::update);
    }
    
}
