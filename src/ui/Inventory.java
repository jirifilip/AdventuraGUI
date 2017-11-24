/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.InputStream;
import java.util.Arrays;
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
import utils.Subscriber;

/**
 *
 * @author Jirka_
 */
public class Inventory extends FlowPane implements Subscriber {

    private final double ITEM_PREF_WIDTH = 50;
    private final double ITEM_PREF_HEIGHT = 50;
    private Batoh backpack;
    private HerniPlan gamePlan;
    
    public Inventory(Batoh backpack, HerniPlan gamePlan) {
        this.backpack = backpack;
        this.gamePlan = gamePlan;
        init();
    }
    
    protected void addItem(Vec item) {
        
        ItemDecorator imageView = new ItemDecorator(item);
        
        imageView.setOnMouseClicked(this::onItemClick);
        
        this.getChildren().add(imageView);
    }
    
    protected void onItemClick(MouseEvent event) {
        ItemDecorator itemD = (ItemDecorator) event.getTarget();
        this.getChildren().remove(itemD);
        
        gamePlan.getAktualniProstor().vlozVec(itemD.getItem());
    }
    
    protected void init() {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setPrefWidth(250);
        

    }

    @Override
    public void update() {
        backpack.getItems().forEach(this::addItem);
    }
}
