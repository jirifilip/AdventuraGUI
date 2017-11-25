package ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.Vec;
import main.Main;

/**
 *  Třída ItemDecorator - slouží k dekorování a zapouzdření věci tak, aby 
 *  vzala Věc a umožnila její vložení do inventáře.
 * 
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class ItemDecorator extends Button {
    
    private Vec item;
    private final double ITEM_PREF_WIDTH = 50;
    private final double ITEM_PREF_HEIGHT = 50;
    
    /**
    * Konstruktor ItemDekorátoru
    *
    *@param item věc, kterou budeme dekorovat
    *@return
    */
    public ItemDecorator(Vec item) {
        this.item = item;
        init();
    }
    
    /**
    * Inicializační metoda
    * 
    * V metodě načteme z Věci url k obrázku, z něhož vytvoříme objekt typu
    * Image a vložíme ho do ImageView. Ten pak vložíme do naší komponenty.
    *
    *@param item věc, kterou budeme dekorovat
    *@return
    */
    private void init() {
        InputStream ImageStream = Main.class.getResourceAsStream(item.getImageUrl());
        Image image = new Image(ImageStream, ITEM_PREF_WIDTH, ITEM_PREF_HEIGHT, false, false);
        ImageView imageView = new ImageView(image);
        this.setGraphic(imageView);
    }
    
    
    /**
    * Statická metoda, která převede kolekci věci na kolekci ItemDekorátorů
    * 
    *
    *@param items kolekce Věcí
    *@return Kolekce ItemDekorátorů
    */
    public static Collection<ItemDecorator> fromItems(Collection<Vec> items) {
        Collection<ItemDecorator> transformed = new ArrayList<>();
        
        for (Vec item : items) {
            transformed.add(new ItemDecorator(item));
        }
        
        return transformed;
    }
    
    /**
    * Getter pro item
    * 
    *@return item
    */
    public Vec getItem() {
        return item;
    }
    
}
