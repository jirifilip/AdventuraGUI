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
import utils.CommandBuilder;

/**
 *
 * @author Jirka_
 */
public class RoomInventory extends Inventory {

    public RoomInventory(IHra game) {
        super(game);
    }

    @Override
    public void update() {
        Collection<ItemDecorator> itemDecs = ItemDecorator.fromItems(
                game.getHerniPlan().getAktualniProstor().getItems());
        
        this.getChildren().setAll(itemDecs);
        this.getChildren().forEach(n -> n.setOnMouseClicked(this::onItemClick));
    }

    @Override
    protected void onItemClick(MouseEvent event) {
        ItemDecorator itemD = (ItemDecorator) event.getTarget();
        Vec item = itemD.getItem();
        
        String command = CommandBuilder.compose(PICK_UP_COMMAND, item.getJmeno());
        
        game.zpracujPrikaz(command);
    }
    
    
    
    
    
    
    
}
