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
 *
 * @author Jirka_
 */
public class Inventory extends FlowPane implements NewGameSubscriber {

    private final double ITEM_PREF_WIDTH = 50;
    private final double ITEM_PREF_HEIGHT = 50;
    
    protected final String DROP_COMMAND = "poloz";
    protected final String PICK_UP_COMMAND = "seber";
    
    protected IHra game;
    protected Main main;
    
    public Inventory(IHra game, Main main) {
        this.game = game;
        this.main = main;
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
        
        String command = CommandBuilder.compose(DROP_COMMAND, itemD.getItem().getJmeno());
        
        String commandString = game.zpracujPrikaz(command);
        
        main.getCenterText().appendCommandResult(commandString);
    }
    
    protected void init() {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setPrefWidth(250);
        

    }

    @Override
    public void update() {
        Collection<ItemDecorator> itemDecs = ItemDecorator.fromItems(
                game.getBackpack().getItems());
        
        this.getChildren().setAll(itemDecs);
        this.getChildren().forEach(n -> n.setOnMouseClicked(this::onItemClick));
    }

    @Override
    public void newGame(IHra hra) {
        this.game = hra;
        update();
    }
}
