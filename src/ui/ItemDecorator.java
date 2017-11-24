/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.Vec;
import main.Main;

/**
 *
 * @author Jirka_
 */
public class ItemDecorator extends ImageView {
    
    private Vec item;
    private final double ITEM_PREF_WIDTH = 50;
    private final double ITEM_PREF_HEIGHT = 50;
    
    public ItemDecorator(Vec item) {
        this.item = item;
        init();
    }
    
    private void init() {
        InputStream ImageStream = Main.class.getResourceAsStream(item.getImageUrl());
        Image image = new Image(ImageStream, ITEM_PREF_WIDTH, ITEM_PREF_HEIGHT, false, false);
        this.setImage(image);
    }

    public Vec getItem() {
        return item;
    }
    
    
    
}
