package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import utils.Publisher;
import utils.Subscriber;

/**
 *  Třída NextRoom - reprezentace GUI pro výběr, do jaké místnosti se hráč
 *  chce dál vydat
 * 
 *  Slouží jako Publisher pro ostatní komponenty.
 * 
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class NextRoom extends FlowPane implements Publisher {
    
    private ComboBox roomsComboBox = new ComboBox();
    private Button goButton = new Button("Jdi do místnosti");
    private List<Button> buttonList = new ArrayList<>();
    
    private HerniPlan gameMap;
    
    private List<Subscriber> subscriberList = new ArrayList<>();
    
    /**
    * Konstruktor NextRoom
    *
    *@param gameMap
    */
    public NextRoom(HerniPlan gameMap) {
        this.gameMap = gameMap;
        init();
    }
    
    /**
    * Event handler pro kliknutí na název místnosti
    * 
    * Zajišťuje, aby se změnila místnost a stav objektu se aktualizoval
    *
    *@param nextRoom další prostor hry, do kterého se po kliknutí máme přesunout
    */
    private void onRoomClicked(Prostor nextRoom) {
        
        gameMap.setAktualniProstor(nextRoom);
        
        init();
        
        publish();
    }
    
    /**
    * Metoda, kterou nám hlavní okno předá signál, že začala nová hras
    *
    *@param game nová hra, která se má spustit
    */
    public void newGame(IHra game) {
        gameMap = game.getHerniPlan();
        
        init();
    }
    
    /**
    * Inicializační metoda - vymaže předchozí tlačítka a na základě východů
    * z aktuálního prostoru vytvoří nový seznam tlačítek a přiřadí jim 
    * event handler při kliknutí.
    *
    */
    private void init() {
        Collection<Prostor> roomList = gameMap.getAktualniProstor().getVychody();
        
        this.getChildren().removeAll(buttonList);
        
        roomList.forEach(room -> {
            Button btn = new Button(room.getNazev());
            btn.setOnAction(e -> onRoomClicked(room));
            buttonList.add(btn);
            
            this.getChildren().add(btn);
        });
    }

    /**
    * Metoda, prostřednictvím níž předáme třídě nového odběratele.
    *
    *@param subscriber nový odběratel, kterému budeme posílat signály
    * 
    *@Override
    */
    public void subscribe(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    /**
    * Metoda, prostřednictvím níž odebereme třídě existujícího odběratele.
    *
    *@param subscriber odběratel, kterého chceme odebrat
    * 
    *@Override
    */
    public void off(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    /**
    * Signalizujeme tak odběratelům, že byly provedeny nějaké
    * změny stavu.
    *
    *@Override
    */
    public void publish() {
        subscriberList.forEach(Subscriber::update);
    }
    
}
