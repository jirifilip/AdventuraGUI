/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.Prostor;
import utils.Publisher;
import utils.Subscriber;

/**
 *
 * @author Jirka_
 */
public class NextRoom extends FlowPane implements Publisher {
    
    private ComboBox roomsComboBox = new ComboBox();
    private Button goButton = new Button("Jdi do místnosti");
    private HerniPlan gameMap;
    
    private List<Subscriber> subscriberList = new ArrayList<>();
    
    public NextRoom(HerniPlan gameMap) {
        this.gameMap = gameMap;
        init();
    }
    
    private void goToNextRoomClicked(MouseEvent event) {
        Prostor nextRoom = (Prostor) roomsComboBox.getValue();
        gameMap.setAktualniProstor(nextRoom);
        
        roomsComboBox.getItems().setAll(gameMap.getAktualniProstor().getVychody());
        roomsComboBox.getSelectionModel().selectFirst();
        
        publish();
    }
    
    private void init() {
        ObservableList<Prostor> roomList = FXCollections.observableArrayList(gameMap.getAktualniProstor().getVychody());
        roomsComboBox.setItems(roomList);
        roomsComboBox.getSelectionModel().selectFirst();
        
        goButton.setOnMouseClicked(this::goToNextRoomClicked);
        
        this.getChildren().addAll(roomsComboBox, goButton);
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
