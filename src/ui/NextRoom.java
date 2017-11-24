/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.Prostor;
import utils.Subscriber;

/**
 *
 * @author Jirka_
 */
public class NextRoom extends FlowPane implements Subscriber {
    
    private ComboBox roomsComboBox = new ComboBox();
    private Button goButton = new Button("Jdi do místnosti");
    private HerniPlan gameMap;
    
    public NextRoom(HerniPlan gameMap) {
        this.gameMap = gameMap;
        init();
    }
    
    private void goToNextRoomClicked(MouseEvent event) {
        Prostor nextRoom = (Prostor) roomsComboBox.getValue();
        gameMap.setAktualniProstor(nextRoom);
    }
    
    private void init() {
        ObservableList<Prostor> roomList = FXCollections.observableArrayList(gameMap.getAktualniProstor().getVychody());
        roomsComboBox.setItems(roomList);
        
        
        
        this.getChildren().addAll(roomsComboBox, goButton);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
