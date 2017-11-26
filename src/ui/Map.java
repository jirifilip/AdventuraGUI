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
 *  Třída Map - slouží k zobrazování polohy, kde se právě hráč nachází
 * 
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class Map extends AnchorPane implements NewGameSubscriber {

    private IHra hra;
    private Circle dot;
    
    /**
    * Konstruktor třídy
    * 
    * třídě předáme instanci hry, díky níž se pak můžeme registrovat k odběru
    * herního plánu a očekávat tak aktualizace.
    * 
    * Konstruktor poté zavolá inicializační metodu
    *
    *@param hra instance hry
    */
    public Map(IHra hra) {
        this.hra = hra;
        
        hra.getHerniPlan().subscribe(this);
        
        init();
    }
    
    /**
    * Inicializační metoda
    * 
    * Vytváří obrázek mapy a bod, který bude ukazovat, v jakém Prostoru se
    * právě hráč nachází
    *
    */
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
    
    /**
    * Metoda, která na základě signálu od Publishera aktualizuje stav
    * 
    * Stav aktualizuje tak, že vykreslí polohu hráče na mapu.
    *
    *@Override
    */
    public void update() {
        dot.setCenterX(hra.getHerniPlan().getAktualniProstor().getxPosition());
        dot.setCenterY(hra.getHerniPlan().getAktualniProstor().getyPosition());
    }

    /**
    * Metoda, kterou dává Publisher najevo, že začala nová hra
    * 
    * Provedeme v podstatě stejnou inicializaci jako v konstruktoru (avšak
    * s novou instancí hry) a zavoláme aktualizaci stavu.
    * 
    *@Override
    */
    public void newGame(IHra hra) {
        hra.getHerniPlan().off(this);
        this.hra = hra;
        this.hra.getHerniPlan().subscribe(this);
        update();
    }
    
}
