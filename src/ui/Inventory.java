/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
    
    public Inventory() {
        init();
    }
    
    public void addAllItems(Vec... items) {
        Arrays.stream(items).forEach(this::addItem);
    }
    
    public void addItem(Vec item) {
        
        
        inventoryImageView.getChil
    }
    
    private void addImage(Image image) {
        
    }

    private void init() {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setPrefWidth(250);
        
        ImageView imageView = new ImageView(
                new Image(Main.class.getResourceAsStream("/zdroje/maliny.jpg"),
                    50,
                    50,
                    false,
                    false
                ));
        this.getChildren().add(imageView);

        
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newGame(IHra hra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
