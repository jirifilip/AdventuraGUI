/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.NewGameSubscriber;
import utils.Subscriber;

/**
 *
 * @author filj03
 */
public class Map extends AnchorPane implements NewGameSubscriber {

    private IHra hra;
    private Circle dot;
    
    public Map(IHra hra) {
        this.hra = hra;
        
        hra.getHerniPlan().subscribe(this);
        
        init();
    }
    
    private void init() {
        ImageView image = new ImageView(
                new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),
                250,
                250,
                false,
                false
        ));
        
        dot = new Circle(5, Paint.valueOf("red"));
        
        this.getChildren().addAll(image, dot);
        
        update();
    }
    
    @Override
    public void update() {
        dot.setCenterX(hra.getHerniPlan().getAktualniProstor().getxPosition());
        dot.setCenterY(hra.getHerniPlan().getAktualniProstor().getyPosition());
    }

    @Override
    public void newGame(IHra hra) {
        hra.getHerniPlan().off(this);
        this.hra = hra;
        this.hra.getHerniPlan().subscribe(this);
        update();
    }
    
}
