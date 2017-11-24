/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import logika.Batoh;
import logika.HerniPlan;

/**
 *
 * @author Jirka_
 */
public class RoomInventory extends Inventory {
    
    private HerniPlan gameMap;

    public RoomInventory(Batoh backpack, HerniPlan gamePlan) {
        super(backpack, gamePlan);
    }
    
    @Override
    public void update() {
        gameMap.getAktualniProstor().getItems().forEach(this::addItem);
    }
    
    
    
    
    
}
